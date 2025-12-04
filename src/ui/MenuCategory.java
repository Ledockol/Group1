package ui;

import java.util.List;

public class MenuCategory extends MenuItem {
    private final List<MenuItem> children;

    public MenuCategory(String title, List<MenuItem> children) {
        super(title);
        this.children = children;
    }

    public List<MenuItem> getChildren() {
        return children;
    }

    @Override
    void accept(MenuVisitor visitor) {
        visitor.visit(this);
    }

    public static MenuCategory create(String title, MenuItem... items) {
        return new MenuCategory(title, List.of(items));
    }
}
