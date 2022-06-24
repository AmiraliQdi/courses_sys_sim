package ir.ac.kntu.recourses.tournaments;

import ir.ac.kntu.recourses.Class;
import ir.ac.kntu.recourses.Practice;
import ir.ac.kntu.recourses.PracticeStatus;
import ir.ac.kntu.recourses.User;
import ir.ac.kntu.storage.TournamentStorage;
import ir.ac.kntu.util.Date;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Tournament extends Class {

    private int maxCustomers;

    private TournamentType tournamentType;

    private int tournamentTime;

    private Date startingDate;

    private Date endingDate;

    private Practice mainPractice;

    private ArrayList<User> topUsers;

    public Tournament(String name, String adminUserName , int tournamentTime,TournamentType tournamentType,Date startingDate,Date endingDate) {
        super(name, "#", adminUserName ,0);
        this.tournamentTime = tournamentTime;
        this.tournamentType = tournamentType;
        setMaxCustomers();
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        makeMainPractice();
    }

    private void makeMainPractice(){
        Practice mainPractice = new Practice("main","main_practice",startingDate,endingDate,0,0);
        mainPractice.setStatus(PracticeStatus.ACCESSIBLE);
    }

    public void startTournament(){
        if (Date.CURRENT_DATE.isAfter(startingDate)){
            mainPractice.makeWorkMap(this);
        }
    }

    private void setMaxCustomers(){
        switch (tournamentType) {
            case NORMAL -> maxCustomers = 50;
            case PRIVATE -> maxCustomers = 20;
            case SPECIAL -> maxCustomers = 100;
        }
    }

    public void closeTournament(){
        Map<User,Double> sortedSumMarks = mainPractice.getSumMarks().entrySet()
                .stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,
                        (oldV,newV) -> oldV, LinkedHashMap::new));
        topUsers = new ArrayList<>(sortedSumMarks.keySet());
        addScores();
        TournamentStorage.removeTournament(this.getName());
    }

    public ArrayList<User> getTopUsers() {
        return topUsers;
    }

    public TournamentType getTournamentType() {
        return tournamentType;
    }

    public abstract void addScores();

}
