package UILayer;

import Teams.Stadium;
import Users.Coach;
import Users.Manager;
import Users.Player;
import javafx.util.Pair;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class ClientController {

        RestTemplate template;
        HttpHeaders requestHeaders;
        HttpEntity<?> requestEntity;


        public ClientController() {
                template = new RestTemplate();
                requestHeaders = new HttpHeaders();
                requestHeaders.set("MyRequestHeader", "MyValue");
                requestEntity = new HttpEntity(requestHeaders);

        }

        /** ------------ LOGIN ------------ **/

        public String logIn(String userName, String password)  {
                HttpEntity<String> request = new HttpEntity<>(userName+ "," + password);
                ResponseEntity<String> response2 = template.exchange("http://localhost:8090/logIn", HttpMethod.POST, request, String.class);
                //assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
                return response2.getBody();
        }

        public void logOut() {
                HttpEntity<ArrayList> response = template.exchange("http://localhost:8090/logOut",
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");
        }

        public String displaySpecialPassword(){
                HttpEntity<String> response = template.exchange("http://localhost:8090/displaySpecialPassword?",
                        HttpMethod.GET, requestEntity, String.class, "42");

                return response.getBody();
        }

        /** ------------ USERS ------------ **/
        public List<String> getAllUsersByType(String type) {
                HttpEntity<ArrayList> response = template.exchange("http://localhost:8090/getAllUsersByType?param="+type,
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        public List<String> getUserDetails() {

                HttpEntity<ArrayList> response = template.exchange("http://localhost:8090/getUserDetails",
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        public void setUserEmail(String userEmail) {
                HttpEntity response = template.exchange("http://localhost:8090/setUserEmail?param="+userEmail,
                        HttpMethod.GET, requestEntity, String.class, "42");
        }

        public void setUserFullName(String fullName) {
                HttpEntity response = template.exchange("http://localhost:8090/setUserFullName?param="+fullName,
                        HttpMethod.GET, requestEntity, String.class, "42");
        }

        ///////////// newwww clientttt
        public String getUserType(String userName){
                HttpEntity<String> response = template.exchange("http://localhost:8090/getUserType?param=" + userName,
                        HttpMethod.GET, requestEntity, String.class, "42");

                return response.getBody();
        }

        /** ------------ COACHES ------------ **/


        public HashMap<String, String> getAllCoaches() {
                HttpEntity<HashMap> response = template.exchange("http://localhost:8090/getAllCoaches",
                        HttpMethod.GET, requestEntity, HashMap.class, "42");

                return response.getBody();
        }

        public void setCoachRole(String coachRole) {
                HttpEntity response = template.exchange("http://localhost:8090/setCoachRole?param="+coachRole,
                        HttpMethod.GET, requestEntity, String.class, "42");
        }

        public ArrayList<String> getLeaguesNames() {

                HttpEntity<ArrayList> response = template.exchange("http://localhost:8090/getLeaguesNames",
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        public String getCoachRole() {
                HttpEntity<String> response = template.exchange("http://localhost:8090/getCoachRole",
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        public String getCoachPageDetails(String user_name) {
                HttpEntity<String> response = template.exchange("http://localhost:8090/getCoachPageDetails?param=" +user_name,
                        HttpMethod.GET, requestEntity, String.class, "42");

                return response.getBody();
        }

        public String createCoachPersonalPage(String coachTeam, String birthDayAsString) {
                HttpEntity<String> response = template.exchange("http://localhost:8090/createCoachPersonalPage?param="+coachTeam+","+birthDayAsString,
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        /** ------------ PLAYERS ------------ **/

        public HashMap<String, String> getAllPlayers() {
                HttpEntity<HashMap> response = template.exchange("http://localhost:8090/getAllPlayers",
                        HttpMethod.GET, requestEntity, HashMap.class, "42");

                return response.getBody();
        }

        public List<String> getFullTeamsNames() {
                HttpEntity<ArrayList> response = template.exchange("http://localhost:8090/getFullTeamsNames",
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        public String getPlayerPosition() {
                HttpEntity<String> response = template.exchange("http://localhost:8090/getPlayerPosition",
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        public void setPlayerPosition(String playerPosition) {
                HttpEntity response = template.exchange("http://localhost:8090/setPlayerPosition?param="+playerPosition,
                        HttpMethod.GET, requestEntity, String.class, "42");
        }

        public String getPlayerTeam() {
                HttpEntity<String> response = template.exchange("http://localhost:8090/getPlayerTeam",
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        public void createPlayerPersonalPage(double height, int weight, int shirtNumber, String team) {
                HttpEntity<String> response = template.exchange("http://localhost:8090/createPlayerPersonalPage?param="+ String.valueOf(height)+ ","+String.valueOf(weight)+","+String.valueOf(shirtNumber)+","+team,
                        HttpMethod.GET, requestEntity, String.class, "42");
        }


        public Pair<String, ArrayList<String>> getPlayerPageDetails(String user_name) {
                ResponseEntity<Pair> response = template.exchange("http://localhost:8090/getPlayerPageDetails?param=" +user_name,
                        HttpMethod.GET, requestEntity, Pair.class, "42");
                //todo: not working!
        public String getPlayerPageDetails(String user_name) {
                HttpEntity<String> response = template.exchange("http://localhost:8090/getPlayerPageDetails?param=" +user_name,
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        public ArrayList<String> getPageHistory(String user_name) {
                HttpEntity<ArrayList> response = template.exchange("http://localhost:8090/getPageHistory?param=" + user_name,
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        /** ------------ REFEREE ------------ **/

        public void setRefereeQualification(String refereeQualification) {
                HttpEntity response = template.exchange("http://localhost:8090/setRefereeQualification?param="+refereeQualification,
                        HttpMethod.GET, requestEntity, String.class, "42");
        }

        public String getRefereeQualifications() {
                HttpEntity<String> response = template.exchange("http://localhost:8090/getRefereeQualifications",
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        /** ------------ TEAMS ------------ **/

        public ArrayList<String> getAllTeams() {
                HttpEntity<ArrayList> response = template.exchange("http://localhost:8090/getAllTeams",
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        public boolean checkIfTeamNameExist(String text) {
                HttpEntity<Boolean> response = template.exchange("http://localhost:8090/checkIfTeamNameExist?param="+text,
                        HttpMethod.GET, requestEntity, Boolean.class, "42");
                return response.getBody();
        }

        public String CreateNewTeam(String teamName, String text) {
                HttpEntity<String> response = template.exchange("http://localhost:8090/CreateNewTeam?param="+teamName+ ","+text,
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        public int getLeagueTeamNumber(String numOfTeams) {
                HttpEntity<Integer> response = template.exchange("http://localhost:8090/getLeagueTeamNumber?param="+numOfTeams,
                        HttpMethod.GET, requestEntity, Integer.class, "42");

                return response.getBody();
        }
        /*

        public List<String> getFullTeamsNames() {
                HttpEntity<ArrayList> response = template.exchange("http://localhost:8090/getFullTeamsNames",
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }
        */


        public String getTeamPageDetails(String team_name) {
                HttpEntity<String> response = template.exchange("http://localhost:8090/getTeamPageDetails?param=" +team_name,
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        public ArrayList<String> getTeamPlayers(String team_name) {
                HttpEntity<ArrayList> response = template.exchange("http://localhost:8090/getTeamPlayers?param=" +team_name,
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");
                return response.getBody();
        }

        public ArrayList<String> getTeamCoaches(String team_name) {
                HttpEntity<ArrayList> response = template.exchange("http://localhost:8090/getTeamCoaches?param=" +team_name,
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");
                return response.getBody();
        }

        public ArrayList<String> getTeamManagers(String team_name) {
                HttpEntity<ArrayList> response = template.exchange("http://localhost:8090/getTeamManagers?param=" +team_name,
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");
                return response.getBody();
        }


        /** ------------ LEAGUE ------------ **/

        public String addSeasonToLeague(String leagueName, int year, String scorePolicy, String gamePolicy, List<String> teams, List<String> referees, List<String> representatives) {

                AddSeasonParam param = new AddSeasonParam(leagueName, year, scorePolicy, gamePolicy, teams, referees, representatives);
                HttpEntity<AddSeasonParam> request = new HttpEntity<>(param);
                request.getBody();
                ResponseEntity<String> response2 = template
                        .exchange("http://localhost:8090/addSeasonToLeague", HttpMethod.POST, request, String.class);
                return response2.getBody();
        }

        /** ------------ SEASON ------------ **/

        public ArrayList<Integer> getAllSeasonYears(String leagueName) {
                HttpEntity<ArrayList> response = template.exchange("http://localhost:8090/getAllSeasonYears?param="+leagueName,
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        /** ------------ GAMES ------------ **/

        public boolean addGameEvent(String type, String time, String playerName, String whichTeam){

                HttpEntity<String> request = new HttpEntity<>(type+ "," + time+ "," + playerName+ "," + whichTeam);
                HttpEntity<Boolean> response = template.exchange("http://localhost:8090/addGameEvent?param="+request,
                        HttpMethod.GET, requestEntity, Boolean.class, "42");

                return response.getBody();
        }

        public ArrayList<String> closestGames(String leagueName) {
                HttpEntity<ArrayList> response = template.exchange("http://localhost:8090/closestGames?param="+leagueName,
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        public String checkFinishedGames(String userName, String userType) {
                HttpEntity<String> response = template.exchange("http://localhost:8090/checkFinishedGames?param="+userName+ ","+userType,
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
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