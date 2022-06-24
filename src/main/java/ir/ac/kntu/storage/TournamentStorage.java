package ir.ac.kntu.storage;

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

    public static void removeTournament(String name){
        tournaments.removeIf(tournament -> tournament.getName().equals(name));
    }
}
