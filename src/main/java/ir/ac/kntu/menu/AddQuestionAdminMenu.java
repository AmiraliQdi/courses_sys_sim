package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.Question;
import ir.ac.kntu.recourses.QuestionDifficulty;
import ir.ac.kntu.recourses.QuestionType;
import ir.ac.kntu.storage.QuestionBank;
import ir.ac.kntu.util.Date;
import ir.ac.kntu.util.ScannerWrapper;

public class AddQuestionAdminMenu implements Menu{
    private static AddQuestionAdminMenu instance = new AddQuestionAdminMenu();

    public static AddQuestionAdminMenu getInstance() {
        return instance;
    }

    private AddQuestionAdminMenu() {


    }

    @Override
    public void printMenu() {
        OPTIONS.add("Make new question");
        OPTIONS.add("Add from bank");
        OPTIONS.add("Back");
        printInteractMenu();
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        switch (input) {
            case "Make_new_question":
                Question question = makeNewQuestion();
                Main.getAdmin().getCurrentPractice().addNewQuestion(question);
                return AddQuestionAdminMenu.getInstance();
            case "Add_from_bank":
                System.out.println("Enter question name : ");
                input = ScannerWrapper.getInstance().next();
                Question search = QuestionBank.findQuestionByName(input);
                Question copy = new Question(search);
                copy.setDateOfUpload(Date.CURRENT_DATE);
                Main.getAdmin().getCurrentPractice().addNewQuestion(copy);
                System.out.println("Question + " + copy.getName() + " added!");
                if (QuestionBank.findQuestionByName(input) == null) {
                    System.out.println("Wrong name");
                    return AddQuestionAdminMenu.getInstance();
                }
                break;
            case "Back":
                return TournamentMainPracticeEdit.getInstance();
            default:
                System.out.println("Wrong input");
                return AddQuestionAdminMenu.getInstance();
        }
        return AddQuestionAdminMenu.getInstance();
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

        System.out.println("Enter answer");
        String answer = ScannerWrapper.getInstance().next();
        QuestionType questionType = returnQuestionType();
        return new Question(name, description, questionDifficulty, questionType, answer);
    }

    private QuestionType returnQuestionType() {
        System.out.println("Enter question type (1:DESCRIPTIVE,2:TEST,3:SHORT_ANSWER,4:FILL_THE_BLANK)");
        String string = ScannerWrapper.getInstance().next();
        if (string.equals("DESCRIPTIVE")) {
            return QuestionType.DESCRIPTIVE;
        } else if (string.equals("TEST")) {
            return QuestionType.TEST;
        } else if (string.equals("SHORT_ANSWER")) {
            return QuestionType.TEST;
        } else if (string.equals("FILL_THE_BLANK")) {
            return QuestionType.TEST;
        } else {
            System.out.println("Wrong input!");
            return null;
        }
    }
}
