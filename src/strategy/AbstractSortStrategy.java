package strategy;

public abstract class AbstractSortStrategy<T> implements SortStrategy<T> {

    @Override
    public void sort(List<T> items) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = i + 1; j < items.size(); j++) {
                T item1 = items.get(i);
                T item2 = items.get(j);
                if (compare(item1, item2) > 0) {
                    swap(items, i, j);
                }
            }
        }
    }

    protected abstract int compare(T item1, T item2);

    protected void swap(List<T> items, int i, int j) {
        T temp = items.get(i);
        items.set(i, items.get(j));
        items.set(j, temp);
    }
}
