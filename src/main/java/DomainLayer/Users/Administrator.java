package DomainLayer.Users;

import DomainLayer.Games.Game;
import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.SystemLogic.MainSystem;
import DomainLayer.SystemLogic.Notification;
import DomainLayer.Teams.Team;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Administrator extends User {

    public Administrator(String userName, String password, String fullName, String userEmail) {
        this.userName = userName;
        this.password = password;
        this.userEmail = userEmail;
        this.userFullName = fullName;
    }


    public String closeTeamForPermanent(String name) {
        DBLocal dbLocal = DBLocal.getInstance();
        Team team = dbLocal.getTeam(name);
        boolean hasMoreGames = false;
        if (team != null) {
            ArrayList<Game> gameList = team.getGameList();
            if(gameList!=null) {
                for (Game g : gameList) {
                    if ((g.getStatus() == Game.gameStatus.active) || (g.getStatus() == Game.gameStatus.preGame)) {
                        hasMoreGames = true;

                    }
                }
            }
            if (hasMoreGames == false) {
                team.setStatus(Team.teamStatus.PermanentlyClosed);
                MainSystem.LOG.info("the team " +name+" is permanently closed by the administrator");

                HashMap<String, TeamOwner> owners = team.getTeamOwners();
                HashMap<String, Manager> managers = team.getManagers();


                //send notification to all owners
                Iterator it = owners.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    TeamOwner owner = (TeamOwner) pair.getValue();
                    Notification notification1 = new Notification(this, "the team "+name+" is permanently closed", owner);
                    notification1.send();
                    MainSystem.LOG.info("notification sent to the team owner "+owner.getUserFullName());

                    it.remove();
                }

                //send notification to all managers
                Iterator it2 = managers.entrySet().iterator();
                while (it2.hasNext()) {
                    Map.Entry pair = (Map.Entry) it2.next();
                    Manager manager = (Manager) pair.getValue();
                    Notification notification2 = new Notification(this, "the team "+name+" is permanently closed", manager);
                    notification2.send();
                    MainSystem.LOG.info("notification sent to the manager "+manager.getUserFullName());

                    it2.remove();
                }
                return "the team closed for permanent";
            } else {
               return "the team cant be closed for permanent because it has open games";
            }
        }
        return "";
    }


    public String deleteUserFromSystem(String name) {
        DBLocal dbLocal = DBLocal.getInstance();
        User user = dbLocal.getUserByFullName(name);
        if (user != null) {
            if (user instanceof AssociationRepresentative) {
                if (dbLocal.checkQuantityOfUsersByType("AssociationRepresentative") >= 2) {
                    AssociationRepresentative associationRep = (AssociationRepresentative) user;

                    if (associationRep.findActiveGame() != null) {//not sure ask tali
                        associationRep.passMyGames();//// not sure, ask tali
                    }
                    dbLocal.removeUser(user.getUserName());
                    MainSystem.LOG.info("the user of Association representative " +name+" is deleted by the administrator");
                    return "the user deleted succsesfully";
                }
                else{
                   return "no delete! the system has less then 2 Association representatives";
                }
            } else if (user instanceof Administrator) {
                if (dbLocal.checkQuantityOfUsersByType("Administrator") >= 2) {
                    dbLocal.removeUser(user.getUserName());
                    MainSystem.LOG.info("the user of administarator " +name+" is deleted by the administrator");
                    return "the user deleted succsesfully";
                }
                else{
                    return "no delete! the system has less then 2 administrator";
                }
            } else if (user instanceof Fan) {
                Fan fan = (Fan) user;
                fan.stopFollowAllTeams();
                fan.stopFollowAllPages();

                //2.	נדרש להסיר את המעקב שלו מכל המשחקים (לדעתי יקרה אוטומטי כאשר יוסר המעקב מקבוצה ?)
                ///do the delete
                dbLocal.removeUser(user.getUserName());
                MainSystem.LOG.info("the fan " +name+" is deleted by the administrator");
                return "the user deleted succsesfully";

            } else if (user instanceof TeamOwner) {
                TeamOwner owner = (TeamOwner) user;
                Team team = owner.getTeam();
                if (team.getTeamOwners().size() >= 2) {
                    for (String str: owner.getTeam_owners_appointments().keySet()){
                        owner.removeAppointmentTeamOwner(owner.getTeam_owners_appointments().get(str));
                    }
                    for (String manager: owner.getManagers_appointments().keySet()){
                        owner.removeAppointmentManager(owner.getManagers_appointments().get(manager));
                    }
                    team.removeAssent(owner);
                    dbLocal.removeUser(owner.getUserName());
                    MainSystem.LOG.info("the team owner " +name+" is deleted by the administrator");
                    return "the user deleted succsesfully";
                }
                else{
                   return "the team has less then 2 team owners";
                }

            }


            else if(user instanceof Referee) {
                Referee ref = (Referee) user;
                LinkedList<Game> games = ref.getMyGames();
                boolean isActive = false;
                for (Game g : games) {
                    if ((g.getStatus() == Game.gameStatus.active) || (g.getStatus() == Game.gameStatus.preGame)) {
                        isActive = true;
                        return "no delete, the referee has an open games";
                    }
                }
                    if(isActive==false){
                        dbLocal.removeUser(user.getUserName());
                        MainSystem.LOG.info("the referee " +name+" is deleted by the administrator");
                        return "the user deleted succsesfully";

                    }
            }


            else if (user instanceof Player) {
                Player player = (Player) user;
                PersonalPage page = player.getPage();

                Team team = player.getCurrentTeam();
                if (team != null) {
                    ArrayList<Game> gamesList = team.getGameList();
                    boolean isActive = false;
                    for (Game game : gamesList) {
                        if ((game.getStatus() == Game.gameStatus.active) || (game.getStatus() == Game.gameStatus.preGame)) {
                            isActive = true;
                            return "no delete, the player has an open games";
                        }
                    }
                    if (isActive == false) {
                        team.removePlayer(player);
                        dbLocal.removeUser(user.getUserName());
                        MainSystem.LOG.info("the player " + name + " is deleted by the administrator");
                        return "the user deleted succsesfully";
                    }

                }
            }


            else if (user instanceof Coach){
                Coach coach = (Coach) user;
                PersonalPage page = coach.getPage();

                    Team team = coach.getCurrentTeam();
                    if (team != null) {
                        ArrayList<Game> gamesList = team.getGameList();
                        boolean isActive = false;
                        for (Game game : gamesList) {
                            if ((game.getStatus() == Game.gameStatus.active) || (game.getStatus() == Game.gameStatus.preGame)) {
                                isActive = true;
                                return "no delete, the coach has an open games";
                            }
                        }
                        if (isActive == false) {
                            team.removeCoach(coach);
                            dbLocal.removeUser(user.getUserName());
                            MainSystem.LOG.info("the coach " +name+" is deleted by the administrator");
                            return "the user deleted succsesfully";
                        }
                    }

            }

            else if (user instanceof Manager){
                dbLocal.removeUser(user.getUserName());
                MainSystem.LOG.info("the manager " +name+" is deleted by the administrator");
                return "the user deleted succsesfully";

            }
        }
        return "the user deleted succsesfully";

    }


    public String watchLog(){
        String logString ="";


        try{
            FileInputStream fstream = new FileInputStream("logs/my.log");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            /* read log line by line */
            while ((strLine = br.readLine()) != null)   {
                /* parse strLine to obtain what you want */
                logString =logString +"\n"+ strLine;
            }
            fstream.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return logString;
    }

    public String watchErrorLog(){
        String logString ="";


        try{
            FileInputStream fstream = new FileInputStream("Logs/SystemError.log");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            /* read log line by line */
            while ((strLine = br.readLine()) != null)   {
                /* parse strLine to obtain what you want */
                logString =logString +"\n"+ strLine;
            }
            fstream.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return logString;
    }


}
