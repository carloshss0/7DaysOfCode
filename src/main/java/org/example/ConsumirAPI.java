package org.example;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;



public class ConsumirAPI {
    public static void main(String[] args) throws IOException, InterruptedException {
        String API_KEY = ""; // add the api key to run the code.


        URI apiIMDB = URI.create("https://imdb-api.com/en/API/Top250TVs/" + API_KEY);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(apiIMDB).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        Parse parse = new Parse(json);

        List<Movie> movieList = parse.generateMoviesList(json);

//        movieList.forEach(System.out::println);

        try {
            File file = new File("template.html");
            HTMLGenerator generator = new HTMLGenerator(new PrintWriter(file));
            generator.generateHTML(movieList);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

    }
}