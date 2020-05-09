package Users;

import SystemLogic.DB;
import Teams.Team;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PlayerPersonalPage extends PersonalPage {

    private int height;
    private int weight;
    private int shirtNumber;
    private String position;

    public PlayerPersonalPage(String name, int age, String position,int height,int weight,int shirtNumber, String team){
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
    public Pair<String, ArrayList<String>> getAllDetails(){
        String details =
                super.name + "," +
                "Age: " + super.age + "," +
                "Current Team: " +super.currentTeam.getName() +  "," +
                "Height:" + height + "," +
                "weight: " + weight + "," +
                "Position: " + position + "," +
                "Shirt Number: " + shirtNumber;
        return new Pair<>(details ,teamHistory);
    }

    public int getHeight() {
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
