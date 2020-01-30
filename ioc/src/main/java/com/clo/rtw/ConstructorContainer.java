package com.clo.rtw;

import java.util.HashMap;

public class ConstructorContainer {
    private HashMap<Class, Object> container;

    public ConstructorContainer() {
        container = new HashMap();
    }

    public void registerComponent(Class compClass) throws IllegalAccessException, InstantiationException {
        Object component = compClass.newInstance();
        container.put(compClass, component);
    }

    public int size() {
        return container.size();
    }

    public Object getComponent(Class compClass) {
        return container.get(compClass);
    }
}
