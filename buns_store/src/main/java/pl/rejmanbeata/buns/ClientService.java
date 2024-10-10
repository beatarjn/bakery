package pl.rejmanbeata.buns;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientService {
    private OkHttpClient client = new OkHttpClient();
    @Value("${bakery.url}")
    private String bakeryUrl;
    @Value("${bakery.port}")
    private String bakeryPort;
    private static final String SEND_HELLO = "/hello";

    public String sendHello() throws IOException {
        var request = new Request.Builder()
                .url(bakeryUrl + bakeryPort + SEND_HELLO)
                .build();

        try (var response = client.newCall(request).execute()) {
            return Optional.ofNullable(response.body())
                    .map(Objects::toString)
                    .orElseGet(()->"Hello world");
        }
    }

}
