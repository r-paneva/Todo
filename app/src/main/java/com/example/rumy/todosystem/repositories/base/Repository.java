package com.example.rumy.todosystem.repositories.base;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collector;

public interface Repository<T> {
    void getAll(Consumer<List<T>> action);

    void add(T item, Consumer<T> action);

    void addOne(T item);

    public void del(T item, Consumer<T> action);
}