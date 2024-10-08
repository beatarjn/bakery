package pl.rejmanbeata.buns;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WireMockTest(httpPort = 8020)
@SpringBootTest
@ActiveProfiles("test")
class ExampleClientControllerTest {
    private OkHttpClient client;

    @Value("${buns.url}")
    private String bunsUrl;

    private static final String SEND_HELLO = "/sendHello";

    @BeforeEach
    public void setup() {
        client = new OkHttpClient();
    }

    @Test
    void runHello_shouldReturnExpectedResponse() throws IOException {
        String expectedResponse = "Hello world";

        WireMock.stubFor(WireMock.get(urlEqualTo(SEND_HELLO))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(expectedResponse)));

        Request request = new Request.Builder()
                .url(bunsUrl + SEND_HELLO)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String actualResponse = response.body().string();

            assertEquals(expectedResponse, actualResponse);
            verify(getRequestedFor(urlEqualTo(SEND_HELLO)));
        }
    }
}