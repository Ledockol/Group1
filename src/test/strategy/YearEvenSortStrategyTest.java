package test.strategy;

import main.models.Car;
import main.strategy.even.YearEvenSortStrategy;

import java.util.ArrayList;
import java.util.List;

public class YearEvenSortStrategyTest {
    public void YearEvenSortStrategyTest() {
        YearEvenSortStrategy strategy = new YearEvenSortStrategy();

        List<Car> cars = new ArrayList<>();

        cars.add(Car.builder().setYear(2019).setFirm("Firm").setEngineVolume(2.0f).build()); // нечетный
        cars.add(Car.builder().setYear(2024).setFirm("Firm").setEngineVolume(2.0f).build()); // четный
        cars.add(Car.builder().setYear(2022).setFirm("Firm").setEngineVolume(2.0f).build()); // четный
        cars.add(Car.builder().setYear(2017).setFirm("Firm").setEngineVolume(2.0f).build()); // нечетный
        cars.add(Car.builder().setYear(2020).setFirm("Firm").setEngineVolume(2.0f).build()); // четный
        cars.add(Car.builder().setYear(2018).setFirm("Firm").setEngineVolume(2.0f).build()); // четный
        cars.add(Car.builder().setYear(2021).setFirm("Firm").setEngineVolume(2.0f).build()); // нечетный

        int[] oddPositions = {0, 3, 6};
        int[] evenYears = {2024, 2022, 2020, 2018};

        strategy.sort(cars);

        for (int pos : oddPositions) {
            if (cars.get(pos).getYear() % 2 != 0) {

                if (cars.get(pos).getYear() != (pos == 0 ? 2019 :
                        pos == 3 ? 2017 :
                                2021)) {
                    throw new RuntimeException("Нечетный элемент должен остаться на позиции " + pos +
                            ", год должен быть " +
                            (pos == 0 ? 2019 :
                                    pos == 3 ? 2017 :
                                            2021));
                }
            } else {
                throw new RuntimeException("На позиции " + pos + " должен быть нечетный год");
            }
        }

        List<Car> evenCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getYear() % 2 == 0) {
                evenCars.add(car);
            }
        }

        for (int i = 0; i < evenCars.size() - 1; i++) {
            if (evenCars.get(i).getYear() > evenCars.get(i + 1).getYear()) {
                throw new RuntimeException("Четные года должны быть отсортированы по возрастанию");
            }
        }

        checkSortedSequence(evenCars, 2018, 2020, 2022, 2024);
    }

    private void checkSortedSequence(List<Car> cars, int... expectedYears) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getYear() != expectedYears[i]) {
                throw new RuntimeException("Ошибка в сортировке: элемент " + i +
                        " должен быть " + expectedYears[i] +
                        ", а не " + cars.get(i).getYear());
            }
        }
    }
}
