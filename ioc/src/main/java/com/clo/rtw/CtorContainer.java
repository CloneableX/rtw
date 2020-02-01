package com.clo.rtw;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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

    public void registerCompImplementation(Class<?> compClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        registerComponent(compClass, injectDependency(compClass));
    }

    public void registerCompImplementation(Class<?> compClass, Object... params) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        registerComponent(compClass, compClass, params);
    }

    public <T> void registerCompImplementation(Class<T> interfaceClass, Class<? extends T> compClass) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
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

    private Object injectDependency(Class<?> compClass) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Field[] fields = compClass.getDeclaredFields();
        Object[] dependenceList = Arrays.stream(fields).map((Field field) -> getComponent(field.getType())).toArray(Object[]::new);
        Class<?>[] dependenceClasses = Arrays.stream(fields).map(Field::getType).toArray(Class<?>[]::new);
        Constructor<?> constructor = compClass.getConstructor(dependenceClasses);
        return constructor.newInstance(dependenceList);
    }

    public int size() {
        return container.size();
    }

    public Object getComponent(Class<?> compClass) {
        return container.get(compClass);
    }
}
