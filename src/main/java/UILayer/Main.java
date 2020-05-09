package UILayer;


import UILayer.Controllers.HomePageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
    private static Stage primaryStage;
    Parent root;
    //private static MyViewModel viewModel;

    //public static MyViewModel getViewModel(){return viewModel;}




    @Override
    public void start(Stage primaryStage) throws Exception{

        setStage(primaryStage);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/HomePage.fxml"));
        root = fxmlLoader.load();
        Image icon = new Image(getClass().getResourceAsStream("/Views/soccerLogo.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("SOCCER SYSTEM 2020");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(700);

        HomePageController lc = fxmlLoader.getController();

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void setStage(Stage stage) {
        Main.primaryStage = stage;
    }

    public static Stage getStage() {
        return primaryStage;
    }
}
