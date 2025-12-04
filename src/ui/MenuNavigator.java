package ui;

import java.util.Scanner;
import java.util.Stack;
import java.util.List;

public class MenuNavigator implements MenuVisitor {
    private MenuCategory currentCategory;
    private final MenuCategory root;

    private final Stack<MenuCategory> breadcrumbs = new Stack<>();
    private final Scanner scanner = new Scanner(System.in);
    private boolean isRunning = true;

    public MenuNavigator(MenuCategory root) {
        this.root = this.currentCategory = root;
    }

    public void run() {
        while (isRunning) {
            printMenu();
            handleInput();
        }
    }

    private void printMenu() {
        String title = breadcrumbs.empty() ? "Главное Меню" : currentCategory.getTitle();
        System.out.println("\n--- " + title + " ---");

        List<MenuItem> items = currentCategory.getChildren();
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            System.out.println((i + 1) + ". " + item.getTitle());
        }

        if (!breadcrumbs.isEmpty()) {
            System.out.println("0. Назад");
        } else {
            System.out.println("0. Выход");
        }
        System.out.print("> ");
    }

    private void handleInput() {
        try {
            String input = scanner.nextLine();
            int choice = Integer.parseInt(input);

            if (choice == 0) {
                goBack();
                return;
            }

            int index = choice - 1;
            List<MenuItem> items = currentCategory.getChildren();

            if (index < 0 || index >= items.size()) {
                System.out.println("Неверный пункт меню.");
                return;
            }

            MenuItem selected = items.get(index);
            selected.accept(this);
        } catch (NumberFormatException e) {
            System.out.println("Введите число.");
        }
    }

    private void goBack() {
        if (breadcrumbs.isEmpty()) {
            isRunning = false;
            System.out.println("Выход из программы.");
        } else {
            currentCategory = breadcrumbs.pop();
        }
    }

    @Override
    public void visit(MenuAction action) {
        action.doAction();
        if(action.isExitToRoot()) {
            breadcrumbs.clear();
            currentCategory = root;
        }
    }

    @Override
    public void visit(MenuCategory category) {
        breadcrumbs.push(currentCategory);
        currentCategory = category;
    }
}
