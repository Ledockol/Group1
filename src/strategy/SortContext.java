package strategy;

import java.util.List;

public class SortContext<T> {

    private SortStrategy<T> strategy;

    public void setStrategy(SortStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void doSort(List<T> items) {
        if (strategy == null) {
            throw new IllegalStateException("Стратегия не установлена");
        }
        if (items == null) {
            throw new IllegalArgumentException("Список не может быть null");
        }
        strategy.sort(items);
    }
}
