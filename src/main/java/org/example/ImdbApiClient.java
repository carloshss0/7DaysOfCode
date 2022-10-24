package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImdbApiClient implements JsonParser{
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

    @Override
    public String[] parseJSONMovies(String json) {
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
    }

    @Override
    public List<String> parseJSONAttributes(String[] moviesArray, int position) {
        return Stream.of(moviesArray)
                .map(e -> e.split("\",\"")[position])
                .map(e -> e.split(":\"")[1])
                .collect(Collectors.toList());
    }

    @Override
    public List<String> parseTitles(String[] moviesArray) {return parseJSONAttributes(moviesArray, 2);}

    @Override
    public List<String> parseUrlImage(String[] moviesArray) {return parseJSONAttributes(moviesArray, 5);}

    @Override
    public List<String> parseRating(String[] moviesArray) {return parseJSONAttributes(moviesArray, 7);}

    @Override
    public List<String> parseYear(String[] moviesArray) {return parseJSONAttributes(moviesArray, 4);}

    @Override
    public List<Movie> generateList() throws IOException, InterruptedException {
        String[] moviesArray = parseJSONMovies(getBody());

        List<String> titles = parseTitles(moviesArray);
        List<String> urlImage = parseUrlImage(moviesArray);
        List<String> rating = parseRating(moviesArray);
        List<String> year = parseYear(moviesArray);

        int attributesSize = titles.size();
        List<Movie> movies = new ArrayList<Movie>();

        for (int i = 0; i < attributesSize; i++) {
            movies.add(new Movie(titles.get(i), urlImage.get(i), rating.get(i), year.get(i)));
        }
        return movies;
    }

}




