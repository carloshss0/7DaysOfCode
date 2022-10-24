package org.example;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;



public class ConsumirAPI {
    public static void main(String[] args) throws IOException, InterruptedException, FileNotFoundException {
        String API_KEY = ""; // add the api key to run the code.

        ImdbApiClient imdbApiClient = new ImdbApiClient(API_KEY);
        List<Movie> movieList = imdbApiClient.generateList();
        movieList.forEach(System.out::println);

        File file = new File("template.html");
        HTMLGenerator generator = new HTMLGenerator(new PrintWriter(file));
        generator.generateHTML(movieList);

    }
}