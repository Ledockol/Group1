package test.strategy;

import main.models.Car;
import main.strategy.EngineVolumeFirmYearSortStrategy;

import java.util.ArrayList;
import java.util.List;

public class EngineVolumeFirmYearSortStrategyTest {
    private List<Car> cars;
    private EngineVolumeFirmYearSortStrategy strategy; // Исправлено название класса

    public void init() {
        strategy = new EngineVolumeFirmYearSortStrategy();
        cars = new ArrayList<>();

        cars.add(Car.builder()
                .setYear(2020)
                .setFirm("BMW")
                .setEngineVolume(2.0f)
                .build());

        cars.add(Car.builder()
                .setYear(2020)
                .setFirm("Audi")
                .setEngineVolume(1.8f)
                .build());

        cars.add(Car.builder()
                .setYear(2019)
                .setFirm("BMW")
                .setEngineVolume(2.5f)
                .build());

        cars.add(Car.builder()
                .setYear(2020)
                .setFirm("BMW")
                .setEngineVolume(2.0f)
                .build());
    }

    public void testSortByVolume() {
        init();
        List<Car> sorted = new ArrayList<>(cars);
        strategy.sort(sorted);

        for (int i = 0; i < sorted.size() - 1; i++) {
            if (sorted.get(i).getEngineVolume() > sorted.get(i + 1).getEngineVolume()) {
                throw new RuntimeException("Ошибка сортировки по объему");
            }

            else if (sorted.get(i).getEngineVolume() == sorted.get(i + 1).getEngineVolume()) {
                checkFirmOrder(i, sorted);
            }
        }
    }

    private void checkFirmOrder(int i, List<Car> sorted) {
        if (sorted.get(i).getFirm().compareTo(sorted.get(i + 1).getFirm()) > 0) {
            throw new RuntimeException("Ошибка сортировки по фирме");
        }

        else if (sorted.get(i).getFirm().equals(sorted.get(i + 1).getFirm())) {
            checkYearOrder(i, sorted);
        }
    }

    private void checkYearOrder(int i, List<Car> sorted) {
        if (sorted.get(i).getYear() > sorted.get(i + 1).getYear()) {
            throw new RuntimeException("Ошибка сортировки по году");
        }
    }
}
