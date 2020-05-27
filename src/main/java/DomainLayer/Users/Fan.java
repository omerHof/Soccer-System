package DomainLayer.Users;

import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.SystemLogic.MainSystem;
import DomainLayer.SystemLogic.Notification;
import DomainLayer.Teams.Stadium;
import DomainLayer.Teams.Team;
import DomainLayer.Teams.TeamPage;

import java.util.*;

public class Fan extends User implements Observer {



    //private HashMap<String,PersonalPage> followedPages;
    private ArrayList<PersonalPage>followedPages;
    private HashMap<String, Team> followedTeams;
    private String pageMessage;
    private String teamMessage;

    private ArrayList<String> followedPagesString;

    public Fan(String userName, String password, String fullName, String email) {

        this.password = password;
        this.userName = userName;
        this.userFullName = fullName;
        this.userEmail = email;
        followedPages = new ArrayList<>();
        followedTeams = new HashMap<>();
        followedPagesString = new ArrayList<>();

    }

    public ArrayList<String> getFollowedPagesString() {
        return followedPagesString;
    }

    public void setFollowedPagesString(ArrayList<String> followedPagesString) {
        this.followedPagesString = followedPagesString;
    }

    public String getPageMessage() {
        return pageMessage;
    }

    public void setPageMessage(String pageMessage) {
        this.pageMessage = pageMessage;
    }

    @Override
    public void update(Observable o, Object arg) {
        DBLocal db = DBLocal.getInstance();
        MainSystem.LOG.info("The user "+userName+ " get update");
        DBLocal dbLocal = DBLocal.getInstance();
        MainSystem.LOG.info("The user get update");
        if (o instanceof PersonalPage) {
            Team team = (Team) arg;
            String name = (String) ((PersonalPage) o).getName();
            User sender = dbLocal.getUserByFullName(name);
            pageMessage = team.getName();
            String message = "new update! " + name + " has moved to " + pageMessage;
            Notification notification = new Notification(sender,message,this);
            notification.send();
        }
        if (o instanceof TeamPage) {
            TeamPage team = (TeamPage) o;
            Team t = dbLocal.getTeam(team.getName());
            String teamName = team.getName();
            User managerSender;
            String message="";
            if(t.getManagers().size()!=0) {
                managerSender = team.getManagers().entrySet().stream().findFirst().get().getValue();
            }
            else{
                managerSender = t.getTeamOwners().entrySet().stream().findFirst().get().getValue();
            }

            if (arg instanceof Player) {
                Player player = (Player) arg;
                pageMessage = player.getUserFullName();
                if(player.getCurrentTeam()==null||t.getName()!=teamName){
                    message = "new update! the player " + pageMessage + " has left the team " + team.getName();

                }
                else if(t.getName()==teamName) {
                     message = "new update! the player " + pageMessage + " has moved to " + team.getName();
                }

                Notification notification = new Notification(managerSender,message,this);
                notification.send();

            } else if (arg instanceof Coach) {
                Coach coach = (Coach) arg;
                pageMessage = coach.getUserFullName();
                if(coach.getCurrentTeam()==null||t.getName()!=teamName){
                    message = "new update! the coach " + pageMessage + " has left the team " + team.getName();

                }
                else if(t.getName()==teamName) {
                    message = "new update! the coach " + pageMessage + " has moved to " + team.getName();
                }
                Notification notification = new Notification(managerSender,message,this);
                notification.send();
            }

            else if (arg instanceof Stadium) {
                Stadium stadium = (Stadium) arg;
                pageMessage= stadium.getName();
                 message = "new update! the team " + team.getName() + " has move to the stadium "+pageMessage;
                Notification notification = new Notification(managerSender,message,this);
                notification.send();

            } else if (arg instanceof TeamOwner) {
                TeamOwner TeamOwner = (DomainLayer.Users.TeamOwner) arg;
                pageMessage = TeamOwner.getUserFullName();
                 message = "new update! the team owner " + pageMessage + " has moved to " + team.getName();
                Notification notification = new Notification(managerSender,message,this);
                notification.send();


            } else if (arg instanceof Manager) {
                Manager manager = (Manager) arg;
                pageMessage = manager.getUserFullName();
                if(manager.getTeam()==null||manager.getTeam().getName()!=t.getName()){
                    message = "new update! the manager " + pageMessage + " has left  " + team.getName();
                    Notification notification = new Notification(managerSender,message,this);
                    notification.send();
                }
                else{
                    message = "new update! the manager " + pageMessage + " has moved to " + team.getName();
                    Notification notification = new Notification(managerSender,message,this);
                    notification.send();
                }


            } else if (arg instanceof String) {

                String updateFromGame = (String)arg;
                 message = updateFromGame;
                Notification notification = new Notification(managerSender,message,this);
                notification.send();

            }
        }

    }


/*
    public HashMap<String, PersonalPage> getFollowedPages() {
        return followedPages;
    }


 */
public ArrayList<PersonalPage> getFollowedPages() {
    return followedPages;
}
    //get notification
    /*
    public void getNotificationOnGames(){
        selectTeams();/// the fan selected the teams that he want to get notidications about them
        for(HashMap.Entry<String,Team> team:notificationTeams.entrySet()) {
            team.getValue().addObserver(this);///not sure
        }
        }
     */

