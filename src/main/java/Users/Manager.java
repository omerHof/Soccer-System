package Users;

import Teams.Assent;
import Teams.Team;

public class Manager extends User implements Assent {
    private double worth;
    private Team team;

    /**
     * Constructor
     * @param userName
     * @param password
     * @param fullName
     * @param userEmail
     */
    public Manager(String userName, String password, String fullName, String userEmail) {
        this.userName = userName;
        this.password = password;
        this.userFullName = fullName;
        this.userEmail = userEmail;
    }

    /**
     * This method changes the player's role
     * @param player
     * @param new_role
     * @return
     */
    public String changePlayerRole(Player player, String new_role){
        player.setCourtRole(new_role);
        return "ok";
    }

    /** ----------------- GETTERS AND SETTERS ----------------- **/

    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
