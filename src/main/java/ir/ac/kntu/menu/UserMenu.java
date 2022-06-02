package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.Class;
import ir.ac.kntu.storage.ClassesStorage;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;

public class UserMenu implements Menu{

    private static UserMenu instance = new UserMenu();

    private UserMenu(){

    }

    public static UserMenu getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {
        options.add("My info");
        options.add("My classes");
        options.add("Owned classes");
        options.add("Question bank");
        options.add("Logout");
        printInteractMenu();
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        switch (input) {
            case "My_info" :
                System.out.println(Main.loggedInUser);
                //MORE_CAN_BE_HERE(EDITING_MENU)!
                System.out.println("press any key");
                ScannerWrapper.getInstance().next();
                return UserMenu.getInstance();
            case "My_classes" :
                return ClassMenu.getInstance();
            case "Owned_classes" :
                ArrayList<Class> ownedClasses = ClassesStorage.classOfUserTeacher(Main.loggedInUser);
                System.out.println(ownedClasses);
                return TeacherMenu.getInstance();
            case "Question_bank" :
                Main.loggedInUser.setLastMenu(QuestionAnswerMenu.getInstance());
                return QuestionBankMenu.getInstance();
            case "Logout" :
                return Logging.getInstance();
            default:
                System.out.println("Wrong input");
                return UserMenu.getInstance();
        }
    }
}
