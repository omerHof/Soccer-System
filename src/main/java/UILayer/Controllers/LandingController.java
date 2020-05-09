package UILayer.Controllers;

import UILayer.Main;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class LandingController extends Controller {

    @FXML
    JFXButton notification;

    @FXML
    JFXButton profileButton;

    @FXML
    JFXButton logInBTN;

    @FXML
    JFXButton signUpBtn;

    @FXML
    JFXButton signOut;

    @FXML
    JFXButton myAppsBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Stage s = Main.getStage();
        s.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        showUserButton();
    }

    public void showUserButton(){
        if(super.userName!=null){
            logInBTN.setVisible(false);
            signUpBtn.setVisible(false);
            notification.setVisible(true);
            profileButton.setText(userName);
            profileButton.setVisible(true);
            signOut.setVisible(true);
            myAppsBtn.setVisible(true);
        }
    }

    @FXML
    public void hideUserButton(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to log out from the system?", ButtonType.YES, ButtonType.NO);
        //alert.setHeaderText("Leaving so soon?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {

            logInBTN.setVisible(true);
            signUpBtn.setVisible(true);
            notification.setVisible(false);
            profileButton.setVisible(false);
            signOut.setVisible(false);
            myAppsBtn.setVisible(false);

            super.userName = null;
        }
    }
}
