package strategy.Even;

import strategy.SortStrategy;

import java.util.List;

public interface EvenSortStrategy<T> extends SortStrategy<T> {
    void sort(List<T> items);

    boolean isEven(T item);
}