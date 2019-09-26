import com.openfaas.function.Handler;
import com.openfaas.model.IResponse;
import com.openfaas.model.Request;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HandlerTest {

//    @Mock
//    RestTemplate restTemplate;
//
//    @Captor
//    private ArgumentCaptor<String> urlArgumentCaptor;
//
//    @Captor
//    private ArgumentCaptor<HttpEntity> dataArgumentCaptor;
//
//
//    @Test
//    public void handlerNewType() {
//        Handler handler = new Handler();
//        handler.setRestTemplate(restTemplate);
//        String url = "http://localhost:3000/test";
//        String event = "{\n" +
//                "  \"id\": \"232fbcd7-795c-42a8-b3c7-17dd0ce2f8d2\",\n" +
//                "  \"source\": \"/http-to-messaging-adapter\",\n" +
//                "  \"type\": null,\n" +
//                "  \"specversion\": \"0.2\",\n" +
//                "  \"time\": \"2019-09-25T20:40:45.011+02:00\",\n" +
//                "  \"schemaurl\": null,\n" +
//                "  \"contenttype\": null,\n" +
//                "  \"data\": {\n" +
//                "    \"header\": {\n" +
//                "      \"content-length\": \"4\",\n" +
//                "      \"accept-language\": \"de,en-US;q=0.7,en;q=0.3\",\n" +
//                "      \"cookie\": \"cookie\",\n" +
//                "      \"host\": \"localhost:8081\",\n" +
//                "      \"content-type\": \"text/plain;charset=UTF-8\",\n" +
//                "      \"connection\": \"keep-alive\",\n" +
//                "      \"headerkey\": \"headerValue\",\n" +
//                "      \"accept-encoding\": \"gzip, deflate\",\n" +
//                "      \"user-agent\": \"Browser\",\n" +
//                "      \"accept\": \"*/*\"\n" +
//                "    },\n" +
//                "    \"base64body\": \"Qm9keQ==\"\n" +
//                "  },\n" +
//                "  \"correlationid\": null,\n" +
//                "  \"createdfrom\": null,\n" +
//                "  \"route\": null,\n" +
//                "  \"routingslip\": null,\n" +
//                "  \"istestmessage\": false,\n" +
//                "  \"filteroutbeforetopic\": null,\n" +
//                "  \"iserrormessage\": false,\n" +
//                "  \"errormessage\": null,\n" +
//                "  \"errortrace\": null,\n" +
//                "  \"expirydate\": null,\n" +
//                "  \"sequenceid\": null,\n" +
//                "  \"sequencenumber\": null,\n" +
//                "  \"sequencesize\": null,\n" +
//                "  \"returntopic\": \"transform-request\",\n" +
//                "  \"dataref\": null,\n" +
//                "  \"subject\": null,\n" +
//                "  \"adapterRequestUrl\": \"" + url + "\",\n" +
//                "  \"adapterRequestMethod\": \"POST\"\n" +
//                "}";
//        Request request = new Request(event, null);
//        IResponse response = handler.Handle(request);
//        Mockito.verify(restTemplate, Mockito.times(1)).exchange(urlArgumentCaptor.capture(), eq(HttpMethod.POST), dataArgumentCaptor.capture(), eq(String.class));
//        assertThat(urlArgumentCaptor.getValue(), is(equalTo(url)));
//        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.INTERNAL_SERVER_ERROR.value())));
//    }

    @Test
    public void handlerNewType3() {
        Handler handler = new Handler();
        String url = "http://localhost:3000/test";
        String event = "{\"id\":\"d0cf8132-9735-4346-83d4-5506440111aa\",\"source\":\"/http-to-messaging-adapter\",\"type\":\"httpEnvelop\",\"specversion\":\"0.2\",\"time\":\"2019-09-26T19:47:03.719Z\",\"schemaurl\":null,\"contenttype\":\"application/json\",\"data\":{\"header\":{\"x-real-ip\":\"10.244.0.1\",\"referer\":\"http://51.145.142.111/\",\"x-forwarded-proto\":\"http\",\"accept-language\":\"de-DE,de;q=0.9,en-US;q=0.8,en;q=0.7\",\"host\":\"51.145.142.111\",\"connection\":\"close\",\"x-forwarded-for\":\"10.244.0.1\",\"dnt\":\"1\",\"accept-encoding\":\"gzip, deflate\",\"user-agent\":\"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.78 Safari/537.36 Vivaldi/2.8.1664.35\",\"accept\":\"*/*\"},\"base64body\":null},\"correlationid\":null,\"createdfrom\":null,\"route\":[{\"type\":\"topic\",\"id\":\"OutPut\",\"timestamp\":\"2019-09-26T19:47:03.719Z\"},{\"type\":\"faas-function\",\"id\":\"messaging-to-http-adapter\",\"timestamp\":\"2019-09-26T19:47:03.841Z\"}],\"routingslip\":null,\"istestmessage\":false,\"filteroutbeforetopic\":null,\"iserrormessage\":false,\"errormessage\":null,\"errortrace\":null,\"expirydate\":null,\"sequenceid\":null,\"sequencenumber\":null,\"sequencesize\":null,\"returntopic\":\"Return\",\"dataref\":null,\"subject\":null,\"adapterRequestUrl\":\"google.de\",\"adapterRequestMethod\":\"GET\",\"backendUrl\":\"httpbackend-aqmhsiqe.mico-workspace.svc.cluster.local:80\"}";
        String event2 = "{\"id\":\"21f3ceab-0137-44ea-98c5-078cbcb27b7e\",\"source\":\"/http-to-messaging-adapter\",\"type\":\"httpEnvelop\",\"specversion\":\"0.2\",\"time\":\"2019-09-26T21:26:33.436Z\",\"schemaurl\":null,\"contenttype\":\"application/json\",\"data\":{\"header\":{\"x-real-ip\":\"10.244.1.1\",\"referer\":\"http://104.45.11.194/\",\"x-forwarded-proto\":\"http\",\"accept-language\":\"de,en-US;q=0.7,en;q=0.3\",\"host\":\"104.45.11.194\",\"connection\":\"close\",\"x-forwarded-for\":\"10.244.1.1\",\"dnt\":\"1\",\"accept-encoding\":\"gzip, deflate\",\"user-agent\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:69.0) Gecko/20100101 Firefox/69.0\",\"accept\":\"*/*\"},\"base64body\":null},\"correlationid\":null,\"createdfrom\":null,\"route\":[{\"type\":\"topic\",\"id\":\"OutPut\",\"timestamp\":\"2019-09-26T21:26:33.436Z\"}],\"routingslip\":null,\"istestmessage\":false,\"filteroutbeforetopic\":null,\"iserrormessage\":false,\"errormessage\":null,\"errortrace\":null,\"expirydate\":null,\"sequenceid\":null,\"sequencenumber\":null,\"sequencesize\":null,\"returntopic\":\"Return\",\"dataref\":null,\"subject\":null,\"adapterRequestUrl\":\"demo.productionready.io/api/articles?limit=10&offset=0\",\"adapterRequestMethod\":\"GET\",\"backendUrl\":\"demo.productionready.io/api\"}";
        Request request = new Request(event2, null);
        IResponse response = handler.Handle(request);
        System.out.println(response.getBody());
    }
//
//    @Test
//    public void handlerNewType2() {
//        Handler handler = new Handler();
//        handler.setRestTemplate(restTemplate);
//        String url = "http://localhost:3000/test";
//        String event = "{\n" +
//                "  \"id\": \"6b94c237-c500-4a02-826d-ea41380e52ae\",\n" +
//                "  \"source\": \"/http-to-messaging-adapter\",\n" +
//                "  \"type\": \"httpEnvelop\",\n" +
//                "  \"specversion\": \"0.2\",\n" +
//                "  \"time\": \"2019-09-26T18:14:59.64Z\",\n" +
//                "  \"schemaurl\": null,\n" +
//                "  \"contenttype\": \"application/json\",\n" +
//                "  \"data\": {\n" +
//                "    \"header\": {\n" +
//                "      \"accept-language\": \"de,en-US;q=0.7,en;q=0.3\",\n" +
//                "      \"host\": \"51.144.125.170:8081\",\n" +
//                "      \"connection\": \"keep-alive\",\n" +
//                "      \"accept-encoding\": \"gzip, deflate\",\n" +
//                "      \"user-agent\": \"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:69.0) Gecko/20100101 Firefox/69.0\",\n" +
//                "      \"accept\": \"*/*\"\n" +
//                "    },\n" +
//                "    \"base64body\": null\n" +
//                "  },\n" +
//                "  \"correlationid\": null,\n" +
//                "  \"createdfrom\": null,\n" +
//                "  \"route\": [\n" +
//                "    {\n" +
//                "      \"type\": \"topic\",\n" +
//                "      \"id\": \"OutPut\",\n" +
//                "      \"timestamp\": \"2019-09-26T18:14:59.64Z\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"type\": \"faas-function\",\n" +
//                "      \"id\": \"messaging-to-http-adapter\",\n" +
//                "      \"timestamp\": \"2019-09-26T18:14:59.661Z\"\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"routingslip\": null,\n" +
//                "  \"istestmessage\": false,\n" +
//                "  \"filteroutbeforetopic\": null,\n" +
//                "  \"iserrormessage\": false,\n" +
//                "  \"errormessage\": null,\n" +
//                "  \"errortrace\": null,\n" +
//                "  \"expirydate\": null,\n" +
//                "  \"sequenceid\": null,\n" +
//                "  \"sequencenumber\": null,\n" +
//                "  \"sequencesize\": null,\n" +
//                "  \"returntopic\": \"Return\",\n" +
//                "  \"dataref\": null,\n" +
//                "  \"subject\": null,\n" +
//                "  \"adapterRequestUrl\": \"http://google.de/testjkljkljlk\",\n" +
//                "  \"adapterRequestMethod\": \"GET\",\n" +
//                "  \"backendUrl\": \"http://google.de\"\n" +
//                "}";
//        Request request = new Request(event, null);
//        IResponse response = handler.Handle(request);
//        Mockito.verify(restTemplate, Mockito.times(1)).exchange(urlArgumentCaptor.capture(), eq(HttpMethod.POST), dataArgumentCaptor.capture(), eq(String.class));
//        //assertThat(urlArgumentCaptor.getValue(), is(equalTo(url)));
//        //assertThat(response.getStatusCode(), is(equalTo(HttpStatus.INTERNAL_SERVER_ERROR.value())));
//        assertTrue(true);
//    }

}
