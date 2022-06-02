package ir.ac.kntu.util.userTypes;

import ir.ac.kntu.Main;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.recourses.*;
import ir.ac.kntu.recourses.Class;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class LoggedUser extends User {

    private ArrayList<Class> joinedClass = null;

    private ArrayList<Class> ownedClass = null;

    private WorkSpace currentWorkSpace = null;

    private Practice currentPractice = null;

    private Menu lastMenu;

    private Class managingClass;

    private Question currentQuestionBank;

    private Map<Question,ArrayList<Answer>> BankAnswers = null;

    public LoggedUser(String name, String userName, String email, String password, String nationalNumber, String phoneNumber) {
        super(name, userName, email, password, nationalNumber, phoneNumber);
    }

    public void setCurrentPractice(Practice currentPractice) {
        this.currentPractice = currentPractice;
    }

    public Practice getCurrentPractice() {
        return currentPractice;
    }

    public ArrayList<Class> getJoinedClass() {
        return joinedClass;
    }

    public Menu getLastMenu() {
        return lastMenu;
    }

    public void setLastMenu(Menu lastMenu) {
        this.lastMenu = lastMenu;
    }

    public void setCurrentWorkSpace(WorkSpace currentWorkSpace) {
        this.currentWorkSpace = currentWorkSpace;
    }

    public WorkSpace getCurrentWorkSpace() {
        return currentWorkSpace;
    }

    public void setJoinedClass(ArrayList<Class> joinedClass) {
        this.joinedClass = joinedClass;
    }

    public void setManagingClass(Class managingClass) {
        this.managingClass = managingClass;
    }

    public Class getManagingClass() {
        return managingClass;
    }

    public void setOwnedClass(ArrayList<Class> ownedClass) {
        this.ownedClass = ownedClass;
    }

    public ArrayList<Class> getOwnedClass() {
        return ownedClass;
    }

    public Question getCurrentQuestionBank() {
        return currentQuestionBank;
    }

    public void setCurrentQuestionBank(Question currentQuestionBank) {
        this.currentQuestionBank = currentQuestionBank;
    }

    public void addNewAnswer(Answer answer){
        for (Question search : BankAnswers.keySet()){
            if (search.equals(currentQuestionBank)){
                BankAnswers.get(search).add(answer);
                return;
            }
        }
        ArrayList<Answer> newAnswer = new ArrayList<>();
        newAnswer.add(answer);
        BankAnswers.put(currentQuestionBank,newAnswer);
    }

    public ArrayList<Answer> seeAnswers(){
        for (Question search : BankAnswers.keySet()){
            if (search.equals(currentQuestionBank)){
                return BankAnswers.get(search);
            }
        }
        return null;
    }
}
