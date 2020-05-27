package DomainLayer.Teams;

import DomainLayer.Games.Game;
import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.SystemLogic.MainSystem;
import DomainLayer.Users.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Team implements Comparable {

    public enum teamStatus {
        active, close , PermanentlyClosed
    }

    private String name;
    private HashMap<String, Player> players;
    private HashMap<String, Coach> coaches;
    private HashMap<String, Manager> managers;
    private HashMap<String, TeamOwner> teamOwners;
    private HashSet<Assent> assents;
    private Stadium stadium;
    private ArrayList<Game> gameList;
    private teamStatus status;
    private Statistics statistics;
    private double budget;
    private TeamPage page;
    private DBLocal dbLocal;



    public Team(String name, HashMap<String, TeamOwner> teamOwners) {
        this.name = name;
        this.teamOwners = teamOwners;
        this.status = teamStatus.active;
        page = null;
        dbLocal = DBLocal.getInstance();
        players = new HashMap<>();
        coaches = new HashMap<>();
        managers = new HashMap<>();
        gameList = new ArrayList<>();
        assents = new HashSet<>();
        stadium = new Stadium();
    }

    public Team(String name) {
        this.name = name;
        this.status = teamStatus.active;
        page = null;
        dbLocal = DBLocal.getInstance();
        players = new HashMap<>();
        coaches = new HashMap<>();
        managers = new HashMap<>();
        teamOwners = new HashMap<>();
        gameList = new ArrayList<>();
        assents = new HashSet<>();
        stadium = new Stadium();
    }


    public String createPage(String history,String nation){
        if(page==null) {
            page = new TeamPage(name, players, coaches, managers, stadium, history, nation);
            return "the page  successfully opened";
        }
        else{
            return "the team already has a page";

        }
    }

    public TeamPage getPage() {
        return page;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public boolean containsAssent(Assent assent){
        return assents.contains(assent);
    }

    public void addAssent(Assent assent){
        if(assent instanceof Player){
            addPlayer((Player)assent);
        }
        if(assent instanceof Coach){
            addCoach((Coach)assent);
        }
        if(assent instanceof Manager){
            addManager((Manager) assent);
        }
        if(assent instanceof TeamOwner){
            addTeamOwner((TeamOwner) assent);
        }
        if (assent instanceof Stadium){
            setStadium((Stadium) assent);
            MainSystem.LOG.info("The stadium " + stadium.getName() + "was added successfully to the team " + this.getName());
        }
        this.assents.add(assent);
    }

   public void removeAssent(Assent assent){
        if(assent instanceof Player){
            removePlayer((Player)assent);
        }
        if(assent instanceof Coach){
            removeCoach((Coach)assent);
        }
        if(assent instanceof Manager){
            removeManager((Manager) assent);
        }
        if(assent instanceof TeamOwner){
            removeTeamOwner((TeamOwner) assent);
        }
        if (assent instanceof Stadium){
            String stadium_name = stadium.getName();
            setStadium(null);
            MainSystem.LOG.info("The stadium " + stadium_name + "was removed successfully to the team " + this.getName());
        }
        this.assents.remove(assent);
    }

    public void setStatus(teamStatus status) {
        this.status = status;
    }





    ////by string

    public boolean addPlayer(String player) {
        User user = dbLocal.getUserByFullName(player);
        Player p = (Player)user;
        if(p==null){
           return false;
        }

        Team team = p.getCurrentTeam();
        if(team!=null) {
            team.removePlayer(player);
            dbLocal.setTeam(team);
        }
        players.put(player, p);
        p.setCurrentTeam(name);
        dbLocal.setTeam(this);

        if(page!=null){
            page.addPlayer(p);
        }
        MainSystem.LOG.info("the player " + player + " was added successfully to the team " + this.getName());
        return true;
    }

    public boolean removePlayer(String player) {
        User user = dbLocal.getUserByFullName(player);
        Player p = (Player)user;
        if(p==null){
            return false;
        }
        if(!players.containsKey(player)){
            return false;
        }

        players.remove(player);
        p.setCurrentTeam(null);
        dbLocal.setTeam(this);
        if(page!=null){
            page.removePlayer(p);
        }
        MainSystem.LOG.info("the player " + player + " was removed successfully from the team " + this.getName());
        return true;
    }

    public boolean addCoach(String coach) {
        User user = dbLocal.getUserByFullName(coach);
        Coach c = (Coach) user;
        if(c==null){
            return false;
        }

        Team team = c.getCurrentTeam();
        if(team!=null) {
            team.removeCoach(coach);
            dbLocal.setTeam(team);
        }
        coaches.put(coach, c);
        c.setCurrentTeam(name);
        dbLocal.setTeam(this);

        if(page!=null){
            page.addCoach(c);
        }
        MainSystem.LOG.info("the coach " + coach + " was added successfully to the team " + this.getName());
        return true;
    }
    public boolean removeCoach(String coach) {
        User user = dbLocal.getUserByFullName(coach);
        Coach c = (Coach)user;
        if(c==null){
            return false;
        }
        if(!coaches.containsKey(coach)){
            return false;
        }

        coaches.remove(coach);
        c.setCurrentTeam(null);
        dbLocal.setTeam(this);
        if(page!=null){
            page.removeCoach(c);
        }
        MainSystem.LOG.info("the coach " + coach + " was removed successfully from the team " + this.getName());
        return true;
    }

    public boolean addManager(String manager) {
        User user = dbLocal.getUserByFullName(manager);
        Manager m = (Manager) user;
        if(m==null){
            return false;
        }

        Team team = m.getTeam();
        if(team!=null) {
            team.removeManager(m);
            dbLocal.setTeam(team);
        }
        managers.put(manager, m);
        m.setTeam(team);
        dbLocal.setTeam(this);

        if(page!=null){
            page.addManager(m);
        }
        MainSystem.LOG.info("the manager " + manager + " was added successfully to the team " + this.getName());
        return true;
    }
    public boolean removeManager(String manager) {
        User user = dbLocal.getUserByFullName(manager);
        Manager m = (Manager) user;
        if(m==null){
            return false;
        }
        if(!managers.containsKey(manager)){
            return false;
        }

        managers.remove(manager);
        m.setTeam(null);
        dbLocal.setTeam(this);
        if(page!=null){
            page.removeManager(m);
        }
        MainSystem.LOG.info("the manager " + manager + " was removed successfully from the team " + this.getName());
        return true;
    }


    public boolean addTeamOwner(String teamOwner) {
        User user = dbLocal.getUserByFullName(teamOwner);
        TeamOwner owner = (TeamOwner) user;
        if(owner==null){
            return false;
        }

        Team team = owner.getTeam();
        if(team!=null) {
            team.removeTeamOwner(owner);
            dbLocal.setTeam(team);
        }else {
            teamOwners.put(teamOwner, owner);
            owner.setTeam(this);
            dbLocal.setTeam(this);
        }




        MainSystem.LOG.info("the team owner " + teamOwner + " was added successfully to the team " + this.getName());
        return true;
    }
    public boolean removeTeamOwner(String teamOwner) {
        User user = dbLocal.getUserByFullName(teamOwner);
        TeamOwner owner = (TeamOwner) user;
        if(owner==null){
            return false;
        }
        if(!teamOwners.containsKey(teamOwner)){
            return false;
        }

        teamOwners.remove(teamOwner);
        owner.setTeam(null);
        dbLocal.setTeam(this);

        MainSystem.LOG.info("the team owner " + teamOwner + " was removed successfully from the team " + this.getName());
        return true;
    }





    ////by object
    public boolean addPlayer(Player player) {
        User user = dbLocal.getUserByFullName(player.getUserFullName());
        if(user==null){
            return false;
        }

        if(player==null){
            return false;
        }

        Team team = player.getCurrentTeam();
        if(team!=null) {
            team.removePlayer(player);
            dbLocal.setTeam(team);
        }
        players.put(player.getUserFullName(), player);
        player.setCurrentTeam(name);
        dbLocal.setTeam(this);

        if(page!=null){
            page.addPlayer(player);
        }
        MainSystem.LOG.info("the player " + player.getUserFullName() + " was added successfully to the team " + this.getName());
        return true;
    }




    public boolean removePlayer(Player player) {

        if(player==null){
            return false;
        }
        if(!players.containsKey(player.getUserFullName())){
            return false;
        }

        players.remove(player.getUserFullName());
        player.setCurrentTeam(null);
        dbLocal.setTeam(this);
        if(page!=null){
            page.removePlayer(player);
        }
        MainSystem.LOG.info("the player " + player.getUserFullName() + " was removed successfully from the team " + this.getName());
        return true;
    }


    public boolean addCoach(Coach coach) {
        User user = dbLocal.getUserByFullName(coach.getUserFullName());
        if(user==null){
            return false;
        }

        if(coach==null){
            return false;
        }
        Team team = coach.getCurrentTeam();
        if(team!=null) {
            team.removeCoach(coach);
            dbLocal.setTeam(team);
        }
        coaches.put(coach.getUserFullName(), coach);
        coach.setCurrentTeam(name);
        dbLocal.setTeam(this);
        if(page!=null){
            page.addCoach(coach);
        }
        MainSystem.LOG.info("the coach " + coach.getUserName() + " was added successfully to the team " + this.getName());
        return true;
    }

    public boolean removeCoach(Coach coach) {
        if(coach==null){
            System.out.println("the user not found in the dbLocal");
            return false;
        }
        if(!coaches.containsKey(coach.getUserFullName())){
            return false;
        }
        coaches.remove(coach.getUserFullName());
        coach.setCurrentTeam(null);
        dbLocal.setTeam(this);
        if(page!=null){
            page.removeCoach(coach);
        }
        MainSystem.LOG.info("the coach " + coach.getUserName() + " was removed successfully to the team " + this.getName());
        return true;
    }

    public boolean addManager(Manager manager) {
        User user = dbLocal.getUserByFullName(manager.getUserFullName());
        if(user==null){
            return false;
        }

        if(manager==null){
            return false;
        }
        Team team = manager.getTeam();
        if(team!=null){
            team.removeManager(manager);
            dbLocal.setTeam(team);
        }
        managers.put(manager.getUserFullName(),manager);
        manager.setTeam(this);
        if(page!=null){
            page.addManager(manager);
        }
        MainSystem.LOG.info("the manager " + manager.getUserName() + " was added successfully to the team " + this.getName());
        return true;
    }

    public boolean removeManager(Manager manager) {
        if(manager==null){
            return false;
        }
        if(!managers.containsKey(manager.getUserFullName())){
            return false;
        }
        managers.remove(manager.getUserFullName());
        manager.setTeam(null);
        dbLocal.setTeam(this);
        if(page!=null){
            page.removeManager(manager);
        }
        MainSystem.LOG.info("the manager " + manager.getUserName() + " was removed successfully to the team " + this.getName());
        return true;
    }

    public boolean addTeamOwner(TeamOwner teamOwner) {
        User user = dbLocal.getUserByFullName(teamOwner.getUserFullName());
        if(user==null){
            return false;
        }
        if(teamOwner==null){
            return false;
        }
        Team team = teamOwner.getTeam();
        if(team!=null){
            team.removeTeamOwner(teamOwner);
            dbLocal.setTeam(team);
        }
        teamOwners.put(teamOwner.getUserFullName(), teamOwner);
        teamOwner.setTeam(this);
        MainSystem.LOG.info("the team owner " + teamOwner.getUserName() + " was added successfully to the team " + this.getName());
        return true;
    }

    public boolean removeTeamOwner(TeamOwner teamOwner) {
        if(teamOwner==null){
            return false;
        }
        if(!teamOwners.containsKey(teamOwner.getUserFullName())){
            return false;
        }
        teamOwners.remove(teamOwner.getUserFullName());
        teamOwner.setTeam(null);
        dbLocal.setTeam(this);
        MainSystem.LOG.info("the team owner " + teamOwner.getUserName() + " was removed successfully to the team " + this.getName());
        return true;
    }

    public HashMap<String, Coach> getCoaches()
    {
        return coaches;
    }

    public HashMap<String, Player> getPlayers()
    {
        return players;
    }

    public HashMap<String, Manager> getManagers() {
        return managers;
    }

    public HashMap<String, TeamOwner> getTeamOwners() {
        return teamOwners;
    }

    public Stadium getStadium() {
        return stadium;

    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
        if(page!=null){
            page.setStadium(stadium);
        }
        dbLocal.setTeam(this);
        MainSystem.LOG.info("the team "+name+ " has a new stadium");

    }

    public ArrayList<Game> getGameList() {
        return gameList;
    }

    public void setGameList(ArrayList<Game> gameList) {
        this.gameList = gameList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        if(page!=null){
            page.setName(name);

        }
        dbLocal.setTeam(this);
        MainSystem.LOG.info("the team change her name to: "+name);

    }

    public void addGame (Game game){
        if (game != null)
            gameList.add(game);
    }

    public teamStatus getStatus() {
        return status;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    @Override
    public int compareTo(Object obj) {
        Team other=(Team)obj;
        return this.getStatistics().compareTo(other.getStatistics());
    }
}
