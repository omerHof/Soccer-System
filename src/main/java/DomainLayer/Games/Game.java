package DomainLayer.Games;

import DomainLayer.SystemLogic.MainSystem;
import DomainLayer.Teams.Stadium;
import DomainLayer.Teams.Statistics;
import DomainLayer.Teams.Team;
import DomainLayer.Users.Referee;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * this class represent a game. game have status and it change by the tine of the game.
 */
public class Game extends Observable {
    /**
     * *****the status that game can be:*******
     * preGame: before the game start
     * active: when the game playing
     * finish: when the game finished
     * close: 5 hours after the game finished
     */
    public enum gameStatus {
        preGame, active, finish, close
    }

    private gameStatus status;
    private Team homeTeam;
    private Team awayTeam;
    private String score;
    private ArrayList<Event> eventBook;
    private String finalReport;
    private LocalDateTime timeOfGame;
    private Timer timer;
    private Stadium stadium;
    private static int idCounter=0;
    private int id;


    /**
     * constructor
     *
     * @param homeTeam
     * @param awayTeam
     * @param timeOfGame
     */
    public Game(Team homeTeam, Team awayTeam, LocalDateTime timeOfGame) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.timeOfGame = timeOfGame;
        this.timer = new Timer();
        this.status = gameStatus.preGame;
        //setAlarms();//set alarm to Stakeholders about the game
        this.eventBook = new ArrayList<>();
        this.score = "0-0";
        this.stadium = homeTeam.getStadium();
        this.finalReport="FINAL REPORT:\n";
        setAlarms();

        idCounter++;
        id=idCounter;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * set the alarms before, after and on the game to all Stakeholders
     */
    public void setAlarms() {
        if(LocalDateTime.now().isBefore(timeOfGame.minus(1,ChronoUnit.MINUTES))) {//todo change as day before
            dayToGame();
            startGame();
            endGame();
            closeGame();


        }
        /*
        if(LocalDateTime.now().isBefore(timeOfGame.minus(2,ChronoUnit.MINUTES))) {//todo change as end game
            endGame();
        }
        if(LocalDateTime.now().isBefore(timeOfGame.minus(7,ChronoUnit.MINUTES))) {//todo change as close game
            closeGame();
        }*/
    }

    public void setChange(){
        setChanged();
    }

    /**
     * set alarms day before the game
     */
    private void dayToGame() {
        LocalDateTime dayBefore = timeOfGame.minus(1, ChronoUnit.MINUTES);//todo change seconds to days
        DayToGame dayToGame = new DayToGame(homeTeam, awayTeam, this);
        LocalDateTime from = LocalDateTime.now();
        Duration duration = Duration.between(from, dayBefore);
        timer.schedule(dayToGame, duration.getSeconds() * 1000);
        setChanged();
    }

    /**
     * set alarms when the game start
     */
    private void startGame() {
        LocalDateTime GameTime = timeOfGame;
        StartGame startGame = new StartGame(homeTeam, awayTeam, this);
        LocalDateTime from = LocalDateTime.now();
        Duration duration = Duration.between(from, GameTime);
        timer.schedule(startGame, duration.getSeconds() * 1000);
    }

    /**
     * set alarms when the game start
     */
    private void endGame() {
        LocalDateTime GameEndTime = timeOfGame.plus(2, ChronoUnit.MINUTES);//todo change to 90 minutes
        EndGame endGame = new EndGame(homeTeam, awayTeam, this, score);
        LocalDateTime from = LocalDateTime.now();
        Duration duration = Duration.between(from, GameEndTime);
        timer.schedule(endGame, duration.getSeconds() * 1000);
    }

    /**
     * set alarms when the game close
     */
    private void closeGame() {
        LocalDateTime closeGameTime = timeOfGame.plus(7, ChronoUnit.MINUTES);//todo change to 6.5 hours
        CloseGame closeGame = new CloseGame(homeTeam, awayTeam, this, score);
        LocalDateTime from = LocalDateTime.now();
        Duration duration = Duration.between(from, closeGameTime);
        timer.schedule(closeGame, duration.getSeconds() * 1000);
    }

