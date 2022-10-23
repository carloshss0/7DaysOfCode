package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImdbApiClient {
    private String API_KEY;

    public ImdbApiClient(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public String getBody() throws IOException, InterruptedException {
        URI apiIMDB = URI.create("https://imdb-api.com/en/API/Top250TVs/" + API_KEY);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(apiIMDB).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        return json;
    }
}
