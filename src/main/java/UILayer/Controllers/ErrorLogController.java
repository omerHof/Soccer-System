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

public class ErrorLogController extends Controller {

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
            showErrorLog();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showErrorLog() throws IOException {
        textArea.setText("");
        textLog = clientController.watchErrorLogFiles();
        textArea.setText(textLog);
        textArea.setFont(Font.font("System", 15));

    }


}

