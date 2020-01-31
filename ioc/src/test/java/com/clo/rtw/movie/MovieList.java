package com.clo.rtw.movie;

public class MovieList {
    private ColonMovieFinder colonMovieFinder;
    private Movie movie;
    private MovieFinder movieFinder;

    public MovieList(ColonMovieFinder movieFinder) {
        this(movieFinder, null);
    }

    public MovieList(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

    public MovieList(ColonMovieFinder movieFinder, Movie movie) {
        this.colonMovieFinder = movieFinder;
        this.movie = movie;
    }

    public ColonMovieFinder getColonMovieFinder() {
        return colonMovieFinder;
    }

    public Movie getMovie() {
        return movie;
    }

    public MovieFinder getMovieFinder() {
        return movieFinder;
    }
}
