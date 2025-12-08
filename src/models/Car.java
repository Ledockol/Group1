package models;

import java.util.Objects;
import java.time.Year;

public final class Car {

    private final String firm;
    private final float engineVolume;
    private final int year;

    private Car(CarBuilder builder) {
        this.firm = builder.firm;
        this.engineVolume = builder.engineVolume;
        this.year = builder.year;
    }

    public String getFirm() {
        return firm;
    }

    public float getEngineVolume() {
        return engineVolume;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Float.compare(engineVolume, car.engineVolume) == 0 &&
                year == car.year &&
                Objects.equals(firm, car.firm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firm, engineVolume, year);
    }

    @Override
    public String toString() {
        return String.format("Car{firm='%s', volume=%.1f, year=%d}", firm, engineVolume, year);
    }

    public static CarBuilder builder() {
        return new CarBuilder();
    }

    public static class CarBuilder {
        final static int CAR_INVENTION_YEAR = 1885;

        private String firm;
        private Float engineVolume;
        private Integer year;

        private CarBuilder() {
        }

        public CarBuilder setFirm(String firm) {
            if (firm == null || firm.trim().isEmpty()) {
                throw new IllegalArgumentException("Firm (фирма) является обязательным полем и не может быть пустым.");
            }
            this.firm = firm;
            return this;
        }

        public CarBuilder setEngineVolume(float engineVolume) {
            if (engineVolume <= 0) {
                throw new IllegalArgumentException("Engine volume (объем двигателя) должен быть положительным числом.");
            }
            this.engineVolume = engineVolume;
            return this;
        }

        public CarBuilder setYear(int year) {
            int currentYear = Year.now().getValue();
            if (year < CAR_INVENTION_YEAR || year > currentYear) {
                throw new IllegalArgumentException("Year (год выпуска) должен быть в диапазоне от " + CAR_INVENTION_YEAR + " до " + currentYear + ".");
            }
            this.year = year;
            return this;
        }

        /**
         * Создает объект models.Car, выполняя перед этим валидацию данных.
         *
         * @return новый объект models.Car
         * @throws IllegalStateException если данные не прошли валидацию (например, фирма пуста или объем <= 0)
         */
        public Car build() {
            if (this.firm == null) {
                throw new IllegalStateException("Невозможно создать автомобиль: поле 'firm' не установлено.");
            }
            if (this.engineVolume == null) {
                throw new IllegalStateException("Невозможно создать автомобиль: поле 'engineVolume' не установлено.");
            }
            if (this.year == null) {
                throw new IllegalStateException("Невозможно создать автомобиль: поле 'year' не установлено.");
            }

            return new Car(this);
        }
    }
}
