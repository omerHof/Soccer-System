package DomainLayer.Users;

import DomainLayer.SystemLogic.DBLocal;
import DomainLayer.SystemLogic.MainSystem;
import DomainLayer.Teams.Assent;
import DomainLayer.Teams.Team;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Player extends User implements Assent {

    private int age;
    private String courtRole;
    private PlayerPersonalPage page;
    private int salary;
    private DBLocal DBLocal1;
    private double worth;
    private Team currentTeam;


    public Player(String userName, String password, String fullName, String userEmail, LocalDate birthDate, String courtRole) {
        this.userName = userName;
        this.password = password;
        this.userFullName = fullName;
        this.userEmail = userEmail;
        this.courtRole = courtRole;
        age = getCurrentAge(birthDate);
        page =null;
        salary = 0;
        DBLocal1 = DBLocal.getInstance();
        currentTeam = null;
    }


    public PlayerPersonalPage createPersonalPage(double height,int weight, int shirtNum, String team) {
        MainSystem.LOG.info("The player " +getUserFullName()+ " create personal page");
        Team currTeam = DBLocal1.getTeam(team);
        if(currTeam==null){
        page = new PlayerPersonalPage(this.userFullName, age, courtRole, height, weight, shirtNum, null);
        }
        else {
            if(currTeam.getPlayers().containsKey(userFullName)){
            page = new PlayerPersonalPage(this.userFullName, age, courtRole, height, weight, shirtNum, team);
            page.setCurrentTeam(currTeam);
            }
            else{
                page = new PlayerPersonalPage(this.userFullName, age, courtRole, height, weight, shirtNum, null);
            }
        }
        return page;
    }


    public int getAge() {
        return age;
    }
/*
    public void setAge(int age) {
        this.age = age;

    }

 */


    public void setCourtRole(String courtRole) {
        this.courtRole = courtRole;
        if(page!=null){
            page.setPosition(courtRole);
        }
        DBLocal1.setUser(this);

        //page.setPosition(courtRole);

    }
    public String getCourtRole() {
        return courtRole;
    }


    ///changes for personal page

    public void setNumberOfShirt(int number){
        if(page!=null) {
            page.setShirtNumber(number);
            DBLocal1.setUser(this);
        }

    }
    public void setHeight(int height){
        if(page!=null) {
            page.setHeight(height);
            DBLocal1.setUser(this);
        }
    }

    public void setWeight(int weight){
        if(page!=null) {
            page.setWeight(weight);
            DBLocal1.setUser(this);
        }
    }
    public boolean setCurrentTeam(String team){
       Team t =  DBLocal1.getTeam(team);
       if(t==null){
           currentTeam=t;
           return false;
       }
        currentTeam=t;
        if(page!=null) {
            page.setCurrentTeam(t);
            page.setOneTeamToHistory(team);
        }
        DBLocal1.setUser(this);
        MainSystem.LOG.info("The player " +getUserFullName()+ " has move to the team "+team);
        return true;

    }

    public double getHeight(){
        if(page!=null){
            return page.getHeight();
        }
        return 0;
    }

    public int getWeight(){
        if(page!=null){
            return page.getWeight();
        }
        return 0;
    }
    public int getNumberOfShirt(){
        if(page!=null){
            return page.getShirtNumber();
        }
        return 0;

    }
    public String getCurrentTeamName(){
       return currentTeam.getName();
    }
    public Team getCurrentTeam(){
        return currentTeam;
    }


    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
        DBLocal1.setUser(this);
    }

    public PlayerPersonalPage getPage() {
        return page;
    }

    public void setTeamHistory(ArrayList<String> teamHistoryList){
        if(page!=null){
             page.setTeamHistory(teamHistoryList);
        }
    }

    @Override
    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }

    public ArrayList<String> getTeamHistory() {
        if(page!=null){
            return page.getTeamHistory();
        }
        return null;
    }




    public int getCurrentAge(LocalDate dateOfBirth) {
        LocalDate today = LocalDate.now();                          //Today's date
        Period p = Period.between(dateOfBirth, today);
        return p.getYears();
    }

}

