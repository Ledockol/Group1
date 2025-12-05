package ui;

abstract class BaseCommand implements Command {
    private final CarStorage storage;

    protected CarStorage getStorage() {
        return storage;
    }

    public BaseCommand(CarStorage storage) {
        this.storage = storage;
    }
}
