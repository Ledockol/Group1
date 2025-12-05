import strategy.*;
import ui.*;

import java.util.ArrayList;

public class Main {

    private static SortContext<Car> sortContext = new SortContext<>();
    private static List<Car> items = new ArrayList<>();

    public static void main(String[] args) {
        MenuCategory root = MenuCategory.create("Главное Меню",
                MenuCategory.create("Заполнить",
                        new MenuAction("Файл", () -> System.out.println("Ввод из файла")),
                        new MenuAction("Вручную", () -> System.out.println("Ввод вручную")),
                        new MenuAction("Рандом", () -> System.out.println("Ввод рандомный"))
                ),
                MenuCategory.create("Сортировка",
                        new MenuAction("По году -> Фирме -> Литражу", () -> sortByYear()),
                        new MenuAction("По фирме -> Году -> Литражу", () -> sortByFirm()),
                        new MenuAction("По литражу -> Фирме -> Году", () -> sortByEngineVolume())
                ),
                new MenuAction("Вывод", () -> printCars())
        );

        new MenuNavigator(root).run();
    }

    private static void sortByYear() {
        try {
            sortContext.setStrategy(new YearFirmVolumeSortStrategy());
            sortContext.doSort(items);
            System.out.println("Сортировка по году выполнена!");
        } catch (Exception e) {
            System.err.println("Ошибка сортировки: " + e.getMessage());
        }
    }

    private static void sortByFirm() {
        try {
            sortContext.setStrategy(new FirmYearVolumeSortStrategy());
            sortContext.doSort(items);
            System.out.println("Сортировка по фирме выполнена!");
        } catch (Exception e) {
            System.err.println("Ошибка сортировки: " + e.getMessage());
        }
    }

    private static void sortByEngineVolume() {
        try {
            sortContext.setStrategy(new EngineVolumeFirmYearSortStrategy());
            sortContext.doSort(items);
            System.out.println("Сортировка по литражу выполнена!");
        } catch (Exception e) {
            System.err.println("Ошибка сортировки: " + e.getMessage());
        }
    }

    private static void printCars() {
        for (Car car : items) {
            System.out.println(car);
        }
    }
}
