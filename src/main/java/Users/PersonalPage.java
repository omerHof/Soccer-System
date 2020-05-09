package Users;

import SystemLogic.DB;
import Teams.Team;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;

public abstract class PersonalPage extends Observable {
    protected String name;
    protected Team currentTeam;
    protected int age;
    protected ArrayList<String> teamHistory;



    public String getName() {
        return name;
    }

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentTeam(Team currentTeam) {
        this.currentTeam = currentTeam;
        setChanged();
        notifyObservers(currentTeam);
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<String> getTeamHistory() {
        return teamHistory;
    }

    public void setTeamHistory(ArrayList<String>teamHistoryList) {

        DB db = DB.getInstance();
        for(String team: teamHistoryList){
            if(db.getTeam(team)!=null){
                Team t = db.getTeam(team);
                teamHistory.add(t.getName());
            }
        }
    }


    public void setOneTeamToHistory(String team){
        DB db = DB.getInstance();

        Team t = db.getTeam(team);
        if(t!=null) {
            teamHistory.add(t.getName());
        }

    }


    public int getCurrentAge(LocalDate dateOfBirth) {
        LocalDate today = LocalDate.now();                          //Today's date
        Period p = Period.between(dateOfBirth, today);
        return p.getYears();
    }
}
