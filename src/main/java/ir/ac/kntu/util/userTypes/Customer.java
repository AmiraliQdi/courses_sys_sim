package ir.ac.kntu.util.userTypes;

public class Customer extends LoggedUser{

    public Customer(String name, String userName, String email, String password, String nationalNumber, String phoneNumber) {
        super(name, userName, email, password, nationalNumber, phoneNumber);
    }

}
