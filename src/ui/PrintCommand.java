package ui;

import models.Car;

public class PrintCommand extends BaseCommand {

    public PrintCommand(CarStorage storage) {
        super(storage);
    }

    @Override
    public void execute() {
        System.out.println("---Список машин ---");

        if (getStorage().getCars().isEmpty()) {
            System.out.println("Пока нет ни одной машины");
            return;
        }

        for (Car car : getStorage().getCars()) {
            System.out.println(car);
        }
    }
}
