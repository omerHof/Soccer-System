package UILayer.Controllers;

import ServiceLayer.LeagueSeasonManagement;
import ServiceLayer.SystemManagement;
import UILayer.ClientController;
import UILayer.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeasonController extends Controller {
    LeagueSeasonManagement seasonManagement = new LeagueSeasonManagement();
    //SystemManagement systemManagement = new SystemManagement();
    List<String> arrayTeams;
    List<String> arrayLeagues;
    List<String> arrayrepresentatives;
    List<String> arrayReferees;
    List<String> arrayMainReferees;


    List<String> arrayTeamsChosen;
    String leaguesChosen;
    List<String> arrayrepresentativesChosen;
    List<String> arrayRefereesChosen;
    List<String> arrayMainRefereesChosen;
    int yearChosen;
    String scoreChosen;
    String gameChosen;

    String initResult;

    @FXML
    private ChoiceBox<String> choiceBox1;
    @FXML
    private ChoiceBox<String> choiceBox2;
    @FXML
    private ChoiceBox<String> choiceBox3;
    @FXML
    private ChoiceBox<String> choiceBox4;
    @FXML
    private ChoiceBox<String> choiceBox5;
    @FXML
    private ChoiceBox<String> choiceBox6;
    @FXML
    private ChoiceBox<String> choiceBox7;
    @FXML
    private ChoiceBox<String> choiceBox8;

    @FXML
    private Pane refereePane;
    @FXML
    private Label refereeMessage;
    @FXML
    private Pane mainRefereePane;
    @FXML
    private Label mainRefereeMessage;
    @FXML
    private Pane teamPane;
    @FXML
    private Label teamMessage;
    @FXML
    private Pane assoPane;
    @FXML
    private Label assoMessage;


    @FXML
    private ChoiceBox<String> whichTeamCB;
    @FXML
    private ChoiceBox<String> eventTypeCB;
    @FXML
    private TextField timeTF;
    @FXML
    private TextField playerNameTF;
    @FXML
    JFXButton submit;
    @FXML
    JFXButton backBTN;

    int numOfTeams=4;


    ObservableList<String> options = FXCollections.observableArrayList();
    ObservableList<String> options2 = FXCollections.observableArrayList();
    ObservableList<String> options3 = FXCollections.observableArrayList();
    ObservableList<String> options4 = FXCollections.observableArrayList();
    ObservableList<String> options5 = FXCollections.observableArrayList();
    ObservableList<String> options6 = FXCollections.observableArrayList();
    ObservableList<String> options7 = FXCollections.observableArrayList();
    ObservableList<String> options8 = FXCollections.observableArrayList();

    ObservableList<String> league = FXCollections.observableArrayList();
    ObservableList<String> referees = FXCollections.observableArrayList();
    ObservableList<String> mainReferees = FXCollections.observableArrayList();
    ObservableList<String> teams = FXCollections.observableArrayList();
    ObservableList<String> representatives = FXCollections.observableArrayList();

    ObservableList<String> whichTeam = FXCollections.observableArrayList();
    ObservableList<String> gameEventsType = FXCollections.observableArrayList();

    ObservableList<String> arrayPlayers = FXCollections.observableArrayList();

   List<String> arrayPlayersString;



    public SeasonController() {

        arrayLeagues = clientController.getLeaguesNames();
        arrayTeams = clientController.getFullTeamsNames();
        arrayReferees = clientController.getAllUsersByType("Referee");
        arrayMainReferees = clientController.getAllUsersByType("MainReferee");
        arrayrepresentatives = clientController.getAllUsersByType("AssociationRepresentative");

/*

        //adding all the relevant players' names to the choice box.
        arrayPlayersString = clientController.getFullTeamsNames(); ///////// ????????????????? WHATTT IS ITTTTTTTTT ???????????????????? ////////////todo: //////////////////////////////
        for ( String name : arrayPlayersString){
            arrayPlayers.add(name);
        }
        playerNameCB.setItems(arrayPlayers);
*/


        setInfo();

        choiceBox1 = new ChoiceBox<>();
        choiceBox2 = new ChoiceBox<>();
        choiceBox3 = new ChoiceBox<>();
        choiceBox4 = new ChoiceBox<>();
        choiceBox8 = new ChoiceBox<>();

        arrayTeamsChosen=new ArrayList<>();
        arrayrepresentativesChosen=new ArrayList<>();
        arrayRefereesChosen=new ArrayList<>();
        arrayMainRefereesChosen=new ArrayList<>();

        whichTeamCB = new ChoiceBox<>();
        eventTypeCB = new ChoiceBox<>();

        initResult = new String();
    }

    public void setInfo() {
        for(String league:arrayLeagues){
            options.add(league);
        }
        for(String referee:arrayReferees){
            options2.add(referee);
        }
        for(String mainReferee:arrayMainReferees){
            options8.add(mainReferee);
        }
        for(String team:arrayTeams){
            options3.add(team);
        }
        for(String representative:arrayrepresentatives){
            options4.add(representative);
        }
        int curYear;
        for(int i=1; i<20; i++){
            curYear=2020+i;
            options5.add(""+curYear);
        }
        options7.add("RegularScorePolicy");
        options7.add("GoalScorePolicy");
        options6.add("OneRoundGamePolicy");
        options6.add("TwoRoundsGamePolicy");
        options6.add("RandomTwoRoundsGamePolicy");

        gameEventsType.add("goal");
        gameEventsType.add("offside");
        gameEventsType.add("foul");
        gameEventsType.add("redTicket");
        gameEventsType.add("yellowTicket");
        gameEventsType.add("injury");
        gameEventsType.add("substitution");

        whichTeam.add("home");
        whichTeam.add("away");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage s = Main.getStage();
        s.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        whichTeamCB.setItems(whichTeam);
        eventTypeCB.setItems(gameEventsType);

        setOptions();
    }


    public void setOptions() {
        try {
            choiceBox1.setItems(options);
            choiceBox2.setItems(options2);
            choiceBox3.setItems(options3);
            choiceBox4.setItems(options4);
            choiceBox5.setItems(options5);
            choiceBox6.setItems(options6);
            choiceBox7.setItems(options7);
            choiceBox8.setItems(options8);




        } catch (Exception e) {

        }
    }

    @FXML
    ComboBox comboLeague;

    public void addLeague() {
        try {
            if (league.size() == 0) {
                league.add(choiceBox1.getValue());
            }
            if (league.size() == 1) {
                league.clear();
                league.add(choiceBox1.getValue());
                comboLeague.setItems(league);
            }
            if(!choiceBox1.getValue().equals("")) {
                //int numOfTeams=systemManagement.getLeagueTeamNumber(choiceBox1.getValue());
                numOfTeams=clientController.getLeagueTeamNumber(choiceBox1.getValue());
                refereePane.setVisible(true);
                refereeMessage.setText("This league requires at least "+(numOfTeams/2*3)+" referees");
                mainRefereePane.setVisible(true);
                mainRefereeMessage.setText("This league requires at least "+(numOfTeams/2)+" main referees");
                teamPane.setVisible(true);
                teamMessage.setText("This league requires "+numOfTeams+" teams");
                assoPane.setVisible(true);
                assoMessage.setText("This league requires at least "+(numOfTeams/2)+" asso");
            }


        } catch (Exception e) {

        }
    }

    public void removeLeague() {
        if(!comboLeague.getSelectionModel().isEmpty()) {
            int y = comboLeague.getSelectionModel().getSelectedIndex();
            comboLeague.getItems().remove(y);
            refereePane.setVisible(false);
            mainRefereePane.setVisible(false);
            teamPane.setVisible(false);
            assoPane.setVisible(false);
        }
    }
    public void removeReferee() {
        if (!comboReferee.getSelectionModel().isEmpty()) {
            int y = comboReferee.getSelectionModel().getSelectedIndex();
            comboReferee.getItems().remove(y);
        }
    }
    public void removeMainReferee() {
        if (!comboMainReferee.getSelectionModel().isEmpty()) {
            int y = comboMainReferee.getSelectionModel().getSelectedIndex();
            comboMainReferee.getItems().remove(y);
        }
    }
    public void removeTeams() {
        if (!comboTeams.getSelectionModel().isEmpty()) {
            int y = comboTeams.getSelectionModel().getSelectedIndex();
            comboTeams.getItems().remove(y);
        }
    }
    public void removeAsso() {
        if (!comboAsso.getSelectionModel().isEmpty()) {
            int y = comboAsso.getSelectionModel().getSelectedIndex();
            comboAsso.getItems().remove(y);
        }
    }

    @FXML
    ComboBox comboReferee;

    public void addReferee() {
        try {
            if (!referees.contains(choiceBox2.getValue())) {
                referees.add(choiceBox2.getValue());
            }
            comboReferee.setItems(referees);

        } catch (Exception e) {

        }
    }

    @FXML
    ComboBox comboMainReferee;

    public void addMainReferee() {
        try {
            if (!mainReferees.contains(choiceBox8.getValue())) {
                mainReferees.add(choiceBox8.getValue());
            }
            comboMainReferee.setItems(mainReferees);

        } catch (Exception e) {

        }
    }

    @FXML
    ComboBox comboTeams;

    public void addTeam() {
        try {
            if (!teams.contains(choiceBox3.getValue())) {
                teams.add(choiceBox3.getValue());
            }
            comboTeams.setItems(teams);
        } catch (Exception e) {

        }
    }

    @FXML
    ComboBox comboAsso;
    public void addAsso() {
        try {
            if (!representatives.contains(choiceBox4.getValue())) {
                representatives.add(choiceBox4.getValue());
            }
            comboAsso.setItems(representatives);
        } catch (Exception e) {

        }
    }
    public void createSeason(ActionEvent actionEvent) {
        if(check()) {
            assertInfo();
            for(String mainReferee:arrayMainRefereesChosen){
                arrayRefereesChosen.add(mainReferee);
            }

            initResult=clientController.addSeasonToLeague(leaguesChosen, yearChosen, scoreChosen, gameChosen, arrayTeamsChosen, arrayRefereesChosen, arrayrepresentativesChosen);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, initResult+"\n", ButtonType.CLOSE);
            alert.setHeaderText("INIT SEASON");
            alert.showAndWait();
            try {
                homePage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean check(){
        if(comboLeague.getItems().size()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose league\n", ButtonType.CLOSE);
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;
        }
        if(comboReferee.getItems().size()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose referees\n", ButtonType.CLOSE);
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;

        }
        if(comboReferee.getItems().size()<comboTeams.getItems().size()/2*3){
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose more referees or less teams\n", ButtonType.CLOSE);
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;

        }
        if(comboMainReferee.getItems().size()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose main referees\n", ButtonType.CLOSE);
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;

        }
        if(comboMainReferee.getItems().size()<comboTeams.getItems().size()/2){
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose more main referees ot less teams\n", ButtonType.CLOSE);
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;

        }
        if(comboTeams.getItems().size()<2){
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose at least 2 teams\n", ButtonType.CLOSE);
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;

        }
        if(comboTeams.getItems().size()%2==1) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose even number of teams\n", ButtonType.CLOSE);
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;
        }
        if(comboTeams.getItems().size()!=numOfTeams) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose "+numOfTeams+" teams\n", ButtonType.CLOSE);
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;
        }
        if(comboAsso.getItems().size()==0){
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose asso\n", ButtonType.CLOSE);
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;
        }
        if(comboAsso.getItems().size()<comboTeams.getItems().size()/2){
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose more asso or less teams\n", ButtonType.CLOSE);
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;
        }
        if(choiceBox5.getValue().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose year\n", ButtonType.CLOSE);
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;

        }
        String league=(String)comboLeague.getItems().get(0);
        //ArrayList<Integer> seasonCreatedYears=systemManagement.getAllSeasonYears(league);
        ArrayList<Integer> seasonCreatedYears= clientController.getAllSeasonYears(league);
        if(seasonCreatedYears.contains(Integer.parseInt(choiceBox5.getValue()))) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "this season year is already created in this league\n", ButtonType.CLOSE);
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;
        }
        if(choiceBox6.getValue().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose game policy\n", ButtonType.CLOSE);
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;

        }
        if(choiceBox7.getValue().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING, "must choose score policy\n", ButtonType.CLOSE);
            alert.setHeaderText("Incorrect fill");
            alert.showAndWait();
            return false;

        }
        return true;

    }

    public void assertInfo() {
        for(String league:league){
            leaguesChosen=league;
        }
        for(String referee:referees){
            arrayRefereesChosen.add(referee);
        }
        for(String mainReferee:mainReferees){
            arrayMainRefereesChosen.add(mainReferee);
        }

        for(String team:teams){
            arrayTeamsChosen.add(team);
        }
        for(String representative:representatives){
            arrayrepresentativesChosen.add(representative);
        }
        if(!choiceBox5.getValue().equals("")) {
            yearChosen = Integer.parseInt(choiceBox5.getSelectionModel().getSelectedItem());
        }
        if(!choiceBox6.getValue().equals("")) {
            gameChosen = choiceBox6.getSelectionModel().getSelectedItem();
        }
        if(!choiceBox7.getValue().equals("")) {
            scoreChosen = choiceBox7.getSelectionModel().getSelectedItem();
        }
    }

    public void gamePolicyInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "3 Game Policies:\n\n" +
                "1. One round of games between all teams\n" +
                "2. Two round of games between all teams- on both stadiums\n" +
                "3. Random two rounds- can't know the order of games", ButtonType.CLOSE);
        alert.setHeaderText("Game Policies Information");
        alert.showAndWait();

    }

    public void scorePolicyInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "2 Score Policies:\n\n" +
                "1. Regular- 3 points for win, 1 points for draw, 0 points for lose\n" +
                "this policy gives preference to high different goals\n\n" +
                "2. Goal- 2 points for win, 1 points for draw, 0 points for lose\n" +
                "this policy gives preference to more goals", ButtonType.CLOSE);        alert.setHeaderText("Score Policies Information");
        alert.showAndWait();
    }



    public void checkDetailsFilled() throws IOException {

        submit.setFocusTraversable(false);

        if(timeTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter the time of the event.");
            return;
        }


        if(playerNameTF.getText().isEmpty()) { //the user didn't enter the player's name.
            showAlert(Alert.AlertType.ERROR, "Form Error!", "You must enter the player whose involved in the event.");
            submit.setFocusTraversable(false);
            return;
        }

        boolean isSelected = (whichTeamCB.getValue() != null );
        if(!isSelected) { //the user didn't choose which team is involved with this event.
            showAlert(Alert.AlertType.ERROR, "Form Error!", "You must choose a team in order to submit the form.");
            submit.setFocusTraversable(false);
            return;
        }

        isSelected = (eventTypeCB.getValue() != null );
        if(!isSelected) { //the user didn't choose the type of event.
            showAlert(Alert.AlertType.ERROR, "Form Error!", "You must choose the event type in order to submit the form.");
            submit.setFocusTraversable(false);
            return;
        }

        submit.setFocusTraversable(false);
        checkDetailsCorrect();
    }

    @FXML
    public void checkDetailsCorrect() throws IOException {

        submit.setFocusTraversable(false);

        String playerName = playerNameTF.getText();
        String time = timeTF.getText();
        int gameTime = Integer.parseInt(time);
        String type = eventTypeCB.getValue();
        String team = whichTeamCB.getValue();

        //checks validation of the time entered:
        if (gameTime < 0 || gameTime > 120) { // out of the game time
            showAlert(Alert.AlertType.ERROR, "Form Error!", "The event time must be between 0-120 minutes. Please try again.");
            return;
        }

        //everything's good
        boolean addedSuccessfully = clientController.addGameEvent(type, time, playerName, team);

        if (addedSuccessfully) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Event added !");
            alert.setContentText("The event you entered was added successfully to the game's events list.");
            alert.showAndWait();
           // goToLanding(); ////////////////////////// ??????????????????????????????? ///////////////////////////////////                ?????????????????????????????????????
            homePage();
        }

        else { //no active game at the moment
            showAlert(Alert.AlertType.ERROR, "Form Error!", "There is no active game at the moment that you can edit. Please try again later.");
            return;
        }
    }


    public void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

}
