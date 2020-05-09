package Users;

import SystemLogic.MainSystem;
import SystemLogic.Notification;
import SystemLogic.Search;
import Teams.Team;

import java.util.*;

public abstract class User {

    protected String userName;
    protected String password;

    protected String userEmail;
    protected String userFullName;

    protected ArrayList<Notification> receivedNotifications = new ArrayList<>();
    protected ArrayList<Notification> readNotifications = new ArrayList<>();
    protected ArrayList<String> searchHistory = new ArrayList<>();

    protected boolean notReadNotifications = false;

    public List<Object> search (String searchLine, String category){
        Search.Category categoryToSend = null;

        if(category!=null) {
            switch (category) {
                case "teams":
                    categoryToSend = Search.Category.teams;
                    break;

                case "players":
                    categoryToSend = Search.Category.players;
                    break;

                case "referees":
                    categoryToSend = Search.Category.referees;
                    break;

                case "leagues":
                    categoryToSend = Search.Category.leagues;
                    break;

                default:
                    categoryToSend = null;
            }
        }
        Search search = new Search(searchLine,categoryToSend);
        searchHistory.add(searchLine);
        return search.search();
    }

    /**
     * This method is for any user that want to send complain for the team
     * The complaint is sent to the team's manager as a notification
     * @param complaint
     * @param team
     * @return
     */
    public boolean complain(String complaint, Team team){
        if(team==null){
            return false;
        }
        Iterator it = team.getManagers().entrySet().iterator();
        if(it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            new Notification(this, complaint, (Manager) (pair.getValue())).send();
        }
        else{
            return false;
        }
        return true;
    }

    public String[] watchDetails(){
        //MainSystem.LOG.info(getUserFullName()+" watch his details");
        String[] details = new String[4];
        details[0]=userFullName;
        details[1]=userName;
        details[2]= password;
        details[3]=userEmail;
        return details;
    }

    /** ---------------- GETTERS AND SETTERS ---------------- **/

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public ArrayList<Notification> getReceivedNotifications() {
        return receivedNotifications;
    }

    public void setReceivedNotifications(ArrayList<Notification> receivedNotifications) {
        this.receivedNotifications = receivedNotifications;
    }

    public ArrayList<Notification> getReadNotifications() {
        return readNotifications;
    }

     public void setReadNotifications(ArrayList<Notification> readNotifications) {
        this.readNotifications = readNotifications;
    }

    public boolean isNonReadNotifications() {
        return notReadNotifications;
    }

    public void setNotReadNotifications(boolean notReadNotifications) {
        this.notReadNotifications = notReadNotifications;
    }


}
