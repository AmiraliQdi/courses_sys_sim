package ir.ac.kntu.recourses;

import ir.ac.kntu.util.Date;

import java.util.Objects;

public class Answer {

    private final User answeredUser;

    private final Question question;

    private final Date sentDate;

    private double score = 0;

    private boolean lastAnswer = false;

    private final String answer;

    public Answer(User answeredUser, Question question, String answer){
        this.answeredUser = answeredUser;
        this.question = question;
        this.answer = answer;
        this.sentDate = Date.CURRENT_DATE;
        if (question.getQuestionType() == QuestionType.TEST || question.getQuestionType() == QuestionType.SHORT_ANSWER){
            this.score = calcScore();
        }

    }

    private double calcScore(){
        if (question.getAnswer().equals(answer)){
            try {
                if (sentDate.isAfter(question.getPractice().getEndingDate())){
                    return 10*question.getPractice().getDelayCoefficient();
                } else {
                    return 10;
                }
            } catch (NullPointerException e) {
                return 10;
            }
        } else {
            return 0;
        }
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setLastAnswer(boolean lastAnswer) {
        this.lastAnswer = lastAnswer;
    }

    public String[] getAnswer() {
        return new String[]{answer};
    }

    public double getScore() {
        return score;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public Question getQuestion() {
        return question;
    }

    public User getAnsweredUser() {
        return answeredUser;
    }

    public boolean isLastAnswer() {
        return lastAnswer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "sentDate=" + sentDate +
                ", score=" + score +
                ", lastAnswer=" + lastAnswer +
                ", answer='" + answer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Answer answer1 = (Answer) o;
        return answeredUser.equals(answer1.answeredUser) && question.equals(answer1.question) && answer.equals(answer1.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answeredUser, question, answer);
    }
}
