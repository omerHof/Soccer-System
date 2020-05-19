package UILayer.Controllers;

import ServiceLayer.UserManagement;
import UILayer.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class OpenPlayerPageController extends Controller {

    ObservableList<String> heights = FXCollections.observableArrayList();
    ObservableList<String> weights = FXCollections.observableArrayList();
    ObservableList<String> shirtNumbers = FXCollections.observableArrayList();
    @FXML
    private ChoiceBox choseHeight;
    @FXML
    private ChoiceBox choseWeight;
    @FXML
    private ChoiceBox choseShirtNumber;

    @FXML
    private Label playerTeamLabel;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage s = Main.getStage();
        s.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });


        for (int i = 0; i < 100; i++) {
            shirtNumbers.add("" + i);
        }

        double height;
        for (double i = 0; i < 60; i++) {
            height = 1.5 + (i / 100);
            DecimalFormat df = new DecimalFormat("#.##");
            heights.add(new DecimalFormat("#.##").format(height));
        }

        int weight = 55;
        for (int i = 0; i < 40; i++) {
            weight = 55 + i;
            weights.add("" + weight);
        }


        choseHeight.setItems(heights);
        choseWeight.setItems(weights);
        choseShirtNumber.setItems(shirtNumbers);
        String playerTeam = clientController.getPlayerTeam();
        if (playerTeam != null) {
            playerTeamLabel.setText(playerTeam);
        }
    }

    public void submitDetails() throws IOException {

        double height = Double.parseDouble(choseHeight.getValue().toString());
        int weight = Integer.parseInt(choseWeight.getValue().toString());
        int shirtNumber = Integer.parseInt(choseShirtNumber.getValue().toString());

        if(playerTeamLabel.getText()==null){
            clientController.createPlayerPersonalPage(height,weight,shirtNumber,"");

        }else{
            clientController.createPlayerPersonalPage(height,weight,shirtNumber,playerTeamLabel.getText());
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Hey " + userName+ "! Your page was created successfully");
        alert.show();
        homePage();


    }

/*    public boolean playerInfoValidation(String text){
        if(text==null){
            return false;
        }
        String regex = "[0-9]+";
        if(!text.matches(regex)){
            return false;
        }
        return true;
    }*/
}
