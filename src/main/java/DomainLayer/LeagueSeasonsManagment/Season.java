package DomainLayer.LeagueSeasonsManagment;

import DomainLayer.Games.Game;
import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.Teams.Team;
import DomainLayer.Users.AssociationRepresentative;
import DomainLayer.Users.MainReferee;
import DomainLayer.Users.Referee;
import DomainLayer.Users.User;

import java.util.*;
import java.util.List;


/**
 * this class represent a season in the system.
 */
public class Season {

    private int year;
    private HashMap<Integer, ArrayList<Game>> allGames;
    private ArrayList<Team> allTeams;
    private ArrayList<Referee> allReferees;
    private ArrayList<AssociationRepresentative> allRepresentatives;
    private IGameInlayPolicy iGameInlayPolicy;
    private IScorePolicy iScorePolicy;
    private SeasonScoreBoard seasonScoreBoard;
    private DBLocal dbLocal;


    /**
     * constructor - with all season's details:
     *
     * @param year - of the new season
     * @param scorePolicy - of the new season
     * @param gamePolicy - of the new season
     * @param allTeams - list of DomainLayer.Teams - to assign to this season's games.
     * @param allReferees - list of Referees - to assign to this season's games.
     * @param allReps - list of AssociationRepresentatives - to assign to this season's games.
     */
    public Season(int year, ArrayList<Team> allTeams, ArrayList<Referee> allReferees,
                  ArrayList<AssociationRepresentative> allReps, String scorePolicy, String gamePolicy) {

        dbLocal = DBLocal.getInstance();
        this.year = year;
        this.allReferees = allReferees;
        this.allTeams = allTeams;
        this.allRepresentatives = allReps;

        setiGameInlayPolicy(gamePolicy);
        setIScorePolicy(scorePolicy);

        this.allGames = iGameInlayPolicy.gameInlayPolicyAlgoImplementation(); //adds list of games to each mahzor.

        this.seasonScoreBoard = new SeasonScoreBoard(allTeams, iScorePolicy, allGames.get(1).get(0).getTimeOfGame(), allGames.size());
    }

    /**
     * assign referees and association representatives to all games
     *
     * @param amount - how many standard referees to assign for each game.
     */
    public void assignUsersToGames(int amount) {

        if (allReferees != null && allRepresentatives != null) {

            for (int mahzor : allGames.keySet()) { //every week.
                ArrayList<Game> currMahzorGames = allGames.get(mahzor); //gets current mahzor's games.

                List<User> mahzorRefereesAndAsso = new LinkedList<>(); //to check constraints and doubles.
                Referee ref;

                for (Game game : currMahzorGames) {

                    for (int i = 0; i < amount; i++) {
                        ref = getRandomReferee();

                        while (mahzorRefereesAndAsso.contains(ref) || ref instanceof MainReferee) { // contains this referee already
                            ref = getRandomReferee(); //gets another one
                        }

                        ref.followThisGame(game); //adds both ways.
                        mahzorRefereesAndAsso.add(ref);
                    }

                    AssociationRepresentative asso = getRandomAsso();
                    while (mahzorRefereesAndAsso.contains(asso)) { //until gets a new one.
                        asso = getRandomAsso();
                    }
                    asso.followThisGame(game); //adds both ways.
                    mahzorRefereesAndAsso.add(asso);

                    MainReferee mainReferee = getRandomMainReferee();

                    while (mainReferee == null || mahzorRefereesAndAsso.contains(mainReferee)) { //until gets a new one.
                        mainReferee = getRandomMainReferee();
                    }
                    mainReferee.followThisGame(game); //adds both ways.
                    mahzorRefereesAndAsso.add(mainReferee);

                    // set teams' games:
                    Team away = game.getAwayTeam();
                    Team home = game.getHomeTeam();

                    away.addGame(game);
                    home.addGame(game);
                }
            }
        } else //no option.
            System.out.println(" cannot create a new season without referees nor association.");
    }

    /**
     * this function returns a random referee.
     *
     * @return Referee - to assign for a game.
     */
    private Referee getRandomReferee() {

        Random random = new Random();
        int rand = random.nextInt(allReferees.size());

        return allReferees.get(rand);
    }

    /**
     * this function returns a random AssociationRepresentative.
     *
     * @return AssociationRepresentative - to assign for a game.
     */
    private AssociationRepresentative getRandomAsso() {

        Random random = new Random();
        int rand = random.nextInt(allRepresentatives.size());

        return allRepresentatives.get(rand);
    }


    /**
     * this function returns a random MainReferee.
     *
     * @return MainReferee - to assign for a game.
     */
    private MainReferee getRandomMainReferee() {

        Random random = new Random();
        int rand = random.nextInt(allReferees.size());

        Referee toReturn = allReferees.get(rand);
        if (toReturn instanceof MainReferee)
            return (MainReferee) toReturn;

        return null;
    }


    /**********getters and setters**********/

    public int getYear() {
        return year;
    }

    public IGameInlayPolicy getiGameInlayPolicy() {
        return iGameInlayPolicy;
    }

    public IScorePolicy getiScorePolicy() {
        return iScorePolicy;
    }

    public void setYear(int newYear){
        this.year=newYear;
    }

    public void setAllTeams(ArrayList<Team> allTeams) {
        this.allTeams = allTeams;
    }

    public void setAllRepresentatives(ArrayList<AssociationRepresentative> allRepresentatives) {
        this.allRepresentatives = allRepresentatives;
    }

    public HashMap<Integer, ArrayList<Game>> getAllGames() {
        return allGames;
    }

    public void setAllGames(HashMap<Integer, ArrayList<Game>> allGames) {
        this.allGames = allGames;
    }


    public void setiGameInlayPolicy(String iGameInlayPolicy) {
        switch(iGameInlayPolicy) {
            case "TwoRoundsGamePolicy":
                this.iGameInlayPolicy = new TwoRoundsGamePolicy(this.allTeams,getYear());
                break;
            case "OneRoundGamePolicy":
                this.iGameInlayPolicy = new OneRoundGamePolicy(this.allTeams, getYear());
                break;
            case "RandomTwoRoundsGamePolicy":
                this.iGameInlayPolicy = new RandomTwoRoundsGamePolicy(this.allTeams, getYear());
                break;

            default:
                this.iGameInlayPolicy = new TwoRoundsGamePolicy(this.allTeams,getYear());
        }
    }

    public void setAllReferees(ArrayList<Referee> allReferees) {
        this.allReferees = allReferees;
    }

    public ArrayList<Team> getAllTeams() {
        return allTeams;
    }

    public List<Referee> getAllReferees() {
        return allReferees;
    }

    public ArrayList<AssociationRepresentative> getAllRepresentatives() {
        return allRepresentatives;
    }

    public void setIScorePolicy(String iScorePolicy) {

        switch(iScorePolicy) {
            case "RegularScorePolicy":
                this.iScorePolicy = new RegularScorePolicy();
                break;
            case "GoalScorePolicy":
                this.iScorePolicy = new GoalScorePolicy();
                break;
            default:
                this.iScorePolicy = new RegularScorePolicy();
        }
    }

    public SeasonScoreBoard getSeasonScoreBoard() {
        return seasonScoreBoard;
    }

    public void setSeasonScoreBoard(SeasonScoreBoard seasonScoreBoard) {
        this.seasonScoreBoard = seasonScoreBoard;
    }
}
