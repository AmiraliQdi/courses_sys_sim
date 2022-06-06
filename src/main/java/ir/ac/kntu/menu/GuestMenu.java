package ir.ac.kntu.menu;

public class GuestMenu implements Menu{

    private static GuestMenu instance = new GuestMenu();

    public static GuestMenu getInstance() {
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
