package DomainLayer.Users;
import DomainLayer.SystemLogic.*;
import DomainLayer.Teams.Assent;
import DomainLayer.Teams.Team;
import DomainLayer.UserGenerator.PremiumUserGenerator;
import DomainLayer.UserGenerator.SimpleUserGenerator;

import java.time.LocalDate;
import java.util.HashMap;

public class TeamOwner extends User implements Assent {
    private Team team = null;
    private double worth;
    private boolean permission =false;
    private boolean afford = true;
    private HashMap<String, TeamOwner> team_owners_appointments = new HashMap<>();
    private HashMap<String, Manager> managers_appointments = new HashMap<>();
    //private HashMap<String, Boolean> authorizations = new HashMap<>();

    /**
     * Constructor
     * @param userName
     * @param password
     * @param fullName
     * @param userEmail
     */
    public TeamOwner(String userName, String password, String fullName,String userEmail) {
        this.userName = userName;
        this.password = password;
        this.userFullName = fullName;
        this.userEmail = userEmail;
    }

    /**
     * This method asks permission from the association representative to open a new team.
     * The answer is assigned to the field permission.
     */
    public void askPermissionToOpenTeam (){
        DBLocal dbLocal = DBLocal.getInstance();
        AssociationRepresentative associationRepresentative = (AssociationRepresentative) dbLocal.getUserType("AssociationRepresentative");//todo: check tali
        this.permission = associationRepresentative.approveRegistration("who", "cares");
    }

    /**
     * This method opens a new team or reopen it after it was closed
     * @param team_name
     * @param initialBudget
     */
    public boolean openTeam(String team_name, double initialBudget){//after he got permission
        if(permission) {
            if(team==null) {
                HashMap<String, TeamOwner> me = new HashMap<>();
                me.put(super.getUserName(), this);
                team = new Team(team_name, me);
                team.setBudget(initialBudget);
                DBLocal.getInstance().addTeam(team);
            }
            else{
                team.setStatus(Team.teamStatus.active);

            }
            return true;
        }
        return false;
    }

    /**
     * This method closing the team by changing its status to "close".
     * @return
     */
    public String closeTeam(){
        if(team.getStatus().equals(Team.teamStatus.close)){
            return "team is closed";
        }
        team.setStatus(Team.teamStatus.close);
        MainSystem.LOG.info("The team " + team.getName() + " is closed");
        return "close";
    }

    /**
     * Thia methods adds new assent (new users or new stadium) to the team.
     * Send to the appointing method, if it is necessary.
     * @param assent
     * @param new_worth
     * @return
     */
    public String addAssent(Assent assent, double new_worth){
        if(assent == null || team==null){
            return "null";
        }

        if(team.getStatus().equals(Team.teamStatus.close)){
            return "team is closed";
        }

        if(this.team.containsAssent(assent)){
            return "already added";
        }
        if(!afford){
            return "money isn't growing on the trees!";
        }

        if (assent.getWorth()==0) {         //means: first time the assent is added to team
            assent.setWorth(new_worth);
        }
        this.team.addAssent(assent);
        outcome(assent.getWorth());

        return "added successfully";
    }

    /**
     * This method removes assent (new users or new stadium) to the team.
     * @param assent
     * @return
     */
    public String removeAssent(Assent assent){
        if(assent == null || team ==null){
            return "null";
        }
        if(team.getStatus().equals(Team.teamStatus.close)){
            return "team is closed";
        }
        if(!this.team.containsAssent(assent)){
            return "not in the team";
        }
        else{
            this.team.removeAssent(assent);
            income(assent.getWorth());
        }
        return "removed successfully";
    }

    /**
     * This method changes the assent's worth
     * @param assent
     * @param new_worth
     * @return
     */
    public String changeAssentWorth(Assent assent, double new_worth){
        if(assent == null || team==null){
            return "null";
        }
        if(team.getStatus().equals(Team.teamStatus.close)){
            return "team is closed";
        }
        if(!this.team.containsAssent(assent)){
            return "not in the team";
        }
        else{
            assent.setWorth(new_worth);
        }
        return "changed successfully";
    }

