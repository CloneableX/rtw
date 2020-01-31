package com.clo.rtw;

import com.clo.rtw.friut.Fruit;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CtorContainerSpec {
    @Test
    public void should_get_component_size_1_when_register_normal_component() throws InstantiationException, IllegalAccessException {
        CtorContainer container = new CtorContainer();
        container.registerCompImplementation(Fruit.class);

        assertThat(container.size(), is(1));
    }
}
