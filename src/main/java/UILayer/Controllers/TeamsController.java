package UILayer.Controllers;

import DataForTest.DataBase;
import ServiceLayer.TeamManagement;
import UILayer.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

public class TeamsController extends Controller {

    TeamManagement teamManagement = new TeamManagement();
    String teamName;
    String budget;
    boolean openTeam=false;

    @FXML
    TableView<SimpleStringProperty> teams_table = new TableView<>();

    @FXML
    TextFlow team_page = new TextFlow();
    @FXML
    TextFlow team_page2 = new TextFlow();

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
        showTeams();
    }

    private void showTeams() {
        DataBase db = new DataBase();
        ArrayList<String> teams_name = clientController.getAllTeams();
        ArrayList<SimpleStringProperty> properties_teams_name = new ArrayList<>();
        for(String team_name: teams_name){
            properties_teams_name.add(new SimpleStringProperty(null,team_name, team_name));
        }

        ObservableList<SimpleStringProperty> teams_name_data = FXCollections.observableArrayList(properties_teams_name);
        TableColumn nameCol = new TableColumn("Teams");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn buttonCol = new TableColumn("Pages");
        buttonCol.setCellValueFactory(new PropertyValueFactory<>(""));

        Callback<TableColumn<SimpleStringProperty, String>, TableCell<SimpleStringProperty, String>> cellFactory
                = //
                new Callback<TableColumn<SimpleStringProperty, String>, TableCell<SimpleStringProperty, String>>() {
                    @Override
                    public TableCell call( TableColumn<SimpleStringProperty, String> param) {
                        TableCell<SimpleStringProperty, String> cell = new TableCell<SimpleStringProperty, String>() {
                            Button btn = new Button("Show Team's Page");
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        SimpleStringProperty simple = getTableView().getItems().get(getIndex());
                                        showTeamPage(simple.getName());
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        buttonCol.setCellFactory(cellFactory);

        teams_table.setItems(teams_name_data);
        teams_table.getColumns().addAll(nameCol, buttonCol);


    }

    private void showTeamPage(String team_name) {

       // Pair<String, Set<String>[]> detailsAsPair = clientController.getTeamPageDetails(team_name);
        String details = clientController.getTeamPageDetails(team_name);
        String[] split_details = details.split(",");
        ArrayList<String> players = clientController.getTeamPlayers(team_name);
        ArrayList<String> coaches = clientController.getTeamCoaches(team_name);
        ArrayList<String> managers = clientController.getTeamManagers(team_name);

        if(details==null || players==null || coaches==null || managers==null){
            team_page.getChildren().clear();
            team_page.getChildren().add(new Text(team_name + " has no page"));
            return;
        }

        team_page.getChildren().clear();
        team_page.getChildren().add(new Text(team_name + "'s Page:" + "\n"));
        for(String detail: split_details){
            team_page.getChildren().add(new Text(detail + "\n"));
        }

        team_page2.getChildren().clear();
        team_page2.getChildren().add(new Text("Players: "));
        for(String player: players){
            team_page2.getChildren().add(new Text(player + ", "));
        }
        team_page2.getChildren().add(new Text("\n"));

        team_page2.getChildren().add(new Text("Coaches: "));
        for(String coach: coaches){
            team_page2.getChildren().add(new Text(coach + ", "));
        }
        team_page2.getChildren().add(new Text("\n"));

        team_page2.getChildren().add(new Text("Manager: "));
        for(String manager: managers){
            team_page2.getChildren().add(new Text(manager + ", "));
        }


    }

    public void submitOpenTeam() throws IOException {
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
        if(teamExist(choosenTeamName.getText())==false){
            errorMessage = errorMessage +" team name already exist please chose different name"+ "\n";
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
            String message = clientController.CreateNewTeam(teamName,choosenTeamBudget.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("TEAM CREATION");
            alert.setContentText(message);
            alert.show();
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
