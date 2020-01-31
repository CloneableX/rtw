package com.clo.rtw;

import java.util.HashMap;
import java.util.Map;

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
}
