package main.strategy.even;

import main.strategy.SortStrategy;


public interface EvenSortStrategy<T> extends SortStrategy<T> {
    boolean isEven(T item);
}
