package com.openfaas.function;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openfaas.model.IRequest;
import com.openfaas.model.IResponse;
import com.openfaas.model.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

public class Handler implements com.openfaas.model.IHandler {

    private RestTemplate restTemplate = new RestTemplate();

    public IResponse Handle(IRequest req) {
        Response res = new Response();
        String message = req.getBody();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode cloudEvent = mapper.readTree(message);
            System.out.println(cloudEvent);
            String requestUrl = cloudEvent.get("adapterRequestUrl").asText();
            String requestMethod = cloudEvent.get("adapterRequestMethod").asText();
            System.out.println("RequestUrl is:'" + requestUrl + "'");
            if (!requestUrl.isEmpty()) {
                String dataElement = cloudEvent.get("data").asText();
                HttpEntity<String> httpDataEntity = new HttpEntity<>(dataElement);
                restTemplate.exchange(requestUrl, HttpMethod.resolve(requestMethod), httpDataEntity, String.class);
                res.setStatusCode(HttpStatus.OK.value());
            } else {
                res.setStatusCode(HttpStatus.BAD_REQUEST.value());
            }
        } catch (Exception e) {
            res.setStatusCode(HttpStatus.BAD_REQUEST.value());
            res.setBody(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Returning status '" + res.getStatusCode() + "'");
        return res;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
