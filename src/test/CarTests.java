package test;
import models.Car;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class CarTests {

    private static DecimalFormat decimalFormat;

    static {

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ENGLISH);
        symbols.setDecimalSeparator('.');
        decimalFormat = new DecimalFormat("#.0", symbols);
    }

    public static void main(String[] args) {
        try {
            testBuilder();
            testEqualsAndHashCode();
            testCompareTo();
            testToString();
        } catch (Exception e) {
            System.out.println("Ошибка в тестах Builder: " + e.getMessage());
        }
    }

    private static void printResult(boolean success, String testName) {
        System.out.println(testName + ": " + (success ? "Пройден" : "НЕ пройден"));
    }

    public static void testBuilder() {
        Car car = Car.builder()
                .setFirm("Ford")
                .setYear(2020)
                .setEngineVolume(2.0f)
                .build();

        boolean success = car != null;
        success = success && car.getFirm().equals("Ford");
        success = success && car.getYear() == 2020;
        success = success && car.getEngineVolume() == 2.0f;

        printResult(success, "Тест билдера");
    }

    public static void testEqualsAndHashCode() {
        Car car1 = Car.builder()
                .setFirm("Ford")
                .setYear(2020)
                .setEngineVolume(2.0f)
                .build();

        Car car2 = Car.builder()
                .setFirm("Ford")
                .setYear(2020)
                .setEngineVolume(2.0f)
                .build();

        Car car3 = Car.builder()
                .setFirm("Ford")
                .setYear(2021)
                .setEngineVolume(2.0f)
                .build();

        boolean success = car1.equals(car1);
        success = success && car1.equals(car2);
        success = success && !car1.equals(car3);
        success = success && car1.hashCode() == car2.hashCode();

        printResult(success, "Тест equals и hashCode");
    }

    public static void testCompareTo() {
        Car car1 = Car.builder()
                .setFirm("Ford")
                .setYear(2020)
                .setEngineVolume(2.0f)
                .build();

        Car car2 = Car.builder()
                .setFirm("Ford")
                .setYear(2021)
                .setEngineVolume(2.0f)
                .build();

        Car car3 = Car.builder()
                .setFirm("BMW")
                .setYear(2020)
                .setEngineVolume(2.0f)
                .build();

        boolean success = car1.compareTo(car1) == 0;
        success = success && car1.compareTo(car2) < 0;
        success = success && car3.compareTo(car1) < 0;

        printResult(success, "Тест compareTo");
    }


    public static void testToString() {
        try {
            boolean success = true;

            Car car1 = createValidCar("Mercedes", 2021, 2.0f);
            String expected1 = "Автомобиль: 'Mercedes', Объем: 2.0л, Год: 2021";
            success = testCarString(car1, expected1);

            Car car2 = createValidCar("BMW", 2020, 1.987f);
            String expected2 = "Автомобиль: 'BMW', Объем: 2.0л, Год: 2020";
            success = success && testCarString(car2, expected2);

            Car car3 = createValidCar("Audi", 2022, 1.499f);
            String expected3 = "Автомобиль: 'Audi', Объем: 1.5л, Год: 2022";
            success = success && testCarString(car3, expected3);

            Car car4 = createValidCar("Toyota", 2023, 1.949f);
            String expected4 = "Автомобиль: 'Toyota', Объем: 1.9л, Год: 2023";
            success = success && testCarString(car4, expected4);

            printResult(success, "Тест ToString");
        } catch (Exception e) {
            System.out.println("Ошибка в тестах формата: " + e.getMessage());
            printResult(false, "Тест ToString");
        }
    }

    private static Car createValidCar(String firm, int year, float volume) {
        return Car.builder()
                .setFirm(firm)
                .setYear(year)
                .setEngineVolume(volume)
                .build();
    }

    private static boolean testCarString(Car car, String expected) {
        if (car == null) {
            System.out.println("Создан некорректный объект автомобиля");
            return false;
        }

        String formattedVolume = decimalFormat.format(car.getEngineVolume());
        String result = String.format("Автомобиль: '%s', Объем: %sл, Год: %d",
                car.getFirm(), formattedVolume, car.getYear());

        if (!result.equals(expected)) {
            System.out.println("Ожидалось: " + expected);
            System.out.println("Получено: " + result);
        }

        return result.equals(expected);
    }
}



