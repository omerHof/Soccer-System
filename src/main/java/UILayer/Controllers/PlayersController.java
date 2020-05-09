package UILayer.Controllers;

import DataForTest.DataBase;
import ServiceLayer.UserManagement;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PlayersController extends Controller {
    UserManagement userManagement = new UserManagement();

    @FXML
    TableView<SimpleStringProperty> players_table = new TableView<>();

    @FXML
    TextFlow personal_page = new TextFlow();

    @FXML
    private TextField choosenHeight;
    @FXML
    private TextField choosenWeight;
    @FXML
    private TextField choosenNumber;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage s = Main.getStage();
        s.setOnCloseRequest(e->{
            e.consume();
            closeProgram();
        });
        showPlayers();
    }

    private void showPlayers() {
        DataBase db = new DataBase();
        HashMap<String, String> players_name = userManagement.getAllPlayers();
        ArrayList<SimpleStringProperty> properties_players_name = new ArrayList<>();
        for(String user_name: players_name.keySet()){
            String full_name = players_name.get(user_name);
            properties_players_name.add(new SimpleStringProperty(null,full_name, user_name));
        }

        ObservableList<SimpleStringProperty> players_name_data = FXCollections.observableArrayList(properties_players_name);
        TableColumn nameCol = new TableColumn("Player");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn buttonCol = new TableColumn("Pages");
        buttonCol.setCellValueFactory(new PropertyValueFactory<>(""));

        Callback<TableColumn<SimpleStringProperty, String>, TableCell<SimpleStringProperty, String>> cellFactory
                = //
                new Callback<TableColumn<SimpleStringProperty, String>, TableCell<SimpleStringProperty, String>>() {
                    @Override
                    public TableCell call( TableColumn<SimpleStringProperty, String> param) {
                        TableCell<SimpleStringProperty, String> cell = new TableCell<SimpleStringProperty, String>() {
                            Button btn = new Button("Show Player's Page");
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        SimpleStringProperty simple = getTableView().getItems().get(getIndex());
                                       showPlayerPage(simple.getValue(), simple.getName());
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

        players_table.setItems(players_name_data);
        players_table.getColumns().addAll(nameCol, buttonCol);


    }

    private void showPlayerPage(String user_name, String full_name) {

        Pair<String, ArrayList<String>> detailsAsPair = userManagement.getPlayerPageDetails(user_name);
        String[] details = detailsAsPair.getKey().split(",");
        personal_page.getChildren().clear();
        personal_page.getChildren().add(new Text(full_name + "'s Personal Page:" + "\n"));
        for(String detail: details){
            personal_page.getChildren().add(new Text(detail + "\n"));
        }
        personal_page.getChildren().add(new Text("Team History: "));
        for(String team: detailsAsPair.getValue()){
            personal_page.getChildren().add(new Text(team + ", "));
        }

    }

    public void openPlayerPage(){
        boolean error =false;
        Alert alertError  = new Alert(Alert.AlertType.ERROR);
        String errorMessage="";
        if(playerInfoValidation(choosenHeight.getText())==false){
            errorMessage = errorMessage +" height not valid"+ "\n";
            alertError.setContentText(errorMessage);
            error=true;

        }
        if(playerInfoValidation(choosenWeight.getText())==false){
            errorMessage = errorMessage +" weight not valid"+ "\n";
            alertError.setContentText(errorMessage);
            error=true;
        }
        if(playerInfoValidation(choosenNumber.getText())==false){
            errorMessage = errorMessage +" shirt number not valid"+ "\n";
            alertError.setContentText(errorMessage);
            error=true;
        }
        if(error==true){
            alertError.show();
        }
        else {

            int height = Integer.parseInt(choosenHeight.getText());
            int weight = Integer.parseInt(choosenWeight.getText());
            int shirtNumber = Integer.parseInt(choosenNumber.getText());

            userManagement.createPlayerPersonalPage(height, weight, shirtNumber, "");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("the page open succsesfully");
            alert.show();
        }

    }

    public boolean playerInfoValidation(String text){
        if(text==null){
            return false;
        }
        String regex = "[0-9]+";
        if(!text.matches(regex)){
            return false;
        }
        return true;

    }


}






