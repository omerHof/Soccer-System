package DomainLayer.Users;

import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.Teams.Team;

import java.time.LocalDate;
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

        DBLocal dbLocal = DBLocal.getInstance();
        for(String team: teamHistoryList){
            if(dbLocal.getTeam(team)!=null){
                Team t = dbLocal.getTeam(team);
                teamHistory.add(t.getName());
            }
        }
    }


    public void setOneTeamToHistory(String team){
        DBLocal dbLocal = DBLocal.getInstance();

        Team t = dbLocal.getTeam(team);
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
