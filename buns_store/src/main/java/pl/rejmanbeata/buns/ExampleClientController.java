package pl.rejmanbeata.buns;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ExampleClientController {

    private OkHttpClient client = new OkHttpClient();
    @Value("${bakery.url}")
    private String bakeryUrl;
    @Value("${bakery.port}")
    private String bakeryPort;
    private static final String SEND_HELLO = "/hello";

    @GetMapping("/sendHello")
    public String runHello() throws IOException {
        Request request = new Request.Builder()
                .url(bakeryUrl + bakeryPort + SEND_HELLO)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}