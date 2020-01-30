package com.clo.rtw;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class ConstructorContainer {
    private HashMap<Class, Object> container;

    public ConstructorContainer() {
        container = new HashMap<Class, Object>();
    }

    public <T> void registerComponent(Class<T> compClass) throws IllegalAccessException, InstantiationException {
        T component = compClass.newInstance();
        container.put(compClass, component);
    }

    public int size() {
        return container.size();
    }

    public <T> T getComponent(Class<T> compClass) {
        return compClass.cast(container.get(compClass));
    }

    public <T> void registerComponent(Class<T> compClass, Object parameter) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<T> constructor = compClass.getConstructor(parameter.getClass());
        T component = constructor.newInstance(parameter);
        container.put(compClass, component);
    }
}
