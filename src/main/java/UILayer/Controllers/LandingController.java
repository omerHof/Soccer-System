package UILayer.Controllers;

import UILayer.Main;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LandingController extends Controller {



    //ArrayList<MenuItem> menuItems = new ArrayList<>();
    ObservableList<MenuItem> menuItems = FXCollections.observableArrayList();



    @FXML
    JFXButton profileButton;

    @FXML

    MenuButton notificationsList;

    @FXML
    JFXButton logInBTN;

    @FXML
    FontAwesomeIcon bell;

    @FXML
    JFXButton signUpBtn;

    @FXML
    JFXButton signOut;

    @FXML
    JFXButton myAppsBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Stage s = Main.getStage();
        s.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        if(userName!=null){
            newNotifications = clientController.getNotifications();
            for(String notification:newNotifications){
                MenuItem item = new MenuItem(notification);
                item.setOnAction(a->{ //lambda expression
                    notificationsList.getItems().remove(item);
                    if(notificationsList.getItems().size()==0){
                        Color c = Color.web("0xbbe0e0",1.0);
                        Paint paint = c;
                        bell.setFill(paint);
                    }
                    clientController.readNotification(notification);
                    newNotifications = clientController.getNotifications();
                });

                notificationsList.getItems().add(item);
            }


        }

        showUserButton();
    }

    public void showUserButton(){
        if(super.userName!=null){
            logInBTN.setVisible(false);
            signUpBtn.setVisible(false);
            notificationsList.setVisible(true);
            profileButton.setText(userName);
            profileButton.setVisible(true);
            signOut.setVisible(true);
            myAppsBtn.setVisible(true);

            if(newNotifications.size()>0){
                Color c = Color.RED;
                Paint paint = c;
                bell.setFill(paint);
            }
        }
    }

    @FXML
    public void hideUserButton() throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to log out from the system?", ButtonType.YES, ButtonType.NO);
        //alert.setHeaderText("Leaving so soon?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {

            logInBTN.setVisible(true);
            signUpBtn.setVisible(true);
            profileButton.setVisible(false);
            signOut.setVisible(false);
            myAppsBtn.setVisible(false);
            notificationsList.setVisible(false);
            super.userName = null;
            clientController.logOut();
            homePage();
        }
    }

    public void markAsRead(MouseEvent mouseEvent) {
        String readNotification = notificationsList.getText();
        notificationsList.getItems().remove(readNotification);
        if(notificationsList.getItems().size()==0){
            Color c = Color.web("0xbbe0e0",1.0);
            Paint paint = c;
            bell.setFill(paint);
        }
        clientController.readNotification(readNotification);
        newNotifications = clientController.getNotifications();

    }
}
