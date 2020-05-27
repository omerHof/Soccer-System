package DomainLayer.Users;

import DomainLayer.SystemLogic.DBLocal;

import java.time.LocalDate;
import java.util.ArrayList;

public  class CoachPersonalPage extends PersonalPage {

    private String teamRole;


    public CoachPersonalPage(String name, LocalDate birthdate, String teamRole, String team){
        this.name=name;
        this.age=getCurrentAge(birthdate);
        this.teamRole=teamRole;
        teamHistory= new ArrayList<>();
        DBLocal dbLocal = DBLocal.getInstance();
        this.currentTeam = dbLocal.getTeam(team);

//        this.teamHistory.add(team.getName());

    }

    public String getTeamRole() {
        return teamRole;
    }

    public void setTeamRole(String teamRole) {
        this.teamRole = teamRole;
    }

    public String getAllDetails() {
        return super.name + "," + "Age: " + super.age + "," + "Current Team: " +super.currentTeam.getName() +  "," +
                        "Team Role:" + teamRole;
    }
}
