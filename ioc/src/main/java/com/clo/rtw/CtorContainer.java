package com.clo.rtw;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CtorContainer {
    private Map<Class<?>, Object> container;

    public CtorContainer() {
        this.container = new HashMap<>();
    }

    public void registerCompImplementation(Class<?> compClass) throws IllegalAccessException, InstantiationException {
        registerComponent(compClass, compClass.newInstance());
    }

    public void registerCompImplementation(Class<?> compClass, Object... params) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        registerComponent(compClass, compClass, params);
    }

    public <T> void registerCompImplementation(Class<T> interfaceClass, Class<? extends T> compClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        registerComponent(interfaceClass, compClass.newInstance());
    }

    public <T> void registerCompImplementation(Class<T> interfaceClass, Class<? extends T> compClass, Object... params) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        registerComponent(interfaceClass, compClass, params);
    }

    private void registerComponent(Class<?> compClass, Class<?> instanceClass, Object[] params) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?>[] paramClasses = new Class[params.length];
        Arrays.stream(params).map(Object::getClass).collect(Collectors.toList()).toArray(paramClasses);
        Constructor<?> constructor = instanceClass.getConstructor(paramClasses);
        Object component = constructor.newInstance(params);
        registerComponent(compClass, component);
    }

    private void registerComponent(Class<?> compClass, Object component) {
        container.put(compClass, component);
    }

    public int size() {
        return container.size();
    }

    public Object getComponent(Class<?> compClass) {
        return container.get(compClass);
    }
}
