package ui;

import models.Car;

import java.util.List;

public interface CarStorage {
    void setCars(List<Car> cars);

    List<Car> getCars();

    boolean isEmpty();
}
