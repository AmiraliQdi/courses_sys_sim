package ir.ac.kntu.recourses.userTypes;

import ir.ac.kntu.recourses.Class;
import ir.ac.kntu.recourses.Practice;
import ir.ac.kntu.recourses.User;
import ir.ac.kntu.recourses.tournaments.Tournament;

public class Admin extends User {

    private Tournament managingTournament;

    private Practice currentPractice;

    private Class managingClass;

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

    public void setCurrentPractice(Practice currentPractice) {
        this.currentPractice = currentPractice;
    }

    public Practice getCurrentPractice() {
        return currentPractice;
    }

    public void setManagingClass(Class managingClass) {
        this.managingClass = managingClass;
    }

    public Class getManagingClass() {
        return managingClass;
    }
}
