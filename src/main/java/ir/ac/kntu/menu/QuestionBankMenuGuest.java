package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.Question;
import ir.ac.kntu.storage.QuestionBank;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.Objects;

public class QuestionBankMenuGuest implements Menu{
    private static QuestionBankMenuGuest instance = new QuestionBankMenuGuest();

    private QuestionBankMenuGuest(){
    }

    public static QuestionBankMenuGuest getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {
        OPTIONS.add("Find question");
        OPTIONS.add("See all questions");
        OPTIONS.add("Back");
        printInteractMenu();
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        if (input.equals("Back")){
            return GuestMenu.getInstance();
        } else if (input.equals("Find_question")){
            System.out.println("Enter question name : ");
            input = ScannerWrapper.getInstance().next();
            return chooseQuestion(input);
        } else if (input.equals("See_all_questions")) {
            for (Question question : QuestionBank.getQuestions()){
                question.smallPrint(1);
                System.out.println("=================================================");
            }
            System.out.println("Choose question : ");
            input = ScannerWrapper.getInstance().next();
            return chooseQuestion(input);
        } else {
            System.out.println("Wrong input");
            return QuestionBankMenuGuest.getInstance();
        }
    }

    private Menu chooseQuestion(String name){
        if (QuestionBank.findQuestionByName(name) != null){
            Question question = new Question(Objects.requireNonNull(QuestionBank.findQuestionByName(name)));
            Main.getGuest().setCurrentQuestionBank(question);
        } else {
            System.out.println("Wrong name");
            return QuestionBankMenuGuest.getInstance();
        }
        Main.getGuest().setLastMenu(QuestionBankMenuGuest.getInstance());
        return QuestionAnswerMenuBankGuest.getInstance();
    }
}
