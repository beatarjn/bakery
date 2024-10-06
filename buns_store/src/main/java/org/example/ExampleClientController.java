package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleClientController {

    private OkHttpClient client = new OkHttpClient();

    @GetMapping("/sendHello")
    public String runHello() throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8080/hello")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}