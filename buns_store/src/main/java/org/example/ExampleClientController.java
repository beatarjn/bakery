package org.example;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleClientController {

    private OkHttpClient client = new OkHttpClient();

    @GetMapping("/sendHello")
    public void runHello() throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8080/hello")
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println( response.body().string());
        }


    }
}
