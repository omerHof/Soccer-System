package IO;

import ServiceLayer.LeagueSeasonManagement;
import ServiceLayer.SystemManagement;
import ServiceLayer.TeamManagement;
import ServiceLayer.UserManagement;
import SystemLogic.MainSystem;
import javafx.util.Pair;
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

    /** ------------ LOGIN ------------ **/

    @RequestMapping(method = RequestMethod.POST,value = "/logIn")
    public String logIn(@RequestBody String details){
        String userDetails[] = details.split(",");
        String check = userManagement.logIn(userDetails[0],userDetails[1]);
        return check;
        }

    @RequestMapping(method = RequestMethod.GET, value = "/logOut")
    public void logOut(){
        userManagement.logOut();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/displaySpecialPassword")
    public String displaySpecialPassword(){
        String type = userManagement.displaySpecialPassword();
        return type;
    }
    /** ------------ USERS ------------ **/

    @RequestMapping(method = RequestMethod.GET, value = "/getUserDetails")
    public ArrayList<String> getUserDetails(){
        ArrayList<String> user = userManagement.watchDetails();
        return userManagement.watchDetails();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllUsersByType")
    public ArrayList<String> getAllUsersByType(@RequestParam String param){
        return systemManagement.getAllUsersByType(param);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/setUserEmail")
    public void setUserEmail(@RequestParam String param){
        userManagement.setUserEmail(param);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/setUserFullName")
    public void setUserFullName(@RequestParam String param){
        userManagement.setUserFullName(param);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUserType")
    public String getUserType(@RequestParam String param){
        String type = userManagement.getUserType(param);
        return type;
    }

    /** ------------ COACHES ------------ **/

    @RequestMapping(method = RequestMethod.GET, value = "/setCoachRole")
    public void setCoachRole(@RequestParam String param){
        userManagement.setCoachTeamRole(param);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getCoachRole")
    public String getCoachRole(){
        return userManagement.getCoachTeamRole();
    }

    /** ------------ PLAYERS ------------ **/
    @RequestMapping(method = RequestMethod.GET, value = "/getAllPlayers")
    public HashMap<String, String> getAllPlayers()
    {
        return userManagement.getAllPlayers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/setPlayerPosition")
    public void setPlayerPosition(@RequestParam String param){
        userManagement.setCourtRole(param);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getPlayerPosition")
    public String getPlayerPosition()
    {
        return userManagement.getCourtRole();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getPlayerPageDetails")
    public Pair<String, ArrayList<String>> getPlayerPageDetails(@RequestParam String param){

        return userManagement.getPlayerPageDetails(param);
    }


    /** ------------ REFEREE ------------ **/

    @RequestMapping(method = RequestMethod.GET, value = "/setRefereeQualification")
    public void setRefereeQualification(@RequestParam String param){

        userManagement.setRefereeQualifications(param);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getRefereeQualifications")
    public String getRefereeQualifications(){
        return userManagement.getRefereeQualifications();
    }

    /** ------------ TEAMS ------------ **/

    @RequestMapping(method = RequestMethod.GET, value = "/closestGames")
    public ArrayList<String> closestGames(@RequestParam String param){
        return systemManagement.closestGames(param);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/checkIfTeamNameExist")
    public Boolean checkIfTeamNameExist(@RequestParam String param){

        return teamManagement.checkIfTeamNameExist(param);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/CreateNewTeam")
    public String CreateNewTeam(@RequestParam String param)
    {
        String userDetails[] = param.split(",");
        return teamManagement.openTeam(userDetails[0],Double.parseDouble(userDetails[1]));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getFullTeamsNames")
    public ArrayList<String> getFullTeamsNames(){
        return systemManagement.getFullTeamsNames();
    }

    /** ------------ LEAGUE ------------ **/

    @RequestMapping(method = RequestMethod.GET, value = "/getLeagueTeamNumber")
    public int getLeagueTeamNumber(@RequestParam String param){
        return systemManagement.getLeagueTeamNumber(param);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getLeaguesNames")
    public ArrayList<String> getLeaguesNames(){
        return systemManagement.getLeagueNames();
    }
    /** ------------ SEASON ------------ **/

    @RequestMapping(method = RequestMethod.GET, value = "/getAllSeasonYears")
    public ArrayList<Integer> getAllSeasonYears(@RequestParam String param){
        return systemManagement.getAllSeasonYears(param);
    }

    /** ------------ GAMES ------------ **/
    @RequestMapping(method = RequestMethod.GET, value = "/addGameEvent")
    public boolean addGameEvent(@RequestParam String param){
        String gameDetails[] = param.split(",");
        gameDetails[0] = gameDetails[0].substring(1);
        boolean ans = leagueSeasonManagement.addGameEvent(gameDetails[0], gameDetails[1], gameDetails[2], gameDetails[3]);
        return ans;
    }














}