    public void followThisPage(PersonalPage page){
        if(page!=null) {
            page.addObserver(this);
            //followedPages.put(page.getName(),page);
            followedPages.add(page);
            MainSystem.LOG.info(getUserFullName()+" follow the page of: "+page.getName());

        }

    }
    public void followThisPage(String pageName){

        DBLocal DBLocal1;
        DBLocal1 = DBLocal.getInstance();


        User user = DBLocal1.getUserByFullName(pageName);
        PersonalPage page;
        if(user instanceof Player){
           page= ((Player) user).getPage();

        }
        else{
            page= ((Coach) user).getPage();
        }
        if(page!=null) {
            followThisPage(page);
        }
    }

    public void stopFollowThisPage(String pageName) {

        for (PersonalPage page : followedPages) {
            if (page.getName().equals(pageName)) {

                followedPages.remove(page);
                page.deleteObserver(this);
                MainSystem.LOG.info(getUserFullName()+" stop follow the page of: "+pageName);
                break;
            }

        }

    }
    public void stopFollowAllPages(){
          while (!followedPages.isEmpty()){
              int i=0;
              PersonalPage p =followedPages.get(i);
              followedPages.remove(p);
              p.deleteObserver(this);
              MainSystem.LOG.info(getUserFullName()+" stop follow the page of: "+p.getName());
              i++;
          }
        MainSystem.LOG.info(getUserFullName()+" stop follow all pages");

    }

    public boolean followTeam(String teamName){
        DBLocal DBLocal1;
        DBLocal1 = DBLocal.getInstance();
        Team team = DBLocal1.getTeam(teamName);
        if(team==null){
            return false;
        }
        TeamPage teamPage = team.getPage();
        if(teamPage==null){
            return false;
        }
        teamPage.addObserver(this);
        followedTeams.put(teamName,team);
        MainSystem.LOG.info(getUserFullName()+" follow the team : "+teamName);
        return true;
    }

    public void stopFollowTeam(String teamName){ ///to fix bug
        Team team;
        if (followedTeams.containsKey(teamName)) {
            team = followedTeams.remove(teamName);
            TeamPage teamPage = team.getPage();
            teamPage.deleteObserver(this);
            MainSystem.LOG.info(getUserFullName()+" stop follow the team : "+teamName);
        }
    }

    public void stopFollowAllTeams(){
        Iterator it = followedTeams.entrySet().iterator();
        Team team;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String key =(String) pair.getKey();
            Team value = (Team)pair.getValue();
            it.remove();
            TeamPage teamPage = value.getPage();
            teamPage.deleteObserver(this);
            MainSystem.LOG.info(getUserFullName()+" stop follow the team : "+key);

        }

        MainSystem.LOG.info(getUserFullName()+" stop follow all of the teams");

    }


    public HashMap<String, Team> getFollowedTeams() {
        return followedTeams;
    }

    public String getTeamMessage() {
        return teamMessage;
    }

    public void setTeamMessage(String teamMessage) {
        this.teamMessage = teamMessage;
    }
}
