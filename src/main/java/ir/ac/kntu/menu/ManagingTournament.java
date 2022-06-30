package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.User;
import ir.ac.kntu.recourses.tournaments.Tournament;
import ir.ac.kntu.recourses.userTypes.Customer;
import ir.ac.kntu.storage.ClassesStorage;
import ir.ac.kntu.storage.PrePrintedStrings;
import ir.ac.kntu.storage.TournamentStorage;
import ir.ac.kntu.storage.UsersStorage;
import ir.ac.kntu.util.Date;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;
import java.util.Optional;

public class ManagingTournament implements Menu{

    private static ManagingTournament instance = new ManagingTournament();

    private ManagingTournament(){

    }

    public static ManagingTournament getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {
        Tournament tournament = Main.getAdmin().getManagingTournament();
        OPTIONS.add("Change name | " + tournament.getName());
        OPTIONS.add("Change type | " + tournament.getTournamentType());
        OPTIONS.add("Change dates | " + tournament.getStartingDate() + " | " + tournament.getEndingDate());
        OPTIONS.add("Change duration | " + tournament.getTournamentTime());
        OPTIONS.add("Start tournament");
        OPTIONS.add("End tournament");
        OPTIONS.add("Questions");
        OPTIONS.add("Students");
        OPTIONS.add("Top makrs");
        OPTIONS.add("Delete");
        OPTIONS.add("Back");
        printInteractMenu();
    }

    @Override
    public Menu handleMenu() {
        Tournament tournament = Main.getAdmin().getManagingTournament();
        String input = ScannerWrapper.getInstance().next();
        switch (input) {
            case "Top_marks":
                System.out.println();
                for (User user : tournament.getTopUsers()){
                    System.out.println(user);
                }
            case "Delete " :
                TournamentStorage.removeTournament(Main.getAdmin().getManagingTournament());
            case "Change_name" :
                changeName(tournament);
            case "Change_type" :
                changeType(tournament);
            case "Change dates" :
                changeDates(tournament);
            case "Change_duration" :
                changeDuration(tournament);
            case "Start_tournament" :
                tournament.startTournament();
                System.out.println("Tournament has started");
                return ManagingTournament.getInstance();
            case "End_tournament" :
                tournament.closeTournament();
                System.out.println("Tournament closed!");
                return ManagingTournament.getInstance();
            case "Questions" :
                Main.getAdmin().setCurrentPractice(tournament.getMainPractice());
                return TournamentMainPracticeEdit.getInstance();
            case "Students" :
                return students(tournament);
            case "Back" :
                return AdminMenu.getInstance();
            default:
                System.out.println("Wrong input!");
                return ManagingTournament.getInstance();
        }
    }

    private Menu students(Tournament tournament){
        System.out.println();
        System.out.println(PrePrintedStrings.START_MENU);
        System.out.println("1)See registered students");
        System.out.println("2)Add new student");
        System.out.println("3)Back");
        System.out.println(PrePrintedStrings.END_MENU);
        System.out.println();
        String input = ScannerWrapper.getInstance().next();
        switch (input) {
            case "See_registered_students" :
                for (User user : Main.getAdmin().getManagingTournament().getRegisteredUsers()){
                    System.out.println(user.getName() + " " + user.getUserType());
                }
                return ManagingTournament.getInstance();
            case "Add_new_student" :
                return addNewStudent(tournament);
            case "Back" :
                return ManagingTournament.getInstance();
            default:
                System.out.println("Wrong input");
                return ManagingTournament.getInstance();
        }
    }

    private Menu addNewStudent(Tournament tournament){
        System.out.println();
        System.out.println(PrePrintedStrings.START_MENU);
        System.out.println("1)Add with name");
        System.out.println("2)Add with national number");
        System.out.println("3)Add with email");
        System.out.println(PrePrintedStrings.END_MENU);
        System.out.println();
        String input = ScannerWrapper.getInstance().next();
        switch (input) {
            case "Add_with_name" :
                System.out.println("Enter student name :");
                String studentName = ScannerWrapper.getInstance().next();
                User user = UsersStorage.findByName(studentName);
                tournament.addToVisible((Customer) user);
                return ManagingTournament.getInstance();
            case "Add_with_national_number" :
                System.out.println("Enter student national_number :");
                String studentNationalNumber = ScannerWrapper.getInstance().next();
                User user1 = UsersStorage.findByNationalNumber(studentNationalNumber);
                tournament.addToVisible((Customer) user1);
                return ManagingTournament.getInstance();
            case "Add_with_email" :
                System.out.println("Enter student email :");
                String studentEmail = ScannerWrapper.getInstance().next();
                User user2 = UsersStorage.findByEmail(studentEmail);
                tournament.addToVisible((Customer) user2);
                return ManagingTournament.getInstance();
            default:
                return ManagingTournament.getInstance();
        }
    }

    private Menu changeName(Tournament tournament){
        System.out.println("Enter new name : ");
        tournament.setName(ScannerWrapper.getInstance().next());
        return ManagingTournament.getInstance();
    }

    private Menu changeType(Tournament tournament){
        System.out.println("Enter new type");
        tournament.setTournamentType(ScannerWrapper.getInstance().next());
        return ManagingTournament.getInstance();
    }

    private Menu changeDates(Tournament tournament){
        System.out.println("Enter new dates : (exp: 1401/3/5-1401/4/6)");
        ArrayList<Date> newDates = Date.makeDates(ScannerWrapper.getInstance().next());
        tournament.setStartingDate(newDates.get(0));
        tournament.setEndingDate(newDates.get(1));
        return ManagingTournament.getInstance();
    }

    private Menu changeDuration(Tournament tournament){
        System.out.println("Enter new duration");
        tournament.setTournamentTime(ScannerWrapper.getInstance().nextInt());
        return ManagingTournament.getInstance();
    }
}
