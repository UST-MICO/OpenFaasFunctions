import com.openfaas.function.Handler;
import com.openfaas.model.IResponse;
import com.openfaas.model.Request;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class HandlerTest {

    @Mock
    RestTemplate restTemplate;

    @Captor
    private ArgumentCaptor<String> urlArgumentCaptor;

    @Captor
    private ArgumentCaptor<HttpEntity> dataArgumentCaptor;


    @Test
    public void handlerNewType() {
        Handler handler = new Handler();
        handler.setRestTemplate(restTemplate);
        String url = "http://localhost:3000/test";
        String event = "{\n" +
                "  \"id\": \"232fbcd7-795c-42a8-b3c7-17dd0ce2f8d2\",\n" +
                "  \"source\": \"/http-to-messaging-adapter\",\n" +
                "  \"type\": null,\n" +
                "  \"specversion\": \"0.2\",\n" +
                "  \"time\": \"2019-09-25T20:40:45.011+02:00\",\n" +
                "  \"schemaurl\": null,\n" +
                "  \"contenttype\": null,\n" +
                "  \"data\": {\n" +
                "    \"header\": {\n" +
                "      \"content-length\": \"4\",\n" +
                "      \"accept-language\": \"de,en-US;q=0.7,en;q=0.3\",\n" +
                "      \"cookie\": \"cookie\",\n" +
                "      \"host\": \"localhost:8081\",\n" +
                "      \"content-type\": \"text/plain;charset=UTF-8\",\n" +
                "      \"connection\": \"keep-alive\",\n" +
                "      \"headerkey\": \"headerValue\",\n" +
                "      \"accept-encoding\": \"gzip, deflate\",\n" +
                "      \"user-agent\": \"Browser\",\n" +
                "      \"accept\": \"*/*\"\n" +
                "    },\n" +
                "    \"base64body\": \"Qm9keQ==\"\n" +
                "  },\n" +
                "  \"correlationid\": null,\n" +
                "  \"createdfrom\": null,\n" +
                "  \"route\": null,\n" +
                "  \"routingslip\": null,\n" +
                "  \"istestmessage\": false,\n" +
                "  \"filteroutbeforetopic\": null,\n" +
                "  \"iserrormessage\": false,\n" +
                "  \"errormessage\": null,\n" +
                "  \"errortrace\": null,\n" +
                "  \"expirydate\": null,\n" +
                "  \"sequenceid\": null,\n" +
                "  \"sequencenumber\": null,\n" +
                "  \"sequencesize\": null,\n" +
                "  \"returntopic\": \"transform-request\",\n" +
                "  \"dataref\": null,\n" +
                "  \"subject\": null,\n" +
                "  \"adapterRequestUrl\": \"" + url + "\",\n" +
                "  \"adapterRequestMethod\": \"POST\"\n" +
                "}";
        Request request = new Request(event, null);
        IResponse response = handler.Handle(request);
        Mockito.verify(restTemplate, Mockito.times(1)).exchange(urlArgumentCaptor.capture(), eq(HttpMethod.POST), dataArgumentCaptor.capture(), eq(String.class));
        assertThat(urlArgumentCaptor.getValue(), is(equalTo(url)));
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.INTERNAL_SERVER_ERROR.value())));
    }

}
