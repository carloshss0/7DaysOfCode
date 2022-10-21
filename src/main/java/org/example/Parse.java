package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parse {
    String json;

    public Parse(String json) {
        this.json = json;
    }

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
        // Achei Regex algo bem confuso e de difícil entendimento, preciso estudar MAIS.
    }

    public List<String> parseJSONAttributes(String[] moviesArray, int position) {
        return Stream.of(moviesArray)
                .map(e -> e.split("\",\"")[position])
                .map(e -> e.split(":\"")[1])
                .collect(Collectors.toList());
    }

    public List<String> parseTitles(String[] moviesArray) {
        return parseJSONAttributes(moviesArray, 2);
    }

    public List<String> parseUrlImage(String[] moviesArray) {
        return parseJSONAttributes(moviesArray, 5);
    }

    public List<String> parseRating(String[] moviesArray) {
        return parseJSONAttributes(moviesArray, 7);
    }

    public List<String> parseYear(String[] moviesArray) {
        return parseJSONAttributes(moviesArray, 4);
    }

    public List<Movie> generateMoviesList(String json) {

        String[] moviesArray = parseJSONMovies(json);

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
