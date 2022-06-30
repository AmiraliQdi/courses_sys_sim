package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.tournaments.Tournament;
import ir.ac.kntu.storage.TournamentStorage;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;

public class GuestMenu implements Menu{

    private static GuestMenu instance = new GuestMenu();

    public static GuestMenu getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {
        OPTIONS.add("Question bank");
        OPTIONS.add("Closed tournaments");
        OPTIONS.add("Back");
        printInteractMenu();
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        switch (input) {
            case "Question_bank" :
                return QuestionBankMenuGuest.getInstance();
            case "Closed_tournaments" :
                return closedTournaments();
            case "Back" :
                return Logging.getInstance();
            default:
                System.out.println("Wrong input");
                return GuestMenu.getInstance();
        }
    }

    private Menu closedTournaments(){
        ArrayList<Tournament> result = TournamentStorage.getClosedTournaments();
        for (Tournament tournament : result) {
            tournament.smallPrintTournament();
        }
        System.out.println();
        System.out.println("Enter tournament name to enter :");
        String input = ScannerWrapper.getInstance().next();
        for (Tournament tournament : result){
            if (tournament.getName().equals(input)){
                Main.getGuest().setCurrentTournament(tournament);
                Main.getGuest().setCurrentPractice(tournament.getMainPractice());
                return TournamentGuest.getInstance();
            }
        }
        System.out.println("Wrong name!");
        System.out.println();
        return GuestMenu.getInstance();
    }
}
