package ServiceLayer;

import DomainLayer.Games.Game;
import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.SystemLogic.MainSystem;
import DomainLayer.SystemLogic.Notification;
import DomainLayer.Teams.Stadium;
import DomainLayer.Teams.Team;
import DomainLayer.UserGenerator.IUserGenerator;
import DomainLayer.UserGenerator.ManagementUserGenerator;
import DomainLayer.UserGenerator.PremiumUserGenerator;
import DomainLayer.UserGenerator.SimpleUserGenerator;
import DomainLayer.Users.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;



/**
 * This class represents a bridge for handling the user creation and functionality between the domain layer and the GUI layer
 */

public class UserManagement {

    User currentUser;

    /** ---------------- USER MANAGEMENT FUNCTIONALITY ----------------**/

    public String createNewUser(String userName, String password, String mangerPassword, String role, String fullName, String userEmail,
                                LocalDate birthDate, String qualification, String courtRole, String teamRole,
                                String type){
        IUserGenerator iUserGenerator;
        switch (type){
            case "SimpleUserGenerator":
                iUserGenerator = new SimpleUserGenerator();
                break;
            case "PremiumUserGenerator":
                iUserGenerator = new PremiumUserGenerator();
                break;
            case "ManagementUserGenerator":
                iUserGenerator = new ManagementUserGenerator();
                break;
            default:
                iUserGenerator = new SimpleUserGenerator();
        }
        String res = MainSystem.getInstance().singUp(userName,password,mangerPassword,role,fullName,userEmail,birthDate,qualification,courtRole,teamRole,iUserGenerator);
        currentUser = MainSystem.getInstance().getCurrentUser();
        return res;
    }

    public String getUserType(String userName){
        return MainSystem.getInstance().getUserType(userName);
    }


    public String logIn(String userName, String password){
        String res = MainSystem.getInstance().logIn(userName,password);
        currentUser = MainSystem.getInstance().getCurrentUser();
        return res;
    }

    public boolean logOut(){
        currentUser = null;

        try {
            DBLocal.getInstance().writeToMongo();
        }catch (Exception e){
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("Logs\\SystemError.log"));
                LocalDateTime date = LocalDateTime.now();
                String error = date +" ERROR - DB connection is lost";

                writer.write(error);
                writer.close();
            }

