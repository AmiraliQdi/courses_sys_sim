package ir.ac.kntu.menu;

import ir.ac.kntu.recourses.Question;
import ir.ac.kntu.recourses.QuestionDifficulty;
import ir.ac.kntu.storage.PrePrintedStrings;
import ir.ac.kntu.storage.QuestionBank;
import ir.ac.kntu.util.ScannerWrapper;

public class QuestionBankMenuAdmin implements Menu{

    private static final QuestionBankMenuAdmin instance = new QuestionBankMenuAdmin();

    private QuestionBankMenuAdmin(){

    }

    public static QuestionBankMenuAdmin getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {
        OPTIONS.add("See all questions");
        OPTIONS.add("Add new question");
        OPTIONS.add("Back");
        printInteractMenu();
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        switch (input){
            case "See_all_questions" :
                return seeAllQuestions();
            case "Add_new_question" :
                Question newQuestion = makeNewQuestion();
                QuestionBank.addQuestionToBank(newQuestion);
                return QuestionBankMenuAdmin.getInstance();
            case "Back" :
                return AdminMenu.getInstance();
            default:
                System.out.println("Wrong input");
                return QuestionBankMenuAdmin.getInstance();
        }
    }

    private Question makeNewQuestion() {
        System.out.println("Enter question name :");
        String name = ScannerWrapper.getInstance().next();
        System.out.println("Enter question description");
        String description = ScannerWrapper.getInstance().next();
        System.out.println("Enter question difficulty : (1,2,3,4)");
        String difficulty = ScannerWrapper.getInstance().next();
        QuestionDifficulty questionDifficulty;
        if (difficulty.equals("1")) {
            questionDifficulty = QuestionDifficulty.EASY;
        } else if (difficulty.equals("2")) {
            questionDifficulty = QuestionDifficulty.MEDIUM;
        } else if (difficulty.equals("3")) {
            questionDifficulty = QuestionDifficulty.HARD;
        } else if (difficulty.equals("4")) {
            questionDifficulty = QuestionDifficulty.INSANE;
        } else {
            System.out.println("Wrong input");
            return null;
        }
        return null;
    }

    private Menu seeAllQuestions(){
        Question chosenQuestion = null;
        for (Question question : QuestionBank.getQuestions()){
            question.smallPrint();
        }
        System.out.println("Choose a question to edit");
        String input = ScannerWrapper.getInstance().next();
        for (Question question : QuestionBank.getQuestions()){
            if (question.getName().equals(input)){
                chosenQuestion = question;
            }
        }
        if (chosenQuestion == null){
            System.out.println("No question by this name");
            return QuestionBankMenuAdmin.getInstance();
        } else {
            System.out.println(chosenQuestion);
            questionEdit(chosenQuestion);
        }
        return QuestionBankMenuAdmin.getInstance();
    }

    private void questionEdit(Question question){
        System.out.println();
        System.out.println(PrePrintedStrings.START_MENU);
        System.out.println("1)Edit name");
        System.out.println("2)Edit description");
        System.out.println("3)Edit answer");
        System.out.println("4)Back");
        System.out.println(PrePrintedStrings.END_MENU);
        System.out.println();
        String input = ScannerWrapper.getInstance().next();
        switch (input){
            case "Edit_name" :
                System.out.println("Enter new name");
                question.setName(ScannerWrapper.getInstance().next());
                questionEdit(question);
            case "Edit_description" :
                System.out.println("Enter new description");
                question.setDescription(ScannerWrapper.getInstance().next());
                questionEdit(question);
            case "Edit_answer" :
                System.out.println("Enter new answer");
                question.setAnswer(ScannerWrapper.getInstance().next());
                questionEdit(question);
            case "Back" :
                return;
            default:
                questionEdit(question);
        }
    }
}
