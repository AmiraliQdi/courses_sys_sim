package ir.ac.kntu.recourses;

import ir.ac.kntu.util.Date;

import java.util.ArrayList;

public class WorkSpace {

    Question question;

    private ArrayList<Answer> answers;

    private User user;

    private double score;

    private Answer lastAnswer;

    public WorkSpace(User user, Question question){
        answers = new ArrayList<>();
        this.question = question;
    }

    public void calcScore(){
        for (Answer answer : answers){
            if (answer.isLastAnswer()){
                score = answer.getScore();
            }
        }
    }

    public Question getQuestion() {
        return question;
    }

    public User getUser() {
        return user;
    }

    private void setLastAnswer(){
        double mark = 0;
        if (question.getQuestionType() != QuestionType.TEST || question.getQuestionType() != QuestionType.FILL_THE_BLANK){
            answers.get(answers.size()-1).setLastAnswer(true);
            lastAnswer = answers.get(answers.size()-1);
            return;
        } else {
            mark = Double.MIN_VALUE;
            if (answers.size() == 1){
                answers.get(0).setLastAnswer(true);
                lastAnswer = answers.get(0);
                return;
            } else {
                for (Answer answer : answers){
                    if (answer.getScore()>=mark){
                        mark = answer.getScore();
                    }
                }
                for (Answer answer : answers){
                    if (answer.getScore() == mark){
                        answer.setLastAnswer(true);
                        lastAnswer = answer;
                        return;
                    }
                }
            }
        }
        System.out.println("Error in last answer");
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Answer getLastAnswer() {
        return lastAnswer;
    }

    public double getScore() {
        return score;
    }

    public void addNewAnswer(Answer answer){
        Date superEndDate = question.getPractice().getEndingDate().addDays(question.getPractice().getDelayedSendingTime());
        if (superEndDate.isAfter(Date.CURRENT_DATE)) {
            if (isNewAnswer(answer)) {
                answers.add(answer);
                setLastAnswer();
                calcScore();
                score = score * question.getPractice().getDelayCoefficient();
            }
        } else if (question.getPractice().getEndingDate().isAfter(Date.CURRENT_DATE)){
            answers.add(answer);
            setLastAnswer();
            calcScore();
        } else {
            System.out.println("Sorry but date of upload has been reached!");
        }
    }

    public void addNewAnswer(Answer answer,String string){
        answers.add(answer);
    }

    private boolean isNewAnswer(Answer answer){
        for (Answer search : answers) {
            if (search.equals(answer)){
                System.out.println("Sorry but you already sent this answer");
                return false;
            }
        }
        return true;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }
}
