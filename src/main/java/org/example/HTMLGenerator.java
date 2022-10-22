package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator {

    private PrintWriter writer;

    public HTMLGenerator(PrintWriter writer) {
        this.writer = writer;
    }

    public void generateHTML(List<Movie> movies) throws IOException {
        String head =
                """
                <head>
                    <meta charset=\"utf-8\">
                    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
                    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\"
                        + "integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">
                </head>
                """;

        writer.write(head);

        String divClass =
                """
                <div class=\"row card-group\">
                """;

        writer.write(divClass);

        String divTemplate =
                """
                    <div class=\"col-4 mb-4\" align=\"center\">
                        <div class=\"card text-white bg-dark mb-3 h-100\" style=\"max-width: 18rem;\">
                            <h4 class=\"card-header\">%s</h4>
                            <div class=\"card-body\">
                                <img class=\"card-img\" src=\"%s\" alt=\"%s\">
                                <p class=\"card-text mt-2\">Nota: %s - Ano: %s</p>
                            </div>
                        </div>
                    </div>
                """;

        for (Movie movie: movies){
            writer.write(String.format(divTemplate, movie.getTitle(), movie.getUrlImage(), movie.getTitle(), movie.getRating(), movie.getYear()));
        }
        String closedivClass =
                """
                </div>
                """;
        writer.write(closedivClass);
        writer.close();
    }
}
