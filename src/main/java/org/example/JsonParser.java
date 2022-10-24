package org.example;

import java.io.IOException;
import java.util.List;

public interface JsonParser {

    public List<? extends Content> generateList() throws IOException, InterruptedException;

    public String[] parseJSONMovies(String json);

    public List<String> parseJSONAttributes(String[] moviesArray, int position);
    public List<String> parseTitles(String[] moviesArray);
    public List<String> parseUrlImage(String[] moviesArray);
    public List<String> parseRating(String[] moviesArray);
    public List<String> parseYear(String[] moviesArray);





}
