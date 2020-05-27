package UILayer.Controllers;

import ServiceLayer.TeamManagement;
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
import java.util.ResourceBundle;

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
    Pane detailsPaneLeft;

    @FXML
    Pane detailsPaneRight;


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
        ArrayList<String> teams_name = clientController.getAllTeams();
        ArrayList<SimpleStringProperty> properties_teams_name = new ArrayList<>();
        for(String team_name: teams_name){
            properties_teams_name.add(new SimpleStringProperty(null,team_name, team_name));
        }
        /* Column 1: Team's name */
        ObservableList<SimpleStringProperty> teams_name_data = FXCollections.observableArrayList(properties_teams_name);
        TableColumn nameCol = new TableColumn("Teams");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        /* Column 2: Team's page button */
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
                                        detailsPaneLeft.setVisible(true);
                                        detailsPaneRight.setVisible(true);

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

        /* Column 3: follow button */
        if(userType!= null && userType.equals("Fan")) {
            TableColumn buttonCol2 = new TableColumn("");
            buttonCol2.setCellValueFactory(new PropertyValueFactory<>(""));

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
                                            followPage(simple.getName());
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
            teams_table.setItems(teams_name_data);
            teams_table.getColumns().addAll(nameCol, buttonCol, buttonCol2);
            return;
        }
        teams_table.setItems(teams_name_data);
        teams_table.getColumns().addAll(nameCol, buttonCol);
    }

    private void showTeamPage(String team_name) {

       // Pair<String, Set<String>[]> detailsAsPair = clientController.getTeamPageDetails(team_name);
        String details = clientController.getTeamPageDetails(team_name);
        ArrayList<String> players = clientController.getTeamPlayers(team_name);
        ArrayList<String> coaches = clientController.getTeamCoaches(team_name);
        ArrayList<String> managers = clientController.getTeamManagers(team_name);

        if(details==null || players==null || coaches==null || managers==null){
            team_page.getChildren().clear();
            team_page.getChildren().add(new Text(team_name + " has no page"));
            return;
        }

        Text teamName = new Text(team_name + "'s Page:" + "\n\n");
        teamName.setUnderline(true);
        teamName.setFont(Font.font("System", FontWeight.BOLD, 18));


        team_page.getChildren().clear();
        team_page.getChildren().add(teamName);

        String[] split_details = details.split(",");
        for(String detail: split_details){
            Text detailText = new Text(detail + "\n");
            detailText.setFont(Font.font("System", FontWeight.NORMAL, 16));
            team_page.getChildren().add(detailText);
        }

        team_page2.getChildren().clear();
        Text playersText = new Text("Players: ");
        playersText.setUnderline(true);
        playersText.setFont(Font.font("System", FontWeight.BOLD, 18));

        team_page2.getChildren().add(playersText);
        for(String player: players){
            Text detailTextPlayer = new Text(player + ", ");
            detailTextPlayer.setFont(Font.font("System", FontWeight.NORMAL, 16));
            team_page2.getChildren().add(detailTextPlayer);
        }
        team_page2.getChildren().add(new Text("\n"));

        Text coachesText = new Text("Coaches: ");
        coachesText.setFont(Font.font("System", FontWeight.BOLD, 18));
        coachesText.setUnderline(true);

        team_page2.getChildren().add(coachesText);
        for(String coach: coaches){
            Text detailTextCoach = new Text(coach + ", ");
            detailTextCoach.setFont(Font.font("System", FontWeight.NORMAL, 16));
            team_page2.getChildren().add(detailTextCoach);
        }
        team_page2.getChildren().add(new Text("\n"));

        Text managerText = new Text("Manager: ");
        managerText.setFont(Font.font("System", FontWeight.BOLD, 18));
        managerText.setUnderline(true);

        team_page2.getChildren().add(managerText);
        for(String manager: managers){
            Text detailTextManager = new Text(manager + ", ");
            detailTextManager.setFont(Font.font("System", FontWeight.NORMAL, 16));
            team_page2.getChildren().add(detailTextManager);
        }
    }

    private void followPage(String team_name) {
        String details = clientController.getTeamPageDetails(team_name);
        if(details==null) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", team_name + " has no page to follow.");
            return;
        }

        clientController.followTeam(team_name);
        showAlert(Alert.AlertType.INFORMATION, "Success!", userName + ", now you are following " +team_name + "'s page" );
    }


}
