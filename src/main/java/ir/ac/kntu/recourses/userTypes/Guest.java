package ir.ac.kntu.recourses.userTypes;

import ir.ac.kntu.recourses.Practice;
import ir.ac.kntu.recourses.Question;
import ir.ac.kntu.recourses.User;
import ir.ac.kntu.recourses.tournaments.Tournament;

public class Guest extends User {

    private Question currentQuestionBank;

    private Tournament currentTournament;

    private Practice currentPractice;

    public Guest(String name, String userName, String email, String password, String nationalNumber, String phoneNumber) {
        super(name, userName, email, password, nationalNumber, phoneNumber);
    }

    public void setCurrentQuestionBank(Question currentQuestionBank) {
        this.currentQuestionBank = currentQuestionBank;
    }

    public Question getCurrentQuestionBank() {
        return currentQuestionBank;
    }

    public void setCurrentTournament(Tournament currentTournament) {
        this.currentTournament = currentTournament;
    }

    public Tournament getCurrentTournament() {
        return currentTournament;
    }

    public Practice getCurrentPractice() {
        return currentPractice;
    }

    public void setCurrentPractice(Practice currentPractice) {
        this.currentPractice = currentPractice;
    }
}

