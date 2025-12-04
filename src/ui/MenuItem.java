package ui;

public abstract class MenuItem {
    private final String title;

    public MenuItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    abstract void accept(MenuVisitor visitor);
}
