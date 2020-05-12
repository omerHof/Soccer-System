package IO;

import ServiceLayer.LeagueSeasonManagement;
import ServiceLayer.SystemManagement;
import ServiceLayer.TeamManagement;
import ServiceLayer.UserManagement;
import SystemLogic.MainSystem;
import Teams.Stadium;
import Users.Coach;
import Users.Manager;
import Users.Player;
import javafx.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    @RequestMapping(method = RequestMethod.GET, value = "/getAllCoaches")
    public HashMap<String, String> getAllCoaches()
    {
        return userManagement.getAllCoaches();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/setCoachRole")
    public void setCoachRole(@RequestParam String param){
        userManagement.setCoachTeamRole(param);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getCoachRole")
    public String getCoachRole(){
        return userManagement.getCoachTeamRole();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/createCoachPersonalPage")
    public void createCoachPersonalPage(@RequestParam String param){
        String userDetails[] = param.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale( Locale.ITALIAN );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
        LocalDate date = LocalDate.parse(userDetails[1], formatter);
        userManagement.createCoachPersonalPage(date,userDetails[0]);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getCoachPageDetails")
    public String getCoachPageDetails(@RequestParam String param){

        return userManagement.getCoachPageDetails(param);
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

    @RequestMapping(method = RequestMethod.GET, value = "/getPlayerTeam")
    public String getPlayerTeam()
    {
        return userManagement.getPlayerTeam();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/createPlayerPersonalPage")
    public void createPlayerPersonalPage(@RequestParam String param)
    {
        String userDetails[] = param.split(",");
        userManagement.createPlayerPersonalPage(Double.parseDouble(userDetails[0]),Integer.parseInt(userDetails[1]),Integer.parseInt(userDetails[2]),userDetails[3]);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getPlayerPageDetails")
    public String getPlayerPageDetails(@RequestParam String param){

        return userManagement.getPlayerPageDetails(param);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/getPageHistory")
    public ArrayList<String> getPageHistory(@RequestParam String param){
        return userManagement.getPageHistory(param);
    }

    /** ------------ FAN ------------ **/
    @RequestMapping(method = RequestMethod.GET, value = "/followPage")
    public void followPage(@RequestParam String param){

        userManagement.followPage(param);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/followTeam")
    public void followTeam(@RequestParam String param){

        userManagement.followTeam(param);
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

    @RequestMapping(method = RequestMethod.GET, value = "/getAllTeams")
    public ArrayList<String> getAllTeams(){
        return teamManagement.getAllTeams();
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

    @RequestMapping(method = RequestMethod.GET, value = "/getTeamPageDetails")
    public  String  getTeamPageDetails(@RequestParam String param){
        return teamManagement.getTeamPageDetails(param);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getTeamPlayers")
    public  ArrayList<String>  getTeamPlayers(@RequestParam String param){
        return teamManagement.getTeamPlayers(param);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getTeamCoaches")
    public  ArrayList<String>  getTeamCoaches(@RequestParam String param){
        return teamManagement.getTeamCoaches(param);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getTeamManagers")
    public  ArrayList<String>  getTeamManagers(@RequestParam String param){
        return teamManagement.getTeamManagers(param);
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

    @RequestMapping(method = RequestMethod.POST, value = "/addSeasonToLeague")
    public String addSeasonToLeague(@RequestBody AddSeasonParam param) {
        String check = leagueSeasonManagement.addSeasonToLeague(param.getLeagueName(),param.getYear(),param.getScorePolicy(),param.getGamePolicy(),param.getTeams(),param.getReferees(),param.getRepresentatives());
        return check;
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

    @RequestMapping(method = RequestMethod.GET, value = "/closestGames")
    public ArrayList<String> closestGames(@RequestParam String param){
        return systemManagement.closestGames(param);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/checkFinishedGames")
    public String checkFinishedGames(@RequestParam String param)
    {
        String userDetails[] = param.split(",");
        return userManagement.checkFinishedGames(userDetails[0],userDetails[1]);
    }
}

class AddSeasonParam {
    String leagueName;
    int year;
    String scorePolicy;
    String gamePolicy;
    List<String> teams;
    List<String> referees;
    List<String> representatives;

    public AddSeasonParam(String leagueName, int year, String scorePolicy, String gamePolicy, List<String> teams, List<String> referees, List<String> representatives) {
        this.leagueName = leagueName;
        this.year = year;
        this.scorePolicy = scorePolicy;
        this.gamePolicy = gamePolicy;
        this.teams = teams;
        this.referees = referees;
        this.representatives = representatives;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public int getYear() {
        return year;
    }

    public String getScorePolicy() {
        return scorePolicy;
    }

    public String getGamePolicy() {
        return gamePolicy;
    }

    public List<String> getTeams() {
        return teams;
    }

    public List<String> getReferees() {
        return referees;
    }

    public List<String> getRepresentatives() {
        return representatives;
    }
}
