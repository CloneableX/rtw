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
        container.put(compClass, newInstance(compClass, parameters));
    }

    public <T> void registerComponent(Class<T> compClass, Class<?>... dependenceClasses) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object[] dependencies = Arrays.stream(dependenceClasses).map((Class dependenceClass) -> getComponent(dependenceClass)).toArray();
        registerComponent(compClass, dependencies);
    }

    public <T> void registerComponentImplementation(Class<T> compClass, Class<? extends T> implementation, Object... parameters) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        container.put(compClass, newInstance(implementation, parameters));
    }

    public <T> void registerComponentImplementation(Class<T> compClass, Class<?> dependency) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Object dependencyComp = getComponent(dependency);
        container.put(compClass, newInstance(compClass, new Object[] {dependencyComp}, dependency));
    }

    private <T> T newInstance(Class<T> instanceClass, Object[] parameters) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?>[] parametersClass = new Class[parameters.length];
        Arrays.stream(parameters).map(Object::getClass).collect(Collectors.toList()).toArray(parametersClass);
        return newInstance(instanceClass, parameters, parametersClass);
    }

    private <T> T newInstance(Class<T> instanceClass, Object[] params, Class<?>... paramClasses) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<T> constructor = instanceClass.getConstructor(paramClasses);
        return constructor.newInstance(params);
    }

    public int size() {
        return container.size();
    }

    public <T> T getComponent(Class<T> compClass) {
        return compClass.cast(container.get(compClass));
    }
}
