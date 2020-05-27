package DomainLayer.SystemLogic;

import DBLayer.ActivateDB;
import DomainLayer.Games.Game;
import DomainLayer.LeagueSeasonsManagment.League;
import DomainLayer.LeagueSeasonsManagment.Season;
import DomainLayer.Teams.Team;
import DomainLayer.Users.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * this class represent the dataBase
 */
public class DBLocal {

    private static DBLocal dbLocal;
    /**
     * implement data structures
     */
    private HashMap<String, User> users;
    private HashMap<String, League> leagues;
    private HashMap<String, Team> teams;

    /**
     * constructor
     */
    private DBLocal() {
        users = new HashMap<>();
        leagues = new HashMap<>();
        teams = new HashMap<>();
    }

    /**
     * singelton class
     *
     * @return instance of DBLocal
     */
    public static DBLocal getInstance() {
        if (dbLocal == null) {
            synchronized (DBLocal.class) {
                if (dbLocal == null) {
                    dbLocal = new DBLocal();
                }
            }
        }
        return dbLocal;
    }

    /***************USER***************/

    /**
     * get user by user name
     *
     * @return user
     */
    public User getUser(String name) {
        if (users.containsKey(name)) {
            return users.get(name);
        }
        return null;
    }

    /**
     * get user by full name
     *
     * @return user
     */
    public User getUserByFullName(String name) {
        Iterator it = users.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            User user = (User) pair.getValue();
            if (user.getUserFullName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    /**
     * check if user exist
     *
     * @param name- user's name
     * @return if user exist
     */
    public boolean userExist(String name) {
        if (users.containsKey(name)) {
            return true;
        }
        return false;
    }

    /**
     * set user
     *
     * @param user- the user object
     */
    public void setUser(User user) {

        if (user != null && user.getUserName() != null && !userExist(user.getUserName())) {
            //user.setPassword(MainSystem.getInstance().encrypte(user.getPassword()));
            users.put(user.getUserName(), user);
        }
    }

    /**
     * add user if user name not contain already
     *
     * @param user- the user object
     * @return if user already contain- false
     */
    public boolean addUser(User user) {
        if (user != null && !users.containsKey(user.getUserName())) {
            users.put(user.getUserName(), user);
            return true;
        }
        return false;
    }

    /**
     * remove user if exist
     *
     * @param name- name of user
     * @return if user removed
     */
    public boolean removeUser(String name) {
        if (users.containsKey(name)) {
            users.remove(name);
            return true;
        }
        return false;
    }

    /***************LEAGUE***************/

    /**
     * get league
     *
     * @return league
     */
    public League getLeague(String name) {
        if (leagues.containsKey(name)) {
            return leagues.get(name);
        }
        return null;
    }

    /**
     * check if league exist
     *
     * @param name- league
     * @return if league exist
     */
    public boolean leagueExist(String name) {
        if (leagues.containsKey(name)) {
            return true;
        }
        return false;
    }

    /**
     * set league
     *
     * @param league object
     */
    public void setLeague(League league) {
        if (league != null && league.getName() != null && !leagueExist(league.getName())) {
            leagues.put(league.getName(), league);
        }
    }

    public int getLeagues() {
        return leagues.size();
    }

    /**
     * add league if user name not contain already
     *
     * @param league object
     * @return if already exist- false
     */
    public boolean addLeague(League league) {
        if (league != null && !leagues.containsKey(league.getName())) {
            leagues.put(league.getName(), league);
            return true;
        }
        return false;
    }

    /**
     * add season to league
     *
     * @param name   of season
     * @param season object
     * @return if the name already exist
     */
    public boolean addSeason(String name, Season season) {
        if (leagues.containsKey(name)) {
            if (season != null && !leagues.get(name).getAllSeasons().contains(season) && checkSeasonYear(name, season.getYear())) {
                List<Season> newList = leagues.get(name).getAllSeasons();
                newList.add(season);
                leagues.get(name).setAllSeasons(newList);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean checkSeasonYear(String leagueName, int seasonYear) {
        try {
            for (Season season : leagues.get(leagueName).getAllSeasons()) {
                if (season.getYear() == seasonYear) {
                    return false;
                }
            }

        } catch (Exception e) {

        }
        return true;
    }

    /**
     * remove league if exist
     *
     * @param name of the league
     * @return if removed
     */
    public boolean removeLeague(String name) {
        if (leagues.containsKey(name)) {
            leagues.remove(name);
            return true;
        }
        return false;
    }

    /***************TEAM***************/

    /**
     * get team
     *
     * @return team
     */
    public Team getTeam(String name) {
        if (teams.containsKey(name)) {
            return teams.get(name);
        }
        return null;
    }

    /**
     * check if team exist
     *
     * @param name of team
     * @return if exist
     */
    public boolean teamExist(String name) {
        if (teams.containsKey(name)) {
            return true;
        }
        return false;
    }

    /**
     * set team
     *
     * @param team object
     */
    public void setTeam(Team team) {
        if (team != null && team.getName() != null && !teamExist(team.getName())) {
            teams.put(team.getName(), team);

        }
    }

    /**
     * add user if user name not contain already
     *
     * @param team object
     * @return if already exist- false
     */
    public boolean addTeam(Team team) {
        if (team != null && !teams.containsKey(team.getName())) {
            teams.put(team.getName(), team);
            return true;
        }
        return false;
    }

    /**
     * remove user if exist
     *
     * @param name of team
     * @return if removed
     */
    public boolean removeTeam(String name) {
        if (teams.containsKey(name)) {
            teams.remove(name);
            return true;
        }
        return false;
    }

    /***************others***************/
    /**
     * return user from requested type
     *
     * @param type of user
     * @return the user type object
     */

    public User getUserType(String type) {

        Iterator it = users.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (type.equals("AssociationRepresentative") && pair.getValue() instanceof AssociationRepresentative) {
                return (User) pair.getValue();
            }
            if (type.equals("Fan") && pair.getValue() instanceof Fan) {
                return (User) pair.getValue();
            }
            if (type.equals("Coach") && pair.getValue() instanceof Coach) {
                return (User) pair.getValue();
            }
            if (type.equals("Manager") && pair.getValue() instanceof Manager) {
                return (User) pair.getValue();
            }
            if (type.equals("Player") && pair.getValue() instanceof Player) {
                return (User) pair.getValue();
            }
            if (type.equals("MainReferee") && pair.getValue() instanceof MainReferee) {
                return (User) pair.getValue();
            }
            if (type.equals("Referee") && pair.getValue() instanceof Referee) {
                return (User) pair.getValue();
            }
            if (type.equals("TeamOwner") && pair.getValue() instanceof TeamOwner) {
                return (User) pair.getValue();
            }
            if (type.equals("Administrator") && pair.getValue() instanceof Administrator) {
                return (User) pair.getValue();
            }
        }
        return null;
    }

    /**
     * return users list from requested type
     *
     * @param type string
     * @return ArrayList of users
     */

    public ArrayList<User> getUserTypeList(String type) {
        Iterator it = users.entrySet().iterator();
        ArrayList<User> userTypeList = new ArrayList<>();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (type.equals("AssociationRepresentative") && pair.getValue() instanceof AssociationRepresentative) {
                userTypeList.add((User) pair.getValue());
            }
            if (type.equals("Fan") && pair.getValue() instanceof Fan) {
                userTypeList.add((User) pair.getValue());
            }
            if (type.equals("Coach") && pair.getValue() instanceof Coach) {
                userTypeList.add((User) pair.getValue());
            }
            if (type.equals("Manager") && pair.getValue() instanceof Manager) {
                userTypeList.add((User) pair.getValue());
            }
            if (type.equals("Player") && pair.getValue() instanceof Player) {
                userTypeList.add((User) pair.getValue());
            }
            if (type.equals("MainReferee") && pair.getValue() instanceof MainReferee) {
                userTypeList.add((User) pair.getValue());
            }
            if (type.equals("Referee") && pair.getValue() instanceof Referee) {
                userTypeList.add((User) pair.getValue());
            }
            if (type.equals("TeamOwner") && pair.getValue() instanceof TeamOwner) {
                userTypeList.add((User) pair.getValue());
            }
            if (type.equals("Administrator") && pair.getValue() instanceof Administrator) {
                userTypeList.add((User) pair.getValue());
            }
        }
        return userTypeList;
    }

    /**
     * check amount of users from type
     *
     * @param type string
     * @return amount
     */
    public int checkQuantityOfUsersByType(String type) {
        Iterator it = users.entrySet().iterator();
        int count = 0;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (type.equals("AssociationRepresentative") && pair.getValue() instanceof AssociationRepresentative) {
                count++;
            }
            if (type.equals("Fan") && pair.getValue() instanceof Fan) {
                count++;
            }
            if (type.equals("Coach") && pair.getValue() instanceof Coach) {
                count++;
            }
            if (type.equals("Manager") && pair.getValue() instanceof Manager) {
                count++;
            }
            if (type.equals("Player") && pair.getValue() instanceof Player) {
                count++;
                ;
            }
            if (type.equals("MainReferee") && pair.getValue() instanceof MainReferee) {
                count++;
            }
            if (type.equals("Referee") && pair.getValue() instanceof Referee) {
                count++;
            }
            if (type.equals("TeamOwner") && pair.getValue() instanceof TeamOwner) {
                count++;
            }
            if (type.equals("Administrator") && pair.getValue() instanceof Administrator) {
                count++;
            }
        }
        return count;
    }

    /**
     * this function searches for a league with the given season (year) that contains the given asso'
     *
     * @param year - season requested
     * @param asso - the AssociationRepresentative to find
     * @return name of league found
     */
    public String whatLeagueImAt(AssociationRepresentative asso, int year) {

        Iterator it = leagues.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            League league = (League) pair.getValue();
            Season season = league.getSeasonByYear(year); //finds the correct season.

            List<AssociationRepresentative> allAsso = season.getAllRepresentatives(); // of the league
            for (AssociationRepresentative currAsso : allAsso) {
                if (currAsso.getUserName().equals(asso.getUserName())) { //the asso' does exist in this season' list.
                    return league.getName();
                }
            }
        }
        return ""; //this asso' doesn't exist in any leaue-year requested.
    }

    public ArrayList<String> getLeagueNames() {
        return new ArrayList<>(this.leagues.keySet());
    }

    public ArrayList<String> getTeamsNames() {
        return new ArrayList<>(this.teams.keySet());
    }

    public ArrayList<String> getFullTeamsNames() {
        ArrayList<String> result = new ArrayList<>();
        Iterator it = teams.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Team team = (Team) pair.getValue();
            if (team.getCoaches().size() > 0 && team.getPlayers().size() > 0) {
                result.add((String) pair.getKey());
            }
        }
        return result;
    }


