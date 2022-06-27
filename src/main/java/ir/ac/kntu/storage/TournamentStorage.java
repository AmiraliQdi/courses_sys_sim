package ir.ac.kntu.storage;

import ir.ac.kntu.menu.TournamentMenu;
import ir.ac.kntu.recourses.Class;
import ir.ac.kntu.recourses.tournaments.NormalTournament;
import ir.ac.kntu.recourses.tournaments.Tournament;
import ir.ac.kntu.recourses.tournaments.TournamentType;

import java.util.ArrayList;

public class TournamentStorage {

    private static ArrayList<Tournament> tournaments = new ArrayList<>();

    public static Tournament findTournamentByName(String name) {
        for (Tournament tournament : tournaments){
            if (tournament.getName().equals(name)){
                return tournament;
            }
        }
        System.out.println("Wrong name");
        return null;
    }

    public static ArrayList<NormalTournament> findNormalTournaments(){
        ArrayList<NormalTournament> result = new ArrayList<>();
        for (Tournament tournament : tournaments){
            if (tournament.getTournamentType() == TournamentType.NORMAL){
                result.add((NormalTournament) tournament);
            }
        }
        return result;
    }

    private static boolean isAvailable(Tournament newTournament){
        for (Tournament targetTournament : tournaments){
            if (targetTournament.equals(newTournament)){
                return false;
            }
        }
        return true;
    }

    public static void addTournament(Tournament tournament){
        if (isAvailable(tournament)){
            tournaments.add(tournament);
        }
    }

    public static ArrayList<Tournament> findByOwner(String userName){
        ArrayList<Tournament> result = new ArrayList<>();
        for (Tournament tournament :tournaments){
            if (tournament.getTeacherName().equals(userName)){
                result.add(tournament);
            }
        }
        if (!result.isEmpty()) {
            return result;
        } else {
            System.out.println("No tournaments found by this user!");
            return null;
        }
    }

    public static void removeTournament(String name){
        tournaments.removeIf(tournament -> tournament.getName().equals(name));
    }
}
