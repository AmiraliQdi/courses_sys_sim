package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.User;
import ir.ac.kntu.storage.PrePrintedStrings;
import ir.ac.kntu.storage.UsersStorage;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.util.userTypes.LoggedUser;

import java.util.ArrayList;

public class Logging implements Menu{

    private static Logging instance = new Logging();

    private Logging(){

    }

    public static Logging getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {
        options.add("Login");
        options.add("SignUp");
        printInteractMenu();
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        if (input.equals("Login")){
            System.out.println("Enter your username : ");
            String username = ScannerWrapper.getInstance().next();
            if (UsersStorage.findByName(username)!=null){
                System.out.println("Enter your password : ");
                String password = ScannerWrapper.getInstance().next();
                User foundedUser = UsersStorage.findByName(username);
                if (foundedUser.getPassword().equals(password)){
                    Main.loggedInUser = (LoggedUser) foundedUser;
                    return UserMenu.getInstance();
                } else {
                    System.out.println("Wrong Password!");
                    return Logging.getInstance();
                }
            }
        } else if (input.equals("SignUp")){
            System.out.println("Enter your username :");
            String username = ScannerWrapper.getInstance().next();
            System.out.println("Enter your National number : ");
            String nationalNumber = ScannerWrapper.getInstance().next();
            System.out.println("Enter your email");
            String email = ScannerWrapper.getInstance().next();
            System.out.println("Enter your phoneNumber : ");
            String phoneNumber = ScannerWrapper.getInstance().next();
            System.out.println("Enter your name : ");
            String name = ScannerWrapper.getInstance().next();
            System.out.println("Enter your password :");
            String password = ScannerWrapper.getInstance().next();
            User newUser = new User(name,username,email,password,nationalNumber,phoneNumber);
            UsersStorage.addUser(newUser);
            Main.loggedInUser = (LoggedUser) newUser;
            return UserMenu.getInstance();
        } else {
            System.out.println("Wrong input");
            return Logging.getInstance();
        }
        return Logging.getInstance();
    }
}
