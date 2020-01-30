package com.clo.rtw;

import com.clo.rtw.movie.MovieFinder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ConstructorContainerSpec {

    private ConstructorContainer container;

    @Before
    public void setUp() {
        container = new ConstructorContainer();
    }

    @Test
    public void should_store_one_instance_when_register_instance_by_container() throws InstantiationException, IllegalAccessException {
        container.registerComponent(MovieFinder.class);
        assertThat(container.size(), is(1));
    }

    @Test
    public void should_get_one_MovieFinder_instance_when_register_MovieList_instance() throws InstantiationException, IllegalAccessException {
        container.registerComponent(MovieFinder.class);
        Object result = container.getComponent(MovieFinder.class);
        assertTrue(result instanceof MovieFinder);
    }
}
