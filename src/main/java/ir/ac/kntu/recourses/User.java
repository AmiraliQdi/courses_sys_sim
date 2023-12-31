package ir.ac.kntu.recourses;

import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.recourses.userTypes.UserType;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public abstract class User {

    private UserType userType;

    private String name;

    private final String userName;

    private String email;

    private String nationalNumber;

    private String phoneNumber;

    private static int idCounter = 0;

    private int id;

    private String password;

    private Menu lastMenu;



    public User(String name, String userName, String email, String password,String nationalNumber,String phoneNumber){
        this.name = name;
        this.userName = userName;
        this.email = email;
        idCounter++;
        this.id = idCounter;
        this.password = password;
        this.nationalNumber = nationalNumber;
        this.phoneNumber = phoneNumber;
        this.userType = UserType.CUSTOMER;
    }

    public User(){
        idCounter++;
        this.id = idCounter;
        this.userType = UserType.GUEST;
        this.userName = "guest"+this.id;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getNationalNumber() {
        return nationalNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNationalNumber(String nationalNumber) {
        this.nationalNumber = nationalNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserType getUserType() {
        return userType;
    }

    public Menu getLastMenu() {
        return lastMenu;
    }

    public void setLastMenu(Menu lastMenu) {
        this.lastMenu = lastMenu;
    }

    @Override
    public String toString() {
        return "Username :" + userName + "\n" +
                "Password :" + password + "\n" +
                "Phone number : " + phoneNumber + "\n" +
                "National number : " + nationalNumber + "\n" +
                "Email : " + email + "\n" +
                "Name : " + name + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && userName.equals(user.userName) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, email, id);
    }

}
