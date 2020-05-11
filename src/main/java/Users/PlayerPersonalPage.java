package Users;

import SystemLogic.DB;
import Teams.Team;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PlayerPersonalPage extends PersonalPage {

    private double height;
    private int weight;
    private int shirtNumber;
    private String position;

    public PlayerPersonalPage(String name, int age, String position,double height,int weight,int shirtNumber, String team){
        this.name=name;
        this.age=age;
        this.position=position;
        this.height = height;
        this.weight = weight;
        this.shirtNumber=shirtNumber;
        teamHistory= new ArrayList<>();

        DB db = DB.getInstance();
        this.currentTeam = db.getTeam(team);
//        this.teamHistory.add(team.getName());
    }

    public String getAllDetails(){
        return super.name + "," +
        "Age: " + super.age + "," +
        "Current Team: " +super.currentTeam.getName() +  "," +
        "Height:" + height + "," +
        "weight: " + weight + "," +
        "Position: " + position + "," +
        "Shirt Number: " + shirtNumber;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;

    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;

    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;

    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;

    }


}
