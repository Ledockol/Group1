package strategy.Even;

import strategy.SortStrategy;


public interface EvenSortStrategy<T> extends SortStrategy<T> {
    boolean isEven(T item);
}