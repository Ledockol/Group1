package ui;

import io.CarCsvRepository;

import java.io.IOException;

import java.util.Scanner;

public class SaveCommand extends BaseCommand {
    public SaveCommand(CarStorage storage) {
        super(storage);
    }

    @Override
    public void execute() {
        System.out.println("--- Сохранение коллекции ---");

        if (getStorage().isEmpty()) {
            System.out.println("Ошибка: Текущая коллекция пуста. Нечего сохранять.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя файла для сохранения (данные будут дописаны в конец): ");
        String filename = scanner.nextLine().trim();

        if (filename.isEmpty()) {
            System.out.println("Ошибка: имя файла не может быть пустым.");
            return;
        }

        CarCsvRepository repository = new CarCsvRepository(filename);

        try {
            repository.save(getStorage().getCars());
            System.out.println("Данные успешно дописаны в файл: " + filename);
        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода при сохранении: " + e.getMessage());
        }
    }
}
