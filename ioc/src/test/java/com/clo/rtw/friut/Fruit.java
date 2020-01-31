package com.clo.rtw.friut;

import java.util.Objects;

public class Fruit {
    private String name;
    private String color;

    public Fruit() {


    }

    public Fruit(String name, String color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Fruit fruit = (Fruit) other;
        return Objects.equals(name, fruit.name) &&
                Objects.equals(color, fruit.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }
}
