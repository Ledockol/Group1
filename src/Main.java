import strategy.*;
import strategy.Even.YearEvenSortStrategy;
import ui.*;

public class Main {

    private static final Context context = new Context();

    public static void main(String[] args) {
        MenuCategory fillMenu = MenuCategory.create("Заполнить",
                new MenuAction("Файл", new LoadCommand(context)),
                new MenuAction("Вручную", new InputCarsCommand(context)),
                new MenuAction("Рандом", new GenerateCarsCommand(context))
        );

        MenuCategory sortMenu = MenuCategory.create("Сортировка",
                new MenuAction(
                        "По году -> Фирме -> Литражу",
                        new SortCommand(context, new YearFirmVolumeSortStrategy(), "году")
                ),
                new MenuAction(
                        "По фирме -> Году -> Литражу",
                        new SortCommand(context, new FirmYearVolumeSortStrategy(), "фирме")
                ),
                new MenuAction(
                        "По литражу -> Фирме -> Году",
                        new SortCommand(context, new EngineVolumeFirmYearSortStrategy(), "по литражу")
                ),
                new MenuAction(
                        "По году (четные)",
                        new SortCommand(context, new YearEvenSortStrategy(), "году (четные)")
                )
        );

        MenuCategory printMenu = MenuCategory.create("Вывод",
                new MenuAction("Консоль", new PrintCommand(context)),
                new MenuAction("Файл", new SaveCommand(context))
        );

        MenuCategory rootMenu = MenuCategory.create("Главное Меню",
                fillMenu,
                sortMenu,
                printMenu
        );

        new MenuNavigator(rootMenu).run();
    }
}