            catch (Exception ex){}



        }


        return MainSystem.getInstance().logOut();
    }

    public String displaySpecialPassword(){
        String pass = MainSystem.getInstance().getSpecialPassword();
        return pass;
    }

    public String getUserName(){
        return currentUser.getUserName();
    }

    public void setUserFullName(String userFullName){
        currentUser.setUserFullName(userFullName);
    }

    public String getUserFullName() {
        return currentUser.getUserFullName();
    }
    public ArrayList<String> getReceivedNotifications() {
        return currentUser.getReceivedNotificationsAsString();
    }

    public String getUserEmail(){
        return currentUser.getUserEmail();
    }

    public void setUserEmail(String userEmail){
        currentUser.setUserEmail(userEmail);
    }

    public ArrayList<String> watchDetails(){
        return currentUser.watchDetails();
    }

    public void sendNotification(String message, String receiver) {
        Notification notification = new Notification(currentUser,message,currentUser);
        notification.send();
    }


    /** ---------------- FAN MANAGEMENT FUNCTIONALITY ---------------- **/

    public void followPage(String pageName){
        ((Fan) currentUser).followThisPage(pageName);
    }

    public void stopFollowThisPage(String pageName){
        ((Fan) currentUser).stopFollowThisPage(pageName);
    }

    public void stopFollowAllPages(){
        ((Fan) currentUser).stopFollowAllPages();
    }

    public void followTeam(String teamName){
        ((Fan) currentUser).followTeam(teamName);
    }

    public void stopFollowTeam(String teamName){
        ((Fan) currentUser).stopFollowTeam(teamName);
    }

    public void stopFollowAllTeams(){
        ((Fan) currentUser).stopFollowAllTeams();
    }

    /** ---------------- COACH MANAGEMENT FUNCTIONALITY ---------------- **/

    public HashMap<String, String> getAllCoaches() {
        return DBLocal.getInstance().getAllUserNameByType("Coach");
    }

    public String getCoachPageDetails(String user_name) {
        Coach coach = (Coach) DBLocal.getInstance().getUser(user_name);
        CoachPersonalPage coachPersonalPage = coach.getPage();
        if(coachPersonalPage==null){
            return null;
        }

        return coachPersonalPage.getAllDetails();
    }

    public void createCoachPersonalPage(LocalDate birthDate, String team){
        currentUser = MainSystem.getInstance().getCurrentUser();
        ((Coach) currentUser).createCoachPersonalPage(birthDate,team);
    }

    public String getCoachPageAsString(){
        CoachPersonalPage coachPersonalPage =  ((Coach) currentUser).getPage();
        //NEED TO TURN IT TO STRING
        return "";
    }

    public String getCoachTeamRole(){
        return ((Coach) currentUser).getTeamRole();
    }

    public void setCoachTeamRole(String teamRole) {
        ((Coach) currentUser).setTeamRole(teamRole);
    }

    public int getCoachSalay(){
        return ((Coach) currentUser).getSalary();
    }

    public Team getCoachCurrentTeam(){
        return ((Coach) currentUser).getCurrentTeam();    }

    public void setCoachCurrentTeam(String team){
        ((Coach) currentUser).setCurrentTeam(team);
    }

    public int getCoachAge(){
        CoachPersonalPage coachPersonalPage =  ((Coach) currentUser).getPage();
        return coachPersonalPage.getAge();
    }

    public void setCoachAge(int age){
        CoachPersonalPage coachPersonalPage =  ((Coach) currentUser).getPage();
        coachPersonalPage.setAge(age);
    }

    /** ---------------- PLAYER MANAGEMENT FUNCTIONALITY ---------------- **/

    public ArrayList<String> getPageHistory(String user_name) {
        User user =  DBLocal.getInstance().getUser(user_name);
        PersonalPage personalPage;
        if(user instanceof  Player){
            personalPage = ((Player)user).getPage();
        }
        else{//coach
            personalPage = ((Coach)user).getPage();
        }
        if (personalPage==null){
            return null;
        }
        return personalPage.getTeamHistory();
    }

    public HashMap<String, String> getAllPlayers(){
        return DBLocal.getInstance().getAllUserNameByType("Player");
    }

    public String getPlayerPageDetails(String user_name){
        Player player = (Player) DBLocal.getInstance().getUser(user_name);
        PlayerPersonalPage playerPersonalPage = player.getPage();
        if(playerPersonalPage==null){
            return null;
        }

        return playerPersonalPage.getAllDetails();
    }

    public void createPlayerPersonalPage(double height,int weight, int shirtNum,String team){
        currentUser = MainSystem.getInstance().getCurrentUser();
        ((Player) currentUser).createPersonalPage(height, weight, shirtNum, team);
    }

    public String getPlayerTeam() {
        currentUser = MainSystem.getInstance().getCurrentUser();
        Team team = ((Player) currentUser).getCurrentTeam();
        if(team!=null){
            return team.getName();
        }
        return currentUser.getUserFullName() + " is a free player";
    }

    public String getPlayerPageAsString(){
        PlayerPersonalPage playerPersonalPage = ((Player) currentUser).getPage();
        //NEED TO TURN IT STRING
        return "";
    }

    public int getPlayerAge(){
         return  ((Player) currentUser).getAge();
    }

    public String getCourtRole() {
        return  ((Player) currentUser).getCourtRole();
    }

    public void setCourtRole(String courtRole) {
        ((Player) currentUser).setCourtRole(courtRole);
    }
    public int getNumberOfShirt(){
        return ((Player) currentUser).getNumberOfShirt();
    }

    public void setNumberOfShirt(int number){
        ((Player) currentUser).setNumberOfShirt(number);
    }

    public void setHeight(int height){
        ((Player) currentUser).setHeight(height);
    }

    public double getHeight(){
        return  ((Player) currentUser).getHeight();
    }

    public int getWeight(){
        return ((Player) currentUser).getWeight();
    }

    public void setWeight(int weight){
        ((Player) currentUser).setWeight(weight);
    }

    public void setPlayerCurrentTeam(String team){
        ((Player) currentUser).setCurrentTeam(team);
    }
    public Team getCurrentTeam(){
        return  ((Player) currentUser).getCurrentTeam();
    }

    public int getSalary() {
        return  ((Player) currentUser).getSalary();
    }

    /** ---------------- TEAM OWNER MANAGEMENT FUNCTIONALITY ---------------- **/

    public String addAssent(String assentType, String assentName) {
        DBLocal db = DBLocal.getInstance();
        if(assentType.equals("Player")){
            Player player = (Player) db.getUserByFullName(assentName);
            return ((TeamOwner) currentUser).addAssent(player, 0);
        }
        if(assentType.equals("Coach")){
            Coach coach = (Coach) db.getUserByFullName(assentName);
            return ((TeamOwner) currentUser).addAssent(coach, 0);
        }

        if(assentType.equals("Stadium")){
            Stadium stadium = new Stadium( 10000,50000, 50 );
            stadium.setName(assentName);
            return ((TeamOwner) currentUser).addAssent(stadium, 0);
        }
        return null;
    }

    public String removeAssent(String assentType, String assentName) {
        DBLocal db = DBLocal.getInstance();
        if(assentType.equals("Player")){
            Player player = (Player) db.getUserByFullName(assentName);
            return ((TeamOwner) currentUser).removeAssent(player);
        }
        if(assentType.equals("Coach")){
            Coach coach = (Coach) db.getUserByFullName(assentName);
            return ((TeamOwner) currentUser).removeAssent(coach);
        }

        return null;
    }

    /** ---------------- REFEREE MANAGEMENT FUNCTIONALITY ---------------- **/

    public String getRefereeQualifications(){
        return  ((Referee) currentUser).getQualification();
    }

    public void setRefereeQualifications(String qualifications ){
        ((Referee) currentUser).setQualification(qualifications);
    }

    public String getMainRefereeQualifications(){
        return  ((MainReferee) currentUser).getQualification();
    }

    public void setMainRefereeQualifications(String qualifications ){
        ((MainReferee) currentUser).setQualification(qualifications);
    }

    public String checkFinishedGames(String userName, String userType){
        MainReferee user=(MainReferee) DBLocal.getInstance().getUser(userName);
        return user.displayGameEvents();

    }

    public ArrayList<String> watchGamesList(){
        currentUser = MainSystem.getInstance().getCurrentUser();
        ArrayList<Game> games = new ArrayList<>();

        if (currentUser instanceof MainReferee)
            games = ((MainReferee) currentUser).watchGamesList();

        else if (currentUser instanceof Referee)
            games = ((Referee) currentUser).watchGamesList();

        else if (currentUser instanceof AssociationRepresentative)
            games = ((AssociationRepresentative) currentUser).watchGamesList();

        return DBLocal.getInstance().getStringGames(games);
    }

    public boolean setReport(String userName, String report){
        try {
            MainReferee user=(MainReferee) DBLocal.getInstance().getUser(userName);
            user.editGameEvents(report);
            return true;
        }catch (Exception e){
            return false;
        }


    }


    public void readNotification(String message) {
        Notification notification = new Notification(currentUser,message,currentUser);
        notification.read();
    }

    /** ---------------- Administrator FUNCTIONALITY ---------------- **/

    public String watchLog(){

        return ((Administrator) currentUser).watchLog();
    }

    public String watchErrorLog(){

        return ((Administrator) currentUser).watchErrorLog();
    }




}
