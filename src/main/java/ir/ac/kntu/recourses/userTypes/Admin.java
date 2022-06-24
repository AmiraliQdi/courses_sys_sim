package ir.ac.kntu.recourses.userTypes;

import ir.ac.kntu.recourses.User;

public class Admin extends User {

    public Admin(String name, String userName, String email, String password, String nationalNumber, String phoneNumber) {
        super(name, userName, email, password, nationalNumber, phoneNumber);
        this.setUserType(UserType.ADMIN);
    }

}
