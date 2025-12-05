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
        return engineVolume == car.engineVolume &&
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
        private String firm;
        private float engineVolume;
        private int year;

        private CarBuilder() {
        }

        public CarBuilder firm(String firm) {
            this.firm = firm;
            return this;
        }

        public CarBuilder engineVolume(float engineVolume) {
            this.engineVolume = engineVolume;
            return this;
        }

        public CarBuilder year(int year) {
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
            if (Objects.isNull(this.firm) || this.firm.trim().isEmpty()) {
                throw new IllegalStateException("Firm (фирма) является обязательным полем и не может быть пустым.");
            }

            if (this.engineVolume <= 0) {
                throw new IllegalStateException("Engine volume (объем двигателя) должен быть положительным числом.");
            }

            final int currentYear = Year.now().getValue();
            final int earliestYear = 1885;

            if (this.year < earliestYear || this.year > currentYear) {
                throw new IllegalStateException("Year (год выпуска) должен быть в диапазоне от " + earliestYear + " до " + currentYear + ".");
            }

            return new Car(this);
        }
    }
}
