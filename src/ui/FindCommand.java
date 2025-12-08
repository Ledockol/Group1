package ui;

import io.CarInput;
import models.Car;

import java.util.Scanner;
import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;

public class FindCommand extends BaseCommand {
    public FindCommand(CarStorage storage) {
        super(storage);
    }

    @Override
    public void execute() {
        System.out.println("--- Поиск автомобиля ---");

        List<Car> carList = getStorage().getCars();

        if (carList.isEmpty()) {
            System.out.println("Хранилище пусто. Поиск невозможен.");
            return;
        }

        Car targetCar = new CarInput().inputCar();

        if (targetCar == null) {
            System.out.println("Поиск отменен из-за неверного ввода.");
            return;
        }

        var scanner = new Scanner(System.in);

        System.out.println("Введите количество потоков: ");

        int threads = -1;

        while (true) {
            try {
                threads = scanner.nextInt();
                if (threads <= 0 || threads > 128) {
                    System.err.println("Ошибка: Количество потоков должно быть > 0 и <= 128.");
                }
                break;
            } catch (NumberFormatException e) {
                System.err.println("Ошибка: Количество потоков должно быть целым числом.");
            }
        }

        System.out.println("Запуск многопоточного поиска...");
        int count = find(carList, targetCar, threads);

        System.out.printf("Элемент %s найден %d раз(а) в коллекции.", targetCar, count);
    }

    private int find(List<Car> list, Car target, int threads) {
        if (list.isEmpty()) return 0;

        ExecutorService executor = Executors.newFixedThreadPool(threads);
        int chunkSize = (int) Math.ceil((double) list.size() / threads);

        List<Future<Integer>> results = new ArrayList<>();

        for (int i = 0; i < threads; i++) {
            final int start = i * chunkSize;
            final int end = Math.min(start + chunkSize, list.size());

            if (start >= list.size()) break;

            Callable<Integer> task = () -> {
                int count = 0;

                for (int j = start; j < end; j++) {

                    if (list.get(j).compareTo(target) == 0) {
                        count++;
                    }
                }
                return count;
            };
            results.add(executor.submit(task));
        }

        int total = 0;
        try {
            for (Future<Integer> result : results) {
                total += result.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Ошибка выполнения многопоточной задачи: " + e.getMessage());
        } finally {
            executor.shutdown();
        }

        return total;
    }
}
