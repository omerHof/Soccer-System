package DomainLayer.LeagueSeasonsManagment;

import DomainLayer.Games.Game;
import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.Teams.Team;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * this class get list of teams and create a scheduling for a season
 */
public class TwoRoundsGamePolicy implements IGameInlayPolicy {

    private String name;
    private ArrayList<Team> ListTeam; // the initial list of teams
    private HashMap<Integer, ArrayList<Game>> listOfGames; // the results
    private LocalDateTime timeOfGame;
    private int day;
    /**
     * constructor
     * @param teams
     * @param year
     */
    public TwoRoundsGamePolicy(ArrayList<Team> teams, int year) {
        name = "TwoRoundsGamePolicy";
        this.ListTeam = teams;
        this.listOfGames = new HashMap<>();
        this.day = DBLocal.getInstance().getLeagues()+1;
        //timeOfGame = LocalDateTime.of(year, Month.JANUARY,day,19,0,0);//todo add to code this line
        timeOfGame = LocalDateTime.now().plus(2, ChronoUnit.MINUTES);
    }

    /*****getters and setters*****/
    @Override
    public String getName() {
        return name;
    }

    /**
     * round robin implementation- from list of team create list of games
     * @return list of games
     */
    @Override
    public HashMap<Integer, ArrayList<Game>> gameInlayPolicyAlgoImplementation() {

        if (ListTeam.size() % 2 != 0) //check odd number of teams
        {
            System.out.println("odd number of teams");
            return null;
        }

        int rounds = (ListTeam.size() - 1); // Days needed to complete tournament

        ArrayList<Team> teams = new ArrayList<>();

        for (Team t : ListTeam) { //copy the list of teams
            teams.add(t);
        }
        teams.remove(0);

        int teamsSize = teams.size();
        int start = 0;
        addGames(start, rounds, teams, false);
        addGames(teamsSize, teamsSize * 2, teams, true);

        return listOfGames;
    }

    /**
     * this function add rounds and games of half season
     * @param start-        the initial round
     * @param rounds-       the number of games each team playing in half season
     * @param teams-        list of teams
     * @param secondSeason- if it's first/second season
     */
    private void addGames(int start, int rounds, ArrayList<Team> teams, boolean secondSeason) {
        int teamsSize = teams.size();
        for (int day = start; day < rounds; day++) {
            ArrayList<Game> games = new ArrayList<>();
            Team homeTeam;
            Team awayTeam;
            int teamIdx = day % teamsSize;
            if (!secondSeason) {
                homeTeam = teams.get(teamIdx);
                awayTeam = ListTeam.get(0);
            } else {
                homeTeam = ListTeam.get(0);
                awayTeam = teams.get(teamIdx);
            }
            Game game = new Game(homeTeam, awayTeam, timeOfGame);
            games.add(game);

            for (int idx = 1; idx < ListTeam.size() / 2; idx++) {
                int firstTeam = (day + idx) % teamsSize;
                int secondTeam = (day + teamsSize - idx) % teamsSize;
                if (!secondSeason) {
                    homeTeam = teams.get(firstTeam);
                    awayTeam = teams.get(secondTeam);
                } else {
                    homeTeam = teams.get(secondTeam);
                    awayTeam = teams.get(firstTeam);
                }
                game = new Game(homeTeam, awayTeam, timeOfGame);
                games.add(game);
            }
            listOfGames.put(day + 1, games);
            timeOfGame = timeOfGame.plus(30, ChronoUnit.MINUTES);//todo change to 1 week
        }
    }
}