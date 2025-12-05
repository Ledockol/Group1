import strategy.*;
import strategy.Even.VolumeEvenSortStrategy;
import strategy.Even.YearEvenSortStrategy;
import ui.*;

public class Main {

    private static final Context context = new Context();

    public static void main(String[] args) {
        MenuCategory root = MenuCategory.create("Главное Меню",
                MenuCategory.create("Заполнить",
                        new MenuAction("Файл", new LoadCommand(context)),
                        new MenuAction("Вручную", new InputCarsCommand(context)),
                        new MenuAction("Рандом", new GenerateCarsCommand(context))
                ),
                MenuCategory.create("Сортировка",
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
                        ),
                        new MenuAction(
                                "По литражу (четные)",
                                new SortCommand(context, new VolumeEvenSortStrategy(), "литражу (четные)")
                        )
                ),
                MenuCategory.create("Вывод",
                        new MenuAction("Консоль", new PrintCommand(context)),
                        new MenuAction("Файл", new SaveCommand(context))
                )
        );

        new MenuNavigator(root).run();
    }
}
