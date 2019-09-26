package com.openfaas.function;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openfaas.function.cloudEvent.CloudEventDeserializer;
import com.openfaas.function.cloudEvent.CloudEventSerializer;
import com.openfaas.function.cloudEvent.MicoCloudEventImpl;
import com.openfaas.model.IRequest;
import com.openfaas.model.IResponse;
import com.openfaas.model.Response;
import io.cloudevents.json.Json;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Handler implements com.openfaas.model.IHandler {

    protected static final String CLOUD_EVENT_ATTRIBUTE_ADAPTER_REQUEST_URL = "adapterRequestUrl";
    protected static final String CLOUD_EVENT_ATTRIBUTE_ADAPTER_REQUEST_METHOD = "adapterRequestMethod";
    protected static final String CLOUD_EVENT_ATTRIBUTE_HTTP_RESPONSE_STATUS = "httpResponseStatus";
    protected static final String CLOUD_EVENT_ATTRIBUTE_MESSAGE_TYPE = "httpEnvelop";
    protected static final String CLOUD_EVENT_ATTRIBUTE_CONTENT_TYPE = "application/json";
    private RestTemplate restTemplate = new RestTemplate();

    public IResponse Handle(IRequest req) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        Response res = new Response();
        String message = req.getBody();
        ObjectMapper mapper = new ObjectMapper();
        try {
            CloudEventDeserializer cloudEventDeserializer = new CloudEventDeserializer();
            MicoCloudEventImpl<JsonNode> cloudEvent = cloudEventDeserializer.deserialize(message);
            if (cloudEvent.getData().isPresent() && cloudEvent.getExtensionsAsMap().isPresent()) {
                HttpRequestWrapper httpRequestWrapper = mapper.treeToValue(cloudEvent.getData().get(), HttpRequestWrapper.class);
                Map<String, JsonNode> extensions = cloudEvent.getExtensionsAsMap().get();
                String adapterRequestUrl = extensions.get(CLOUD_EVENT_ATTRIBUTE_ADAPTER_REQUEST_URL).asText();
                if (!adapterRequestUrl.startsWith("http://")) {
                    adapterRequestUrl = "http://" + adapterRequestUrl;
                }
                String method = extensions.get(CLOUD_EVENT_ATTRIBUTE_ADAPTER_REQUEST_METHOD).asText();
                String body = httpRequestWrapper.getBody();
                HttpEntity<String> httpDataEntity;
                if (httpRequestWrapper.getHeader().isEmpty()) {
                    httpDataEntity = new HttpEntity<>(body);
                } else {
                    MultiValueMap<String, String> multiValueHeaderMap = new LinkedMultiValueMap<>();
                    for (Map.Entry<String, String> entry : httpRequestWrapper.getHeader().entrySet()) {
                        if (!entry.getKey().equalsIgnoreCase("accept-encoding")) {
                            multiValueHeaderMap.put(entry.getKey(), Collections.singletonList(entry.getValue()));
                        }
                    }
                    httpDataEntity = new HttpEntity<>(body, multiValueHeaderMap);
                }
                String responseBody = null;
                String responseStatusCode = "500";
                Map<String, String> headers;
                try {
                    ResponseEntity<String> responseEntity = restTemplate.exchange(adapterRequestUrl, HttpMethod.resolve(method.toUpperCase()), httpDataEntity, String.class);
                    responseBody = responseEntity.getBody();
                    responseStatusCode = "" + responseEntity.getStatusCode().value();
                    headers = getHeader(responseEntity.getHeaders());
                } catch (HttpClientErrorException e) {
                    e.printStackTrace();
                    responseStatusCode = "" + e.getStatusCode().value();
                    responseBody = e.getResponseBodyAsString();
                    headers = getHeader(e.getResponseHeaders());
                }


                MicoCloudEventImpl<JsonNode> responseCloudEvent = getResponseCloudEvent(cloudEvent);
                System.out.println("Got response body:" + responseBody + "with status code:" + responseStatusCode);
                responseCloudEvent.setExtension(CLOUD_EVENT_ATTRIBUTE_HTTP_RESPONSE_STATUS, mapper.valueToTree(responseStatusCode));

                HttpRequestWrapper httpReturnWrapper = new HttpRequestWrapper();
                if (responseBody != null && !responseBody.isEmpty()) {
                    httpReturnWrapper.setBody(responseBody);
                }
                httpReturnWrapper.setHeader(headers);
                responseCloudEvent.setData(mapper.valueToTree(httpReturnWrapper));
                res.setStatusCode(HttpStatus.OK.value());
                CloudEventSerializer cloudEventSerializer = new CloudEventSerializer();
                System.out.flush();
                responseCloudEvent.setExtension("FaaSLog", mapper.valueToTree(baos.toString()));
                res.setBody(Json.encode(Collections.singleton(responseCloudEvent)));
            }

        } catch (Exception e) {
            res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            res.setBody(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Returning status '" + res.getStatusCode() + "'");
        System.setOut(old);
        return res;
    }

    private Map<String, String> getHeader(HttpHeaders httpHeaders) {
        Map<String, String> headers = new HashMap<>();
        //https://stackoverflow.com/a/3097052
        for (Map.Entry<String, List<String>> entry : httpHeaders.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().stream().collect(Collectors.joining(","));
            headers.put(key, value);
        }
        return headers;
    }

    private void setRoutingSlip(MicoCloudEventImpl<JsonNode> cloudEvent, MicoCloudEventImpl<JsonNode> responseCloudEvent) {
        LinkedList<List<String>> routingSlip = new LinkedList<>();
        List<String> routingElement = new LinkedList<>();
        routingElement.add(cloudEvent.getReturnTopic().get());
        routingSlip.add(routingElement);
        responseCloudEvent.setRoutingSlip(routingSlip);
    }

    private MicoCloudEventImpl<JsonNode> getResponseCloudEvent(MicoCloudEventImpl<JsonNode> cloudEvent) {
        MicoCloudEventImpl<JsonNode> responseCloudEvent = new MicoCloudEventImpl<>();
        responseCloudEvent.setRandomId();
        responseCloudEvent.setCorrelationId(cloudEvent.getId());
        responseCloudEvent.setTime(ZonedDateTime.now());
        responseCloudEvent.setIsErrorMessage(false);
        responseCloudEvent.setIsTestMessage(false);
        responseCloudEvent.setCreatedFrom(cloudEvent.getId());
        responseCloudEvent.setType(CLOUD_EVENT_ATTRIBUTE_MESSAGE_TYPE);
        responseCloudEvent.setContentType(CLOUD_EVENT_ATTRIBUTE_CONTENT_TYPE);
        try {
            responseCloudEvent.setSource(new URI("com.openfaas.function.messaging.to.http.adapter"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        setRoutingSlip(cloudEvent, responseCloudEvent);
        return responseCloudEvent;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
