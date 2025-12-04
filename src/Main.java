import ui.*;

public class Main {
    public static void main(String[] args) {
        MenuCategory root = MenuCategory.create("Главное Меню",
                MenuCategory.create("Заполнить",
                        new MenuAction("Файл", () -> System.out.println("Ввод из файла")),
                        new MenuAction("Вручную", () -> System.out.println("Ввод вручную")),
                        new MenuAction("Рандом", () -> System.out.println("Ввод рандомный"))
                ),
                MenuCategory.create("Сортировка",
                        new MenuAction("По году", () -> System.out.println("Сортировка по годам")),
                        new MenuAction("По фирме", () -> System.out.println("Сортировка по мирме")),
                        new MenuAction("По литражу", () -> System.out.println("Сортировка1 по литражу"))
                ),
                new MenuAction("Вывод", () -> System.out.println("Вывод!"))
        );

        new MenuNavigator(root).run();
    }
}
