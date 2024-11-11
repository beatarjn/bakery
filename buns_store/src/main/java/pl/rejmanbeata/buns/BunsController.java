package pl.rejmanbeata.buns;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/v1")
public class BunsController {

    private OkHttpClient client = new OkHttpClient();
    @Value("${bakery.url}")
    private String bakeryUrl;
    @Value("${bakery.port}")
    private String bakeryPort;
    private static final String SEND_HELLO = "/hello";
    private static final String BUNS_INFO = "/bunsInfo";

    @GetMapping("/sendHello")
    public String runHello() throws IOException {
        Request request = new Request.Builder()
                .url(bakeryUrl + bakeryPort + SEND_HELLO)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @PostMapping("/bunsInfo")
    public String createBunsInfo() throws IOException {
        Request request = new Request.Builder()
                .url(bakeryUrl + bakeryPort + "/v1" + BUNS_INFO)
                .post(RequestBody
                        .create("""
                                {
                                    "hello": "hello send from buns"
                                }
                                """.getBytes(StandardCharsets.UTF_8)
                        ))
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}