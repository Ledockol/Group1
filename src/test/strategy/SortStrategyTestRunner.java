package test.strategy;


public class SortStrategyTestRunner {
    public static void main(String[] args) {

        testEngineVolumeStrategy();
        testFirmYearStrategy();
        testYearFirmStrategy();
        testYearEvenStrategy();
    }

    private static void testEngineVolumeStrategy() {
        EngineVolumeFirmYearSortStrategyTest test = new EngineVolumeFirmYearSortStrategyTest();
        try {
            test.init();
            test.testSortByVolume();
            System.out.println("EngineVolumeFirmYearSortStrategy тесты пройдены");
        } catch (Exception e) {
            handleTestError(e, "EngineVolumeFirmYearSortStrategy");
        }
    }

    private static void testFirmYearStrategy() {
        FirmYearVolumeSortStrategyTest test = new FirmYearVolumeSortStrategyTest();
        try {
            test.init();
            test.testSortByYear();
            System.out.println("FirmYearVolumeSortStrategy тесты пройдены");
        } catch (Exception e) {
            handleTestError(e, "FirmYearVolumeSortStrategy");
        }
    }

    private static void testYearFirmStrategy() {
        YearFirmVolumeSortStrategyTest test = new YearFirmVolumeSortStrategyTest();
        try {
            test.setUp();
            test.testSortByYear();
            test.testSortByFirm();
            test.testSortByVolume();
            System.out.println("YearFirmVolumeSortStrategy тесты пройдены");
        } catch (Exception e) {
            handleTestError(e, "YearFirmVolumeSortStrategy");
        }
    }

    private static void testYearEvenStrategy() {
        YearEvenSortStrategyTest test = new YearEvenSortStrategyTest();
        try {
            new YearEvenSortStrategyTest();
            System.out.println("YearEvenSortStrategy тесты пройдены");
        } catch (Exception e) {
            handleTestError(e, "YearEvenSortStrategy");
        }
    }

    private static void handleTestError(Exception e, String strategyName) {
        System.err.println("Ошибка в тестах " + strategyName + ": " + e.getMessage());
        System.err.println("Тест не пройден");
        System.exit(1);
    }
}
