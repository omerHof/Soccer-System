package Users;

import SystemLogic.DB;
import Teams.Team;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public  class CoachPersonalPage extends PersonalPage {

    private String teamRole;


    public CoachPersonalPage(String name, LocalDate birthdate, String teamRole, String team){
        this.name=name;
        this.age=getCurrentAge(birthdate);
        this.teamRole=teamRole;
        teamHistory= new ArrayList<>();
        DB db = DB.getInstance();
        this.currentTeam = db.getTeam(team);

//        this.teamHistory.add(team.getName());

    }

    public String getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(String teamRole) {
        this.teamRole = teamRole;
    }

    public Pair<String, ArrayList<String>> getAllDetails() {
        String details =
                super.name + "," +
                        "Age: " + super.age + "," +
                        "Current Team: " +super.currentTeam.getName() +  "," +
                        "Team Role:" + teamRole;

        return new Pair<>(details ,teamHistory);
    }
}
