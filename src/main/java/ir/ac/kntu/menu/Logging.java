package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.User;
import ir.ac.kntu.storage.UsersStorage;
import ir.ac.kntu.util.ScannerWrapper;
import ir.ac.kntu.recourses.userTypes.Customer;
import ir.ac.kntu.recourses.userTypes.Guest;

public class Logging implements Menu{

    private static Logging instance = new Logging();

    private Logging(){

    }

    public static Logging getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {
        OPTIONS.add("Login");
        OPTIONS.add("SignUp");
        OPTIONS.add("Continue as guest");
        OPTIONS.add("Exit");
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
                    Main.setLoggedInUser(foundedUser);
                    return returnMenu(foundedUser);
                }
            } else {
                System.out.println("Wrong user name!");
                return Logging.getInstance();
            }
        } else if (input.equals("SignUp")) {
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
            User newUser = new Customer(name, username, email, password, nationalNumber, phoneNumber);
            UsersStorage.addUser(newUser);
            Main.setLoggedInUser(newUser);
            return CustomerMenu.getInstance();
        } else if (input.equals("Continue_as_guest")) {
            Main.setLoggedInUser(new Guest("guest","#","#","#","#","#"));
        } else if (input.equals("Exit")){
            System.exit(0);
        } else {
            System.out.println("Wrong input");
            return Logging.getInstance();
        }
        return Logging.getInstance();
    }

    private Menu returnMenu(User foundedUser){
        switch (foundedUser.getUserType()){
            case ADMIN -> {
                return AdminMenu.getInstance();
            }
            case CUSTOMER -> {
                return CustomerMenu.getInstance();
            }
            default -> {
                return GuestMenu.getInstance();
            }
        }
    }
}
