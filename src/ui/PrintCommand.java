package ui;

import models.Car;

public class PrintCommand extends BaseCommand {

    public PrintCommand(CarStorage storage) {
        super(storage);
    }

    @Override
    public void execute() {
        for (Car car : getStorage().getCars()) {
            System.out.println(car);
        }
    }
}
