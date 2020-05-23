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
import java.util.Scanner;

public class LogController extends Controller {

    @FXML
    TextArea textArea = new TextArea();

    @FXML

    String textLog = "";
    //boolean setReport=false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage s = Main.getStage();
        s.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        try {
            showLog();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void showLog() throws IOException {

        String textToEdit = clientController.watchLogFiles();
        Scanner scanner = new Scanner(textToEdit);
        String textLog = "";
        int index;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // process the line
            index = line.indexOf("-");
            textLog = textLog + line.substring(index + 2) + "\n";
        }
        scanner.close();

    }
}

