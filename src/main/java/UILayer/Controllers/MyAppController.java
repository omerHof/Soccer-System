package UILayer.Controllers;

import ServiceLayer.UserManagement;
import UILayer.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MyAppController extends Controller {

    @FXML
    public Button addAssent;
    @FXML
    public Button removeAssent;
    @FXML
    private Button createNewTeamButton;
    @FXML
    private Button initNewSeasonButton;
    @FXML
    private Button openTeamPage;
    @FXML
    private Button openPlayerPage;
    @FXML

    private Button addGameEvent;
    @FXML
    private Button openCoachPage;
    @FXML
    private Button getManagementPassword;
    @FXML
    private Button editReport;
    @FXML
    private Button showLog;
    @FXML
    private Button showErrorLog;


    @FXML
    TableView<String[]> tab1 = new TableView();
    @FXML
    private Button showMyGames;

    @FXML
    Label leagueText1 = new Label();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage s = Main.getStage();
        s.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        UserManagement userManagement = new UserManagement();
        //userManagement.getUserType(userName);
        setButton();

    }

    public void setButton() {

        switch (userType) {

            case "Administrator":
                showLog.setVisible(true);
                showErrorLog.setVisible(true);
                break;

            case "Fan":

                break;
            case "Player":
                openPlayerPage.setVisible(true);

                break;
            case "Coach":
                openCoachPage.setVisible(true);
                break;

            case "AssociationRepresentative":
                initNewSeasonButton.setVisible(true);
                addGameEvent.setVisible(true);
                getManagementPassword.setVisible(true);
                showMyGames.setVisible(true);
                break;

            case "TeamOwner":
                createNewTeamButton.setVisible(true);
                openTeamPage.setVisible(true);
                addAssent.setVisible(true);
                removeAssent.setVisible(true);
                openTeamPage.setVisible(true);
                break;

            case "Manager":
                openTeamPage.setVisible(true);
                break;

            case "Referee":
                showMyGames.setVisible(true);
                break;

            case "MainReferee":
                editReport.setVisible(true);
                showMyGames.setVisible(true);
                break;


        }

    }


    public void showPassword() {
        String pass = clientController.displaySpecialPassword();
        showAlert(Alert.AlertType.INFORMATION, "Form Information", "The current management password is: " + pass);

    }





 /*   private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }*/
}
