package UILayer;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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


/*
        public void addSeasonToLeague(String leagueName, int year, String scorePolicy, String gamePolicy, List<String> teams, List<String> referees, List<String> representatives) {

                HttpEntity<String,Integer> request = new HttpEntity<>(leagueName,year);
                ResponseEntity<String> response2 = template
                        .exchange("http://localhost:8090/addSeasonToLeague", HttpMethod.POST, request, String.class);
                //assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
                String foo = response2.getBody();
        }*/



}
