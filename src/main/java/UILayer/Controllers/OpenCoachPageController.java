package UILayer.Controllers;

import UILayer.ClientController;
import UILayer.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OpenCoachPageController extends Controller {

    ObservableList<String> allTeams = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox teams;

    @FXML
    private DatePicker birthDay;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage s = Main.getStage();
        s.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        ArrayList<String> allTeamsInDB = clientController.getAllTeams();
        for(String team:allTeamsInDB){
            allTeams.add(team);
        }
        teams.setItems(allTeams);
    }

    public void submitDetails(ActionEvent actionEvent) throws IOException {
        String coachTeam = teams.getValue().toString();
        String birthDayAsString = birthDay.getValue().toString();
        clientController.createCoachPersonalPage(coachTeam,birthDayAsString);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Hey " + userName+ "! Your page was created successfully");
        alert.show();
        homePage();
    }

}
