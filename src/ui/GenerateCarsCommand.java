package ui;

import io.RandomCar;
import models.Car;
import java.util.List;
import java.util.Scanner;

public class GenerateCarsCommand extends BaseCommand {

    public GenerateCarsCommand(CarStorage storage) {
        super(storage);
    }

    @Override
    public void execute() {
        try {
            CarStorage storage = getStorage();

            Scanner scanner = new Scanner(System.in); // или используем существующий, если есть

            System.out.println("Введите количество автомобилей для генерации:");

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                throw new IllegalArgumentException("Ввод не может быть пустым");
            }

            int count = Integer.parseInt(input);

            List<Car> cars = RandomCar.generateRandomCars(count);

            storage.setCars(cars);

            System.out.println("Сгенерировано " + cars.size() + " автомобилей");
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: введите корректное число");
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Произошла ошибка при генерации автомобилей: " + e.getMessage());
        }
    }
}
