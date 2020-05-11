package UILayer.Controllers;

import UILayer.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportController extends Controller{

    @FXML
    TextArea textArea = new TextArea();

    @FXML
    Button button = new Button();

    String report="";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage s = Main.getStage();
        s.setOnCloseRequest(e->{
            e.consume();
            closeProgram();
        });
        button.setVisible(false);
        checkGame();
    }

    private void checkGame() {
        //check if this main referee has a game that finished but not closed
        //if yes: show the report and button visibility- true
        report=clientController.checkFinishedGames(userName,userType);
        button.setVisible(true);


    }

    public void setChange(ActionEvent actionEvent) {
        // get text from text field and put into te game report
        //clientController.changeReport(textArea.getAccessibleText());

    }
}
