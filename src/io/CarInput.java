package io;

import models.Car;

import java.util.Scanner;

public class CarInput {

    public Car inputCar() {
        var scanner = new Scanner(System.in);

        Car.CarBuilder builder = Car.builder();

        System.out.println("--- Ввод параметров Автомобиля ---");

        while (true) {
            System.out.print("Введите Модель (например, BMW): ");
            String rawFirm = scanner.nextLine().trim();
            try {
                builder.setFirm(rawFirm);
                break;
            } catch (IllegalArgumentException e) {
                System.err.println("Ошибка: " + e.getMessage());
                System.out.println("Попробуйте еще раз.");
            }
        }

        while (true) {
            System.out.print("Введите объем двигателя (л): ");
            String rawVolume = scanner.nextLine().trim();
            try {
                float volume = Float.parseFloat(rawVolume);
                builder.setEngineVolume(volume);
                break;
            } catch (NumberFormatException e) {
                System.err.println("Ошибка: Введите корректное число (например, 2.5).");
            } catch (IllegalArgumentException e) {
                System.err.println("Ошибка логики: " + e.getMessage());
            }
        }

        while (true) {
            System.out.print("Введите Год производства: ");
            String rawYear = scanner.nextLine().trim();
            try {
                int year = Integer.parseInt(rawYear);
                builder.setYear(year);
                break;
            } catch (NumberFormatException e) {
                System.err.println("Ошибка: Год должен быть целым числом.");
            } catch (IllegalArgumentException e) {
                System.err.println("Ошибка логики: " + e.getMessage());
            }
        }

        return builder.build();
    }
}
