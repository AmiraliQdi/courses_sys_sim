package ir.ac.kntu.menu;

public class AdminMenu implements Menu{

    private static AdminMenu instance = new AdminMenu();

    public static AdminMenu getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {

    }

    @Override
    public Menu handleMenu() {
        return null;
    }
}
