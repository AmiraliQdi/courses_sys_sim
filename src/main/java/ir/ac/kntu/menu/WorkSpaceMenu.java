package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.Practice;
import ir.ac.kntu.recourses.Question;
import ir.ac.kntu.util.ScannerWrapper;

public class WorkSpaceMenu implements Menu {

    private static WorkSpaceMenu instance = new WorkSpaceMenu();

    public static WorkSpaceMenu getInstance() {
        return instance;
    }

    private WorkSpaceMenu(){

    }

    private void printQuestions(Practice practice){
        for (Question question : practice.getQuestions()){
            System.out.println();
            question.smallPrint();
        }
    }

    @Override
    public void printMenu() {
        //Hard_coded
        System.out.println("=================================================");
        System.out.println();
        System.out.println("Score board");
        printQuestions(Main.loggedInUser.getCurrentPractice());
        System.out.println("Back");
        System.out.println();
        System.out.println("=================================================");
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        Question targetQuestion = findQuestion(input);
        if (input.equals("Score_board")){
            if (Main.loggedInUser.getCurrentPractice().getScoreBoardStatus()){
                EditingPractice.printMarks();
                ScannerWrapper.getInstance().next();
            } else {
                System.out.println("Score board is OFF by teacher");
            }
            return WorkSpaceMenu.getInstance();
        }
        if (targetQuestion == null){
            if (input.equals("Back")){
                return StudentMenu.getInstance();
            } else {
                System.out.println("There is no question with that name");
                return WorkSpaceMenu.getInstance();
            }
        }
        Main.loggedInUser.setCurrentWorkSpace(targetQuestion.getWorkSpace());
        Main.loggedInUser.setLastMenu(WorkSpaceMenu.getInstance());
        return QuestionAnswerMenu.getInstance();
    }

    private Question findQuestion(String name){
        for (Question question : Main.loggedInUser.getCurrentPractice().getWorkMap().get(Main.loggedInUser)){
            if (question.getName().equals(name)){
                return question;
            }
        }
        System.out.println("Wrong name");
        return null;
    }
}
