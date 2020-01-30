package com.clo.rtw.movie;

public class MovieList {
    private MovieFinder movieFinder;
    private Movie movie;

    public MovieList(MovieFinder movieFinder) {
        this(movieFinder, null);
    }

    public MovieList(MovieFinder movieFinder, Movie movie) {
        this.movieFinder = movieFinder;
        this.movie = movie;
    }

    public MovieFinder getMovieFinder() {
        return movieFinder;
    }

    public Movie getMovie() {
        return movie;
    }
}
