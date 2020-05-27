package UILayer;

import DomainLayer.Games.Game;
import DomainLayer.Teams.Stadium;
import DomainLayer.Users.Coach;
import DomainLayer.Users.Manager;
import DomainLayer.Users.Player;
import javafx.util.Pair;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;


public class ClientController {

        RestTemplate template;
        HttpHeaders requestHeaders;
        HttpEntity<?> requestEntity;


        public ClientController() {
                template = new RestTemplate();
                requestHeaders = new HttpHeaders();
                requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                requestHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
                requestHeaders.set("MyRequestHeader", "MyValue");
                requestEntity = new HttpEntity(requestHeaders);

        }

        /** ------------ LOGIN + SIGN UP ------------ **/

        public String logIn(String userName, String password)  {
                HttpEntity<String> request = new HttpEntity<>(userName+ "," + password);
                ResponseEntity<String> response2 = template.exchange("http://132.72.65.53:8090/logIn", HttpMethod.POST, request, String.class);
                //assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
                return response2.getBody();
        }

        public void logOut() {
                HttpEntity<ArrayList> response = template.exchange("http://132.72.65.53:8090/logOut",
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");
        }

        public String displaySpecialPassword(){
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/displaySpecialPassword?",
                        HttpMethod.GET, requestEntity, String.class, "42");

                return response.getBody();
        }

        public String signUp(String username, String password, String managementPassword, String userType, String fullname, String email, LocalDate birthday, String qualification, String courtRole, String teamRole, String generatorType) {
                SignUpParam signUpParam = new SignUpParam(username,password,managementPassword,userType,fullname,email,birthday,qualification,courtRole,teamRole,generatorType);
                HttpEntity<SignUpParam> request = new HttpEntity<>(signUpParam);
                request.getBody();
                ResponseEntity<String> response2 = template
                        .exchange("http://132.72.65.53:8090/signUp", HttpMethod.POST, request, String.class);
                return response2.getBody();

        }

