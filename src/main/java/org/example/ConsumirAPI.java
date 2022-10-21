package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ConsumirAPI {
    public static void main(String[] args) throws IOException, InterruptedException {
        String API_KEY = "";


        URI apiIMDB = URI.create("https://imdb-api.com/en/API/Top250TVs/" + API_KEY);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(apiIMDB).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        String[] movies = parseJSONMovies(json);

        List<String> ids = parseJSONAttributes(movies, 0);
        List<String> ranks = parseJSONAttributes(movies, 1);
        List<String> title = parseJSONAttributes(movies, 2);
        List<String> fullTitle = parseJSONAttributes(movies, 3);
        List<String> yearOftheMovie = parseJSONAttributes(movies, 4);
        List<String> urlImage = parseJSONAttributes(movies, 5);
        List<String> crew = parseJSONAttributes(movies, 6);
        List<String> imDBRating = parseJSONAttributes(movies, 7);
        List<String> imDMRatingCount = parseJSONAttributes(movies, 8);

    }

    public static String[] parseJSONMovies(String json) {
        Matcher matcher = Pattern.compile(".*\\[(.*)\\].*").matcher(json);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Nao bateu com o " + json);
        }

        String[] moviesArray = matcher.group(1).split("\\},\\{");
        moviesArray[0] = moviesArray[0].substring(1);
        int last = moviesArray.length - 1;
        String lastString = moviesArray[last];
        moviesArray[last] = lastString.substring(0, lastString.length() - 1);

        return moviesArray;


        // Achei Regex algo bem confuso e de difícil entendimento, preciso estudar MAIS.
    }

    public static List<String> parseJSONAttributes(String[] moviesArray, int position) {
        return Stream.of(moviesArray)
                .map(e -> e.split("\",\"")[position])
                .map(e -> e.split(":\"")[1])
                .collect(Collectors.toList());
    }

}