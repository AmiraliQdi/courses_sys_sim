package ir.ac.kntu.menu;

public class UsersSettingsMenu implements Menu{

    private static final UsersSettingsMenu instance = new UsersSettingsMenu();

    private UsersSettingsMenu() {

    }

    public static UsersSettingsMenu getInstance() {
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
