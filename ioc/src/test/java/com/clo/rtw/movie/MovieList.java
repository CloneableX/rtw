package com.clo.rtw.movie;

public class MovieList {
    private MovieFinder movieFinder;

    public MovieList(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

    public MovieFinder getMovieFinder() {
        return movieFinder;
    }
}
