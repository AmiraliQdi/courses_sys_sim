package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.Class;
import ir.ac.kntu.storage.ClassesStorage;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;

public class CustomerMenu implements Menu{

    private static CustomerMenu instance = new CustomerMenu();

    private CustomerMenu(){

    }

    public static CustomerMenu getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {
        OPTIONS.add("My info");
        OPTIONS.add("My classes");
        OPTIONS.add("Owned classes");
        OPTIONS.add("Tournaments");
        OPTIONS.add("Question bank");
        OPTIONS.add("Logout");
        printInteractMenu();
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        switch (input) {
            case "Tournaments" :
                return TournamentsMenu.getInstance();
            case "My_info" :
                System.out.println(Main.getCustomer());
                //MORE_CAN_BE_HERE(EDITING_MENU)!
                System.out.println("press any key");
                ScannerWrapper.getInstance().next();
                return CustomerMenu.getInstance();
            case "My_classes" :
                return ClassMenu.getInstance();
            case "Owned_classes" :
                ArrayList<Class> ownedClasses = ClassesStorage.classOfUserTeacher(Main.getCustomer());
                System.out.println(ownedClasses);
                return TeacherMenu.getInstance();
            case "Question_bank" :
                Main.getCustomer().setLastMenu(QuestionAnswerMenu.getInstance());
                return QuestionBankMenu.getInstance();
            case "Logout" :
                return Logging.getInstance();
            default:
                System.out.println("Wrong input");
                return CustomerMenu.getInstance();
        }
    }
}
