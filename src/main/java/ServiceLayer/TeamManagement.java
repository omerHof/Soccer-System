package ServiceLayer;

import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.SystemLogic.MainSystem;
import DomainLayer.Teams.Assent;
import DomainLayer.Teams.Team;
import DomainLayer.Teams.TeamPage;
import DomainLayer.Users.*;

import java.util.ArrayList;

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
        if(((TeamOwner) currentUser).openTeam(team_name,initialBudget)){
            return "Congratulations " + currentUser.getUserName() + "! the team "+ team_name + " was open successfully, You can now start managing your team" ;
        } else{
            return "Sorry, you didn't get approved from the Soccer Association, please try again later";
        }

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

    public ArrayList<String> getAllTeams() {
        return DBLocal.getInstance().getFullTeamsNames();
    }

    public String getTeamPageDetails(String team_name) {
        Team team = DBLocal.getInstance().getTeam(team_name);
        TeamPage teamPage = team.getPage();
        if(teamPage==null){
            return null;
        }
        return teamPage.getAllDetails();
    }

    public ArrayList<String> getTeamPlayers(String team_name) {
        Team team = DBLocal.getInstance().getTeam(team_name);
        TeamPage teamPage = team.getPage();
        if(teamPage==null){
            return null;
        }
        ArrayList<String> result = new ArrayList<>();
        teamPage.getPlayers().keySet();
        for(String user_name: teamPage.getPlayers().keySet()){
            result.add(teamPage.getPlayers().get(user_name).getUserFullName());
        }
        return result;

    }

    public ArrayList<String> getTeamCoaches(String team_name) {
        Team team = DBLocal.getInstance().getTeam(team_name);
        TeamPage teamPage = team.getPage();
        if(teamPage==null){
            return null;
        }
        ArrayList<String> result = new ArrayList<>();
        teamPage.getCoaches().keySet();
        for(String user_name: teamPage.getCoaches().keySet()){
            result.add(teamPage.getCoaches().get(user_name).getUserFullName());
        }
        return result;
    }

    public ArrayList<String> getTeamManagers(String team_name) {
        Team team = DBLocal.getInstance().getTeam(team_name);
        TeamPage teamPage = team.getPage();
        if(teamPage==null){
            return null;
        }
        ArrayList<String> result = new ArrayList<>();
        teamPage.getManagers().keySet();
        for(String user_name: teamPage.getManagers().keySet()){
            result.add(teamPage.getManagers().get(user_name).getUserFullName());
        }
        return result;
    }

    public boolean checkIfTeamNameExist(String teamName){
        if(DBLocal.getInstance().getTeam(teamName)==null){
            return true;
        }
        return false;
    }

    public String checkIfHasATeam() {
        findTheUser();
        return ((TeamOwner) currentUser).checkIfOwnerHasTeam();
    }
}
