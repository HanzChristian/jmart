package com.HanzChristianJmartMH;

/**
 * Merupakan interface untuk pengecekkan kondisi
 * @param <T>
 */
@FunctionalInterface
public interface Predicate<T>{
    public boolean predicate (T arg);
}
