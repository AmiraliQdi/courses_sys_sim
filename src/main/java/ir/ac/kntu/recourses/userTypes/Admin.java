package ir.ac.kntu.recourses.userTypes;

import ir.ac.kntu.recourses.User;
import ir.ac.kntu.recourses.tournaments.Tournament;

public class Admin extends User {

    private Tournament managingTournament;

    public Admin(String name, String userName, String email, String password, String nationalNumber, String phoneNumber) {
        super(name, userName, email, password, nationalNumber, phoneNumber);
        this.setUserType(UserType.ADMIN);
    }

    public void setManagingTournament(Tournament managingTournament) {
        this.managingTournament = managingTournament;
    }

    public Tournament getManagingTournament() {
        return managingTournament;
    }
}
