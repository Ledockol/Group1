package ui;

public class MenuAction extends MenuItem {
    private final Command command;
    private final boolean exitToRoot;

    public boolean isExitToRoot() {
        return exitToRoot;
    }

    public MenuAction(String title, Command command) {
        this(title, command, true);
    }

    public MenuAction(String title, Command command, boolean exitToRoot) {
        super(title);
        this.command = command;
        this.exitToRoot = exitToRoot;
    }

    public void doAction() {
        command.execute();
    }

    @Override
    void accept(MenuVisitor visitor) {
        visitor.visit(this);
    }
}
