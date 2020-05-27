package ServiceLayer;

import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.SystemLogic.MainSystem;
import DBLayer.ActivateDB;

import java.util.ArrayList;

public class SystemManagement {

    private LeagueSeasonManagement leagueSeasonManagement;
    private TeamManagement teamManagement;
    private UserManagement userManagement;
    public SystemManagement() {
       this.leagueSeasonManagement = new LeagueSeasonManagement();
       this.teamManagement = new TeamManagement();
       this.userManagement = new UserManagement();
       systemInitialize();


    }

    private void systemInitialize() {
        MainSystem.getInstance().initializeSystem();
        DBLocal db = DBLocal.getInstance();
        try {
            ActivateDB ac = ActivateDB.getInstance();

            ac.readInfo();
        } catch (Exception e){
            System.out.println("no db");
        }


    }

    public ArrayList<String> getLeagueNames(){
        return DBLocal.getInstance().getLeagueNames();
    }
    public ArrayList<String> getFullTeamsNames(){
        return DBLocal.getInstance().getFullTeamsNames();
    }

    public ArrayList<String> getAllUsersByType( String type){
        return DBLocal.getInstance().getAllUserByType(type);
    }

    public int getLeagueTeamNumber(String league){
        return DBLocal.getInstance().getNumberOfTeamsInLeague(league);
    }
    public ArrayList<Integer> getAllSeasonYears(String league){
        return DBLocal.getInstance().getAllSeasonYearsFromLeague(league);
    }
    public ArrayList<String> closestGames(String league){
        return DBLocal.getInstance().closestGames(league);
    }
}


