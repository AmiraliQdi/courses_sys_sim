package ir.ac.kntu.recourses.tournaments;

import ir.ac.kntu.util.Date;

public class SpecialTournament extends Tournament{

    public SpecialTournament(String name, String adminUserName, int tournamentTime, TournamentType tournamentType, Date startingDate, Date endingDate) {
        super(name, adminUserName, tournamentTime, tournamentType,startingDate,endingDate);
        setMaxCustomers(100);
    }

    @Override
    public void addScores() {

    }

}
