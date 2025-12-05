import models.Car;
import ui.CarStorage;

import java.util.ArrayList;
import java.util.List;

class Context implements CarStorage {
    private List<Car> cars = new ArrayList<>();
    @Override
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public List<Car> getCars() {
        return cars;
    }

    @Override
    public boolean isEmpty() {
        return cars.isEmpty();
    }
}
