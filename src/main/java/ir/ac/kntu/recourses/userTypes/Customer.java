package ir.ac.kntu.recourses.userTypes;

import ir.ac.kntu.recourses.*;
import ir.ac.kntu.recourses.Class;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Customer extends User {

    private ArrayList<Class> joinedClass = new ArrayList<>();

    private ArrayList<Class> ownedClass = new ArrayList<>();

    private WorkSpace currentWorkSpace = null;

    private Practice currentPractice = null;

    private Class managingClass;

    private Question currentQuestionBank;

    private Map<Question,ArrayList<Answer>> bankAnswers = new HashMap<>();

    private int userGlobalScore;

    private int userRank;

    public Customer(String name, String userName, String email, String password, String nationalNumber, String phoneNumber) {
        super(name, userName, email, password, nationalNumber, phoneNumber);
        this.setUserType(UserType.CUSTOMER);
        bankAnswers = new HashMap<>();
        userGlobalScore = 0;
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

    public void setCurrentWorkSpace(WorkSpace currentWorkSpace) {
        this.currentWorkSpace = currentWorkSpace;
    }

    public WorkSpace getCurrentWorkSpace() {
        return currentWorkSpace;
    }

    public void setJoinedClass(ArrayList<Class> joinedClass) {
        this.joinedClass = joinedClass;
    }

    public void setOwnedClass(ArrayList<Class> ownedClass) {
        this.ownedClass = ownedClass;
    }

    public ArrayList<Class> getOwnedClass() {
        return ownedClass;
    }

    public void setManagingClass(Class managingClass) {
        this.managingClass = managingClass;
    }

    public Class getManagingClass() {
        return managingClass;
    }

    public Question getCurrentQuestionBank() {
        return currentQuestionBank;
    }

    public void setCurrentQuestionBank(Question currentQuestionBank) {
        this.currentQuestionBank = currentQuestionBank;
    }

    public void addNewAnswer(Answer answer){
        for (Question search : bankAnswers.keySet()){
            if (search.equals(currentQuestionBank)){
                bankAnswers.get(search).add(answer);
                return;
            }
        }
        ArrayList<Answer> newAnswer = new ArrayList<>();
        newAnswer.add(answer);
        bankAnswers.put(currentQuestionBank,newAnswer);
    }

    public ArrayList<Answer> seeAnswers(){
        for (Question search : bankAnswers.keySet()){
            if (search.equals(currentQuestionBank)){
                return bankAnswers.get(search);
            }
        }
        return null;
    }

    public void addScore(int score){
        userGlobalScore += score;
        userRank = userGlobalScore/50;
    }

    private int getUserGlobalScore(){
        return userGlobalScore;
    }

}