    /**********getters and setters**********/

    public LocalDateTime getTimeOfGame() {
        return timeOfGame;
    }

    public void setTimeOfGame(LocalDateTime timeOfGame) {
        this.timeOfGame = timeOfGame;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public LocalDateTime getGameDate() {
        return timeOfGame;
    }

    public gameStatus getStatus() { return status; }

    public String getScore() {
        return score;
    }

    public ArrayList<Event> getEventBook() {
        return eventBook;
    }

    public void setEventBook(ArrayList<Event> eventBook) {
        this.eventBook = eventBook;
    }

    public String getFinalReport() {
        return finalReport;
    }

    public void setFinalReport(String finalReport) {
        //this.finalReport = "FINAL REPORT:\n"+finalReport;
        this.finalReport = finalReport;
    }
    public void setFinalReportEndGame(String finalReport) {
        this.finalReport = "FINAL REPORT:\n"+finalReport;
        //this.finalReport = finalReport;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setGameDate(LocalDateTime gameDate) {
        this.timeOfGame = gameDate;
    }

    public void setScore(String score) {
        this.score = score;
        if(homeTeam.getPage()!=null) {
            homeTeam.getPage().setChange();
            homeTeam.getPage().notifyObservers("GOAL!!!! the score is "+score+" in the game between: "+homeTeam.getName()+" and "+awayTeam.getName());
        }
        if(awayTeam.getPage()!=null) {
            awayTeam.getPage().setChange();
            awayTeam.getPage().notifyObservers("GOAL!!!! the score is "+score+" in the game between: "+homeTeam.getName()+" and "+awayTeam.getName());
        }
    }

    public void setStatus(gameStatus status) {
        this.status = status;
    }

    public void addEvent(Event event) {
        eventBook.add(event);
    }
}

/**
 * this class represent the day before a game
 */
class DayToGame extends TimerTask {
    private List<Referee> referees;
    private Team homeTeam;
    private Team awayTeam;
    private Game game;

    /**
     * constructor
     * @param homeTeam
     * @param awayTeam
     */
    public DayToGame(Team homeTeam, Team awayTeam, Game game) {

        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.game= game;
    }

    @Override
    public void run() {
        game.setChange();

        if(homeTeam.getPage()!=null) {
            homeTeam.getPage().setChange();
            homeTeam.getPage().notifyObservers("DayToGame between: " + homeTeam.getName() + " and " + awayTeam.getName());
        }
        if(awayTeam.getPage()!=null) {
            awayTeam.getPage().setChange();
            awayTeam.getPage().notifyObservers("DayToGame between: " + homeTeam.getName() + " and " + awayTeam.getName());
        }

        game.notifyObservers("Day To Game you are assigned to between: "+homeTeam.getName()+" and "+awayTeam.getName());
        MainSystem.LOG.info("The game between: " + game.getHomeTeam().getName() + " and " + game.getAwayTeam().getName() + " will start in 24 hours!");
    }
}

/**
 * this class represent the start of a game
 */
class StartGame extends TimerTask {
    private Team homeTeam;
    private Team awayTeam;
    private Game game;

    /**
     * constructor
     * @param homeTeam
     * @param awayTeam
     */
    public StartGame(Team homeTeam, Team awayTeam, Game game) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.game = game;
    }

    @Override
    public void run() {
        if(homeTeam.getPage()!=null) {
            homeTeam.getPage().setChange();
            homeTeam.getPage().notifyObservers("game Start between: " + homeTeam.getName() + " and " + awayTeam.getName());
        }
        if(awayTeam.getPage()!=null) {
            awayTeam.getPage().setChange();
            awayTeam.getPage().notifyObservers("game Start between: " + homeTeam.getName() + " and " + awayTeam.getName());
        }
        game.setStatus(Game.gameStatus.active);
        MainSystem.LOG.info("The game between: " + game.getHomeTeam().getName() + " and " + game.getAwayTeam().getName() + " started");
    }
}

