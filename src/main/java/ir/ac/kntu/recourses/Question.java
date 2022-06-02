package ir.ac.kntu.recourses;

import ir.ac.kntu.util.Date;

import java.util.ArrayList;
import java.util.Objects;

public class Question {

    private Practice practice = null;

    private String name;

    private final int iD;

    private static int idCounter = 0;

    private String description;

    private QuestionDifficulty questionDifficulty;

    private QuestionType questionType;

    private double multiplier;

    private Date dateOfUpload;

    private String answer;

    private WorkSpace workSpace;

    Question(String name,String description,QuestionDifficulty questionDifficulty,QuestionType questionType,Date dateOfUpload){
        this.dateOfUpload = dateOfUpload;
        this.name = name;
        this.description = description;
        this.questionDifficulty = questionDifficulty;
        this.questionType = questionType;
        idCounter++;
        this.iD = idCounter;
    }

    public Question(String name, String description, QuestionDifficulty questionDifficulty, QuestionType questionType, String answer){
        this.dateOfUpload = dateOfUpload;
        this.name = name;
        this.description = description;
        this.questionDifficulty = questionDifficulty;
        this.questionType = questionType;
        idCounter++;
        this.iD = idCounter;
        this.answer = answer;
    }

    public Question(Question question){
        this.name = question.getName();
        this.description = question.getDescription();
        this.questionDifficulty = question.getQuestionDifficulty();
        this.questionType = question.getQuestionType();
        this.iD = question.getID();
        this.answer = question.getAnswer();
        this.practice = question.getPractice();
        this.multiplier = question.getMultiplier();
        this.dateOfUpload = question.getDateOfUpload();
    }

    public WorkSpace getWorkSpace() {
        return workSpace;
    }

    public Date getDateOfUpload() {
        return dateOfUpload;
    }

    public void makeWorkSpace(User user){
        workSpace = new WorkSpace(user,this);
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public QuestionDifficulty getQuestionDifficulty() {
        return questionDifficulty;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Practice getPractice() {
        return practice;
    }

    public void setPractice(Practice practice) {
        this.practice = practice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public void setQuestionDifficulty(QuestionDifficulty questionDifficulty) {
        this.questionDifficulty = questionDifficulty;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public int getID() {
        return iD;
    }

    public static ArrayList<Question> cloneArray(ArrayList<Question> questions){
        ArrayList<Question> newQuestions = new ArrayList<>();
        for (Question question : questions){
            Question newQuestion = new Question(question);
            newQuestions.add(newQuestion);
        }
        return newQuestions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Question question = (Question) o;
        return iD == question.iD && questionDifficulty == question.questionDifficulty && questionType == question.questionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(iD, questionDifficulty, questionType);
    }

    public void smallPrint(){
        System.out.println(name + " date of upload : " + dateOfUpload + " dead line : " + practice.getEndingDate());
    }

    public void smallPrint(String teacher){
        System.out.println(name + " date of upload : " + dateOfUpload + " answer : " + answer);
    }

    public void setDateOfUpload(Date dateOfUpload) {
        this.dateOfUpload = dateOfUpload;
    }

    public String toString(){
        return "Question name : " + name + "\n" + description;
    }
}

