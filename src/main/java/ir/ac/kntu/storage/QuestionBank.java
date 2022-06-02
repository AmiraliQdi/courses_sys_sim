package ir.ac.kntu.storage;

import ir.ac.kntu.recourses.Question;

import java.util.ArrayList;

public class QuestionBank {

    public final static  double MAX_SCORE = 10;

    private static ArrayList<Question> questions = new ArrayList<>();

    private QuestionBank(){
    }

    public static ArrayList<Question> getQuestions() {
        return Question.cloneArray(questions);
    }

    private static boolean isInBank(Question question){
        for (Question checkingQuestion : questions){
            if (question.equals(checkingQuestion)){
                return true;
            }
        }
        return false;
    }

    public static void addQuestionToBank(Question question){
        if (isInBank(question)){
            System.out.println("This question is already in QBank!");
        } else {
            questions.add(question);
        }
    }

    public static void removeQuestionFromBank(Question question){
        if (isInBank(question)){
            questions.remove(question);
        } else {
            System.out.println("This question is not in QBank to be removed");
        }
    }

    public static Question findQuestionByName(String name){
        for (Question question : questions){
            if (question.getName().equals(name)){
                return question;
            }
        }
        System.out.println("Could not find any Question with that name");
        return null;
    }

    public static Question findQuestionByID(int iD){
        for (Question question : questions){
            if (question.getID() == iD){
                return question;
            }
        }
        System.out.println("Could not find any Question with this ID");
        return null;
    }
}
