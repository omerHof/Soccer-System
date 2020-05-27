package DomainLayer.LeagueSeasonsManagment;

import DomainLayer.Games.Game;
import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.Teams.Team;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * this class get list of teams and create a scheduling for a season
 */
public class RandomTwoRoundsGamePolicy implements IGameInlayPolicy {
    private String name;
    private ArrayList<Team> ListTeam; // the initial list of teams
    private HashMap<Integer, ArrayList<Game>> listOfGames; // the results
    private LocalDateTime timeOfGame;
    private int day;

    /**
     * constructor
     * @param teams
     */
    public RandomTwoRoundsGamePolicy(ArrayList<Team> teams, int year) {
        this.name = "RandomTwoRoundsGamePolicy";
        this.ListTeam = teams;
        this.listOfGames = new HashMap<>();
        this.day = DBLocal.getInstance().getLeagues()+1;
        //this.timeOfGame = LocalDateTime.of(year, Month.JANUARY, day, 19, 0, 0);//todo
        timeOfGame = LocalDateTime.now().plus(2, ChronoUnit.MINUTES);//todo

    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * random round robin implementation
     * @param
     * @return the list of games
     */
    @Override
    public HashMap<Integer, ArrayList<Game>> gameInlayPolicyAlgoImplementation() {
        Collections.shuffle(ListTeam);
        IGameInlayPolicy policy = new TwoRoundsGamePolicy(ListTeam, timeOfGame.getYear());
        return policy.gameInlayPolicyAlgoImplementation();
    }
}
