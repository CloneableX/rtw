package com.clo.rtw;

import com.clo.rtw.friut.Apple;
import com.clo.rtw.friut.Fruit;
import com.clo.rtw.friut.Peelable;
import com.clo.rtw.friut.Peeler;
import org.junit.Before;
import org.junit.Test;

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
    public void should_get_component_size_1_when_register_normal_component() throws Exception {
        container.registerCompImplementation(Fruit.class);

        assertThat(container.size(), is(1));
    }

    @Test
    public void should_get_component_when_register_component() throws Exception {
        container.registerCompImplementation(Fruit.class);

        assertTrue(container.getComponent(Fruit.class) instanceof Fruit);
    }

    @Test
    public void should_get_component_with_params_when_register_component_with_params() throws Exception {
        String name = "apple";
        container.registerCompImplementation(Fruit.class, name, FRUIT_COLOR);
        Fruit component = (Fruit) container.getComponent(Fruit.class);

        assertThat(component, is(new Fruit(name, FRUIT_COLOR)));
    }

    @Test
    public void should_get_component_by_interface_class_when_register_component_implementation() throws Exception {
        container.registerCompImplementation(Peelable.class, Apple.class);
        Peelable component = (Peelable) container.getComponent(Peelable.class);

        assertTrue(component instanceof Apple);
    }

    @Test
    public void should_get_component_by_interface_class_when_register_component_implementation_with_params() throws Exception {
        container.registerCompImplementation(Peelable.class, Apple.class, FRUIT_COLOR);
        Peelable component = (Peelable) container.getComponent(Peelable.class);

        assertThat(component, is(new Apple(FRUIT_COLOR)));
    }

    @Test
    public void should_get_component_with_dependence_when_register_component_with_dependence() throws Exception {
        container.registerCompImplementation(Peelable.class, Apple.class, FRUIT_COLOR);
        container.registerCompImplementation(Peeler.class);
        Peelable dependence = (Peelable) container.getComponent(Peelable.class);
        Peeler component = (Peeler) container.getComponent(Peeler.class);

        assertThat(component.getPeelable(), is(dependence));
    }
}
