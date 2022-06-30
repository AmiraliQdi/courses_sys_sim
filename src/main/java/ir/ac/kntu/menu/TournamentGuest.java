package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.Practice;
import ir.ac.kntu.recourses.Question;
import ir.ac.kntu.util.ScannerWrapper;

public class TournamentGuest implements Menu{

    private static TournamentGuest instance = new TournamentGuest();

    public static TournamentGuest getInstance() {
        return instance;
    }

    private TournamentGuest(){

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
        printQuestions(Main.getGuest().getCurrentPractice());
        System.out.println("Back");
        System.out.println();
        System.out.println("=================================================");
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        if (input.equals("Back")) {
            return GuestMenu.getInstance();
        }
        Question targetQuestion = findQuestion(input);
        if (targetQuestion == null){
            return TournamentGuest.getInstance();
        }
        System.out.println();
        System.out.println(targetQuestion);
        System.out.println("Press any key to continue");
        if (ScannerWrapper.getInstance().hasNext()){
            return TournamentGuest.getInstance();
        }
        return TournamentGuest.getInstance();
    }

    private Question findQuestion(String name){
        for (Question question : Main.getCustomer().getCurrentPractice().getWorkMap().get(Main.getCustomer())){
            if (question.getName().equals(name)){
                return question;
            }
        }
        System.out.println("Wrong name");
        return null;
    }
}
