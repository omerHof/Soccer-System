package UILayer.Controllers;

import UILayer.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LogController extends Controller {

    @FXML
    TextArea textArea = new TextArea();

    @FXML

    String textLog="";
    //boolean setReport=false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage s = Main.getStage();
        s.setOnCloseRequest(e->{
            e.consume();
            closeProgram();
        });
        /*
        try {
            checkGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
         */
    }

    private void showLog() throws IOException {
        //check if this main referee has a game that finished but not closed
        //if yes: show the report and button visibility- true
       // textLog=clientController.checkFinishedGames(userName,userType);
        /*

            textArea.setText(report);
            button.setVisible(true);
            homePage();

         */


    }

    public void setChange(ActionEvent actionEvent) throws IOException {
        // get text from text field and put into te game report
        //clientController.changeReport(textArea.getAccessibleText());
        /*
        setReport=clientController.setReport(userName,textArea.getText());
        if(setReport){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The report changed", ButtonType.CLOSE);
            alert.setHeaderText("CONFIRMATION");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "set report failure", ButtonType.CLOSE);
            alert.setHeaderText("ERROR");
            alert.showAndWait();
        }
        homePage();

         */

    }
}
