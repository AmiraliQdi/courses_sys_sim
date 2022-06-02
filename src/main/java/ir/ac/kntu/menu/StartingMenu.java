package ir.ac.kntu.menu;

import ir.ac.kntu.storage.PrePrintedStrings;

public class StartingMenu implements Menu {

    private static StartingMenu instance = new StartingMenu();

    private StartingMenu(){
    }

    public static StartingMenu getInstance() {
        return instance;
    }

    @Override
    public Menu handleMenu(){
        return Logging.getInstance();
    }

    @Override
    public void printMenu(){
        printInfoMenu(PrePrintedStrings.STARTING_MENU);
    }
}
