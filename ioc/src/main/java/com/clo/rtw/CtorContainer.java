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
        Object component = compClass.newInstance();
        container.put(compClass, component);
    }

    public int size() {
        return container.size();
    }

    public Object getComponent(Class<?> compClass) {
        return container.get(compClass);
    }

    public void registerCompImplementation(Class<?> compClass, Object... params) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?>[] paramClasses = new Class[params.length];
        Arrays.stream(params).map(Object::getClass).collect(Collectors.toList()).toArray(paramClasses);
        Constructor<?> constructor = compClass.getConstructor(paramClasses);
        Object component = constructor.newInstance(params);
        container.put(compClass, component);
    }
}
