package UILayer.Controllers;

import ServiceLayer.TeamManagement;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class OpenTeamController extends Controller{

    TeamManagement teamManagement = new TeamManagement();
    String teamName;
    boolean openTeam=false;

    @FXML
    private TextField chosenTeamName;
    @FXML
    private TextField  chosenTeamBudget;
    @FXML
    private TextField chosenNation;
    @FXML
    private TextField chosenHistory;

    public void submitOpenTeam() throws IOException {
        boolean error =false;
        Alert alertError  = new Alert(Alert.AlertType.ERROR);
        String errorMessage="";

        if(!validationBudget(chosenTeamBudget.getText())){

            errorMessage = errorMessage +" budget not valid"+ "\n";
            alertError.setContentText(errorMessage);
            error=true;
        }

        if(!validationName(chosenTeamName.getText())){
            errorMessage = errorMessage +" name not valid"+ "\n";
            alertError.setContentText(errorMessage);
            error=true;
        }
        if(!teamExist(chosenTeamName.getText())){
            errorMessage = errorMessage +" team name already exist please chose different name"+ "\n";
            alertError.setContentText(errorMessage);
            error=true;
        }
        if(clientController.checkIfHasATeam().equals("Owner already has a team")){
            errorMessage = errorMessage +" Owner already has a team"+ "\n";
            alertError.setContentText(errorMessage);
            error=true;
        }


        if(error){
            alertError.show();
        }
        else {
            //create team
            openTeam = true;
            teamName = chosenTeamName.getText();
            String checkIfHasaTeam = clientController.checkIfHasATeam();
            if(checkIfHasaTeam.equals("null")){
                String message = clientController.CreateNewTeam(teamName,chosenTeamBudget.getText());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("TEAM CREATION");
                alert.setContentText(message);
                alert.show();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("TEAM DID NOT CREAT");
                alert.setContentText(checkIfHasaTeam);
                alert.show();
            }

            homePage();
        }

    }

    private boolean teamExist(String text) {
        return clientController.checkIfTeamNameExist(text);
    }

    public boolean validationBudget(String budget){
        String regex = "[0-9]+";
        if(!budget.matches(regex)){
            return false;
        }
        Double budgetDouble = Double.parseDouble(budget);
        if(budgetDouble==0||budgetDouble==null|| budgetDouble>1000000){
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

    public void createTeamPage(){
        boolean error =false;
        Alert alertError  = new Alert(Alert.AlertType.ERROR);
        String errorMessage="";

        if(!validationHistory(chosenHistory.getText())){

            errorMessage = errorMessage +" history not valid"+ "\n";
            alertError.setContentText(errorMessage);
            error=true;

        }
        if(!validationNation(chosenNation.getText())){
            errorMessage = errorMessage +" nation not valid"+ "\n";
            alertError.setContentText(errorMessage);
            error=true;
        }

        if(clientController.checkIfHasATeam().equals("Owner already has a team")){
            String message = clientController.opemTeamPage(chosenHistory.getText(),chosenNation.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(message);
            alert.show();

        }
        else if(clientController.checkIfHasATeam().equals("null")){
            errorMessage = errorMessage +" The owner has no active team"+ "\n";
            alertError.setContentText(errorMessage);
            error=true;
        }
        if(error){
            alertError.show();
        }
    }
}
