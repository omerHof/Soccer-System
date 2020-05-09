package SystemLogic;

import LeagueSeasonsManagment.League;
import Teams.Team;
import Users.Player;
import Users.Referee;
import Users.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * this class represent a search in the system.
 */
public class Search {


    /**
     * ***** all category's options the user can search for:*******
     */
    public enum Category {
        teams, players, referees, leagues ;
    }

    private String toSearch;
    private Category category;
    private DB db = DB.getInstance();


    /**
     * constructor - all the search details:
     * @param toSearch - string the user want to find about
     * @param category - type of category the user want to focus on. can be null if none.
     */
    public Search(String toSearch, Category category) {
        this.toSearch = toSearch;
        this.category = category;
    }

    /**
     * this function calls to the specific search we want to do - all or by category
     * @toSearch - the String we want to find
     * @category - the Category enum we want to look for
     * @return list of Objects found.
     */
    public List<Object> search (){

        List<Object> toReturn;

        if (category!= null)
            toReturn = searchByCategory();
        else
            toReturn = searchAll();

    return toReturn;
    }


    /**
     * this function searches for a specific kind of Objects in the DB
     * @toSearch - the String we want to find
     * @category - the Category enum we want to look for
     * @return list of Objects found.
     */
    public List<Object> searchByCategory (){

        List<Object> allObjects = new LinkedList<>();

        if(category.equals(Category.leagues)) { //category search by League:

            League objectToAdd = db.getLeague(toSearch); //league name
            if (objectToAdd != null)
                allObjects.add(objectToAdd);
        }

        else if(category.equals(Category.players)) { //category search by player:

            Player objectToAdd = null;
            Object user = db.getUserByFullName(toSearch);
            if(user instanceof Player)
                objectToAdd = (Player)user;
            if (objectToAdd != null && !allObjects.contains(objectToAdd)) //without duplicates))
                allObjects.add(objectToAdd);

            ArrayList<User> obj = db.getUserTypeList(toSearch);

            user = db.getUser(toSearch); //username
            if(user instanceof Player)
                objectToAdd = (Player)user;
            if (objectToAdd != null && !allObjects.contains(objectToAdd)) //without duplicates)))
                allObjects.add(objectToAdd);

            if(obj!=null && obj.size()>0){
                for(User currUser : obj) { //adds all the users list
                    if(!allObjects.contains(currUser)) //without duplicates
                        allObjects.add(currUser);
                }
            }
        }

        else if(category.equals(Category.referees)) { //category search by Referee:

            Referee objectToAdd = (Referee)db.getUserType(toSearch);
            if(objectToAdd!=null && !allObjects.contains(objectToAdd)) //without duplicates))))
                allObjects.add(objectToAdd);

            ArrayList<User> obj = db.getUserTypeList(toSearch);

            User user = db.getUserByFullName(toSearch); //fullname
            if(user instanceof Referee)
                objectToAdd = (Referee)user;
            if (objectToAdd != null && !allObjects.contains(objectToAdd)) //without duplicates))))
                allObjects.add(objectToAdd);

            if(obj!=null && obj.size()>0){
                for(User currUser : obj) { //adds all the users list
                    if (!allObjects.contains(currUser)) //without duplicates
                        allObjects.add(currUser);
                }
            }

            user = db.getUser(toSearch); //username
            if(user instanceof Referee)
                objectToAdd = (Referee)user;
            if (objectToAdd != null && !allObjects.contains(objectToAdd)) //without duplicates))))
                allObjects.add(objectToAdd);
        }

        else if(category.equals(Category.teams)) { //category search by Team:

            Team objectToAdd = db.getTeam(toSearch);
            if(objectToAdd!=null && !allObjects.contains(objectToAdd)) //without duplicates))))
                allObjects.add(objectToAdd);
        }
        return allObjects;
    }


    /**
     * this function searches for all kind of Objects in the DB
     * @toSearch - the String we want to find
     * @return list of Objects found.
     */
    public List<Object> searchAll (){

        List<Object> allObjects = new LinkedList<>();
        Object objectToAdd;

        //using any kind of search on DB in 3.. 2.... 1......

        objectToAdd = db.getUser(toSearch);
        if(objectToAdd!=null && !allObjects.contains(objectToAdd)) //without duplicates
            allObjects.add(objectToAdd);

        objectToAdd = db.getUserByFullName(toSearch);
        if(objectToAdd!=null && !allObjects.contains(objectToAdd)) //without duplicates
            allObjects.add(objectToAdd);

        objectToAdd = db.getTeam(toSearch);
        if(objectToAdd!=null && !allObjects.contains(objectToAdd)) //without duplicates)
            allObjects.add(objectToAdd);

        objectToAdd = db.getUserType(toSearch);
        if(objectToAdd!=null && !allObjects.contains(objectToAdd)) //without duplicates)
            allObjects.add(objectToAdd);

        objectToAdd = db.getLeague(toSearch);
        if(objectToAdd!=null && !allObjects.contains(objectToAdd)) //without duplicates)
            allObjects.add(objectToAdd);

        ArrayList<User> obj = db.getUserTypeList(toSearch);
        if(obj!=null && obj.size()>0){
            for(User user : obj) { //adds all the users list
                if(!allObjects.contains(user)) //without duplicates)
                    allObjects.add(user);
            }
        }

        return allObjects;
    }
}
