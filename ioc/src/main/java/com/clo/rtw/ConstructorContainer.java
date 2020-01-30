package com.clo.rtw;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
        registerComponent(compClass, new Object[] {parameter});
    }

    public <T> void registerComponent(Class<T> compClass, Object... parameters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class[] parametersClass = new Class[parameters.length];

        Arrays.stream(parameters).map(Object::getClass).collect(Collectors.toList()).toArray(parametersClass);
        Constructor<T> constructor = compClass.getConstructor(parametersClass);
        T component = constructor.newInstance(parameters);
        container.put(compClass, component);
    }
}
