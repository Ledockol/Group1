package io;

import models.Car;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomCar {

    public static List<Car> generateRandomCars(int count) {
        validateCount(count);

        String[] firms = {"Mazda", "Ford", "BMW", "Toyota", "Mercedes", "Audi"};

        List<Car> cars = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            Car car = Car.builder()
                    .firm(firms[random.nextInt(firms.length)])
                    .year(random.nextInt(1990, 2025))
                    .engineVolume(random.nextInt(1, 4))
                    .build();

            cars.add(car);
        }

        return cars;
    }

    private static void validateCount(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Некорректно задано количество");
        }
    }
}