    public ArrayList<String> getAllUserByType(String type) {
        ArrayList<String> allUsersByType = new ArrayList<>();
        Iterator it = users.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            User user = (User) pair.getValue();
            if (type.equals(user.getClass().getSimpleName())) {
                allUsersByType.add(user.getUserFullName());
            }
        }
        return allUsersByType;
    }

    public HashMap<String, String> getAllUserNameByType(String type) {
        HashMap<String, String> allUsersByType = new HashMap<>();
        for(String userName: users.keySet()){
            User user = users.get(userName);
            if(type.equals(user.getClass().getSimpleName())){
                allUsersByType.put(userName, user.getUserFullName());
            }
        }
        return allUsersByType;
    }

    public int getNumberOfTeamsInLeague(String league) {
        Iterator it = leagues.entrySet().iterator();
        int result = 0;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (pair.getKey().equals(league)) {
                result = ((League) pair.getValue()).getNumOfTeams();
            }
        }
        return result;
    }

    public ArrayList<Integer> getAllSeasonYearsFromLeague(String league) {
        ArrayList<Integer> result = new ArrayList<>();
        Iterator it = leagues.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (pair.getKey().equals(league)) {
                League l = (League) pair.getValue();
                for (Season season : l.getAllSeasons()) {
                    result.add(season.getYear());
                }
                return result;
            }
        }
        return result;
    }

    public ArrayList<String> closestGames(String leagueName) {
        League league = leagues.get(leagueName);
        for (Season season : league.getAllSeasons()) {
            if (season.getYear() == LocalDateTime.now().getYear()||season.getYear()-1 == LocalDateTime.now().getYear()) {
                Iterator it = season.getAllGames().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    ArrayList<Game> allGames = (ArrayList<Game>) pair.getValue();
                    if (allGames.get(0).getGameDate().isAfter(LocalDateTime.now())) {
                        return getStringGames(allGames);
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<String> getStringGames(ArrayList<Game> allGames) {
        ArrayList<String> result = new ArrayList<>();
        for (Game game : allGames) {
            result.add(game.getHomeTeam().getName() + "%" + game.getAwayTeam().getName() + "%" +
                    game.getGameDate().getDayOfMonth()+"."+game.getGameDate().getMonth()+"."+game.getGameDate().getYear()+"%"+game.getGameDate().getHour()
                    +"%"+game.getGameDate().getMinute()+"%"+game.getHomeTeam().getStadium().getName()+"%"+game.getStatus());
        }
        return result;
    }

    public String getUserType(User user){
        return user.getClass().getSimpleName();
    }

    public void writeToMongo(){
        ActivateDB.getInstance().writeInfo(new ArrayList<>(users.values()),new ArrayList<>(teams.values()),new ArrayList<>(leagues.values()));
    }

    public void readFromMongo(){
        ActivateDB.getInstance().readInfo();
    }

    public Game getGameById(int id){
        for(League league:leagues.values()){
            for(Season season:league.getAllSeasons()){
                for(ArrayList<Game> games:season.getAllGames().values()){
                    for(Game game:games){
                        if(game.getId()==id){
                            return game;
                        }
                    }
                }
            }
        }
        return null;
    }
}