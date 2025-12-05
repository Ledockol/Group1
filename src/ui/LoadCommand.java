package ui;

import models.Car;
import io.CarCsvRepository;

import java.io.IOException;
import java.util.*;

public class LoadCommand extends BaseCommand {
    public LoadCommand(CarStorage storage) {
        super(storage);
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Загрузка коллекции ---");
        System.out.print("Введите имя файла (например, cars.csv): ");
        String filename = scanner.nextLine().trim();

        if (filename.isEmpty()) {
            System.out.println("Ошибка: имя файла не может быть пустым.");
            return;
        }

        CarCsvRepository repository = new CarCsvRepository(filename);

        try {
            List<Car> loadedCars = repository.load();

            if (loadedCars.isEmpty()) {
                System.out.println("Файл пуст или не найден. Коллекция не изменена.");
                return;
            }

            getStorage().setCars(loadedCars);
            System.out.println("Успешно загружено объектов: " + loadedCars.size());
            System.out.println("Текущий список обновлен.");
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
