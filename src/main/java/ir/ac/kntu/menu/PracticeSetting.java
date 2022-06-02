package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.PracticeStatus;
import ir.ac.kntu.util.Date;
import ir.ac.kntu.util.ScannerWrapper;

public class PracticeSetting implements Menu{

    private static  PracticeSetting instance = new PracticeSetting();

    public static PracticeSetting getInstance() {
        return instance;
    }

    private PracticeSetting(){
    }

    @Override
    public void printMenu() {
        options.add("Set dead line");
        options.add("Set starting date");
        options.add("Set status");
        options.add("Set score board status");
        options.add("Set delay sending time");
        options.add("Set delay coefficient");
        options.add("Set new name");
        options.add("Delete practice");
        options.add("Back");
        printInteractMenu();
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        switch (input) {
            case "Set_status" :
                System.out.println("Current status : " + Main.loggedInUser.getCurrentPractice().getStatus());
                Main.loggedInUser.getCurrentPractice().setStatus(setStatus());
                return PracticeSetting.getInstance();
            case "Set_dead_line" :
                System.out.println("Current dead line : " + Main.loggedInUser.getCurrentPractice().getEndingDate());
                Main.loggedInUser.getCurrentPractice().setEndingDate(setDeadLine());
                return PracticeSetting.getInstance();
            case "Set_starting_date" :
                System.out.println("Current dead line : " + Main.loggedInUser.getCurrentPractice().getStartingDate());
                Main.loggedInUser.getCurrentPractice().setStartingDate(setStartingDate());
                return PracticeSetting.getInstance();
            case "Set_score_board_status" :
                System.out.println("Current status is " + Main.loggedInUser.getCurrentPractice().getStatus());
                setScoreBoardStatus();
                return PracticeSetting.getInstance();
            case "Set_delay_sending_time" :
                System.out.println("Current delay sending time is " + Main.loggedInUser.getCurrentPractice().getDelayedSendingTime());
                Main.loggedInUser.getCurrentPractice().setDelayedSendingTime(setDelaySendingTime());
                return PracticeSetting.getInstance();
            case "Set_delay_coefficient" :
                System.out.println("Current delay coefficient is " + Main.loggedInUser.getCurrentPractice().getDelayCoefficient());
                Main.loggedInUser.getCurrentPractice().setDelayCoefficient(setDelayCoefficient());
                return PracticeSetting.getInstance();
            case "Set_new_name" :
                System.out.println("Current practice name is " + Main.loggedInUser.getCurrentPractice().getName());
                Main.loggedInUser.getCurrentPractice().setName(setName());
                return PracticeSetting.getInstance();
            case "Delete" :
                Main.loggedInUser.getManagingClass().removePractice(Main.loggedInUser.getCurrentPractice());
                return ManegingClass.getInstance();
            case "Back" :
                return ManegingClass.getInstance();
            default:
                System.out.println("Wrong input");
                return PracticeSetting.getInstance();
        }
    }

    private String setName(){
        System.out.println("Enter new name : ");
        return ScannerWrapper.getInstance().next();
    }

    private double setDelayCoefficient(){
        System.out.println("Enter new coefficient");
        return ScannerWrapper.getInstance().nextDouble();
    }

    private int setDelaySendingTime(){
        System.out.println("Enter how many days ");
        return ScannerWrapper.getInstance().nextInt();
    }

    private boolean setScoreBoardStatus(){
        System.out.println("Enter status (ON/OFF");
        String input = ScannerWrapper.getInstance().next();
        if (input.equals("ON")){
            return true;
        } else if (input.equals("OFF")){
            return false;
        } else {
            System.out.println("Wrong input");
            setScoreBoardStatus();
        }
        return false;
    }

    private Date setStartingDate(){
        System.out.println("Enter new date : ");
        String input = ScannerWrapper.getInstance().next();
        return Date.readDate(input);
    }

    private Date setDeadLine(){
        System.out.println("Enter new date : ");
        String input = ScannerWrapper.getInstance().next();
        return Date.readDate(input);
    }

    private PracticeStatus setStatus(){
        System.out.println("TESTING, HIDDEN, ACCESSIBLE");
        String input = ScannerWrapper.getInstance().next();
        switch (input){
            case "TESTING" :
                return PracticeStatus.TESTING;
            case "HIDDEN" :
                return PracticeStatus.HIDDEN;
            case "ACCESSIBLE" :
                return PracticeStatus.ACCESSIBLE;
            default:
                System.out.println("Wrong input");
                return null;
        }
    }
}
