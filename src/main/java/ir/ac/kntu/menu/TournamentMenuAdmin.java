package ir.ac.kntu.menu;

import ir.ac.kntu.Main;
import ir.ac.kntu.recourses.tournaments.*;
import ir.ac.kntu.storage.TournamentStorage;
import ir.ac.kntu.util.Date;
import ir.ac.kntu.util.ScannerWrapper;

import java.util.ArrayList;

public class TournamentMenuAdmin implements Menu{

    private static TournamentMenuAdmin instance = new TournamentMenuAdmin();

    private TournamentMenuAdmin(){

    }

    public static TournamentMenuAdmin getInstance() {
        return instance;
    }

    @Override
    public void printMenu() {
        OPTIONS.add("See all tournaments");
        OPTIONS.add("Create new tournament");
        OPTIONS.add("Back");
        printInteractMenu();
    }

    @Override
    public Menu handleMenu() {
        String input = ScannerWrapper.getInstance().next();
        switch (input) {
            case "See_all_tournaments" :
                System.out.println();
                ArrayList<Tournament> list = TournamentStorage.getTournaments();
                if (list.isEmpty()){
                    System.out.println("Empty!");
                } else {
                    for (Tournament tournament : list){
                        tournament.smallPrint();
                    }
                }
                System.out.println("Choose : ");
                input = ScannerWrapper.getInstance().next();
                Tournament result = findByName(input,list);
                if (result!=null){
                    Main.getAdmin().setManagingTournament(result);
                    return ManagingTournament.getInstance();
                } else {
                    System.out.println("Found no tournament with that name!");
                    return TournamentMenuAdmin.getInstance();
                }
            case "Create_new_tournament" :
                Tournament newTournament = makeNewTournament();
                Main.getAdmin().setManagingTournament(newTournament);
                return ManagingTournament.getInstance();
            case "Back" :
                return AdminMenu.getInstance();
            default:
                System.out.println("Wrong input!");
                return TournamentMenuAdmin.getInstance();
        }
    }

    private Tournament makeNewTournament() throws NullPointerException{
        System.out.println("Enter tournament name :");
        String name = ScannerWrapper.getInstance().next();
        System.out.println("Enter starting and ending date (exp : 1401/2/5-1401/3/3");
        ArrayList<Date> dates = Date.makeDates(ScannerWrapper.getInstance().next());
        System.out.println("Enter tournament duration (hours) : ");
        int duration = ScannerWrapper.getInstance().nextInt();
        System.out.println("Enter tournament type : (NORMAL,SPECIAL,PRIVATE)");
        TournamentType tournamentType;
        try {
            tournamentType = getType(ScannerWrapper.getInstance().next());
        } catch (NullPointerException e){
            System.out.println("Wrong input!");
            System.out.println("Try again");
            tournamentType = getType(ScannerWrapper.getInstance().next());
        }
        if (tournamentType == null){
            throw new NullPointerException();
        }
        switch (tournamentType) {
            case NORMAL -> {
                return normalTournament(name,dates,duration);
            }
            case SPECIAL -> {
                return specialTournament(name,dates,duration);
            }
            case PRIVATE -> {
                return privateTournament(name,dates,duration);
            }
            default -> {
                return null;
            }
        }
    }

    private NormalTournament normalTournament(String name,ArrayList<Date> dates,int duration){
        return new NormalTournament(name,Main.getAdmin().getUserName(),duration,TournamentType.NORMAL,dates.get(0),dates.get(1));
    }

    private PrivateTournament privateTournament(String name,ArrayList<Date> dates,int duration){
        return new PrivateTournament(name,Main.getAdmin().getUserName(),duration,TournamentType.PRIVATE,dates.get(0),dates.get(1));
    }

    private SpecialTournament specialTournament(String name,ArrayList<Date> dates,int duration){
        return new SpecialTournament(name,Main.getAdmin().getUserName(),duration,TournamentType.SPECIAL,dates.get(0),dates.get(1));
    }

    private Tournament makeNew(String name,ArrayList<Date> dates,TournamentType tournamentType,int duration) {
        switch (tournamentType) {
            case PRIVATE -> new PrivateTournament(name,Main.getAdmin().getUserName(),duration,TournamentType.PRIVATE,dates.get(0),dates.get(1));
            case SPECIAL -> new SpecialTournament(name,Main.getAdmin().getUserName(),duration,TournamentType.SPECIAL,dates.get(0),dates.get(1));
            case NORMAL -> new NormalTournament(name,Main.getAdmin().getUserName(),duration,TournamentType.NORMAL,dates.get(0),dates.get(1));
            default -> {
                return null;
            }
        }
        return null;
    }

    private TournamentType getType(String input) throws NullPointerException {
        return switch (input) {
            case "NORMAL" -> TournamentType.NORMAL;
            case "SPECIAL" -> TournamentType.SPECIAL;
            case "PRIVATE" -> TournamentType.PRIVATE;
            default -> throw new NullPointerException();
        };
    }

    private Tournament findByName(String name,ArrayList<Tournament> tournaments){
        for (Tournament tournament : tournaments){
            if (tournament.getName().equals(name)){
                return tournament;
            }
        }
        return null;
    }
}
