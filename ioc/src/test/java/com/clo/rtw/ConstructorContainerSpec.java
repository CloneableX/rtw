package com.clo.rtw;

import com.clo.rtw.movie.Movie;
import com.clo.rtw.movie.ColonMovieFinder;
import com.clo.rtw.movie.MovieList;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class ConstructorContainerSpec {

    public static final String FILE_NAME = "movie.txt";
    private ConstructorContainer container;

    @Before
    public void setUp() {
        container = new ConstructorContainer();
    }

    @Test
    public void should_store_one_instance_when_register_MovieFinder_instance_by_container() throws InstantiationException, IllegalAccessException {
        container.registerComponent(ColonMovieFinder.class);
        assertThat(container.size(), is(1));
    }

    @Test
    public void should_get_one_MovieFinder_instance_when_register_MovieFinder_instance() throws InstantiationException, IllegalAccessException {
        container.registerComponent(ColonMovieFinder.class);
        ColonMovieFinder result = container.getComponent(ColonMovieFinder.class);
        assertNotNull(result);
    }

    @Test
    public void should_store_MovieFinder_when_register_MovieFinder_instance_with_parameters() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String separator = ";";
        container.registerComponent(ColonMovieFinder.class, FILE_NAME, separator);
        ColonMovieFinder movieFinder = container.getComponent(ColonMovieFinder.class);
        assertThat(movieFinder, is(new ColonMovieFinder(FILE_NAME, separator)));
    }

    @Test
    public void should_store_MovieList_when_register_MovieList_with_dependencies() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        container.registerComponent(ColonMovieFinder.class, FILE_NAME);
        container.registerComponent(Movie.class);
        container.registerComponent(MovieList.class, ColonMovieFinder.class, Movie.class);
        MovieList movieList = container.getComponent(MovieList.class);
        assertThat(movieList.getMovieFinder(), is(new ColonMovieFinder(FILE_NAME)));
        assertNotNull(movieList.getMovie());
    }

}
