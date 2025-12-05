package ui;

import models.Car;
import strategy.SortStrategy;

import java.util.List;

public class SortCommand extends BaseCommand {
    private final SortStrategy<Car> strategy;
    private final String field;

    public SortCommand(CarStorage storage, SortStrategy<Car> strategy, String field) {
        super(storage);

        this.strategy = strategy;
        this.field = field;
    }

    @Override
    public void execute() {
        try {
            CarStorage storage = getStorage();
            List<Car> cars = storage.getCars();
            strategy.sort(cars);
            storage.setCars(cars);
            System.out.println("Сортировка по " + field + " выполнена!");
        } catch (Exception e) {
            System.err.println("Ошибка сортировки: " + e.getMessage());
        }
    }
}
