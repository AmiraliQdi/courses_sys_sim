package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.Answer;
import ir.ac.kntu.util.ScannerWrapper;


public class QuestionAnswerMenuBank implements Menu {

    private static QuestionAnswerMenuBank instance = new QuestionAnswerMenuBank();

    public static QuestionAnswerMenuBank getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {
        OPTIONS.add(Main.getCustomer().getCurrentQuestionBank().toString());
        OPTIONS.add("New answer");
        OPTIONS.add("See answers");
        OPTIONS.add("Back");
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
            Answer answer = new Answer(Main.getCustomer(),Main.getCustomer().getCurrentQuestionBank(),input);
            Main.getCustomer().addNewAnswer(answer);
            System.out.println("New answered submitted");
        } else if (input.equals("See_answers")){
            System.out.println();
            if (Main.getCustomer().seeAnswers().size()== 0){
                System.out.println("There is no answer");
            }
            for (Answer answer : Main.getCustomer().seeAnswers()){
                System.out.println(answer);
                System.out.println("---------------------------------------------------");
            }
        }
        return QuestionAnswerMenuBank.getInstance();
    }
}
