package ir.ac.kntu.recourses.tournaments;

import ir.ac.kntu.recourses.Class;
import ir.ac.kntu.recourses.Practice;
import ir.ac.kntu.recourses.PracticeStatus;
import ir.ac.kntu.recourses.User;
import ir.ac.kntu.recourses.userTypes.Customer;
import ir.ac.kntu.storage.TournamentStorage;
import ir.ac.kntu.storage.UsersStorage;
import ir.ac.kntu.util.Date;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Tournament extends Class {

    private ArrayList<Customer> visibleForUsers;

    private int maxCustomers;

    private TournamentType tournamentType;

    private int tournamentTime;

    private Date startingDate;

    private Date endingDate;

    private Practice mainPractice;

    private ArrayList<User> topUsers;

    private boolean hasStarted = false;

    public Tournament(String name, String adminUserName, int tournamentTime, TournamentType tournamentType, Date startingDate, Date endingDate) {
        super(name, "#", adminUserName, 0);
        this.tournamentTime = tournamentTime;
        this.tournamentType = tournamentType;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        makeMainPractice();
        visibleForUsers = new ArrayList<>();
        if (tournamentType.equals(TournamentType.NORMAL)) {
            visibleForUsers.addAll(UsersStorage.getCustomers());
        }
        TournamentStorage.addTournament(this);
    }

    private void makeMainPractice() {
        Practice newPractice = new Practice("main", "main_practice", startingDate, endingDate, 0, 0);
        newPractice.setStatus(PracticeStatus.ACCESSIBLE);
        newPractice.setOwner(this);
        mainPractice = newPractice;
    }

    public void startTournament() {
        mainPractice.makeWorkMap(this);
        hasStarted = true;
    }

    public boolean isHasStarted() {
        return hasStarted;
    }

    public void setMaxCustomers(int maxCustomers) {
        this.maxCustomers = maxCustomers;
    }

    public int getMaxCustomers() {
        return maxCustomers;
    }

    public void closeTournament() {
        Map<User, Double> sortedSumMarks = mainPractice.getSumMarks().entrySet()
                .stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldV, newV) -> oldV, LinkedHashMap::new));
        topUsers = new ArrayList<>(sortedSumMarks.keySet());
        addScores();
        TournamentStorage.removeTournament(this);
        hasStarted = false;
    }

    public ArrayList<User> getTopUsers() {
        return topUsers;
    }

    public TournamentType getTournamentType() {
        return tournamentType;
    }

    public abstract void addScores();

    public void longPrint() {
        System.out.println(getName() + " | " + tournamentType + " | " + "Owner : " + getTeacherName());
        System.out.println("Starting date : " + startingDate + " | " + "Ending date :" + endingDate);
    }

    public void smallPrint() {
        System.out.println(getName() + " | " + tournamentType + " | " + "Owner : " + getTeacherName());
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public void setTournamentTime(int tournamentTime) {
        this.tournamentTime = tournamentTime;
    }

    public int getTournamentTime() {
        return tournamentTime;
    }

    public void setTournamentType(String newType) {
        switch (newType) {
            case "SPECIAL":
                tournamentType = TournamentType.SPECIAL;
            case "NORMAL":
                tournamentType = TournamentType.NORMAL;
            case "PRIVATE":
                tournamentType = TournamentType.PRIVATE;
            default:
                System.out.println("Wrong type!");
        }
    }

    public Practice getMainPractice() {
        return mainPractice;
    }

    public void setMainPractice(Practice mainPractice) {
        this.mainPractice = mainPractice;
    }

    public void addToVisible(Customer user) {
        visibleForUsers.add(user);
    }

    public ArrayList<Customer> getVisibleForUsers() {
        return visibleForUsers;
    }

    public void smallPrintTournament() {
        System.out.println(getName() + "-" + tournamentType + "-" + startingDate + "/" + endingDate);
    }

    public String toString() {
        return getName() + "-" + tournamentType + "-" + startingDate + "/" + endingDate;
    }
}
