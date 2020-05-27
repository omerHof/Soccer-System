package DomainLayer.Users;

import DomainLayer.Games.Event;
import DomainLayer.Games.Game;
import DomainLayer.LeagueSeasonsManagment.*;
import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.SystemLogic.MainSystem;
import DomainLayer.SystemLogic.Notification;
import DomainLayer.Teams.Team;
import DomainLayer.UserGenerator.PremiumUserGenerator;
import DomainLayer.UserGenerator.SimpleUserGenerator;

import java.util.*;

/**
 * this class represent an AssociationRepresentative. He has a lot of permissions in the system.
 */
public class AssociationRepresentative extends User implements Observer {

    private static int numOfApprovals = 0 ;
    private DBLocal dbLocal = DBLocal.getInstance();
    private List<Game> myGames;
    private ArrayList<Integer> gamesId;

    /**
     * constructor - all tha person's details:
     *
     * @param userName
     * @param password
     * @param fullName
     * @param userEmail
     */
    public AssociationRepresentative(String userName, String password, String fullName,String userEmail) {
        this.userName = userName;
        this.password = password;
        this.userFullName = fullName;
        this.userEmail = userEmail;
        myGames = new LinkedList<>();
    }

    public ArrayList<Integer> getGamesId() {
        return gamesId;
    }

    public void setGamesId(ArrayList<Integer> gamesId) {
        this.gamesId = gamesId;
    }

    public List<Game> getMyGames() {
        return myGames;
    }


    /**
     * this function randomly approve users that want to join the system.
     * @return - true if approved, false if not.
     */
    public boolean approveRegistration(String fullName, String role){ //random (symbolic) function

        if (numOfApprovals < 9) {
            numOfApprovals++;
            return true;
        }

        // after 9 approvals.
        numOfApprovals=0; //begins from the start.
        return false;
    }

    ////////////////////////////// USE CASE 9.1 //////////////////////////////
    public boolean addLeague (String leagueName, int numOfTeams){

        League newLeague = new League(leagueName, numOfTeams);
        if (dbLocal.addLeague(newLeague)){ //this league does not exist yet. OK.
            MainSystem.LOG.info("new league: " + leagueName + " were added.");
            return true;
        }
        else
            return false;
    }

    ////////////////////////////// USE CASE 9.2 //////////////////////////////
    /**
     * this function adds (and creates) a new season to an existing league
     * @param leagueName - of the league we want to add to
     * @param year - of the new season
     * @param scorePolicy - of the new season
     * @param gamePolicy - of the new season
     * @param teams - list of Strings of the teams' names - to find in DBLocal and add to this season's games.
     * @param referees - list of Strings of the referees' names - to find in DBLocal and add to this season's games.
     * @param representatives - list of Strings of the representatives' names - to find in DBLocal and add to this season's games.
     * @return string with the function's outcome. - success, or any type of problem occurred.
     */
    public String addSeasonToLeague (String leagueName, int year, String scorePolicy, String gamePolicy, List<String> teams, List<String> referees, List<String> representatives){

        if (teams==null || referees== null || representatives==null || (referees.size() < (teams.size()*1.5)))
            return "couldn't create a new season: " + year + " - not enough referees/representatives";

        ArrayList<Team> allTeams;
        ArrayList<Referee> allReferees;
        ArrayList<AssociationRepresentative> allReps;

        //sets all users and teams themselves from DBLocal (out of strings)
        allTeams = setLeagueTeams(teams);
        allReferees = setLeagueReferees(referees);
        allReps = setLeagueRepresentatives(representatives);

        int countMainRefereeConstraint = 0;
        for (int i = 0; i < allReferees.size(); i++) {
            if (allReferees.get(i) instanceof MainReferee)
                countMainRefereeConstraint++;
        }

        if(countMainRefereeConstraint < (teams.size()/2)) //must have at least half on the teams' amount in the season.
            return "couldn't create a new season: " + year + " - not enough main referees";

        Season newSeason;

        if(scorePolicy!=null && gamePolicy!= null) {
            newSeason = new Season(year, allTeams, allReferees, allReps, scorePolicy, gamePolicy); //constructor
            newSeason.assignUsersToGames(3); //assign 3 referees for each game, 1 main referee, and 1 association rep.

            if (newSeason != null) {
                dbLocal.addSeason(leagueName, newSeason); //adds the season to DBLocal.
                MainSystem.LOG.info("new season: " + year + " was added to league: " + leagueName + ".");
                return "new season: " + year + " was added to league: " + leagueName + ".";
            }
            else
                return "couldn't create a new season here.";
        }

        else
            return "couldn't create a new season here.";
    }

