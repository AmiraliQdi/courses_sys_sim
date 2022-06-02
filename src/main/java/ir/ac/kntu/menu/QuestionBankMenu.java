package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.Question;
import ir.ac.kntu.storage.QuestionBank;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.Objects;

public class QuestionBankMenu implements Menu{

    private static QuestionBankMenu instance = new QuestionBankMenu();

    private QuestionBankMenu(){
    }

    public static QuestionBankMenu getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {
        options.add("1)Find question");
        options.add("2)See all questions");
        options.add("Back");
        printInteractMenu();
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        if (input.equals("Back")){
            return UserMenu.getInstance();
        } else if (input.equals("Find_question")){
            System.out.println("Enter question name : ");
            input = ScannerWrapper.getInstance().next();
            return chooseQuestion(input);
        } else if (input.equals("See_all_questions")) {
            for (Question question : QuestionBank.getQuestions()){
                question.smallPrint();
                System.out.println("=================================================");
            }
            System.out.println("Choose question : ");
            input = ScannerWrapper.getInstance().next();
            return chooseQuestion(input);
        } else {
            System.out.println("Wrong input");
            return QuestionBankMenu.getInstance();
        }
    }

    private Menu chooseQuestion(String name){
        if (QuestionBank.findQuestionByName(name) != null){
            Question question = new Question(Objects.requireNonNull(QuestionBank.findQuestionByName(name)));
            Main.loggedInUser.setCurrentQuestionBank(question);
        } else {
            System.out.println("Wrong name");
        }
        return QuestionAnswerMenu.getInstance();
    }
}