/**
 * this class represent the end of a game
 */
class EndGame extends TimerTask {
    private Team homeTeam;
    private Team awayTeam;
    private Game game;
    private String score;
    private int homeTeamGoals;
    private int awayTeamGoals;

    /**
     * constructor
     * @param homeTeam
     * @param awayTeam
     * @param game
     * @param score
     */
    public EndGame(Team homeTeam, Team awayTeam, Game game, String score) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.game = game;
        this.score = score;
        this.homeTeamGoals = 0;
        this.awayTeamGoals = 0;
    }

    @Override
    public void run() {
        game.setChange();

        game.setStatus(Game.gameStatus.finish);
        double moneyFromGame=homeTeam.getStadium().getCapacity()*homeTeam.getStadium().getPrice();
        homeTeam.setBudget(homeTeam.getBudget()+moneyFromGame);
        Random rand = new Random();//todo remove
        int randomNum = rand.nextInt((6 - 0) + 1) + 0;//todo remove
        int randomNum2 = rand.nextInt((6 - 0) + 1) + 0;//todo remove
        game.setScore(randomNum + "-" + randomNum2);//todo remove
        setStatistic();
        if(homeTeam.getPage()!=null) {
            homeTeam.getPage().setChange();
            homeTeam.getPage().notifyObservers("End game between: " + homeTeam.getName() + " and " + awayTeam.getName() + " in a score: " + this.game.getScore());
        }
        if(awayTeam.getPage()!=null) {
            awayTeam.getPage().setChange();
            awayTeam.getPage().notifyObservers("End game between: " + homeTeam.getName() + " and " + awayTeam.getName() + " in a score: " + this.game.getScore());
        }
        game.setFinalReportEndGame(eventListToReport(game.getEventBook()));
        MainSystem.LOG.info("The game between: " + game.getHomeTeam().getName() + " and " + game.getAwayTeam().getName() + " ended. the score: " + game.getScore());
    }

    /**
     * set the statistic from the game to the teams
     */
    private void setStatistic() {
        String[] result = game.getScore().split("-");
        homeTeamGoals = Integer.parseInt(result[0]);
        awayTeamGoals = Integer.parseInt(result[1]);
        Statistics homeTeamStatistics = homeTeam.getStatistics();
        Statistics awayTeamStatistics = awayTeam.getStatistics();

        homeTeamStatistics.setGoals(homeTeamGoals);
        homeTeamStatistics.setGc(awayTeamGoals);
        awayTeamStatistics.setGoals(awayTeamGoals);
        awayTeamStatistics.setGc(homeTeamGoals);

        if (homeTeamGoals > awayTeamGoals) {
            homeTeamStatistics.setWins();
            awayTeamStatistics.setLoses();
        } else if (homeTeamGoals < awayTeamGoals) {
            awayTeamStatistics.setWins();
            homeTeamStatistics.setLoses();
        } else {
            homeTeamStatistics.setTie();
            awayTeamStatistics.setTie();
        }
        homeTeam.setStatistics(homeTeamStatistics);
        awayTeam.setStatistics(awayTeamStatistics);
    }

    /**
     * create the report from the event list
     * @param eventBook
     * @return report
     */
    private String eventListToReport(ArrayList<Event> eventBook) {
        String report = "";
        for (Event event : eventBook) {
            report += event.evenToString() + "\n";
        }
        return report;
    }
}

/**
 * this class represent 5 hours after a game
 */
class CloseGame extends TimerTask {
    private Team homeTeam;
    private Team awayTeam;
    private Game game;
    private String score;

    /**
     * constructor
     * @param homeTeam
     * @param awayTeam
     * @param game
     */
    public CloseGame(Team homeTeam, Team awayTeam, Game game, String score) {
        this.game = game;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = score;
    }

    @Override
    public void run() {
        game.setStatus(Game.gameStatus.close);
        MainSystem.LOG.info("The game between: " + game.getHomeTeam().getName() + " and " + game.getAwayTeam().getName() + " is close");
    }
}