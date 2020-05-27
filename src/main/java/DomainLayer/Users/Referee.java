package DomainLayer.Users;

import DomainLayer.Games.Game;
import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.SystemLogic.MainSystem;
import DomainLayer.SystemLogic.Notification;

import java.util.*;


/**
 * this class represent a referee in the system.
 */
public class Referee extends User implements Observer {

    private String qualification;
    protected LinkedList<Game> myGames;
    private DBLocal dbLocal;
    protected ArrayList<Integer> gamesId;



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
        this.dbLocal = DBLocal.getInstance();
    }

    /**********getters and setters**********/

    public ArrayList<Integer> getGamesId() {
        return gamesId;
    }

    public void setGamesId(ArrayList<Integer> gamesId) {
        this.gamesId = gamesId;
    }

    public LinkedList<Game> getMyGames() {
        return myGames;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
        MainSystem.LOG.info(this.userName + ": referee's details were updated.");
        dbLocal.setUser(this);
    }

    public void addGame (Game g){ //adds a game to the referee's list of games.
        myGames.add(g);
    }


    ////////////////////////////// USE CASE 10.2 ////////////////////////////////////
    /**
     * this function display all referee's games with details.
     * @return - list of Strings - each one represents a different game.
     */
    public ArrayList<Game> watchGamesList (){

        ArrayList<Game> allGames = new ArrayList<>();

        if (myGames != null) {
            for (Game g : myGames) {
                if( g != null && g.getStatus().equals(Game.gameStatus.preGame))
                    allGames.add(g);
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
        dbLocal.setUser(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        User user = DBLocal.getInstance().getUserType("AssociationRepresentative");
        String message = (String) arg;
        Notification notification = new Notification(user,message,this);
        //System.out.println("i got message");
        notification.send();
    }
}
