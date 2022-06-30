package ir.ac.kntu.recourses;

import ir.ac.kntu.storage.QuestionBank;
import ir.ac.kntu.util.Date;
import ir.ac.kntu.util.ScoreBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Practice {

    private Class owner;

    private String name;

    private String description;

    private Date startingDate;

    private Date endingDate;

    private PracticeStatus status = PracticeStatus.NA;

    private ScoreBoard scoreBoard;

    private boolean scoreBoardStatus = false;

    private double delayCoefficient;

    private int delayedSendingTime;

    private ArrayList<Question> questions;

    private Map<User,ArrayList<Question>> workMap;

    private Map<User,ArrayList<Double>> userMarks;

    private Map<User,Double> sumMarks;

    public Practice(String name, String description, Date startingDate, Date endingDate, double delayCoefficient, int delayedSendingTime){
        questions = new ArrayList<>();
        this.name = name;
        this.description = description;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.delayedSendingTime = delayedSendingTime;
        this.delayCoefficient = delayCoefficient;
        workMap = new HashMap<>();
        userMarks = new HashMap<>();
        sumMarks = new HashMap<>();
        if (Date.CURRENT_DATE.isAfter(startingDate)){
            status = PracticeStatus.ACCESSIBLE;
        }
    }

    public Practice(Practice practice){
        this.name = practice.getName();
        this.description = practice.getDescription();
        this.status = practice.getStatus();
        this.startingDate = practice.getStartingDate();
        this.endingDate = practice.getEndingDate();
        this.delayCoefficient = practice.getDelayCoefficient();
        this.delayedSendingTime = practice.getDelayedSendingTime();
        this.questions = practice.getQuestions();
        this.scoreBoardStatus = practice.getScoreBoardStatus();
        this.scoreBoard = practice.getScoreBoard();
    }

    public void setDelayCoefficient(double delayCoefficient) {
        this.delayCoefficient = delayCoefficient;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public void setDelayedSendingTime(int delayedSendingTime) {
        this.delayedSendingTime = delayedSendingTime;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public void setScoreBoardStatus(boolean scoreBoardStatus) {
        this.scoreBoardStatus = scoreBoardStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<User, ArrayList<Double>> getUserMarks() {
        return userMarks;
    }

    public Class getOwner() {
        return owner;
    }

    public Map<User, ArrayList<Question>> getWorkMap() {
        return workMap;
    }

    public Map<User, Double> getSumMarks() {
        return sumMarks;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void makeWorkMap(Class owner){
        for (User user : owner.getRegisteredUsers()){
            ArrayList<Question> copy = Question.cloneArray(questions);
            for (Question question : copy){
                question.makeWorkSpace(user);
            }
            workMap.put(user,copy);
        }
    }

    public void makeMarks(){
        ArrayList<Double> marks;
        for (User user : owner.getRegisteredUsers()){
            marks = new ArrayList<>();
            double sumMark = 0;
            for (Question question : workMap.get(user)){
                sumMark += question.getWorkSpace().getScore();
                marks.add(question.getWorkSpace().getScore());
            }
            userMarks.put(user,marks);
            sumMarks.put(user,sumMark);
        }
    }

    public void setOwner(Class owner) {
        this.owner = owner;
    }

    ///////////////////????????????????????
    public void makeScoreBoard(){

    }

    public void setStatus(PracticeStatus status) {
        this.status = status;
    }

    public void setScoreBoard(boolean scoreBoardISOn) {
        this.scoreBoardStatus = scoreBoardISOn;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public double getDelayCoefficient() {
        return delayCoefficient;
    }

    public PracticeStatus getStatus() {
        return status;
    }

    public int getDelayedSendingTime() {
        return delayedSendingTime;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public boolean getScoreBoardStatus(){
        return this.scoreBoardStatus;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public static ArrayList<Practice> cloneArray(ArrayList<Practice> practices){
        ArrayList<Practice> copyPractices = new ArrayList<>();
        for (Practice practice : practices){
            Practice newPractice = new Practice(practice);
            copyPractices.add(newPractice);
        }
        return copyPractices;
    }

    public void addNewQuestion(Question question){
        Question copy = new Question(question);
        copy.setPractice(this);
        questions.add(copy);
        for (User user : workMap.keySet()){
            Question newCopy = new Question(copy);
            newCopy.makeWorkSpace(user);
            workMap.get(user).add(newCopy);
        }
    }

    public void addNewQuestion(String name){
        Question question = QuestionBank.findQuestionByName(name);
        if (question == null){
            return;
        }
        Question copy = new Question(question);
        copy.setPractice(this);
        questions.add(copy);
    }

    public void addNewQuestion(int id){
        Question question = QuestionBank.findQuestionByID(id);
        Question copy = new Question(question);
        copy.setPractice(this);
        questions.add(copy);
    }

    public void removeQuestion(Question question){
        for (Question search : questions){
            if (search.equals(question)){
                questions.remove(search);
                return;
            }
        }
        System.out.println("There is no such a question in this practice");
    }

    public void removeQuestion(String name){
        for (Question search : questions){
            if (search.getName().equals(name)){
                questions.remove(search);
                return;
            }
        }
        System.out.println("There is no such a question in this practice");
    }

    public void addWorkSpace(User user){
        workMap.put(user,questions);
    }

    public String toString(){
        return "name : " + name + " - Question counts : " + questions.size();
    }
}

