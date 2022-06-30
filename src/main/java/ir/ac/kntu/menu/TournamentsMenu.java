package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.tournaments.NormalTournament;
import ir.ac.kntu.recourses.tournaments.PrivateTournament;
import ir.ac.kntu.recourses.tournaments.Tournament;
import ir.ac.kntu.storage.TournamentStorage;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;

public class TournamentsMenu implements Menu{

    private static TournamentsMenu instance = new TournamentsMenu();

    private TournamentsMenu() {

    }

    public static TournamentsMenu getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {
        OPTIONS.add("See joined tournaments");
        OPTIONS.add("Join new tournament");
        OPTIONS.add("Back");
        printInteractMenu();
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        switch (input) {
            case "See_joined_tournaments" :
                return seeJoinedTournaments();
            case "Join_new_tournament" :
                return joinNewTournament();
            case "Back":
                return CustomerMenu.getInstance();
            default:
                System.out.println("Wrong input");
                return TournamentsMenu.getInstance();
        }
    }

    private Menu joinNewTournament(){
        ArrayList<NormalTournament> normalTournaments = TournamentStorage.findNormalTournaments();
        ArrayList<PrivateTournament> privateTournaments = TournamentStorage.findInvitedTournaments(Main.getCustomer());
        for (NormalTournament normalTournament : normalTournaments){
            System.out.println("Normal tournament : " + normalTournament.getName() + "-" +  normalTournament.getStartingDate());
        }
        for (PrivateTournament privateTournament : privateTournaments){
            System.out.println("Private tournament : " + privateTournament.getName() + "-" +  privateTournament.getStartingDate());
        }
        System.out.println("Choose which tournament you wish to join");
        String input = ScannerWrapper.getInstance().next();
        for (NormalTournament normalTournament : normalTournaments){
            if (normalTournament.getName().equals(input)){
                normalTournament.register(Main.getCustomer());
                Main.getCustomer().addToJoinedTournaments(normalTournament);
                System.out.println("Joined " + normalTournament.getName() + "tournament!");
                return TournamentsMenu.getInstance();
            }
        }
        for (PrivateTournament privateTournament : privateTournaments){
            if (privateTournament.getName().equals(input)){
                privateTournament.register(Main.getCustomer());
                Main.getCustomer().addToJoinedTournaments(privateTournament);
                System.out.println("Joined " + privateTournament.getName() + "tournament");
                return TournamentsMenu.getInstance();
            }
        }
        System.out.println("Wrong tournament name!");
        return TournamentsMenu.getInstance();
    }

    private Menu seeJoinedTournaments(){
        for (Tournament tournament : Main.getCustomer().getJoinedTournaments()){
            tournament.smallPrintTournament();
        }
        System.out.println();
        System.out.println("Enter tournament name to enter :");
        String input = ScannerWrapper.getInstance().next();
        for (Tournament tournament : Main.getCustomer().getJoinedTournaments()){
            if (tournament.getName().equals(input)){
                Main.getCustomer().setCurrentTournament(tournament);
                Main.getCustomer().setCurrentPractice(tournament.getMainPractice());
                return WorkSpaceMenu.getInstance();
            }
        }
        System.out.println("Wrong name!");
        System.out.println();
        return seeJoinedTournaments();
    }
}
