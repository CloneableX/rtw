package com.clo.rtw.friut;

import java.util.Objects;

public class Apple implements Peelable {
    private String color;

    public Apple() {
    }

    public Apple(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Apple apple = (Apple) other;
        return Objects.equals(color, apple.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
