package io;

import models.Car;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс для работы с CSV файлом для объектов Car.
 * Формат CSV: Firm,EngineVolume,Year
 */
public class CarCsvRepository {

    private final Path filePath;
    private static final String DELIMITER = ";";

    public CarCsvRepository(String filename) {
        this.filePath = Path.of(filename);
    }

    /**
     * Сохраняет коллекцию автомобилей в файл.
     *
     * @param cars коллекция автомобилей для сохранения
     * @throws IOException если возникла ошибка ввода-вывода
     */
    public void save(Collection<Car> cars) throws IOException {
        if (cars == null || cars.isEmpty()) {
            return;
        }

        if (filePath.getParent() != null) {
            Files.createDirectories(filePath.getParent());
        }

        try (BufferedWriter writer = Files.newBufferedWriter(
                filePath,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            for (Car car : cars) {
                writer.write(convertToCsv(car));
                writer.newLine();
            }
        }
    }

    /**
     * Загружает автомобили из файла.
     *
     * @return Список уникальных автомобилей
     * @throws IOException если возникла ошибка чтения
     */
    public List<Car> load() throws IOException {
        if (!Files.exists(filePath)) {
            return Collections.emptyList();
        }

        try (Stream<String> lines = Files.lines(filePath, StandardCharsets.UTF_8)) {
            return lines
                    .filter(line -> !line.isBlank())
                    .map(line -> {
                        try {
                            return parseFromCsv(line);
                        } catch (IllegalStateException | NumberFormatException | IndexOutOfBoundsException e) {
                            System.err.println("Ошибка при чтении строки: " + line + ". Причина: " + e.getMessage());
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
    }

    private String convertToCsv(Car car) {
        return String.join(DELIMITER,
                car.getFirm(),
                String.format("%.1f", car.getEngineVolume()),
                String.valueOf(car.getYear())
        );
    }

    private Car parseFromCsv(String line) {
        String[] parts = line.split(DELIMITER);

        if (parts.length < 3) {
            throw new IllegalStateException("Неверный формат CSV строки");
        }

        String firm = parts[0].trim();
        float engineVolume = Float.parseFloat(parts[1].trim());
        int year = Integer.parseInt(parts[2].trim());

        return Car.builder()
                .setFirm(firm)
                .setEngineVolume(engineVolume)
                .setYear(year)
                .build();
    }
}
