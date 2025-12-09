package io;

import models.Car;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarCollection extends AbstractList<Car> implements RandomAccess {
    private final List<Car> cars = new ArrayList<>();

    @Override
    public Car get(int index) {
        return cars.get(index);
    }

    @Override
    public int size() {
        return cars.size();
    }

    @Override
    public boolean add(Car car) {
        if (car == null) {
            throw new NullPointerException("Автомобиль не может быть null");
        }
        return cars.add(car);
    }

    @Override
    public boolean addAll(Collection<? extends Car> cars) {
        if (cars == null) {
            throw new NullPointerException("Коллекция не может быть null");
        }
        return this.cars.addAll(cars);
    }

    public Stream<Car> stream() {
        return cars.stream();
    }

    public CarCollection filterByYear(int minYear, int maxYear) {
        CarCollection result = new CarCollection();
        result.addAll(
                cars.stream()
                        .filter(car -> car.getYear() >= minYear && car.getYear() <= maxYear)
                        .collect(Collectors.toList())
        );
        return result;
    }

    public CarCollection filterByVolume(float minVolume, float maxVolume) {
        CarCollection result = new CarCollection();
        result.addAll(
                cars.stream()
                        .filter(car -> car.getEngineVolume() >= minVolume && car.getEngineVolume() <= maxVolume)
                        .collect(Collectors.toList())
        );
        return result;
    }

    public CarCollection sortByYear() {
        CarCollection sorted = new CarCollection();
        sorted.addAll(
                cars.stream()
                        .sorted(Comparator.comparingInt(Car::getYear))
                        .collect(Collectors.toList())
        );
        return sorted;
    }

    public CarCollection sortByVolume() {
        CarCollection sorted = new CarCollection();
        sorted.addAll(
                cars.stream()
                        .sorted(Comparator.comparingDouble(Car::getEngineVolume))
                        .collect(Collectors.toList())
        );
        return sorted;
    }
}