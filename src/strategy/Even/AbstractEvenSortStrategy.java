package strategy.Even;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEvenSortStrategy<T> implements EvenSortStrategy<T> {

    @Override
    public void sort(List<T> items) {
        if (items == null) {
            throw new IllegalArgumentException("Список не может быть null");
        }

        List<Integer> evenIndices = new ArrayList<>();

        for (int i = 0; i < items.size(); i++) {
            if (isEven(items.get(i))) {
                evenIndices.add(i);
            }
        }

        List<T> evenItems = new ArrayList<>();
        for (int index : evenIndices) {
            evenItems.add(items.get(index));
        }

        sortEvenItems(evenItems);

        for (int i = 0; i < evenIndices.size(); i++) {
            items.set(evenIndices.get(i), evenItems.get(i));
        }
    }

    protected abstract void sortEvenItems(List<T> items);

    @Override
    public abstract boolean isEven(T item);

    protected void swap(List<T> items, int i, int j) {
        T temp = items.get(i);
        items.set(i, items.get(j));
        items.set(j, temp);
    }

}

