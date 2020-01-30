package com.clo.rtw;

import com.clo.rtw.movie.MovieFinder;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ConstructorContainerSpec {

    private ConstructorContainer container;

    @Before
    public void setUp() {
        container = new ConstructorContainer();
    }

    @Test
    public void should_store_one_instance_when_register_MovieFinder_instance_by_container() throws InstantiationException, IllegalAccessException {
        container.registerComponent(MovieFinder.class);
        assertThat(container.size(), is(1));
    }

    @Test
    public void should_get_one_MovieFinder_instance_when_register_MovieFinder_instance() throws InstantiationException, IllegalAccessException {
        container.registerComponent(MovieFinder.class);
        MovieFinder result = container.getComponent(MovieFinder.class);
        assertNotNull(result);
    }

    @Test
    public void should_store_MovieFinder_when_register_MovieFinder_instance_with_parameter() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String fileName = "movie.txt";
        container.registerComponent(MovieFinder.class, fileName);
        MovieFinder movieFinder = container.getComponent(MovieFinder.class);
        assertThat(movieFinder.getFileName(), is(fileName));
    }
}
