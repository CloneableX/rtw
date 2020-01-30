package com.clo.rtw;

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
}
