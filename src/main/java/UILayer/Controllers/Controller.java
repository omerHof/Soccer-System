package UILayer.Controllers;

import UILayer.ClientController;
import UILayer.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    protected static String userName;
    protected static String userType;
    protected static ArrayList<String> newNotifications;
    //DBLayer db = new DBLayer();
    protected static ClientController clientController = new ClientController();


    /**
     *  close the program with confirming
     *  close the servers
     */
    protected void closeProgram() {
        Stage s = Main.getStage();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit the system?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Leaving so soon?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            clientController.closeProgram();
            s.close();
        }
    }


    @FXML
    protected void goToLanding() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Landing.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        LandingController lc = fxmlLoader.getController();


        Main.setStage(s);
        s.show();
    }

    @FXML
    public void homePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/HomePage.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        HomePageController cc = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }

    @FXML
    protected void goToLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Login.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);

        s.setScene(scene);
        LoginController lg = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }



    @FXML
    protected void goToSignUp() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/SignUp.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        SignUpController suc = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }
    @FXML
    public void goToPlayers() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Players.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        PlayersController pc = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }
    @FXML
    public void goToCoaches() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Coaches.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        CoachesController cc = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }
    @FXML
    public void goToTeams() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Teams.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        TeamsController cc = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }
    @FXML
    public void goToProfile() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Profile.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        ProfileController cc = fxmlLoader.getController();
        Main.setStage(s);
        s.show();
    }

    @FXML
    public void goToEditReport() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Report.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        ReportController cc = fxmlLoader.getController();
        Main.setStage(s);
        s.show();
    }
    public void goToShowLog() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/openLogFile.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        LogController cc = fxmlLoader.getController();
        Main.setStage(s);
        s.show();
    }
    public void goToShowErrorLog() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/openErrorLogFile.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        ErrorLogController cc = fxmlLoader.getController();
        Main.setStage(s);
        s.show();
    }


    public void openMyApps() throws IOException {
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(getClass().getResource("/Views/MyApp.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        MyAppController pp = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }

    public void openAddSeason() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/addSeason.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        SeasonController pp = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }


    public void openNewTeam() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/addTeam.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        OpenTeamController pp = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }

    public void addAssent() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/addAssent.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        TeamOwnerController pp = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }

    public void removeAssent(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/removeAssent.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        TeamOwnerController pp = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }

    public void goToOpenPlayerPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/addPlayerPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        OpenPlayerPageController pp = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }

    public void openTeamPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/addTeamPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        OpenTeamController pp = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }
    public void openPlayerPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/addPlayerPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        PlayersController pp = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }

    public void openCoachPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/addCoachPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        CoachesController pp = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }


    public void addGameEvent() throws IOException {

        /*// currUser !!1! ?????
        AssociationRepresentative asso = new AssociationRepresentative("", "", "" , ""); // /////////// ?????????? //////////
        Game toAdd = asso.findActiveGame();

        if (toAdd != null ){
            showAlert(Alert.AlertType.ERROR, "Illegal action!", "None of your games is active at the moment. Please try again later.");
        }*/


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/addGameEvent.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        SeasonController sc = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }

    public void watchGamesList() throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/watchGamesList.fxml"));
        Parent root = fxmlLoader.load();
        Stage s = Main.getStage();
        Scene scene = new Scene(root);
        s.setScene(scene);
        watchGamesController sc = fxmlLoader.getController();

        Main.setStage(s);
        s.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    protected void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
