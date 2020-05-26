package UILayer.Controllers;

import ServiceLayer.UserManagement;

import UILayer.Main;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
//import Views.Sign


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController extends Controller {

    private UserManagement userManagement;

    private String username;
    private String fullname;
    private String password;
    private String email;

    private String qualification;
    private String courtRole;
    private String teamRole;
    private String managementPassword;
    private LocalDate birthDate;

    private String userType;
    private String generatorType;

    private ObservableList<String> coachRoleOptions = FXCollections.observableArrayList();
    private ObservableList<String> refereeRoleOptions = FXCollections.observableArrayList();
    private ObservableList<String> playerCourtRoleOptions = FXCollections.observableArrayList();


    //all type buttons:
    @FXML
    JFXButton refereeBTN;
    @FXML
    JFXButton coachBTN;
    @FXML
    JFXButton fanBTN;
    @FXML
    JFXButton teamOwnerBTN;
    @FXML
    JFXButton managerBTN;
    @FXML
    JFXButton administratorBTN;
    @FXML
    JFXButton associationRepresentativeBTN;
    @FXML
    JFXButton playerBTN;


    //mutual fields for all users.
    @FXML
    TextField emailTF;
    @FXML
    TextField usernameTF;
    @FXML
    TextField fullnameTF;
    @FXML
    PasswordField passwordTF;

    //specific fields for each user.
    @FXML
    DatePicker birthdateDP;
    @FXML
    Label birthdateLabel;
    @FXML
    Label teamRoleLabel;
    @FXML
    ChoiceBox coachTeamRoleCB;
    @FXML
    Label courtRoleLabel;
    @FXML
    ChoiceBox playerCourtRoleCB;

    @FXML
    Label qualificationLabel;
    @FXML
    ChoiceBox refereeQualificationCB;

    @FXML
    TextField managePassTF;
    @FXML
    Label managePassLabel;

    @FXML
    JFXButton submit;
    @FXML
    JFXButton backBTN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Stage s = Main.getStage();
        s.setOnCloseRequest(e->{
            e.consume();
            closeProgram();
        });

        refereeBTN.setFocusTraversable(false);
        fanBTN.setFocusTraversable(false);
        playerBTN.setFocusTraversable(false);
        coachBTN.setFocusTraversable(false);
        teamOwnerBTN.setFocusTraversable(false);
        managerBTN.setFocusTraversable(false);
        administratorBTN.setFocusTraversable(false);
        associationRepresentativeBTN.setFocusTraversable(false);
        backBTN.setFocusTraversable(false);


        userManagement = new UserManagement();

        coachRoleOptions.add("Head Coach");
        coachRoleOptions.add("Assistant Coach");
        coachRoleOptions.add("Fitness Coach");
        coachTeamRoleCB.setItems(coachRoleOptions);

        refereeRoleOptions.add("Var referee");
        refereeRoleOptions.add("Linesman referee");
        refereeQualificationCB.setItems(refereeRoleOptions);

        playerCourtRoleOptions.add("GoalKeeper");
        playerCourtRoleOptions.add("Defender");
        playerCourtRoleOptions.add("MidFielder");
        playerCourtRoleOptions.add("Striker");
        playerCourtRoleCB.setItems(playerCourtRoleOptions);

        DatePicker birthdateDP = new DatePicker();

        StackPane root = new StackPane();
        root.getChildren().add(birthdateDP);
        s.setScene(new Scene(root, 500, 650));
        s.show();

        try {
            allExtraFieldsOff();
            submit.setDisable(true); //until the basic details are full by the user.

            //checkDetailsFilled();

            } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void typePlayer() throws IOException {

        hideAllUnderLines();
        playerBTN.setUnderline(true);

        allExtraFieldsOff();

        birthdateDP.setVisible(true);
        birthdateLabel.setVisible(true);
        playerCourtRoleCB.setVisible(true);
        courtRoleLabel.setVisible(true);

        userType = "Player";

        submit.setDisable(false); //now the user can try pressing the submit button.
        premiumAlert(); //lets the user know that his registration depends on the representative's approve.

    }

    @FXML
    public void typeCoach() throws IOException {

        hideAllUnderLines();

        coachBTN.setUnderline(true);
        allExtraFieldsOff();

        coachTeamRoleCB.setVisible(true);
        teamRoleLabel.setVisible(true);

        userType = "Coach";

        submit.setDisable(false); //now the user can try pressing the submit button.
        premiumAlert(); //lets the user know that his registration depends on the representative's approve.

    }

    @FXML
    public void typeReferee() throws IOException {

        hideAllUnderLines();

        refereeBTN.setUnderline(true);
        allExtraFieldsOff();

        refereeQualificationCB.setVisible(true);
        qualificationLabel.setVisible(true);

        userType = "Referee";

        submit.setDisable(false); //now the user can try pressing the submit button.

        premiumAlert(); //lets the user know that his registration depends on the representative's approve.
    }

    private void premiumAlert() {
        showAlert(Alert.AlertType.INFORMATION, "Form Information", "Please notice that your registration is subject to approval of one of our association representatives.");
    }


    @FXML
    public void allExtraFieldsOff() throws IOException {

        birthdateDP.setVisible(false);
        birthdateLabel.setVisible(false);
        coachTeamRoleCB.setVisible(false);
        teamRoleLabel.setVisible(false);
        playerCourtRoleCB.setVisible(false);
        courtRoleLabel.setVisible(false);
        refereeQualificationCB.setVisible(false);
        playerCourtRoleCB.setVisible(false);
        coachTeamRoleCB.setVisible(false);
        qualificationLabel.setVisible(false);
        managePassTF.setVisible(false);
        managePassLabel.setVisible(false);

        submit.setDisable(false); //now the user can try to push on the submit button.
        submit.setFocusTraversable(false);

    }


    public void checkDetailsFilled() throws IOException {

        submit.setFocusTraversable(false);

        if(fullnameTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter your name.");
//            submit.setFocusTraversable(false);
            return;
        }


        if(emailTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter your email address.");
            submit.setFocusTraversable(false);
            return;
        }

        if(usernameTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a username.");
            submit.setFocusTraversable(false);
            return;
        }

        if(passwordTF.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter a password.");
            submit.setFocusTraversable(false);
            return;
        }

        submit.setFocusTraversable(false);
        checkDetailsCorrect();
    }

    @FXML
    public void checkDetailsCorrect() throws IOException {

        submit.setFocusTraversable(false);

        username = usernameTF.getText();
        fullname = fullnameTF.getText();
        password = passwordTF.getText();
        email = emailTF.getText();

        //checks validation of the basic details entered:
        if (!fullname.matches("^[a-zA-Z ]*$|| ")) { //check full name validation (only letters)
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Your name must contains only characters. Please try again.");
            return;
        }

        if (!fullname.contains(" ")) { //not full name
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter your full name.");
            return;

        }

        String regex = "^(.+)@(.+)$";//
        //String regexxx = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) { //invalid email address.
            showAlert(Alert.AlertType.ERROR, "Form Error!", "The email address you entered is not valid. Please try again.");
            submit.setFocusTraversable(false);
            return;
        }

        if (!username.matches("^[a-zA-Z0-9]*$")) { //check username validation (letters and nums)
            showAlert(Alert.AlertType.ERROR, "Form Error!", "The username you chose must contains only characters and digits. Please try again.");
            submit.setFocusTraversable(false);
            return;
        }

        if (!password.matches("^[a-zA-Z0-9]*$")) { //check password validation  (letters and nums)
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Your password must contains only characters and digits. Please try again.");
            submit.setFocusTraversable(false);
            return;
        }

        if (password.length() < 6) { //check password validation  (letters and nums)
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Your password must be at least 6 characters. Please try again.");
            submit.setFocusTraversable(false);
            return;
        }

        //creates the correct user generator, to send through the service layer.
        if (userType.equals("Player") || userType.equals("Coach") || userType.equals("Manager") || userType.equals("TeamOwner") || userType.equals("Referee"))
            generatorType = "PremiumUserGenerator";

        else if (userType.equals("AssociationRepresentative") || userType.equals("Administrator"))
            generatorType = "ManagementUserGenerator";

        else // a fan
            generatorType = "SimpleUserGenerator";


        // gets details by user type:
        String userCreatedMessage;
        boolean isSelected;

        if (userType.equals("Referee")){

            //checks whether all the fields are filled or not:
            isSelected = (refereeQualificationCB.getValue() != null );

            if(!isSelected) { //the user didn't choose any of the options.
                showAlert(Alert.AlertType.ERROR, "Form Error!", "You must choose a qualification in order to submit the form.");
                submit.setFocusTraversable(false);
                return;
            }
            qualification = refereeQualificationCB.getValue().toString();

            // checks whether the user exists in the system already.
            userCreatedMessage = clientController.signUp(username,password, "", userType, fullname, email, null, qualification, "", "", generatorType);
        }

        else if (userType.equals("Player")){
            //checks whether all the fields are filled or not:
            isSelected = (playerCourtRoleCB.getValue() != null );

            if(!isSelected) { //the user didn't choose any of the options.
                showAlert(Alert.AlertType.ERROR, "Form Error!", "You must choose a court role in order to submit the form.");
                submit.setFocusTraversable(false);
                return;
            }
            courtRole = playerCourtRoleCB.getValue().toString();

            //birthdateDP.setOnAction(event -> { // gets the value of the date picker
                birthDate = birthdateDP.getValue();
            //});

            if(birthDate==null){
                showAlert(Alert.AlertType.ERROR, "Form Error!", "You must choose your birth date in order to submit the form.");
                submit.setFocusTraversable(false);
                return;
            }
            userCreatedMessage = clientController.signUp(username,password, "", userType, fullname, email, birthDate, "", courtRole, "", generatorType);
        }

        else if (userType.equals("Coach")){

            isSelected = (coachTeamRoleCB.getValue() != null );

            if(!isSelected) { //the user didn't choose any of the options.
                showAlert(Alert.AlertType.ERROR, "Form Error!", "You must choose your team role in order to submit the form.");
                submit.setFocusTraversable(false);
                return;
            }
            teamRole = coachTeamRoleCB.getValue().toString();
            userCreatedMessage = clientController.signUp(username,password, "", userType, fullname, email, null, "", "", teamRole, generatorType);
        }

        else if ((userType.equals("AssociationRepresentative")) || (userType.equals("Administrator"))){

            managementPassword = managePassTF.getText();

            if(managementPassword.equals("")){
                showAlert(Alert.AlertType.ERROR, "Form Error!", "You must enter your management password in order to submit the form.");
                submit.setFocusTraversable(false);
                return;
            }
            userCreatedMessage = clientController.signUp(username,password, managementPassword, userType, fullname, email, null, "", "", "", generatorType);
        }

        else { // fan / manager / teamowner
            userCreatedMessage = clientController.signUp(username,password, "", userType, fullname, email, null, "", "", "", generatorType);
        }


        if(userCreatedMessage.equals("exist")){
            showAlert(Alert.AlertType.ERROR, "Form Error!", "The username you choose is already exist. Please choose a new one.");
            submit.setFocusTraversable(false);
            return;
        }

        else if(userCreatedMessage.equals("association representative")){
            showAlert(Alert.AlertType.ERROR, "Form Error!", "The association representative didn't approve your register request. Please try again later.");
            submit.setFocusTraversable(false);
            return;
        }

        else { //everything's good
            super.userType = this.userType; //updates attributes of the main controller
            super.userName = this.username;

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Welcome aboard !");
                alert.setContentText("Your user was added successfully to the system.");
                alert.showAndWait();

//            showAlert(Alert.AlertType.CONFIRMATION, "Form Information", "Welcome aboard ! Your user was added successfully to the system.");

            usernameTF.clear();
            passwordTF.clear();
            fullnameTF.clear();
            emailTF.clear();

            clientController.sendNotification("welcome to Soccer Association System!",username);
            homePage();
        }
    }


    /*private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }*/


    public void typeFan() {

        hideAllUnderLines();

        userType = "Fan";
        fanBTN.setUnderline(true);


        try {
            allExtraFieldsOff();
        }
        catch (IOException i){

        }
    }

    public void typeTeamOwner() {

        hideAllUnderLines();
        userType = "TeamOwner";
        teamOwnerBTN.setUnderline(true);

        try {
            allExtraFieldsOff();
            premiumAlert(); //lets the user know that his registration depends on the representative's approve.

        }
        catch (IOException i){

        }
    }

    public void typeManager() {

        hideAllUnderLines();

        userType = "Manager";
        managerBTN.setUnderline(true);
        premiumAlert(); //lets the user know that his registration depends on the representative's approve.

        try {
            allExtraFieldsOff();
        }
        catch (IOException i){

        }
    }

    public void typeAdministrator() {

        hideAllUnderLines();
        administratorBTN.setUnderline(true);

        try {
            allExtraFieldsOff();
        }
        catch (IOException i){

        }

        managePassTF.setVisible(true);
        managePassLabel.setVisible(true);

        userType = "Administrator";

        submit.setDisable(false); //now the user can try pressing the submit button.
    }

    public void typeAssociationRepresentative() {

        hideAllUnderLines();
        associationRepresentativeBTN.setUnderline(true);
        //associationRepresentativeBTN.setFocusTraversable(true);

        userType = "AssociationRepresentative";

        try {
            allExtraFieldsOff();
        }
        catch (IOException i){

        }

        managePassTF.setVisible(true);
        managePassLabel.setVisible(true);
        submit.setDisable(false); //now the user can try pressing the submit button.
    }

    private void hideAllUnderLines(){

        refereeBTN.setUnderline(false);
        fanBTN.setUnderline(false);
        playerBTN.setUnderline(false);
        coachBTN.setUnderline(false);
        teamOwnerBTN.setUnderline(false);
        managerBTN.setUnderline(false);
        administratorBTN.setUnderline(false);
        associationRepresentativeBTN.setUnderline(false);
    }
}
