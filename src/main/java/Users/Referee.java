package Users;

import Games.Game;
import SystemLogic.DB;
import SystemLogic.MainSystem;
import SystemLogic.Notification;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


/**
 * this class represent a referee in the system.
 */
public class Referee extends User implements Observer {

    private String qualification;
    protected LinkedList<Game> myGames;
    private DB db;


    /**
     * constructor - all tha person's details:
     *
     * @param userName
     * @param password
     * @param fullName
     * @param userEmail
     * @param qualification - an unique attribute for the referee (linesman, VAR,...)
     */
    public Referee(String userName, String password, String fullName,String userEmail, String qualification) {
        this.userName = userName;
        this.password = password;
        this.userFullName = fullName;
        this.userEmail = userEmail;
        this.qualification = qualification;
        myGames = new LinkedList<>();
        this.db = DB.getInstance();
    }

    /**********getters and setters**********/

    public LinkedList<Game> getMyGames() {
        return myGames;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
        MainSystem.LOG.info(this.userName + ": referee's details were updated.");
        db.setUser(this);
    }

    public void addGame (Game g){ //adds a game to the referee's list of games.
        myGames.add(g);
    }


    ////////////////////////////// USE CASE 10.2 ////////////////////////////////////
    /**
     * this function display all referee's games with details.
     * @return - list of Strings - each one represents a diferent game.
     */
    public List<String> watchGamesList (){

        LinkedList<String> allGames = new LinkedList<>();

        if (myGames != null) {
            for (Game g : myGames) {
                if( g != null)
                    allGames.add(g.getGameDate() + " , " + g.getHomeTeam().getName() + "-" + g.getAwayTeam().getName());
            }
            return allGames;
        }
        return null;
    }


    /**
     * this function adds the game to this referee's list of games,
     * and adds the referee as an observer to the game. (both directions)
     */
    public void followThisGame(Game game){
        game.addObserver(this);
        myGames.add(game);
        db.setUser(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        User user = DB.getInstance().getUserType("AssociationRepresentative");
        String message = (String) arg;
        Notification notification = new Notification(user,message,this);
        //System.out.println("i got message");
        notification.send();
    }
}
