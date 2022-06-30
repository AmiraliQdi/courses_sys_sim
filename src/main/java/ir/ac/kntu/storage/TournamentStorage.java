package ir.ac.kntu.storage;

import ir.ac.kntu.recourses.User;
import ir.ac.kntu.recourses.tournaments.NormalTournament;
import ir.ac.kntu.recourses.tournaments.PrivateTournament;
import ir.ac.kntu.recourses.tournaments.Tournament;
import ir.ac.kntu.recourses.tournaments.TournamentType;
import ir.ac.kntu.recourses.userTypes.Customer;
import ir.ac.kntu.util.Date;

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

    public static ArrayList<PrivateTournament> findInvitedTournaments(Customer customer){
        ArrayList<PrivateTournament> result = new ArrayList<>();
        for (PrivateTournament privateTournament : getPrivateTournaments()){
            for (Customer searchCustomer : privateTournament.getVisibleForUsers()){
                if (searchCustomer.equals(customer)){
                    result.add(privateTournament);
                }
            }
        }
        return result;
    }

    public static ArrayList<PrivateTournament> getPrivateTournaments(){
        ArrayList<PrivateTournament> result = new ArrayList<>();
        for (Tournament tournament : tournaments){
            if (tournament.getTournamentType().equals(TournamentType.PRIVATE)){
                result.add((PrivateTournament) tournament);
            }
        }
        return result;
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

    public static ArrayList<Tournament> getTournaments() {
        return tournaments;
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

    public static ArrayList<Tournament> getClosedTournaments(){
        ArrayList<Tournament> result = new ArrayList<>();
        for (Tournament tournament : tournaments){
            if (tournament.getEndingDate().isAfter(Date.CURRENT_DATE)){
                result.add(tournament);
            }
        }
        if (result.isEmpty()){
            System.out.println("No tournaments founded");
            return null;
        } else {
            return result;
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

    public static void removeTournament(Tournament tournament){
        for (Customer customer : UsersStorage.getCustomers()) {
            customer.getJoinedTournaments().remove(tournament);
        }
        tournaments.remove(tournament);
    }
}
