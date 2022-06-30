package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.Answer;
import ir.ac.kntu.util.ScannerWrapper;

public class QuestionAnswerMenuBankGuest implements Menu{
    private static QuestionAnswerMenuBankGuest instance = new QuestionAnswerMenuBankGuest();

    public static QuestionAnswerMenuBankGuest getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {
        OPTIONS.add(Main.getGuest().getCurrentQuestionBank().toString());
        OPTIONS.add("New answer");
        OPTIONS.add("Back");
        printInteractMenu();
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        if (input.equals("Back")){
            return QuestionBankMenuGuest.getInstance();
        }
        if (input.equals("New_answer")){
            System.out.println("Enter your answer");
            input = ScannerWrapper.getInstance().next();
            Answer answer = new Answer(Main.getGuest(),Main.getGuest().getCurrentQuestionBank(),input);
            System.out.println("New answered submitted");
        }
        return QuestionAnswerMenuBankGuest.getInstance();
    }
}
