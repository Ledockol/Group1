package ui;

import io.CarInput;
import models.Car;

public class InputCarsCommand extends BaseCommand {
    public InputCarsCommand(CarStorage storage) {
        super(storage);
    }

    @Override
    public void execute() {

        System.out.println("--- Ввод автомобиля ---");

        Car targetCar = new CarInput().inputCar();

        if (targetCar == null) {
            System.out.println("Ввод отменен.");
            return;
        }

        var cars = getStorage().getCars();

        cars.add(targetCar);

        getStorage().setCars(cars);
    }
}
