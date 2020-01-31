package com.clo.rtw;

import com.clo.rtw.friut.Apple;
import com.clo.rtw.friut.Fruit;
import com.clo.rtw.friut.Peelable;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CtorContainerSpec {

    public static final String FRUIT_COLOR = "red";
    private CtorContainer container;

    @Before
    public void setUp() {
        container = new CtorContainer();
    }

    @Test
    public void should_get_component_size_1_when_register_normal_component() throws InstantiationException, IllegalAccessException {
        container.registerCompImplementation(Fruit.class);

        assertThat(container.size(), is(1));
    }

    @Test
    public void should_get_component_when_register_component() throws InstantiationException, IllegalAccessException {
        container.registerCompImplementation(Fruit.class);

        assertTrue(container.getComponent(Fruit.class) instanceof Fruit);
    }

    @Test
    public void should_get_component_with_params_when_register_component_with_params() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String name = "apple";
        container.registerCompImplementation(Fruit.class, name, FRUIT_COLOR);
        Fruit component = (Fruit) container.getComponent(Fruit.class);

        assertThat(component, is(new Fruit(name, FRUIT_COLOR)));
    }

    @Test
    public void should_get_component_by_interface_class_when_register_component_implementation() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        container.registerCompImplementation(Peelable.class, Apple.class);
        Peelable component = (Peelable) container.getComponent(Peelable.class);

        assertTrue(component instanceof Apple);
    }

    @Test
    public void should_get_component_by_interface_class_when_register_component_implementation_with_params() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        container.registerCompImplementation(Peelable.class, Apple.class, FRUIT_COLOR);
        Peelable component = (Peelable) container.getComponent(Peelable.class);

        assertThat(component, is(new Apple(FRUIT_COLOR)));
    }
}
