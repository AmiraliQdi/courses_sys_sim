package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.Answer;
import ir.ac.kntu.util.ScannerWrapper;

public class QuestionAnswerMenu implements Menu {

    private static QuestionAnswerMenu instance = new QuestionAnswerMenu();

    public static QuestionAnswerMenu getInstance() {
        return instance;
    }

    private QuestionAnswerMenu(){
    }

    @Override
    public void printMenu() {
        options.add(Main.loggedInUser.getCurrentWorkSpace().getQuestion().toString());
        options.add("1)New answer");
        options.add("2)See answers");
        options.add("Back");
        printInteractMenu();
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        if (input.equals("Back")){
            if (Main.loggedInUser.getLastMenu() == WorkSpaceMenu.getInstance()){
                return WorkSpaceMenu.getInstance();
            } else if (Main.loggedInUser.getLastMenu() == QuestionBankMenu.getInstance()){
                return QuestionBankMenu.getInstance();
            }
        }
        if (input.equals("New_answer")){
            System.out.println("Enter your answer");
            input = ScannerWrapper.getInstance().next();
            Answer answer = new Answer(Main.loggedInUser,Main.loggedInUser.getCurrentWorkSpace().getQuestion(),input);
            Main.loggedInUser.getCurrentWorkSpace().addNewAnswer(answer);
            Main.loggedInUser.getCurrentPractice().makeMarks();
            System.out.println("New answered submitted");
        } else if (input.equals("See_answers")){
            System.out.println();
            if (Main.loggedInUser.getCurrentWorkSpace().getAnswers().size()== 0){
                System.out.println("There is no answer");
            }
            for (Answer answer : Main.loggedInUser.getCurrentWorkSpace().getAnswers()){
                System.out.println(answer);
                System.out.println("---------------------------------------------------");
            }
        }
        return QuestionAnswerMenu.getInstance();
    }
}
