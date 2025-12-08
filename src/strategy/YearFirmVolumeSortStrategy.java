package strategy;

import models.Car;

public class YearFirmVolumeSortStrategy extends AbstractSortStrategy<Car> {
    @Override
    protected int compare(Car item1, Car item2) {
        int result = Integer.compare(item1.getYear(), item2.getYear());
        if (result == 0) {
            result = item1.getFirm().compareTo(item2.getFirm());
            if (result == 0) {
                return Float.compare(item1.getEngineVolume(), item2.getEngineVolume());
            }
        }
        return result;
    }
}
