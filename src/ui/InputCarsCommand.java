package ui;

import io.CarInput;

import java.util.Scanner;

public class InputCarsCommand extends BaseCommand {
    public InputCarsCommand(CarStorage storage) {
        super(storage);
    }

    @Override
    public void execute() {
        System.out.println("--- Ручной ввод автомобилей ---");

        var scanner = new Scanner(System.in);

        var cars = getStorage().getCars();

        while (true) {
            System.out.println("Введите данные нового автомобиля:");

            var targetCar = new CarInput().inputCar();

            if (targetCar != null) {
                cars.add(targetCar);
                System.out.println("Автомобиль успешно добавлен в список.");
            } else {
                System.out.println("Ввод этого автомобиля был отменен.");
            }

            System.out.print("Прекратить ввод? (да/нет): ");
            String answer = scanner.nextLine().trim().toLowerCase();

            if (answer.equals("да") || answer.equals("y") || answer.equals("yes")) {
                break;
            }
        }

        getStorage().setCars(cars);
        System.out.println("--- Ввод завершен. Всего машин в базе: " + cars.size() + " ---");
    }
}
