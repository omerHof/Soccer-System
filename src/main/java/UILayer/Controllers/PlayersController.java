package UILayer.Controllers;

import UILayer.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PlayersController extends Controller {

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

    @FXML
    private Pane detailsPane;


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
        HashMap<String, String> players_name = clientController.getAllPlayers();
        ArrayList<SimpleStringProperty> properties_players_name = new ArrayList<>();
        for(String user_name: players_name.keySet()){
            String full_name = players_name.get(user_name);
            properties_players_name.add(new SimpleStringProperty(null,full_name, user_name));
        }
        /* Column 1: player's name*/
        ObservableList<SimpleStringProperty> players_name_data = FXCollections.observableArrayList(properties_players_name);
        TableColumn nameCol = new TableColumn("Player");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        /* Column 2: show-page button */
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
                                        detailsPane.setVisible(true);
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

        /* Column 3: follow button */
        if(userType!= null && userType.equals("Fan")) {


            TableColumn buttonCol2 = new TableColumn("");
            buttonCol.setCellValueFactory(new PropertyValueFactory<>(""));

            Callback<TableColumn<SimpleStringProperty, String>, TableCell<SimpleStringProperty, String>> cellFactory2
                    = //
                    new Callback<TableColumn<SimpleStringProperty, String>, TableCell<SimpleStringProperty, String>>() {
                        @Override
                        public TableCell call(TableColumn<SimpleStringProperty, String> param) {
                            TableCell<SimpleStringProperty, String> cell = new TableCell<SimpleStringProperty, String>() {
                                Button btn = new Button("Follow");

                                @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                        btn.setOnAction(event -> {
                                            SimpleStringProperty simple = getTableView().getItems().get(getIndex());
                                            followPage(simple.getValue(), simple.getName());
                                        });
                                        setGraphic(btn);
                                        setText(null);
                                    }
                                }
                            };
                            return cell;
                        }
                    };
            buttonCol2.setCellFactory(cellFactory2);
            /* Put all columns in table */
            players_table.setItems(players_name_data);
            players_table.getColumns().addAll(nameCol, buttonCol, buttonCol2);
            return; // work?
        }
        else{
            /* Put all columns in table */
            players_table.setItems(players_name_data);
            players_table.getColumns().addAll(nameCol, buttonCol);
        }

    }



    private void showPlayerPage(String user_name, String full_name) {
        String details = clientController.getPlayerPageDetails(user_name);
        ArrayList<String> history = clientController.getPageHistory(user_name);

        if(details==null || history==null){
            personal_page.getChildren().clear();
            personal_page.getChildren().add(new Text(full_name + " has no personal page"));
            return;
        }

        String[] split_details = details.split(",");
        personal_page.getChildren().clear();
        Text playerText = new Text(full_name + "'s Personal Page:" + "\n");
        playerText.setUnderline(true);
        playerText.setFont(Font.font("System", FontWeight.BOLD, 20));

        personal_page.getChildren().add(playerText);
        for(String detail: split_details){
            personal_page.getChildren().add(new Text(detail + "\n"));
        }

        personal_page.getChildren().add(new Text("Team History: "));
        int i;
        for(i=0; i<history.size()-1; i++){
            personal_page.getChildren().add(new Text(history.get(i) + ", "));
        }
        personal_page.getChildren().add(new Text(history.get(i)));
    }

    private void followPage(String user_name_player, String full_name) {
        // 1. get pageName
        String details = clientController.getPlayerPageDetails(user_name_player);
        if(details==null ){
            showAlert(Alert.AlertType.ERROR, "Form Error!", full_name + " has no page to follow.");
            return;
        }
        String[] split_details = details.split(",");
        String pageName = split_details[0];
        // 2. follow
        clientController.followPage(pageName);
        showAlert(Alert.AlertType.INFORMATION, "Success!", userName + ", now you are following " +full_name + "'s page" );

    }
}






