package ir.ac.kntu.util.userTypes;

public class Guest extends LoggedUser{

    public Guest(String name, String userName, String email, String password, String nationalNumber, String phoneNumber) {
        super(name, userName, email, password, nationalNumber, phoneNumber);
    }

}

