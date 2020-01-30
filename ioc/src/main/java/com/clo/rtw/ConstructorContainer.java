package com.clo.rtw;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ConstructorContainer {
    private HashMap<Class<?>, Object> container;

    public ConstructorContainer() {
        container = new HashMap<>();
    }

    public <T> void registerComponent(Class<T> compClass) throws IllegalAccessException, InstantiationException {
        T component = compClass.newInstance();
        container.put(compClass, component);
    }

    public <T> void registerComponent(Class<T> compClass, Object... parameters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?>[] parametersClass = new Class[parameters.length];

        Arrays.stream(parameters).map(Object::getClass).collect(Collectors.toList()).toArray(parametersClass);
        Constructor<T> constructor = compClass.getConstructor(parametersClass);
        T component = constructor.newInstance(parameters);
        container.put(compClass, component);
    }

    public <T> void registerComponent(Class<T> compClass, Class<?>... dependenceClasses) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object[] dependencies = Arrays.stream(dependenceClasses).map((Class dependenceClass) -> getComponent(dependenceClass)).toArray();
        registerComponent(compClass, dependencies);
    }

    public int size() {
        return container.size();
    }

    public <T> T getComponent(Class<T> compClass) {
        return compClass.cast(container.get(compClass));
    }
}
