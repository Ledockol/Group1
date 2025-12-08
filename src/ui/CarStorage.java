package ui;

import models.Car;
import java.util.List;
import java.util.Scanner;

public interface CarStorage {
    void setCars(List<Car> cars);

    List<Car> getCars();

    boolean isEmpty();
}
