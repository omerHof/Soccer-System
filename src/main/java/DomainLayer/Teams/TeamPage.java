package DomainLayer.Teams;

import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.Users.Coach;
import DomainLayer.Users.Manager;
import DomainLayer.Users.Player;

import java.util.*;

public class TeamPage extends Observable {
    private String name;
    private HashMap<String, Player> players;
    private HashMap<String, Coach> coaches;
    private HashMap<String, Manager> managers;
    private String history;
    private Stadium stadium;
    private String nation;


    public TeamPage(String name, HashMap<String, Player> players, HashMap<String, Coach> coaches, HashMap<String, Manager> managers, Stadium stadium, String history, String nation){
        this.name = name;
        this.players=players;
        this.coaches=coaches;
        this.managers=managers;
        this.stadium=stadium;
        this.history=history;
        this.nation = nation;
    }
    public void setChange(){
        this.setChanged();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers(name);
    }

    public HashMap<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(HashMap<String, Player> players) {
        this.players = players;
        setChanged();
        notifyObservers(players);
    }

    public HashMap<String, Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(HashMap<String, Coach> coaches) {
        this.coaches = coaches;
        setChanged();
        notifyObservers(coaches);
    }

    public HashMap<String, Manager> getManagers() {
        return managers;
    }

    public void setManagers(HashMap<String, Manager> managers) {
        this.managers = managers;
        setChanged();
        notifyObservers(managers);
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
        setChanged();
        notifyObservers(stadium);
    }


    public void addPlayer(Player player) {
        players.put(player.getUserFullName(), player);
        setChanged();
        notifyObservers(player);
    }

    public void removePlayer(Player player) {
        players.remove(player.getUserFullName());
        setChanged();
        notifyObservers(player);
    }

    public void addCoach(Coach coach) {
        coaches.put(coach.getUserFullName(), coach);
        setChanged();
        notifyObservers(coach);
    }

    public void removeCoach(Coach coach) {
        coaches.remove(coach.getUserFullName());
        setChanged();
        notifyObservers(coach);
    }

    public void addManager(Manager manager) {
        managers.put(manager.getUserFullName(), manager);
        setChanged();
        notifyObservers(manager);
    }

    public void removeManager(Manager manager) {
        managers.remove(manager.getUserFullName());
        setChanged();
        notifyObservers(manager);
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAllDetails() {
        DBLocal db = DBLocal.getInstance();
       return name + "," + "Stadium: " + stadium.getName() + "," + "History: " + history +  "," + "Nation:" + nation;
    }
}
