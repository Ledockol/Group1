package strategy.Even;

import java.util.List;

import models.Car;

public class VolumeEvenSortStrategy extends AbstractEvenSortStrategy<Car> {

    @Override
    protected void sortEvenItems(List<Car> items) {
        for (int i = 0; i < items.size() - 1; i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).getEngineVolume() > items.get(j).getEngineVolume()) {
                    swap(items, i, j);
                }
            }
        }
    }

    @Override
    public boolean isEven(Car car) {
        return car.getEngineVolume() % 2 == 0;
    }


}