    /**
     * adds all associationRepresentative to this season - help function
     * @param representativesNames - list of AssociationRepresentative's names
     */
    private ArrayList<AssociationRepresentative> setLeagueRepresentatives(List<String> representativesNames) {

        ArrayList<AssociationRepresentative> allReps = new ArrayList<>();

        if (representativesNames != null) {

            for (String representative : representativesNames) {
                User currRepresentative = dbLocal.getUserByFullName(representative);

                if(currRepresentative instanceof AssociationRepresentative)
                    allReps.add((AssociationRepresentative)currRepresentative);
            }
            return allReps;
            //MainSystem.LOG.info("Associations Representatives were added to league: " + leagueName + ", season: " + season.getYear());
        }

        else{
            return null;
        }
    }

    /**
     * adds all teams to this season - help function
     * @param teamsNames - list of teams' names
     */
    private ArrayList<Team> setLeagueTeams(List<String> teamsNames) {

        ArrayList<Team> teams = new ArrayList<>();
        if (teamsNames!= null) {

            for (String team : teamsNames) {
                Team currTeam = dbLocal.getTeam(team);

                if(currTeam.getStatus().equals(Team.teamStatus.active) && !currTeam.getCoaches().isEmpty() && !currTeam.getPlayers().isEmpty()) //checks that the team is legal.
                    teams.add(currTeam);
            }
            return teams;
            //MainSystem.LOG.info("DomainLayer.Teams were added to league: " + leagueName + ", season: " + season.getYear());
        }
        else {
            return null;
        }
    }

    ////////////////////////////// USE CASE 9.3.1 //////////////////////////////
    /**
     * change this fan's status to be a referee.
     * @param fullName - of the fan user we want to nominate as a referee.
     * @return true if success, false if not.
     */
    public boolean addReferee (String fullName){

        if(dbLocal.getUserByFullName(fullName)!= null) { //checks whether this referee already exists in the DBLocal.

            Fan oldFan = (Fan) dbLocal.getUserByFullName(fullName); //gets fan itself
            PremiumUserGenerator premiumUserG = new PremiumUserGenerator();

            // creates a referee, based on the fan's attributes.
            Referee newReferee = (Referee) premiumUserG.generate(oldFan.userName, oldFan.password, "onlyChangeStatus", "Referee", fullName,
                    oldFan.userEmail, null, "linesmen", "", "");

            dbLocal.removeUser(oldFan.userName); //removes fan from DBLocal
            dbLocal.addUser(newReferee); //adds referee to DBLocal.

            Notification notification = new Notification(this, "Congrats! You were added as a referee to the system.", newReferee);
            notification.send(); //send a notification to this added referee.

            MainSystem.LOG.info("the fan: " + oldFan.getUserName() + " became a referee.");
            return true;
        }

        else {
            //System.out.println("this user does not exist in the system.");
            return false;
        }
    }


    ////////////////////////////// USE CASE 9.3.2 //////////////////////////////
    /**
     * change this referee's status to be a simple fan.
     * @param fullName - of the referee user we want to save as a fan.
     * @return true if success, false if not.
     */
    public boolean removeReferee (String fullName){

        if( dbLocal.getUserByFullName(fullName) != null ) { //checks whether this referee already exists in the DBLocal.

            Referee oldReferee = (Referee) dbLocal.getUserByFullName(fullName); //gets referee itself
            SimpleUserGenerator simpleUserG = new SimpleUserGenerator();

            Fan newFan = (Fan) simpleUserG.generate(oldReferee.userName, oldReferee.password, "", "", oldReferee.userFullName,
                    oldReferee.userEmail,  null, "", "", ""); //creates a new one.

            dbLocal.removeUser(oldReferee.userName); //removes referee
            dbLocal.addUser(newFan); //adds as a fan.

            Notification notification = new Notification(this, "Unfortunately You are no longer a referee in the system, your status is now a fan.", newFan);
            notification.send(); //send a notification to this added referee.

            MainSystem.LOG.info("referee: " + newFan.userName + " was removed, and added as a simple fan.");
            return true;
        }

        else {
            //System.out.println("Can not remove a referee that does not exist in the DBLocal.");
            return false;
        }
    }

