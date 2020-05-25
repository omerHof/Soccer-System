package UILayer.Controllers;

import UILayer.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
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
        textArea.setText("");
        textLog = clientController.watchLogFiles();
        textArea.setText(textLog);
        textArea.setFont(Font.font("System", 15));

        /*
        String[] lines = textToEdit.split("\n");
        for(int i=1;i<lines.length;i++){
            int index = lines[i].indexOf("-");
            textLog = textLog + lines[i].substring(index + 2) + "\n";

        }
        textArea.setText(textLog);

         */

    }


}

