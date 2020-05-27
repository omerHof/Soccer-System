package DomainLayer.LeagueSeasonsManagment;

import DomainLayer.Games.Game;
import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.Teams.Team;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * this class get list of teams and create a scheduling for a season
 */
public class OneRoundGamePolicy implements IGameInlayPolicy {

    private String name;
    private ArrayList<Team> ListTeam; // the initial list of teams
    private HashMap<Integer, ArrayList<Game>> tempListOfGames; // the results
    private HashMap<Integer, ArrayList<Game>> listOfGames; // the results
    private LocalDateTime timeOfGame;
    private int day;
    /**
     * constructor
     * @param teams
     */
    public OneRoundGamePolicy(ArrayList<Team> teams, int year) {
        this.name = "OneRoundGamePolicy";
        this.ListTeam = teams;
        this.listOfGames = new HashMap<>();
        this.tempListOfGames = new HashMap<>();
        this.day = DBLocal.getInstance().getLeagues()+1;
        //this.timeOfGame = LocalDateTime.of(year, Month.JANUARY, day, 19, 0, 0);//todo add to code this line
        timeOfGame = LocalDateTime.now().plus(2, ChronoUnit.MINUTES);
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * round robin implementation
     * @return the list of games
     */
    @Override
    public HashMap<Integer, ArrayList<Game>> gameInlayPolicyAlgoImplementation() {
        Collections.shuffle(ListTeam);
        IGameInlayPolicy policy = new TwoRoundsGamePolicy(ListTeam, timeOfGame.getYear());
        tempListOfGames = policy.gameInlayPolicyAlgoImplementation();
        Iterator it = tempListOfGames.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if ((int) pair.getKey() < ListTeam.size()) {
                listOfGames.put((int) pair.getKey(), (ArrayList<Game>) pair.getValue());
            }
        }
        return listOfGames;
    }
}




