package com.clo.rtw.movie;

import java.util.Objects;

public class MovieFinder {
    private String fileName;
    private String separator;

    public MovieFinder() {
    }

    public MovieFinder(String fileName) {
        this(fileName, ";");
    }

    public MovieFinder(String fileName, String separator) {
        this.fileName = fileName;
        this.separator = separator;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieFinder that = (MovieFinder) o;

        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        return separator != null ? separator.equals(that.separator) : that.separator == null;
    }

    @Override
    public int hashCode() {
        int result = fileName != null ? fileName.hashCode() : 0;
        result = 31 * result + (separator != null ? separator.hashCode() : 0);
        return result;
    }
}
