package DomainLayer.Users;

import DomainLayer.SystemLogic.DBLocal;

import java.util.ArrayList;

public class PlayerPersonalPage extends PersonalPage {

    private double height;
    private int weight;
    private int shirtNumber;
    private String position;

    public PlayerPersonalPage(String name, int age, String position,double height,int weight,int shirtNumber, String team){
        super.name=name;
        this.age=age;
        this.position=position;
        this.height = height;
        this.weight = weight;
        this.shirtNumber=shirtNumber;
        teamHistory= new ArrayList<>();

        DBLocal dbLocal = DBLocal.getInstance();
        this.currentTeam = dbLocal.getTeam(team);
//        this.teamHistory.add(team.getName());
    }

    public String getAllDetails(){
        String result =
         super.name + "," +
        "Age: " + super.age + "," +
        "Current Team: " +super.currentTeam.getName() +  "," +
        "Height:" + height + "," +
        "weight: " + weight + "," +
        "Position: " + position + "," +
        "Shirt Number: " + shirtNumber;
        if (super.currentTeam!=null){
            result = result + "," + "Current Team: " + super.currentTeam.getName();
        }
        return result;
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
