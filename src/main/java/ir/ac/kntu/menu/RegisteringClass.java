package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.storage.ClassesStorage;
import ir.ac.kntu.util.ScannerWrapper;

public class RegisteringClass implements Menu{

    private static RegisteringClass instance = new RegisteringClass();

    private RegisteringClass(){

    }

    public static RegisteringClass getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {
        options.add("1)Class name");
        options.add("2)Class educational institution");
        options.add("3)Class teacher name");
        printInteractMenu();
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        switch (input) {
            case "Class_name" :
                System.out.println("Enter class name ");
                input = ScannerWrapper.getInstance().next();
                if (ClassesStorage.findClassByName(input) == null){
                    System.out.println("There is not Class with that name");
                } else {
                    ClassesStorage.register(ClassesStorage.findClassByName(input), Main.getCustomer());
                }
                return RegisteringClass.getInstance();
            case "Class_educational_institution" :
                System.out.println("Enter class educational institution name ");
                input = ScannerWrapper.getInstance().next();
                if (ClassesStorage.findByEI(input) == null){
                    System.out.println("There is not Class with that name");
                } else {
                    ClassesStorage.register(ClassesStorage.findByEI(input), Main.getCustomer());
                }
                return RegisteringClass.getInstance();
            case "Back" :
                return Main.getCustomer().getLastMenu();
            default:
                System.out.println("Wrong input");
                return RegisteringClass.getInstance();
        }
    }
}
