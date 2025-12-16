package test.strategy;

import models.Car;
import strategy.SortStrategy;

import java.util.ArrayList;
import java.util.List;

public class SortStrategyCommonTests {
    public void testEmptyList(SortStrategy<Car> strategy) {
        List<Car> emptyList = new ArrayList<>();
        strategy.sort(emptyList);
        if (!emptyList.isEmpty()) {
            throw new RuntimeException("Ошибка сортировки пустого списка");
        }
    }

    public void testSingleElement(SortStrategy<Car> strategy) {
        List<Car> singleElement = new ArrayList<>();
        singleElement.add(Car.builder().setYear(2020).build());
        strategy.sort(singleElement);
        if (singleElement.size() != 1) {
            throw new RuntimeException("Ошибка сортировки одного элемента");
        }
    }
}
