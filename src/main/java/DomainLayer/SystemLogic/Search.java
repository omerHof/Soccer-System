package DomainLayer.SystemLogic;

import DomainLayer.LeagueSeasonsManagment.League;
import DomainLayer.Teams.Team;
import DomainLayer.Users.Player;
import DomainLayer.Users.Referee;
import DomainLayer.Users.User;

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
    private DBLocal dbLocal = DBLocal.getInstance();


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
     * this function searches for a specific kind of Objects in the DBLocal
     * @toSearch - the String we want to find
     * @category - the Category enum we want to look for
     * @return list of Objects found.
     */
    public List<Object> searchByCategory (){

        List<Object> allObjects = new LinkedList<>();

        if(category.equals(Category.leagues)) { //category search by League:

            League objectToAdd = dbLocal.getLeague(toSearch); //league name
            if (objectToAdd != null)
                allObjects.add(objectToAdd);
        }

        else if(category.equals(Category.players)) { //category search by player:

            Player objectToAdd = null;
            Object user = dbLocal.getUserByFullName(toSearch);
            if(user instanceof Player)
                objectToAdd = (Player)user;
            if (objectToAdd != null && !allObjects.contains(objectToAdd)) //without duplicates))
                allObjects.add(objectToAdd);

            ArrayList<User> obj = dbLocal.getUserTypeList(toSearch);

            user = dbLocal.getUser(toSearch); //username
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

            Referee objectToAdd = (Referee) dbLocal.getUserType(toSearch);
            if(objectToAdd!=null && !allObjects.contains(objectToAdd)) //without duplicates))))
                allObjects.add(objectToAdd);

            ArrayList<User> obj = dbLocal.getUserTypeList(toSearch);

            User user = dbLocal.getUserByFullName(toSearch); //fullname
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

            user = dbLocal.getUser(toSearch); //username
            if(user instanceof Referee)
                objectToAdd = (Referee)user;
            if (objectToAdd != null && !allObjects.contains(objectToAdd)) //without duplicates))))
                allObjects.add(objectToAdd);
        }

        else if(category.equals(Category.teams)) { //category search by Team:

            Team objectToAdd = dbLocal.getTeam(toSearch);
            if(objectToAdd!=null && !allObjects.contains(objectToAdd)) //without duplicates))))
                allObjects.add(objectToAdd);
        }
        return allObjects;
    }


    /**
     * this function searches for all kind of Objects in the DBLocal
     * @toSearch - the String we want to find
     * @return list of Objects found.
     */
    public List<Object> searchAll (){

        List<Object> allObjects = new LinkedList<>();
        Object objectToAdd;

        //using any kind of search on DBLocal in 3.. 2.... 1......

        objectToAdd = dbLocal.getUser(toSearch);
        if(objectToAdd!=null && !allObjects.contains(objectToAdd)) //without duplicates
            allObjects.add(objectToAdd);

        objectToAdd = dbLocal.getUserByFullName(toSearch);
        if(objectToAdd!=null && !allObjects.contains(objectToAdd)) //without duplicates
            allObjects.add(objectToAdd);

        objectToAdd = dbLocal.getTeam(toSearch);
        if(objectToAdd!=null && !allObjects.contains(objectToAdd)) //without duplicates)
            allObjects.add(objectToAdd);

        objectToAdd = dbLocal.getUserType(toSearch);
        if(objectToAdd!=null && !allObjects.contains(objectToAdd)) //without duplicates)
            allObjects.add(objectToAdd);

        objectToAdd = dbLocal.getLeague(toSearch);
        if(objectToAdd!=null && !allObjects.contains(objectToAdd)) //without duplicates)
            allObjects.add(objectToAdd);

        ArrayList<User> obj = dbLocal.getUserTypeList(toSearch);
        if(obj!=null && obj.size()>0){
            for(User user : obj) { //adds all the users list
                if(!allObjects.contains(user)) //without duplicates)
                    allObjects.add(user);
            }
        }

        return allObjects;
    }
}
