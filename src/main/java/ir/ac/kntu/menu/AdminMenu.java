package ir.ac.kntu.menu;

import ir.ac.kntu.util.ScannerWrapper;

public class AdminMenu implements Menu{

    private static AdminMenu instance = new AdminMenu();

    public static AdminMenu getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {
        OPTIONS.add("Tournaments");
        OPTIONS.add("Classes");
        OPTIONS.add("Question bank");
        OPTIONS.add("Users");
        OPTIONS.add("Back");
        printInteractMenu();
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        switch (input) {
            case "Tournaments" :
                return TournamentMenuAdmin.getInstance();
            case "Classes" :
                return ClassMenuAdmin.getInstance();
            case "Question_bank" :
                return QuestionBankMenuAdmin.getInstance();
            case "User" :
                return UsersSettingsMenu.getInstance();
            case "Back" :
                return Logging.getInstance();
            default:
                System.out.println("Wrong input!");
                return AdminMenu.getInstance();
        }
    }
}
