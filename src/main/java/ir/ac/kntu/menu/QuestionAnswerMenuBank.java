package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.Answer;
import ir.ac.kntu.recourses.Question;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;
import java.util.Map;

public class QuestionAnswerMenuBank implements Menu {

    @Override
    public void printMenu() {
        options.add(Main.loggedInUser.getCurrentQuestionBank().toString());
        options.add("1)New answer");
        options.add("2)See answers");
        options.add("Back");
        printInteractMenu();
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        if (input.equals("Back")){
            return QuestionBankMenu.getInstance();
        }
        if (input.equals("New_answer")){
            System.out.println("Enter your answer");
            input = ScannerWrapper.getInstance().next();
            Answer answer = new Answer(Main.loggedInUser,Main.loggedInUser.getCurrentQuestionBank(),input);
            Main.loggedInUser.addNewAnswer(answer);
            System.out.println("New answered submitted");
        } else if (input.equals("See_answers")){
            System.out.println();
            if (Main.loggedInUser.seeAnswers().size()== 0){
                System.out.println("There is no answer");
            }
            for (Answer answer : Main.loggedInUser.seeAnswers()){
                System.out.println(answer);
                System.out.println("---------------------------------------------------");
            }
        }
        return QuestionAnswerMenu.getInstance();
    }
}
