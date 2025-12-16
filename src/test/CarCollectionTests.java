package test;

import models.Car;
import inputoutput.CarCollection;
import java.util.Arrays;
import java.util.List;

public class CarCollectionTests {
    private CarCollection collection;
    private Car car1;
    private Car car2;
    private Car car3;
    private Car car4;
    private Car car5;
    private int successCount = 0;

    public void setUp() {
        car1 = Car.builder()
                .setFirm("BMW")
                .setYear(2020)
                .setEngineVolume(2.0f)
                .build();

        car2 = Car.builder()
                .setFirm("Toyota")
                .setYear(2019)
                .setEngineVolume(1.8f)
                .build();

        car3 = Car.builder()
                .setFirm("Ford")
                .setYear(2021)
                .setEngineVolume(2.5f)
                .build();

        car4 = Car.builder()
                .setFirm("BMW")
                .setYear(2022)
                .setEngineVolume(3.0f)
                .build();

        car5 = Car.builder()
                .setFirm("Ford")
                .setYear(2023)
                .setEngineVolume(2.0f)
                .build();

        collection = new CarCollection();
        collection.add(car1);
        collection.add(car2);
        collection.add(car3);
        collection.add(car4);
        collection.add(car5);
    }

    public void testSortByYear() {
        CarCollection sorted = collection.sortByYear();
        checkYearSorting(sorted);
        printSuccess("testSortByYear");
    }

    private void checkYearSorting(CarCollection collection) {
        if (collection.size() < 2) return;

        for (int i = 0; i < collection.size() - 1; i++) {
            if (collection.get(i).getYear() > collection.get(i + 1).getYear()) {
                throw new RuntimeException("Ошибка сортировки по году: " +
                        collection.get(i).getYear() + " > " + collection.get(i + 1).getYear());
            }
        }
    }

    public void testSortByVolume() {
        CarCollection sorted = collection.sortByVolume();
        checkVolumeSorting(sorted);
        printSuccess("testSortByVolume");
    }

    private void checkVolumeSorting(CarCollection collection) {
        if (collection.size() < 2) return;

        for (int i = 0; i < collection.size() - 1; i++) {
            if (collection.get(i).getEngineVolume() > collection.get(i + 1).getEngineVolume()) {
                throw new RuntimeException("Ошибка сортировки по объему: " +
                        collection.get(i).getEngineVolume() + " > " + collection.get(i + 1).getEngineVolume());
            }
        }
    }

    public void testSortByFirm() {
        CarCollection sorted = collection.sortByFirm();
        checkFirmSorting(sorted);
        printSuccess("testSortByFirm");
    }

    private void checkFirmSorting(CarCollection collection) {
        if (collection.size() < 2) return;

        for (int i = 0; i < collection.size() - 1; i++) {
            if (collection.get(i).getFirm().compareTo(collection.get(i + 1).getFirm()) > 0) {
                throw new RuntimeException("Ошибка сортировки по фирме: " +
                        collection.get(i).getFirm() + " > " + collection.get(i + 1).getFirm());
            }
        }
    }
    public void testAdd() {
        Car newCar = Car.builder()
                .setFirm("Mercedes")
                .setYear(2024)
                .setEngineVolume(2.2f)
                .build();

        collection.add(newCar);
        if (!collection.contains(newCar)) {
            throw new RuntimeException("Ошибка добавления элемента");
        }
        if (collection.size() != 6) {
            throw new RuntimeException("Ошибка размера коллекции после добавления");
        }
        printSuccess("testAdd");
    }

    public void testAddNull() {
        try {
            collection.add(null);
            throw new RuntimeException("Тест не пройден: не было выброшено исключение при добавлении null");
        } catch (NullPointerException e) {
            printSuccess("testAddNull");
        }
    }

    public void testAddAll() {
        List<Car> newCars = Arrays.asList(
                Car.builder().setFirm("Audi").setYear(2024).setEngineVolume(2.0f).build(),
                Car.builder().setFirm("Volvo").setYear(2023).setEngineVolume(2.5f).build()
        );

        int initialSize = collection.size(); // Сохраняем начальный размер

        collection.addAll(newCars);

        if (collection.size() != initialSize + newCars.size()) {
            throw new RuntimeException("Ошибка размера коллекции после добавления всех. " +
                    "Ожидалось: " + (initialSize + newCars.size()) +
                    ", получено: " + collection.size());
        }

        for (Car car : newCars) {
            if (!collection.contains(car)) {
                throw new RuntimeException("Элемент " + car.getFirm() +
                        " не найден в коллекции после добавления всех");
            }
        }

        printSuccess("testAddAll");
    }

