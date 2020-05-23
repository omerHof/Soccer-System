package UILayer.Controllers;

import ServiceLayer.UserManagement;
import UILayer.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class watchGamesController extends Controller {


    @FXML
    TableView<String[]> tab1 = new TableView();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage s = Main.getStage();
        s.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        //UserManagement userManagement = new UserManagement();
        //userManagement.getUserType(userName);
        setText();

    }

    public void setText() {

        int i = 0;
        ArrayList<String> games = new ArrayList<>();
        //leagueText.setText("LEAGUE: " + leagueString);
        games = clientController.watchGamesList();
        String[][] data = new String[games.size()][8];

        for (String game : games) {
            data[i] = new String[]{ game.split("%")[0], game.split("%")[1], game.split("%")[2],
                    game.split("%")[3], game.split("%")[4], game.split("%")[5], game.split("%")[6]};
            i++;
        }

        createTable(data,tab1);
        tab1.setVisible(true);
    }


    private void createTable(String[][] data, TableView<String[]> tab) {

        TableColumn<String[], String> columnOne = new TableColumn();
        columnOne.setText("Home Team");
        columnOne.setPrefWidth(105);

        TableColumn<String[], String> columnTwo = new TableColumn();
        columnTwo.setText("Away Team");
        columnTwo.setPrefWidth(105);

        TableColumn<String[], String> columnThree = new TableColumn();
        columnThree.setText("Date");
        columnThree.setPrefWidth(120);

        TableColumn<String[], String> columnFour = new TableColumn();
        columnFour.setText("Hour");
        columnFour.setPrefWidth(60);

        TableColumn<String[], String> columnFive = new TableColumn();
        columnFive.setText("Minute");
        columnFive.setPrefWidth(65);

        TableColumn<String[], String> columnSix = new TableColumn();
        columnSix.setText("Stadium");
        columnSix.setPrefWidth(90);

        TableColumn<String[], String> columnSeven = new TableColumn();
        columnSeven.setText("Status");
        columnSeven.setPrefWidth(85);


/*        TableColumn<String[], String> columneight = new TableColumn();
        columneight.setText("Status");*/

        tab.getColumns().

                addAll(columnOne, columnTwo, columnThree, columnFour, columnFive, columnSix, columnSeven);

        // Add cell value factories
        columnOne.setCellValueFactory((p) ->

        {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > 0 ? x[0] : "<no name>");
        });

        columnTwo.setCellValueFactory((p) ->

        {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > 1 ? x[1] : "<no value>");
        });

        columnThree.setCellValueFactory((p) ->

        {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > 1 ? x[2] : "<no value>");
        });

        columnFour.setCellValueFactory((p) ->

        {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > 1 ? x[3] : "<no value>");
        });

        columnFive.setCellValueFactory((p) ->

        {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > 1 ? x[4] : "<no value>");
        });

        columnSix.setCellValueFactory((p) ->

        {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > 1 ? x[5] : "<no value>");
        });
        columnSeven.setCellValueFactory((p) ->

        {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > 1 ? x[6] : "<no value>");
        });

/*
        columneight.setCellValueFactory((p) ->

        {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > 1 ? x[7] : "<no value>");
        });
*/

        columnOne.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length > 0) {
                    return new SimpleStringProperty(x[0]);
                } else {
                    return new SimpleStringProperty("<no name>");
                }
            }
        });

        columnTwo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length > 1) {
                    return new SimpleStringProperty(x[1]);
                } else {
                    return new SimpleStringProperty("<no value>");
                }
            }
        });
        columnThree.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length > 1) {
                    return new SimpleStringProperty(x[2]);
                } else {
                    return new SimpleStringProperty("<no value>");
                }
            }
        });
        columnFour.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length > 1) {
                    return new SimpleStringProperty(x[3]);
                } else {
                    return new SimpleStringProperty("<no value>");
                }
            }
        });
        columnFive.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length > 1) {
                    return new SimpleStringProperty(x[4]);
                } else {
                    return new SimpleStringProperty("<no value>");
                }
            }
        });
        columnSix.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length > 1) {
                    return new SimpleStringProperty(x[5]);
                } else {
                    return new SimpleStringProperty("<no value>");
                }
            }
        });
        columnSeven.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length > 1) {
                    return new SimpleStringProperty(x[6]);
                } else {
                    return new SimpleStringProperty("<no value>");
                }
            }
        });

 /*       columneight.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length > 1) {
                    return new SimpleStringProperty(x[7]);
                } else {
                    return new SimpleStringProperty("<no value>");
                }
            }
        });*/
        tab.getItems().

                addAll(Arrays.asList(data));
    }

}
