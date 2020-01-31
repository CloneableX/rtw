package com.clo.rtw.movie;

public class MovieList {
    private ColonMovieFinder movieFinder;
    private Movie movie;

    public MovieList(ColonMovieFinder movieFinder) {
        this(movieFinder, null);
    }

    public MovieList(ColonMovieFinder movieFinder, Movie movie) {
        this.movieFinder = movieFinder;
        this.movie = movie;
    }

    public ColonMovieFinder getMovieFinder() {
        return movieFinder;
    }

    public Movie getMovie() {
        return movie;
    }
}