    /**
     * This method appoints user to owner/manager of the team
     * @param user
     * @param role
     * @param worth
     * @return
     */
    public String appoint(User user, String role, double worth){
        if(user == null  || !(role.equals("manager") | role.equals("teamowner"))){
            return "null";
        }
        if((user instanceof Manager && role.equals("manager") )|| user instanceof TeamOwner){
            return "already has team";
        }
        if(team.getStatus().equals(Team.teamStatus.close)){
            return "team is closed";
        }
        if(!afford){
            return "money isn't growing on the trees!";
        }

        PremiumUserGenerator premiumUserGenerator = new PremiumUserGenerator();
        DBLocal dbLocal = DBLocal.getInstance();
        dbLocal.removeUser(user.getUserName());
        User new_user =  premiumUserGenerator.generate(user.getUserName(),user.getPassword(),""
                ,role, user.getUserFullName(), user.getUserEmail(), null,"","","");
        if(role.equals("teamowner")){
            TeamOwner teamOwner = (TeamOwner)new_user;
            teamOwner.setWorth(300000);
            dbLocal.addUser(teamOwner);
            team.addAssent(teamOwner);
           teamOwner.setTeam(team);
            team_owners_appointments.put(new_user.getUserName(), (TeamOwner) new_user);
        }
        if(role.equals("manager")){
            Manager manager = (Manager)new_user;
            manager.setWorth(200000);
            dbLocal.addUser(manager);
            team.addAssent(manager);
           manager.setTeam(team);
            managers_appointments.put(new_user.getUserName(), (Manager) new_user);
        }

        String message ="You have been appointed to " + role +  " of the team " + this.team.getName();
        Notification notification = new Notification(this, message, new_user);
        notification.send();
        MainSystem.LOG.info(this.userName + " appointed " + new_user.getUserName() + " to " + this.team.getName() + "'s " + role);
        return "appointed";
    }

    /**
     * This method removes user of being owner of the team and changes him to fan
     * @param teamOwner
     * @return
     */
    public String removeAppointmentTeamOwner(TeamOwner teamOwner){
        if(teamOwner == null){
            return "null";
        }
        if(team.getStatus().equals(Team.teamStatus.close)){
            return "team is closed";
        }
        if(!team_owners_appointments.containsValue(teamOwner)){
            return "not appointed by me!";
        }
        for (String owner: teamOwner.getTeam_owners_appointments().keySet()){
            teamOwner.removeAppointmentTeamOwner(teamOwner.getTeam_owners_appointments().get(owner));
        }
        for (String manager: teamOwner.getManagers_appointments().keySet()){
            teamOwner.removeAppointmentManager(teamOwner.getManagers_appointments().get(manager));
        }
        this.removeAssent(teamOwner);
        this.team_owners_appointments.remove(teamOwner.getUserName());
        SimpleUserGenerator simpleUserGenerator = new SimpleUserGenerator();
        DBLocal dbLocal = DBLocal.getInstance();
        dbLocal.removeUser(teamOwner.getUserName());
        Fan fan =  (Fan)simpleUserGenerator.generate(teamOwner.getUserName(),teamOwner.getPassword(),""
                ,"", teamOwner.getUserFullName(), teamOwner.getUserEmail(), null,"","","");
        dbLocal.addUser(fan);
        String message ="You have been removed of being team  owner of the team " + this.team.getName();
        Notification notification = new Notification(this, message, fan);
        notification.send();
        MainSystem.LOG.info(fan.getUserName()+ "'s appointment was removed");
        return null;
    }

