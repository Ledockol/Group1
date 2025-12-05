package strategy.Even;

import java.util.List;

public class YearEvenSortStrategy extends AbstractEvenSortStrategy<Car> {

    @Override
    protected void sortEvenItems(List<Car> items) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).getYear() > items.get(j).getYear()) {
                    swap(items, i, j);
                }
            }
        }
    }

    @Override
    public boolean isEven(Car car) {
        return car.getYear() % 2 == 0;
    }
}
