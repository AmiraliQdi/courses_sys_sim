package ir.ac.kntu.recourses.tournaments;

import ir.ac.kntu.recourses.User;
import ir.ac.kntu.recourses.userTypes.Customer;
import ir.ac.kntu.util.Date;

import java.util.ArrayList;

public class PrivateTournament extends Tournament {

    private static final int REWARD_SCORE = 10;

    private static int rewardsCount = 3;

    public PrivateTournament(String name, String adminUserName, int tournamentTime, TournamentType tournamentType, Date startingDate, Date endingDate) {
        super(name, adminUserName, tournamentTime, tournamentType, startingDate, endingDate);
        setMaxCustomers(20);
    }

    @Override
    public void addScores() {
        ArrayList<Customer> customers = new ArrayList<>();
        for (User user : getTopUsers()) {
            customers.add((Customer) user);
        }
        if (getTopUsers().size() < rewardsCount) {
            rewardsCount = getTopUsers().size();
        }
        for (int i = 0; i < rewardsCount; i++) {
            customers.get(i).addScore(REWARD_SCORE);
        }
    }
}
