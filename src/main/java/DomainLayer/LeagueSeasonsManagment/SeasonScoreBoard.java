package DomainLayer.LeagueSeasonsManagment;

import DomainLayer.Teams.Team;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.TimeUnit.*;

/**
 * this class represent the table of a season
 */
public class SeasonScoreBoard {

    private ArrayList<Team> teams;
    private ArrayList<Team> table;
    private IScorePolicy policy;
    private LocalDateTime firstGameDate;
    private int numOfWeeks;

    /**
     * constructor
     * @param teams
     * @param policy
     * @param firstGameDate
     * @param numOfWeeks
     */
    public SeasonScoreBoard(ArrayList<Team> teams, IScorePolicy policy, LocalDateTime firstGameDate, int numOfWeeks) {
        this.teams = teams;
        this.policy = policy;
        this.table = initTable();
        this.firstGameDate = firstGameDate;//todo put this line
        //this.firstGameDate = LocalDateTime.now().plus(15, ChronoUnit.SECONDS);//todo remove this line
        this.numOfWeeks = numOfWeeks;
        updateTable(); //class- update the table after every week game
    }

    /*****getters and setters*****/
    public ArrayList<Team> getTable() {
        return table;
    }

    public void setTable(ArrayList<Team> table) {
        this.table = table;
    }

    public IScorePolicy getPolicy() {
        return policy;
    }

    public void setPolicy(IScorePolicy policy) {
        this.policy = policy;
    }

    public Team getTeamByName(String name) {
        for (Team team : table) {
            if (team.getName().equals(name)) {
                return team;
            }
        }
        return null;
    }

    /**
     * init table- init the table with all teams and initial statistics
     * @return table
     */
    private ArrayList<Team> initTable() {
        ArrayList<Team> table = new ArrayList();
        for (Team team : teams) {
            //Statistics statistics= new Statistics(policy);
            team.getStatistics().setNewSeasonStatistics(policy);
            table.add(team);
        }
        return table;
    }

    /**
     * sort table by chosen score policy
     */
    public void sortByValue() {
        Collections.sort(table);
        showTable();
    }

    /**
     * print table- help function
     */
    public void showTable() {
        int i = 1;
        System.out.println("Table:");
        for (Team team : table) {
            System.out.println(i + ". Name: " + team.getName() + " | score: " + team.getStatistics().getScore() +
                    " | w:" + team.getStatistics().getWins() + " | d: " + team.getStatistics().getTie() + " | l: " + team.getStatistics().getLoses() +
                    " | goals:" + team.getStatistics().getGoals() +
                    " | goals c:" + team.getStatistics().getGc() + " | dif: " + team.getStatistics().getDif());
            i++;
        }
    }

    /**
     * this function scheduling the start of updating the table and using the updateTable class
     * to update every week after the game by number of teams
     */
    public void updateTable() {
        try {
            LocalDateTime timeToUpdate;
            timeToUpdate = firstGameDate.plus(5, ChronoUnit.MINUTES);//todo change to 2 hours
            LocalDateTime from = LocalDateTime.now();
            Duration duration = Duration.between(from, timeToUpdate);
            UpdateTable updateTable = new UpdateTable(duration.getSeconds(), this, new AtomicInteger(numOfWeeks));
            updateTable.beep();
        } catch (Exception e) {

        }
    }
}

/**
 * this class represent the time to update the score board
 */

class UpdateTable {

    private long delay;
    private AtomicInteger count;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private SeasonScoreBoard scoreBoard;

    public UpdateTable(Long tDelay, SeasonScoreBoard scoreBoard1, AtomicInteger numOfWeeks) {
        this.delay = tDelay;
        this.scoreBoard = scoreBoard1;
        this.count = numOfWeeks;
    }

    public void beep() {
        final Runnable beeper = new Runnable() {
            public void run() {
                count.getAndDecrement();
                scoreBoard.sortByValue();
                if (count.get() == 0) {
                    scheduler.shutdown();
                }
            }
        };
        final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(
                beeper, delay, 30, MINUTES);//todo change to 7 days
    }
}

