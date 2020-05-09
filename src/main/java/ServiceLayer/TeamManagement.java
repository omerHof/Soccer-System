package ServiceLayer;

import SystemLogic.MainSystem;
import Teams.Assent;
import Teams.Team;
import Teams.TeamPage;
import Users.Manager;
import Users.TeamOwner;
import Users.User;

/**
 * This class represents a bridge for handling the team creation and functionality between the domain layer and the GUI layer
 */

public class TeamManagement {

    User currentUser;

    private void findTheUser() {
        if(this.currentUser == null){
            this.currentUser= MainSystem.getInstance().getCurrentUser();
        }
    }

    public String openTeam(String team_name, double initialBudget){
        findTheUser();
        ((TeamOwner) currentUser).askPermissionToOpenTeam();
        ((TeamOwner) currentUser).openTeam(team_name,initialBudget);
        return "team was open successfully";
    }

    public String openTeamPage(String team_history,String team_nation){
        findTheUser();
        if(currentUser instanceof TeamOwner) {
           String message = ((TeamOwner) currentUser).getTeam().createPage(team_history, team_nation);
           if(message == "the team already has a page"){
               return message;
           }
           else {
               return "team page was open successfully";
           }

        }
        if(currentUser instanceof Manager) {
            String message =  ((Manager) currentUser).getTeam().createPage(team_history, team_nation);
            if(message == "the team already has a page"){
                return message;
            }
            else {
                return "team page was open successfully";
            }
        }
        return "the user not allowd to open a team page";

    }

    public String addAssent(Assent assent, double new_assent){
        findTheUser();
        return ((TeamOwner) currentUser).addAssent(assent,new_assent);
    }

    public String removeAssent(Assent assent){
        findTheUser();
        return ((TeamOwner) currentUser).removeAssent(assent);
    }

    public String changeAssentWorth(Assent assent, double new_worth){
        findTheUser();
        return ((TeamOwner) currentUser).changeAssentWorth(assent, new_worth);
    }

    public String appointToOwnerOrManager(User user, String role, double worth){
        findTheUser();
        return ((TeamOwner) currentUser).appoint(user,role,worth);
    }

    public String removeAppointmentTeamOwner(TeamOwner teamOwner){
        findTheUser();
        return ((TeamOwner) currentUser).removeAppointmentTeamOwner(teamOwner);
    }

    public String removeAppointmentManager(Manager manager){
        findTheUser();
        return ((TeamOwner) currentUser).removeAppointmentManager(manager);
    }

    public String closeTeam(){
        findTheUser();
        return ((TeamOwner) currentUser).closeTeam();
    }

}
