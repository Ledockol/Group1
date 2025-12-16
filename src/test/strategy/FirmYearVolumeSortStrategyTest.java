package test.strategy;

import models.Car;
import strategy.FirmYearVolumeSortStrategy;

import java.util.ArrayList;
import java.util.List;

public class FirmYearVolumeSortStrategyTest {
    private List<Car> cars;
    private FirmYearVolumeSortStrategy strategy;

    public void init() {
        strategy = new FirmYearVolumeSortStrategy();
        cars = new ArrayList<>();


        cars.add(Car.builder().setYear(2020).setFirm("BMW").setEngineVolume(2.0f).build());
        cars.add(Car.builder().setYear(2020).setFirm("Audi").setEngineVolume(2.0f).build());
        cars.add(Car.builder().setYear(2019).setFirm("BMW").setEngineVolume(2.5f).build());
        cars.add(Car.builder().setYear(2020).setFirm("BMW").setEngineVolume(1.8f).build());
    }

    public void testSortByYear() {
        init();
        List<Car> sorted = new ArrayList<>(cars);
        strategy.sort(sorted);

        for (int i = 0; i < sorted.size() - 1; i++) {
            if (sorted.get(i).getFirm().equals(sorted.get(i + 1).getFirm())) {
                if (sorted.get(i).getYear() > sorted.get(i + 1).getYear()) {
                    throw new RuntimeException("Ошибка сортировки по году");
                }

                else if (sorted.get(i).getYear() == sorted.get(i + 1).getYear()) {
                    if (sorted.get(i).getEngineVolume() > sorted.get(i + 1).getEngineVolume()) {
                        throw new RuntimeException("Ошибка сортировки по объему");
                    }
                }
            }
        }
    }
}
