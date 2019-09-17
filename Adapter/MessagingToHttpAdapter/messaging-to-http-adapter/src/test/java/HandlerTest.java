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
    public void handlerNoUrl() {
        Handler handler = new Handler();
        String event = "{\r\n   \"specversion\":\"0.2\",\r\n   \"type\":\"io.github.ust.mico.result\",\r\n   \"source\":\"/router\",\r\n   \"id\":\"A234-1234-1234\",\r\n   \"time\":\"2019-05-08T17:31:00Z\",\r\n   \"contentType\":\"application/json\",\r\n   \"adapterRequestUrl\":\"test\",\r\n   \"data\":\"test\"\r\n}";
        Request request = new Request(event, null);
        IResponse response = handler.Handle(request);
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.BAD_REQUEST.value())));
    }

    @Test
    public void handlerEmptyMessage() {
        Handler handler = new Handler();
        String event = "";
        Request request = new Request(event, null);
        IResponse response = handler.Handle(request);
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.BAD_REQUEST.value())));
    }

    @Test
    public void handlerCorrect() {
        Handler handler = new Handler();
        handler.setRestTemplate(restTemplate);
        String url = "http://localhost:3000/test";
        String test = "test";
        String event = "{\n" +
                "   \"specversion\":\"0.2\",\n" +
                "   \"type\":\"io.github.ust.mico.result\",\n" +
                "   \"source\":\"/router\",\n" +
                "   \"id\":\"A234-1234-1234\",\n" +
                "   \"time\":\"2019-05-08T17:31:00Z\",\n" +
                "   \"contentType\":\"application/json\",\n" +
                "   \"adapterRequestUrl\":\"" + url + "\",\n" +
                "   \"adapterRequestMethod\" : \"POST\",\n" +
                "   \"data\":\"" + test + "\"\n" +
                "}";
        Request request = new Request(event, null);
        IResponse response = handler.Handle(request);
        Mockito.verify(restTemplate, Mockito.times(1)).exchange(urlArgumentCaptor.capture(), eq(HttpMethod.POST), dataArgumentCaptor.capture(), eq(String.class));
        assertThat(urlArgumentCaptor.getValue(), is(equalTo(url)));
        assertThat(dataArgumentCaptor.getValue().getBody(), is(equalTo(test)));
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK.value())));
    }

}
