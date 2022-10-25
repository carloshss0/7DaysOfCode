package org.example;


public class Movie implements Content {

    private String title;
    private String urlImage;
    private String rating;
    private String year;

    public Movie(String title, String urlImage, String rating, String year) {
        this.title = title;
        this.urlImage = urlImage;
        this.rating = rating;
        this.year = year;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public String urlImage() {
        return urlImage;
    }

    @Override
    public String rating() {
        return rating;
    }

    @Override
    public String year() {
        return year;
    }


    @Override
    public String toString() {
        return
                "Title: " + title + '\'' +
                ", urlImage: " + urlImage + '\'' +
                ", rating: " + rating + '\'' +
                ", year: " + year + '\'';
    }

    @Override
    public int compareTo(Content content) {
        return this.rating().compareTo(content.rating());
    }
}