    /**
     * This method removes user of being manager of the team and changes him to fan
     * @param manager
     * @return
     */
    public String removeAppointmentManager(Manager manager){
        if(manager == null){
            return "null";
        }
        if(team.getStatus().equals(Team.teamStatus.close)){
            return "team is closed";
        }
        if(!managers_appointments.containsValue(manager)){
            return "not appointed by me!";
        }
        this.removeAssent(manager);
        this.managers_appointments.remove(manager.getUserName());
        SimpleUserGenerator simpleUserGenerator = new SimpleUserGenerator();
        DBLocal dbLocal = DBLocal.getInstance();
        dbLocal.removeUser(manager.getUserName());
        Fan fan =  (Fan)simpleUserGenerator.generate(manager.getUserName(),manager.getPassword(),""
                ,"", manager.getUserFullName(), manager.getUserEmail(), null,"","","");
        dbLocal.addUser(fan);
        String message ="You have been removed of being manager of the team " + this.team.getName();
        Notification notification = new Notification(this, message, fan);
        notification.send();
        MainSystem.LOG.info(fan.getUserName()+ "'s appointment was removed");
        return null;
    }

    /**
     * This updates the team's budget and change the boolean variant "afford" if the budget is to low.
     * @param amount
     */
    private void outcome(double amount){
        double oldBudget = this.team.getBudget();
        double newBudget = oldBudget - amount;
        this.team.setBudget(newBudget);
        MainSystem.LOG.info("The team budget decreased from " + oldBudget + " to " + newBudget);
        if(newBudget<=10000){
            afford = false;
        }
    }

    /**
     * This updates the team's budget
     * @param amount
     */
    private void income(double amount){
        double oldBudget = this.team.getBudget();
        double newBudget = oldBudget + amount;
        this.team.setBudget(newBudget);
        MainSystem.LOG.info("The team budget increased from " + oldBudget + " to " + newBudget);
    }

    /**
     * This method send to the private methods "outcome" and "income" due to the amount's type
     * If a user in the team should be inform about this action, he gets a notification.
     * @param amount
     * @param type
     * @param toUser
     * @param user
     */
    public void reportFinance(double amount, String type, boolean toUser, User user) {
        if (type.equals("income")) {
            income(amount);
        } else {
            outcome(amount);
            if (toUser){
                String message = "You have been paid "+ amount + " gogo'im";
                Notification notification = new Notification(this, message, user);
                notification.send();
            }
            payTax(amount);
        }

    }


    public void payTax(double amount){
        AccountSystemProxy accountSystemProxy = new AccountSystemProxy();
        double tax = amount*getTaxRate(amount);
        accountSystemProxy.addPayment(this.team.getName(), LocalDate.now().toString(),tax);
        outcome(tax);
    }

    public double getTaxRate(double revenueAmount){
        TaxSystem taxSystem = new TaxSystem();
        return taxSystem.getTaxRate(revenueAmount);
    }
    public String checkIfOwnerHasTeam(){
        if(this.team==null){
            return "null";
        }
        else{
            return "Owner already has a team";
        }
    }



    /** ----------------- GETTERS AND SETTERS ----------------- **/

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }


    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public boolean isAfford() {
        return afford;
    }

    public void setAfford(boolean afford) {
        this.afford = afford;
    }

    public HashMap<String, TeamOwner> getTeam_owners_appointments() {
        return team_owners_appointments;
    }

    public void setTeam_owners_appointments(HashMap<String, TeamOwner> team_owners_appointments) {
        this.team_owners_appointments = team_owners_appointments;
    }

    public HashMap<String, Manager> getManagers_appointments() {
        return managers_appointments;
    }

    public void setManagers_appointments(HashMap<String, Manager> managers_appointments) {
        this.managers_appointments = managers_appointments;
    }

    public boolean setCurrentTeam(String team){
        Team t = DBLocal.getInstance().getTeam(team);
        if(t==null){
            this.team=t;
            return false;
        }
        this.team=t;

        DBLocal.getInstance().setUser(this);
        MainSystem.LOG.info("The team owner " +getUserFullName()+ " has move to the team "+team);
        return true;

    }
}