    public void testAddAllNull() {
        try {
            collection.addAll(null);
            throw new RuntimeException("Тест не пройден: не было выброшено исключение при добавлении null коллекции");
        } catch (NullPointerException e) {
            printSuccess("testAddAllNull");
        }
    }

    public void testFilterByYear() {
        CarCollection result = collection.filterByYear(2020, 2022);
        checkFilterByYear(result, 2020, 2022);
        printSuccess("testFilterByYear");
    }

    private void checkFilterByYear(CarCollection collection, int minYear, int maxYear) {
        for (Car car : collection) {
            if (car.getYear() < minYear || car.getYear() > maxYear) {
                throw new RuntimeException("Ошибка фильтрации по году: " +
                        car.getYear() + " не входит в диапазон [" + minYear + ", " + maxYear + "]");
            }
        }
    }

    public void testFilterByVolume() {
        CarCollection result = collection.filterByVolume(2.0f, 3.0f);
        checkFilterByVolume(result, 2.0f, 3.0f);
        printSuccess("testFilterByVolume");
    }

    private void checkFilterByVolume(CarCollection collection, float minVolume, float maxVolume) {
        for (Car car : collection) {
            if (car.getEngineVolume() < minVolume || car.getEngineVolume() > maxVolume) {
                throw new RuntimeException("Ошибка фильтрации по объему: " +
                        car.getEngineVolume() + " не входит в диапазон [" + minVolume + ", " + maxVolume + "]");
            }
        }
    }

    public void testFilterByFirm() {
        CarCollection result = collection.filterByFirm("BMW");
        checkFilterByFirm(result, "BMW");
        printSuccess("testFilterByFirm");
    }

    private void checkFilterByFirm(CarCollection collection, String firm) {
        for (Car car : collection) {
            if (!car.getFirm().equalsIgnoreCase(firm)) {
                throw new RuntimeException("Ошибка фильтрации по фирме: " +
                        car.getFirm() + " не соответствует " + firm);
            }
        }
    }

    public void testEmptyCollection() {
        CarCollection empty = new CarCollection();
        if (empty.size() != 0) {
            throw new RuntimeException("Ошибка создания пустой коллекции");
        }
        printSuccess("testEmptyCollection");
    }

    private void printSuccess(String testName) {
        System.out.println("Тест " + testName + " пройден успешно");
        successCount++;
    }


    public void runAllTests() {
        try {
            setUp();
            testSortByYear();
            testSortByVolume();
            testSortByFirm();
            testAdd();
            testAddNull();
            testAddAll();
            testAddAllNull();
            testFilterByYear();
            testFilterByVolume();
            testFilterByFirm();
            testEmptyCollection();
            testBoundaryValues();
            testEmptyFilter();

        } catch (Exception e) {
            System.err.println("Ошибка при выполнении тестов: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void testBoundaryValues() {
        CarCollection minYear = collection.filterByYear(2019, 2019);
        if (minYear.size() != 1) {
            throw new RuntimeException("Ошибка проверки минимального года");
        }

        CarCollection maxVolume = collection.filterByVolume(3.0f, 3.0f);
        if (maxVolume.size() != 1) {
            throw new RuntimeException("Ошибка проверки максимального объема");
        }

        printSuccess("testBoundaryValues");
    }

    public void testEmptyFilter() {
        CarCollection emptyYear = collection.filterByYear(3000, 4000);
        if (!emptyYear.isEmpty()) {
            throw new RuntimeException("Ошибка проверки пустого фильтра по году");
        }

        CarCollection emptyVolume = collection.filterByVolume(5.0f, 6.0f);
        if (!emptyVolume.isEmpty()) {
            throw new RuntimeException("Ошибка проверки пустого фильтра по объему");
        }

        CarCollection emptyFirm = collection.filterByFirm("Неизвестная фирма");
        if (!emptyFirm.isEmpty()) {
            throw new RuntimeException("Ошибка проверки пустого фильтра по фирме");
        }

        printSuccess("testEmptyFilter");
    }


    public static void main(String[] args) {
        CarCollectionTests tests = new CarCollectionTests();
        tests.runAllTests();
    }
}