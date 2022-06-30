package ir.ac.kntu.menu;

import ir.ac.kntu.recourses.User;
import ir.ac.kntu.recourses.userTypes.UserType;
import ir.ac.kntu.storage.PrePrintedStrings;
import ir.ac.kntu.storage.UsersStorage;
import ir.ac.kntu.util.ScannerWrapper;

public class UsersSettingsMenu implements Menu{

    private static final UsersSettingsMenu instance = new UsersSettingsMenu();

    private UsersSettingsMenu() {

    }

    public static UsersSettingsMenu getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {
        System.out.println();
        System.out.println("List of all students");
        for (User user : UsersStorage.getUsers()){
            System.out.println(user);
        }
        System.out.println();
        System.out.println("Enter student name to change");
        System.out.println("Back");
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        if (input.equals("Back")){
            return AdminMenu.getInstance();
        }
        User foundedUser = UsersStorage.findByName(input);
        if (foundedUser == null){
            return UsersSettingsMenu.getInstance();
        } else {
            System.out.println();
            System.out.println(PrePrintedStrings.START_MENU);
            System.out.println("1)User info");
            if (ScannerWrapper.getInstance().next().equals("User_info")){
                System.out.println(foundedUser);
                System.out.println("To make user admin press 0");
                if (ScannerWrapper.getInstance().nextInt() == 0) {
                    foundedUser.setUserType(UserType.ADMIN);
                    System.out.println(foundedUser.getName() + " has been set to admin");
                }
                System.out.println("Press any key to continue");
            }
            if (ScannerWrapper.getInstance().hasNext()){
                return AdminMenu.getInstance();
            }
        }
        return AdminMenu.getInstance();
    }
}
