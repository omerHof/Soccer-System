package DomainLayer.LeagueSeasonsManagment;

import DomainLayer.Teams.Team;

import java.util.LinkedList;
import java.util.List;

/**
 * this class represent a league in the system.
 */
public class League {

    private String name;
    private int numOfTeams;
    private List<Season> allSeasons;
    private List<Team>teams;

    /**
     * constructor - with all league's details:
     *
     * @param name - of the new league (unique)
     * @param numOfTeams - total teams playing in the new league
     */
    public League(String name, int numOfTeams) {
        this.name = name;
        this.numOfTeams = numOfTeams;
        allSeasons = new LinkedList<>();
    }

    /**********getters and setters**********/

    public List<Season> getAllSeasons() {
        return allSeasons;
    }
    public String getName(){
        return name;
    }
    public int getNumOfTeams(){
        return numOfTeams;
    }
    public List<Team>getTeams(){
        return teams;
    }

    public void setAllSeasons(List<Season> allSeasons) {
        this.allSeasons = allSeasons;
    }
    public void setName(String newName){
        name = newName;
    }
    public void setNumOfTeams(int num){
        numOfTeams=num;
    }
    public void setTeams(List<Team> allTeams){
        teams=allTeams;
    }
    public void addSeason(Season season){
        allSeasons.add(season);
    }

    public Season getSeasonByYear (int year){
        if (year > 0){
            for (Season season : allSeasons){
                if (season.getYear() == year)
                    return season;
            }
        }
        return null;
    }
}
