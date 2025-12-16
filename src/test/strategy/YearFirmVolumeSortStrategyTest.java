package test.strategy;

import main.models.Car;
import main.strategy.YearFirmVolumeSortStrategy;

import java.util.ArrayList;
import java.util.List;

public class YearFirmVolumeSortStrategyTest {
    private List<Car> cars;
    private YearFirmVolumeSortStrategy strategy;

    public void setUp() {
        strategy = new YearFirmVolumeSortStrategy();
        cars = new ArrayList<>();

        cars.add(Car.builder().setYear(2020).setFirm("BMW").setEngineVolume(2.0f).build());
        cars.add(Car.builder().setYear(2020).setFirm("Audi").setEngineVolume(2.0f).build());
        cars.add(Car.builder().setYear(2019).setFirm("BMW").setEngineVolume(2.5f).build());
        cars.add(Car.builder().setYear(2020).setFirm("BMW").setEngineVolume(1.8f).build());
    }

    public void testSortByYear() {
        setUp();
        List<Car> sorted = new ArrayList<>(cars);
        strategy.sort(sorted);

        for (int i = 0; i < sorted.size() - 1; i++) {
            if (sorted.get(i).getYear() > sorted.get(i + 1).getYear()) {
                throw new RuntimeException("Ошибка сортировки по году");
            }

            else if (sorted.get(i).getYear() == sorted.get(i + 1).getYear()) {
                checkFirmOrder(i, sorted);
            }
        }
    }

    private void checkFirmOrder(int i, List<Car> sorted) {
        if (sorted.get(i).getFirm().compareTo(sorted.get(i + 1).getFirm()) > 0) {
            throw new RuntimeException("Ошибка сортировки по фирме");
        }

        else if (sorted.get(i).getFirm().equals(sorted.get(i + 1).getFirm())) {
            checkVolumeOrder(i, sorted);
        }
    }

    private void checkVolumeOrder(int i, List<Car> sorted) {
        if (sorted.get(i).getEngineVolume() > sorted.get(i + 1).getEngineVolume()) {
            throw new RuntimeException("Ошибка сортировки по объему");
        }
    }

    public void testSortByFirm() {
        setUp();
        List<Car> sorted = new ArrayList<>(cars);
        strategy.sort(sorted);

        for (int i = 0; i < sorted.size() - 1; i++) {
            if (sorted.get(i).getYear() == sorted.get(i + 1).getYear()) {
                if (sorted.get(i).getFirm().compareTo(sorted.get(i + 1).getFirm()) > 0) {
                    throw new RuntimeException("Ошибка сортировки по фирме");
                }
            }
        }
    }

    public void testSortByVolume() {
        setUp();
        List<Car> sorted = new ArrayList<>(cars);
        strategy.sort(sorted);

        for (int i = 0; i < sorted.size() - 1; i++) {
            if (sorted.get(i).getYear() == sorted.get(i + 1).getYear() &&
                    sorted.get(i).getFirm().equals(sorted.get(i + 1).getFirm())) {

                if (sorted.get(i).getEngineVolume() > sorted.get(i + 1).getEngineVolume()) {
                    throw new RuntimeException("Ошибка сортировки по объему");
                }
            }
        }
    }
}
