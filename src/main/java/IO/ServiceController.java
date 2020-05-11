package IO;

import ServiceLayer.LeagueSeasonManagement;
import ServiceLayer.SystemManagement;
import ServiceLayer.TeamManagement;
import ServiceLayer.UserManagement;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class ServiceController {

    SystemManagement systemManagement;
    LeagueSeasonManagement leagueSeasonManagement;
    TeamManagement teamManagement;
    UserManagement userManagement;

    public ServiceController() {
        systemManagement = new SystemManagement();
        leagueSeasonManagement = new LeagueSeasonManagement();
        teamManagement = new TeamManagement();
        userManagement = new UserManagement();
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getLeaguesNames")
    public ArrayList<String> getLeaguesNames(){
        return systemManagement.getLeagueNames();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getFullTeamsNames")
    public ArrayList<String> getFullTeamsNames(){
        return systemManagement.getFullTeamsNames();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllUsersByType")
    public ArrayList<String> getAllUsersByType(@RequestParam String param){
        return systemManagement.getAllUsersByType(param);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getLeagueTeamNumber")
    public int getLeagueTeamNumber(@RequestParam String param){
        return systemManagement.getLeagueTeamNumber(param);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllSeasonYears")
    public ArrayList<Integer> getAllSeasonYears(@RequestParam String param){
        return systemManagement.getAllSeasonYears(param);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllPlayers")
    public HashMap<String, String> getAllPlayers(){
        return userManagement.getAllPlayers();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/testPost")
    public String checkPost(@RequestBody String id){

        return id;
    }

}
