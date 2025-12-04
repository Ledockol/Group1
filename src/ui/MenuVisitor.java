package ui;

interface MenuVisitor {
    void visit(MenuAction action);

    void visit(MenuCategory submenu);
}
