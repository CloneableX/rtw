package com.clo.rtw.movie;

import java.util.Objects;

public class ColonMovieFinder {
    private String fileName;
    private String separator;

    public ColonMovieFinder() {
    }

    public ColonMovieFinder(String fileName) {
        this(fileName, ";");
    }

    public ColonMovieFinder(String fileName, String separator) {
        this.fileName = fileName;
        this.separator = separator;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        ColonMovieFinder that = (ColonMovieFinder) other;
        return Objects.equals(fileName, that.fileName) &&
                Objects.equals(separator, that.separator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, separator);
    }
}