        /** ------------ USERS ------------ **/
        public List<String> getAllUsersByType(String type) {
                HttpEntity<ArrayList> response = template.exchange("http://132.72.65.53:8090/getAllUsersByType?param="+type,
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        public List<String> getUserDetails() {

                HttpEntity<ArrayList> response = template.exchange("http://132.72.65.53:8090/getUserDetails",
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        public void setUserEmail(String userEmail) {
                HttpEntity response = template.exchange("http://132.72.65.53:8090/setUserEmail?param="+userEmail,
                        HttpMethod.GET, requestEntity, String.class, "42");
        }

        public void setUserFullName(String fullName) {
                HttpEntity response = template.exchange("http://132.72.65.53:8090/setUserFullName?param="+fullName,
                        HttpMethod.GET, requestEntity, String.class, "42");
        }

        ///////////// newwww clientttt
        public String getUserType(String userName){
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/getUserType?param=" + userName,
                        HttpMethod.GET, requestEntity, String.class, "42");

                return response.getBody();
        }

        public ArrayList<String> getNotifications() {
                HttpEntity<ArrayList> response = template.exchange("http://132.72.65.53:8090/getNotifications",
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        public void sendNotification(String s, String username) {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/sendNotification?param="+s+","+username,
                        HttpMethod.GET, requestEntity, String.class, "42");
        }
        public void readNotification(String notification) {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/readNotification?param="+notification,
                        HttpMethod.GET, requestEntity, String.class, "42");
        }



        /** ------------ COACHES ------------ **/


        public HashMap<String, String> getAllCoaches() {
                HttpEntity<HashMap> response = template.exchange("http://132.72.65.53:8090/getAllCoaches",
                        HttpMethod.GET, requestEntity, HashMap.class, "42");

                return response.getBody();
        }

        public void setCoachRole(String coachRole) {
                HttpEntity response = template.exchange("http://132.72.65.53:8090/setCoachRole?param="+coachRole,
                        HttpMethod.GET, requestEntity, String.class, "42");
        }

        public ArrayList<String> getLeaguesNames() {

                HttpEntity<ArrayList> response = template.exchange("http://132.72.65.53:8090/getLeaguesNames",
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        public String getCoachRole() {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/getCoachRole",
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        public String getCoachPageDetails(String user_name) {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/getCoachPageDetails?param=" +user_name,
                        HttpMethod.GET, requestEntity, String.class, "42");

                return response.getBody();
        }

        public String createCoachPersonalPage(String coachTeam, String birthDayAsString) {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/createCoachPersonalPage?param="+coachTeam+","+birthDayAsString,
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }



        /** ------------ PLAYERS ------------ **/

        public HashMap<String, String> getAllPlayers() {
                HttpEntity<HashMap> response = template.exchange("http://132.72.65.53:8090/getAllPlayers",
                        HttpMethod.GET, requestEntity, HashMap.class, "42");

                return response.getBody();
        }

        public List<String> getFullTeamsNames() {
                HttpEntity<ArrayList> response = template.exchange("http://132.72.65.53:8090/getFullTeamsNames",
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        public String getPlayerPosition() {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/getPlayerPosition",
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        public void setPlayerPosition(String playerPosition) {
                HttpEntity response = template.exchange("http://132.72.65.53:8090/setPlayerPosition?param="+playerPosition,
                        HttpMethod.GET, requestEntity, String.class, "42");
        }

        public String getPlayerTeam() {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/getPlayerTeam",
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        public void createPlayerPersonalPage(double height, int weight, int shirtNumber, String team) {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/createPlayerPersonalPage?param="+ String.valueOf(height)+ ","+String.valueOf(weight)+","+String.valueOf(shirtNumber)+","+team,
                        HttpMethod.GET, requestEntity, String.class, "42");
        }



        public String getPlayerPageDetails(String user_name) {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/getPlayerPageDetails?param=" +user_name,
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        public ArrayList<String> getPageHistory(String user_name) {
                HttpEntity<ArrayList> response = template.exchange("http://132.72.65.53:8090/getPageHistory?param=" + user_name,
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        /** ------------ FAN ------------ **/

        public void followPage(String user_name_player) {
               template.exchange("http://132.72.65.53:8090/followPage?param="+ user_name_player,
                        HttpMethod.GET, requestEntity, String.class, "42");
        }

        public void followTeam(String team_name) {
                template.exchange("http://132.72.65.53:8090/followTeam?param="+team_name,
                        HttpMethod.GET, requestEntity, String.class, "42");
        }

        /** ------------ TEAM OWNER ------------ **/
        public String addAssent(String assentType, String assentName) {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/addAssent?param="+assentType + ","+assentName,
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        public String removeAssent(String assentType, String assentName) {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/removeAssent?param="+assentType + ","+assentName,
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        /** ------------ REFEREE ------------ **/

        public void setRefereeQualification(String refereeQualification) {
                HttpEntity response = template.exchange("http://132.72.65.53:8090/setRefereeQualification?param="+refereeQualification,
                        HttpMethod.GET, requestEntity, String.class, "42");
        }

        public String getRefereeQualifications() {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/getRefereeQualifications",
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        public  ArrayList<String> watchGamesList() {
                HttpEntity<ArrayList> response = template.exchange("http://132.72.65.53:8090/watchGamesList",
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");
                return response.getBody();
        }

        /** ------------ ADMINISTRATOR ------------ **/
        public  String watchLogFiles() {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/getLogFiles",
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        public  String watchErrorLogFiles() {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/getErrorLogFiles",
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }


        /** ------------ TEAMS ------------ **/

        public ArrayList<String> getAllTeams() {
                HttpEntity<ArrayList> response = template.exchange("http://132.72.65.53:8090/getAllTeams",
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        public boolean checkIfTeamNameExist(String text) {
                HttpEntity<Boolean> response = template.exchange("http://132.72.65.53:8090/checkIfTeamNameExist?param="+text,
                        HttpMethod.GET, requestEntity, Boolean.class, "42");
                return response.getBody();
        }

        public String CreateNewTeam(String teamName, String text) {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/CreateNewTeam?param="+teamName+ ","+text,
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        public int getLeagueTeamNumber(String numOfTeams) {
                HttpEntity<Integer> response = template.exchange("http://132.72.65.53:8090/getLeagueTeamNumber?param="+numOfTeams,
                        HttpMethod.GET, requestEntity, Integer.class, "42");

                return response.getBody();
        }

        public String getTeamPageDetails(String team_name) {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/getTeamPageDetails?param=" +team_name,
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        public ArrayList<String> getTeamPlayers(String team_name) {
                HttpEntity<ArrayList> response = template.exchange("http://132.72.65.53:8090/getTeamPlayers?param=" +team_name,
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");
                return response.getBody();
        }

        public ArrayList<String> getTeamCoaches(String team_name) {
                HttpEntity<ArrayList> response = template.exchange("http://132.72.65.53:8090/getTeamCoaches?param=" +team_name,
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");
                return response.getBody();
        }

        public ArrayList<String> getTeamManagers(String team_name) {
                HttpEntity<ArrayList> response = template.exchange("http://132.72.65.53:8090/getTeamManagers?param=" +team_name,
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");
                return response.getBody();
        }

        public String opemTeamPage(String history, String nation) {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/opemTeamPage?param="+history+ ","+nation,
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        public String checkIfHasATeam() {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/checkIfHasATeam",
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }




        /** ------------ LEAGUE ------------ **/

        public String addSeasonToLeague(String leagueName, int year, String scorePolicy, String gamePolicy, List<String> teams, List<String> referees, List<String> representatives) {

                AddSeasonParam param = new AddSeasonParam(leagueName, year, scorePolicy, gamePolicy, teams, referees, representatives);
                HttpEntity<AddSeasonParam> request = new HttpEntity<>(param);
                request.getBody();
                ResponseEntity<String> response2 = template
                        .exchange("http://132.72.65.53:8090/addSeasonToLeague", HttpMethod.POST, request, String.class);
                return response2.getBody();
        }



        /** ------------ SEASON ------------ **/

        public ArrayList<Integer> getAllSeasonYears(String leagueName) {
                HttpEntity<ArrayList> response = template.exchange("http://132.72.65.53:8090/getAllSeasonYears?param="+leagueName,
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        /** ------------ GAMES ------------ **/

        public boolean addGameEvent(String type, String time, String playerName, String whichTeam){

                HttpEntity<String> request = new HttpEntity<>(type+ "," + time+ "," + playerName+ "," + whichTeam);
                HttpEntity<Boolean> response = template.exchange("http://132.72.65.53:8090/addGameEvent?param="+request,
                        HttpMethod.GET, requestEntity, Boolean.class, "42");

                return response.getBody();
        }

        public ArrayList<String> closestGames(String leagueName) {
                HttpEntity<ArrayList> response = template.exchange("http://132.72.65.53:8090/closestGames?param="+leagueName,
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        public String checkFinishedGames(String userName, String userType) {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/checkFinishedGames?param="+userName+ ","+userType,
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        public boolean setReport(String userName, String report) {
                HttpEntity<String> request = new HttpEntity<>(userName+","+report);//todo change to String
                request.getBody();
                ResponseEntity<Boolean> response2 = template
                        .exchange("http://132.72.65.53:8090/setReport", HttpMethod.POST, request, Boolean.class);
                return response2.getBody();
        }

        public void closeProgram() {
                HttpEntity<String> response = template.exchange("http://132.72.65.53:8090/closeProgram",
                        HttpMethod.GET, requestEntity, String.class, "42");

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

class SignUpParam{
        String userName;
        String password;
        String managementPassword;
        String userType;
        String fullname;
        String email;
        LocalDate birthDay;
        String qualification;
        String courtRole;
        String teamRole;
        String generatorType;

        public SignUpParam(String userName, String password, String managementPassword, String userType, String fullname, String email, LocalDate birthDay, String qualification, String courtRole, String teamRole, String generatorType) {
                this.userName = userName;
                this.password = password;
                this.managementPassword = managementPassword;
                this.userType = userType;
                this.fullname = fullname;
                this.email = email;
                this.birthDay = birthDay;
                this.qualification = qualification;
                this.courtRole = courtRole;
                this.teamRole = teamRole;
                this.generatorType = generatorType;
        }

        public String getUserName() {
                return userName;
        }

        public String getPassword() {
                return password;
        }

        public String getManagementPassword() {
                return managementPassword;
        }

        public String getUserType() {
                return userType;
        }

        public String getFullname() {
                return fullname;
        }

        public String getEmail() {
                return email;
        }

        public LocalDate getBirthDay() {
                return birthDay;
        }

        public String getQualification() {
                return qualification;
        }

        public String getCourtRole() {
                return courtRole;
        }

        public String getTeamRole() {
                return teamRole;
        }

        public String getGeneratorType() {
                return generatorType;
        }
}