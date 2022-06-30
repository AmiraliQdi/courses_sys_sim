package ir.ac.kntu.storage;

import ir.ac.kntu.recourses.User;
import ir.ac.kntu.recourses.userTypes.Customer;
import ir.ac.kntu.recourses.userTypes.UserType;

import java.util.ArrayList;

public class UsersStorage {

    private static ArrayList<User> users = new ArrayList<>();

    private UsersStorage(){
    }

    private static boolean isAvailable(User user){
        for (User targetUser : users){
            if (user.equals(targetUser)){
                return false;
            }
        }
        return true;
    }

    public static User findByEmail(String email){
        for (User user : users){
            if (user.getEmail().equals(email)){
                return user;
            }
        }
        System.out.println("FindByEmail could not find user with this " + email + " address");
        return null;
    }

    public static User findByNationalNumber(String number){
        for (User user : users){
            if (user.getNationalNumber().equals(number)){
                return user;
            }
        }
        System.out.println("Could not find user with this national number");
        return null;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static User findByName(String userName){
        for (User user : users){
            if (user.getUserName().equals(userName)){
                return user;
            }
        }
        System.out.println("Could not find any user with this user name");
        return null;
    }

    public static void addUser(User user){
        if (isAvailable(user)){
            users.add(user);
        } else {
            System.out.println("This user is already registered");
        }
    }

    public static ArrayList<Customer> getCustomers(){
        ArrayList<Customer> result = new ArrayList<>();
        for (User user : users) {
            if (user.getUserType().equals(UserType.CUSTOMER)){
                result.add((Customer) user);
            }
        }
        return result;
    }
}
