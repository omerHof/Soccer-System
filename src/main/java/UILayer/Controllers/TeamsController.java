package UILayer.Controllers;

import ServiceLayer.TeamManagement;
import UILayer.Main;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TeamsController extends Controller {

    TeamManagement teamManagement = new TeamManagement();
    String teamName;
    String budget;
    boolean openTeam=false;


    @FXML
    private TextField choosenTeamName;
    @FXML
    private TextField  choosenTeamBudget;
    @FXML
    private TextField choosenNation;
    @FXML
    private TextField choosenHistory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage s = Main.getStage();
        s.setOnCloseRequest(e->{
            e.consume();
            closeProgram();
        });
    }



    public boolean validationBudget(String budget){

        String regex = "[0-9]+";
        if(!budget.matches(regex)){
            return false;
        }
        Double budgetDuoble = Double.parseDouble(budget);
        if(budgetDuoble==0||budgetDuoble==null){
            return false;
        }

        return true;
    }

        public boolean validationName(String name) {
        if (name == null || name.length() == 0) {
            return false;
        }
        if (name.matches(".*\\d.*")) {
            return false;
        }
        if (!name.matches("[a-z A-Z]+")) {
            return false;
        }
        return true;

    }
    public boolean validationNation(String name) {
        if (name == null || name.length() == 0) {
            return false;
        }
        if (name.matches(".*\\d.*")) {
            return false;
        }
        if (!name.matches("[a-z A-Z]+")) {
            return false;
        }
        return true;

    }
    public boolean validationHistory(String name) {
        if (name == null || name.length() == 0) {
            return false;
        }

        return true;

    }

            public void submitOpenTeam(){
                boolean error =false;
                Alert alertError  = new Alert(Alert.AlertType.ERROR);
                String errorMessage="";

                if(validationBudget(choosenTeamBudget.getText())==false){

                    errorMessage = errorMessage +" budget not valid"+ "\n";
                    alertError.setContentText(errorMessage);
                    error=true;



                }
                if(validationName(choosenTeamName.getText())==false){
                    errorMessage = errorMessage +" name not valid"+ "\n";
                    alertError.setContentText(errorMessage);
                    error=true;
                }

                ///need to add check if the name of the team exist!!!

                if(error==true){
                    alertError.show();
                }
                else {
                    //create team
                    openTeam = true;
                    teamName = choosenTeamName.getText();
                    String message = teamManagement.openTeam(choosenTeamName.getText(), Double.parseDouble(choosenTeamBudget.getText()));
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText(message);
                    alert.show();
                }

            }


            public void createTeamPage(){
                boolean error =false;
                Alert alertError  = new Alert(Alert.AlertType.ERROR);
                String errorMessage="";

        if(validationHistory(choosenHistory.getText())==false){

            errorMessage = errorMessage +" history not valid"+ "\n";
            alertError.setContentText(errorMessage);
            error=true;

        }
        if(validationNation(choosenNation.getText())==false){
            errorMessage = errorMessage +" nation not valid"+ "\n";
            alertError.setContentText(errorMessage);
            error=true;
        }

        if(openTeam==true){
            String message =  teamManagement.openTeamPage(choosenHistory.getText(),choosenNation.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(message);
            alert.show();
        }
        else{

            errorMessage = errorMessage +" The owner has no active team"+ "\n";
            alertError.setContentText(errorMessage);
            error=true;
        }
                if(error==true){
                    alertError.show();
                }

    }


}