    ////////////////////////////// USE CASE 9.4 //////////////////////////////
    /**
     * adds all teams to this season - uses as an help function
     * @param refereesNames - list of referees' names
     */
    public ArrayList<Referee> setLeagueReferees (List<String> refereesNames) {

        ArrayList<Referee> allReferees = new ArrayList<>();

        if (refereesNames != null) {
            for (String referee : refereesNames) {
                User currReferee = dbLocal.getUserByFullName(referee);
                if(currReferee instanceof Referee)
                    allReferees.add((Referee)currReferee);
            }
            return allReferees;
            //MainSystem.LOG.info("Referees were added to league: " + leagueName + ", season: " + season.getYear());
        }

        else{
            return null;
        }
    }

    ////////////////////////////// USE CASE 10.3 //////////////////////////////
    /**
     * this function adds an event to an active game's event book.
     * @return true if successes, false if not.
     */
    public boolean addGameEvent(Event.eventType type, String time, String playerName, String whichTeam){

        Game gameToAdd = findActiveGame();

        if(gameToAdd!= null) { //there is an active game
            int timeInt = Integer.parseInt(time);
            Event newEvent = new Event(type, timeInt, playerName);
            gameToAdd.addEvent(newEvent);

            if(type.toString().equals("goal")){ //needs to update score.
                String score = gameToAdd.getScore();
                int homeScore = Integer.parseInt(score.substring(0,score.indexOf("-")));
                int awayScore = Integer.parseInt(score.substring(score.indexOf("-")+1));

                if (whichTeam.equals("home"))
                    homeScore = homeScore+1; //adds one to home score.
                else // away team
                    awayScore = awayScore +1; //adds one to away score.

                score = homeScore + "-" + awayScore;
                gameToAdd.setScore(score); //set the new score to game.
            }

            MainSystem.LOG.info("A new event: " + type + "(" + playerName + ",'" + time + ") was added to game: " + gameToAdd.getHomeTeam().getName() + "-" + gameToAdd.getAwayTeam().getName() + ", " + gameToAdd.getGameDate());
            return true;
        }
        else //no active game to add events to.
            return false; //could not add an event obviously.
    }

    /**
     * gets the only active game of this representative - an help function.
     * @return Game
     */
    public Game findActiveGame(){

        for (Game game : myGames)
            if(game.getStatus().equals(Game.gameStatus.active))
                return game;

        return null; //no active game at the moment.
    }

    public void setMyGame(Game newGame) {
        myGames.add(newGame);
    }


    /**
     * this function finds another representative in DBLocal and passes this rep' games from
     * @return true if successes, false if not.
     */
    public boolean passMyGames (){

        int year = myGames.get(0).getTimeOfGame().getYear(); //my season's year. // just a random game.
        String myLeague = dbLocal.whatLeagueImAt(this, year);

        int i=0 ;
        boolean outOfAsso = false;
        List<User> allAssociations = dbLocal.getUserTypeList("AssociationRepresentative");
        AssociationRepresentative substituteAsso = (AssociationRepresentative)allAssociations.get(i); //first Asso'

        int substituteYear = substituteAsso.getMyGames().get(0).getGameDate().getYear();
        String substituteLeague = dbLocal.whatLeagueImAt(substituteAsso, substituteYear);

        while (!outOfAsso && myLeague.equals(substituteLeague) && substituteAsso.getMyGames().get(0).getTimeOfGame().equals(this.myGames.get(0).getTimeOfGame())){ //keep getting random asso' till the leagues are not overlapping.
            i++;
            if (i == allAssociations.size()-1){ //the asso' list s over. no good asso' to pass to.
                outOfAsso = true; //last chance.
            }
            substituteAsso = (AssociationRepresentative)allAssociations.get(i); //next one in list.
            substituteYear = substituteAsso.myGames.get(0).getGameDate().getYear();
            substituteLeague = dbLocal.whatLeagueImAt(substituteAsso, substituteYear); //gets his league's name.
        }

        if(!outOfAsso) {
            //finally found a good substitute.
            for (Game game : myGames) {
                substituteAsso.setMyGame(game); //transfer game by game.
            }
            return true;
        }
        return false;
    }



    public ArrayList<Game> watchGamesList (){

        ArrayList<Game> allGames = new ArrayList<>();

        if (myGames != null) {
            for (Game g : myGames) {
                if( g != null && g.getStatus().equals(Game.gameStatus.preGame))
                    allGames.add(g);
            }
            return allGames;
        }
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        User user = DBLocal.getInstance().getUserType("AssociationRepresentative");
        String message = (String) arg;
        Notification notification = new Notification(user,message,this);
        notification.send();
    }

    /**
     * this function adds the game to this representative list of games,
     * and adds the representative as an observer to the game. (both directions)
     */
    public void followThisGame(Game game){
        game.addObserver(this);
        myGames.add(game);
    }
}
