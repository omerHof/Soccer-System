package UILayer;

import Games.Event;
import SystemLogic.MainSystem;
import Users.AssociationRepresentative;
import com.jfoenix.controls.JFXTextField;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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

        /**
         * example of GET request
         */

        public ArrayList<String> getLeaguesNames() {

                HttpEntity<ArrayList> response = template.exchange("http://localhost:8090/getLeaguesNames",
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        public List<String> getFullTeamsNames() {
                HttpEntity<ArrayList> response = template.exchange("http://localhost:8090/getFullTeamsNames",
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();

        }

        /**
         * example of GET with parameter
         * @param type
         * @return
         */
        public List<String> getAllUsersByType(String type) {
                HttpEntity<ArrayList> response = template.exchange("http://localhost:8090/getAllUsersByType?param="+type,
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        public int getLeagueTeamNumber(String numOfTeams) {
                HttpEntity<Integer> response = template.exchange("http://localhost:8090/getLeagueTeamNumber?param="+numOfTeams,
                        HttpMethod.GET, requestEntity, Integer.class, "42");

                return response.getBody();
        }

        public ArrayList<Integer> getAllSeasonYears(String leagueName) {
                HttpEntity<ArrayList> response = template.exchange("http://localhost:8090/getAllSeasonYears?param="+leagueName,
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        public ArrayList<String> closestGames(String leagueName) {
                HttpEntity<ArrayList> response = template.exchange("http://localhost:8090/closestGames?param="+leagueName,
                        HttpMethod.GET, requestEntity, ArrayList.class, "42");

                return response.getBody();
        }

        public HashMap<String, String> getAllPlayers() {
                HttpEntity<HashMap> response = template.exchange("http://localhost:8090/getAllPlayers",
                        HttpMethod.GET, requestEntity, HashMap.class, "42");

                return response.getBody();
        }


        /**
         * example of POST request
         */

        public void setNewSeason() {
                HttpEntity<String> request = new HttpEntity<>(new String("bar"));
                ResponseEntity<String> response2 = template
                        .exchange("http://132.72.65.53/8080/testPost", HttpMethod.POST, request, String.class);

                //assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

                String foo = response2.getBody();
        }

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

        public boolean addGameEvent(String type, String time, String playerName, String whichTeam){

                HttpEntity<String> request = new HttpEntity<>(type+ "," + time+ "," + playerName+ "," + whichTeam);
                HttpEntity<Boolean> response = template.exchange("http://localhost:8090/addGameEvent?param="+request,
                        HttpMethod.GET, requestEntity, Boolean.class, "42");

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

        public void setCoachRole(String coachRole) {
                HttpEntity response = template.exchange("http://localhost:8090/setCoachRole?param="+coachRole,
                        HttpMethod.GET, requestEntity, String.class, "42");
        }

        public void setPlayerPosition(String playerPosition) {
                HttpEntity response = template.exchange("http://localhost:8090/setPlayerPosition?param="+playerPosition,
                        HttpMethod.GET, requestEntity, String.class, "42");
        }

        public void setRefereeQualification(String refereeQualification) {
                HttpEntity response = template.exchange("http://localhost:8090/setRefereeQualification?param="+refereeQualification,
                        HttpMethod.GET, requestEntity, String.class, "42");
        }

        ///////////// newwww clientttt
        public String getUserType(String userName){
                HttpEntity<String> response = template.exchange("http://localhost:8090/getUserType?param="+userName,
                        HttpMethod.GET, requestEntity, String.class, "42");

                return response.getBody();
        }

        public String displaySpecialPassword(){
                HttpEntity<String> response = template.exchange("http://localhost:8090/displaySpecialPassword?",
                        HttpMethod.GET, requestEntity, String.class, "42");

                return response.getBody();
        }


        public String getPlayerPosition() {
                HttpEntity<String> response = template.exchange("http://localhost:8090/getPlayerPosition",
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        public String getCoachRole() {
                HttpEntity<String> response = template.exchange("http://localhost:8090/getCoachRole",
                        HttpMethod.GET, requestEntity, String.class, "42");
                return response.getBody();
        }

        public String getRefereeQualifications() {
                HttpEntity<String> response = template.exchange("http://localhost:8090/getRefereeQualifications",
                        HttpMethod.GET, requestEntity, String.class, "42");
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






/*
        public void addSeasonToLeague(String leagueName, int year, String scorePolicy, String gamePolicy, List<String> teams, List<String> referees, List<String> representatives) {

                HttpEntity<String,Integer> request = new HttpEntity<>(leagueName,year);
                ResponseEntity<String> response2 = template
                        .exchange("http://localhost:8090/addSeasonToLeague", HttpMethod.POST, request, String.class);
                //assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
                String foo = response2.getBody();
        }*/



}
