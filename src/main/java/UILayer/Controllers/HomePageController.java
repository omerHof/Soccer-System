package UILayer.Controllers;

import ServiceLayer.SystemManagement;
import UILayer.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class HomePageController extends Controller {

    //SystemManagement systemManagement;
    ArrayList<String> leaguesNames;

    @FXML
    TableView<String[]> tab1 = new TableView();
    @FXML
    TableView<String[]> tab2 = new TableView();

    @FXML
    Label leagueText1 = new Label();

    @FXML
    Label leagueText2 = new Label();

    public HomePageController() {
        //systemManagement = new SystemManagement();
        leaguesNames = new ArrayList<>();
        leaguesNames = clientController.getLeaguesNames();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Stage s = Main.getStage();
        s.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        setText();

    }

    public void setText() {

        String[][] data = new String[10][8];
        int i = 0;
        int numOfTable=1;
        for (String leagueString : leaguesNames) {
            //leagueText.setText("LEAGUE: " + leagueString);
            ArrayList<String> games = new ArrayList<>();
            games=clientController.closestGames(leagueString);
            if(games!=null) {
                for (String game : games) {
                    data[i] = new String[]{leagueString, game.split("%")[0], game.split("%")[1], game.split("%")[2],
                            game.split("%")[3], game.split("%")[4], game.split("%")[5], game.split("%")[6]};
                    i++;
                }

                if (numOfTable == 1) {
                    createTable(data, tab1, leagueText1, leagueString);
                    tab1.setVisible(true);
                } else {
                    createTable(data, tab2, leagueText2, leagueString);
                    tab2.setVisible(true);

                }
                numOfTable++;
            }
        }
    }

    private void createTable(String[][] data, TableView<String[]> tab, Label leagueText, String leagueString) {
        leagueText.setText("LEAGUE: " + leagueString);

        TableColumn<String[], String> columnOne = new TableColumn();
        columnOne.setText("League");
        columnOne.setPrefWidth(90);

        TableColumn<String[], String> columnTwo = new TableColumn();
        columnTwo.setText("Home Team");
        columnTwo.setPrefWidth(105);

        TableColumn<String[], String> columnThree = new TableColumn();
        columnThree.setText("Away Team");
        columnThree.setPrefWidth(105);


        TableColumn<String[], String> columnFour = new TableColumn();
        columnFour.setText("Date");
        columnFour.setPrefWidth(120);


        TableColumn<String[], String> columnFive = new TableColumn();
        columnFive.setText("Hour");
        columnFive.setPrefWidth(60);


        TableColumn<String[], String> columnSix = new TableColumn();
        columnSix.setText("Minute");
        columnSix.setPrefWidth(65);


        TableColumn<String[], String> columnSeven = new TableColumn();
        columnSeven.setText("Stadium");
        columnSeven.setPrefWidth(90);


        TableColumn<String[], String> columneight = new TableColumn();
        columneight.setText("Status");
        columneight.setPrefWidth(85);


        tab.getColumns().

                addAll(columnOne, columnTwo, columnThree, columnFour, columnFive, columnSix, columnSeven, columneight);

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

        columneight.setCellValueFactory((p) ->

        {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > 1 ? x[7] : "<no value>");
        });
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
        columneight.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length > 1) {
                    return new SimpleStringProperty(x[7]);
                } else {
                    return new SimpleStringProperty("<no value>");
                }
            }
        });
        tab.getItems().

                addAll(Arrays.asList(data));
    }
}



