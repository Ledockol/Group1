package strategy.even;

import strategy.SortStrategy;


public interface EvenSortStrategy<T> extends SortStrategy<T> {
    boolean isEven(T item);
}