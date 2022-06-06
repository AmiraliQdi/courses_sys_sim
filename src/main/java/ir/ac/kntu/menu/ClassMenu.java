package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.Class;
import ir.ac.kntu.storage.ClassesStorage;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;

public class ClassMenu implements Menu{

    private static ClassMenu instance = new ClassMenu();

    private ClassMenu(){
    }

    public static ClassMenu getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {

        options.add("See_my_classes");
        options.add("Register_in_new_class");
        options.add("Back");
        printInteractMenu();

    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        switch (input){
            case "See_my_classes" :
                ArrayList<Class> myClasses = ClassesStorage.classOfUser(Main.getCustomer());
                Main.getCustomer().setJoinedClass(myClasses);
                return StudentMenu.getInstance();
            case "Register_in_new_class" :
                Main.getCustomer().setLastMenu(ClassMenu.getInstance());
                return RegisteringClass.getInstance();
            case "Back" :
                return UserMenu.getInstance();
            default:
                System.out.println("Wrong input");
                return ClassMenu.getInstance();
        }
    }

}
