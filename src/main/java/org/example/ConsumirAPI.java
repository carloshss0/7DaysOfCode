package org.example;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class ConsumirAPI {
    public static void main(String[] args) throws IOException, InterruptedException, FileNotFoundException {
        String API_KEY = ""; // add the api key to run the code.

        ImdbApiClient imdbApiClient = new ImdbApiClient(API_KEY);
        List<Movie> movieList = imdbApiClient.generateList();
        movieList.forEach(System.out::println);

        // A API já retorna com base na ordenação das notas de forma descendente (rating)
        //Será implementado ordenação por nota e ano.

        //movieList.sort(Movie::compareTo); // notas crescentes
        //Collections.sort(movieList, Comparator.comparing(Content::year)); // Ano crescente
        Collections.sort(movieList, Comparator.comparing(Content::year).reversed()); // Ano decrescente


        PrintWriter writer = new PrintWriter("template.html");
        HTMLGenerator generator = new HTMLGenerator(new PrintWriter(writer));
        generator.generateHTML(movieList);
        writer.close();

    }
}