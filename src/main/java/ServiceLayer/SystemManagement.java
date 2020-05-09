package ServiceLayer;

import DataForTest.DataBase;
import SystemLogic.DB;
import SystemLogic.MainSystem;

import java.util.ArrayList;

public class SystemManagement {

    private LeagueSeasonManagement leagueSeasonManagement;
    private TeamManagement teamManagement;
    private UserManagement userManagement;
    private DataBase dataBase;//todo only for test
    public SystemManagement() {
       this.leagueSeasonManagement = new LeagueSeasonManagement();
       this.teamManagement = new TeamManagement();
       this.userManagement = new UserManagement();
       dataBase= new DataBase();//todo remove- only for test
       systemInitialize();


    }

    private void systemInitialize() {
        MainSystem.getInstance().initializeSystem();

    }

    public ArrayList<String> getLeagueNames(){
        return DB.getInstance().getLeagueNames();
    }
    public ArrayList<String> getFullTeamsNames(){
        return DB.getInstance().getFullTeamsNames();
    }

    public ArrayList<String> getAllUsersByType( String type){
        return DB.getInstance().getAllUserByType(type);
    }

    public int getLeagueTeamNumber(String league){
        return DB.getInstance().getNumberOfTeamsInLeague(league);
    }
    public ArrayList<Integer> getAllSeasonYears(String league){
        return DB.getInstance().getAllSeasonYearsFromLeague(league);
    }
    public ArrayList<String> closestGames(String league){
        return DB.getInstance().closestGames(league);
    }
}


