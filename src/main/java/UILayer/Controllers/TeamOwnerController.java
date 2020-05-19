package UILayer.Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class TeamOwnerController extends Controller {

    @FXML
    public BorderPane navBar;

    @FXML
    public ChoiceBox choseAssentType;

    @FXML
    public TextField choseAssentName;

    @FXML
    public JFXButton add;

    public void addAssentToTeam(ActionEvent actionEvent) {
        String assentType = choseAssentType.getValue().toString();
        String assentName = choseAssentName.getText();
        String result = clientController.AddAssent(assentType, assentName);
        if (result.equals("null")){
            showAlert(Alert.AlertType.ERROR, "ERROR!", "There is no assent called '" + assentName + "' in the system!");
        }
        if (result.equals("team is closed")){
            showAlert(Alert.AlertType.ERROR, "ERROR!", "Your team is closed! You can't make any changes!");
        }
        if (result.equals("already added")){
            showAlert(Alert.AlertType.ERROR, "ERROR!", assentName+ " has already added to your team!");
        }
        if (result.equals("money isn't growing on the trees!")){
            showAlert(Alert.AlertType.ERROR, "ERROR!", "Unfortunately, Your team's budget is to low. get some money honey");
        }
        if (result.equals("added successfully")){
            showAlert(Alert.AlertType.INFORMATION, "SUCCESS!", assentName + " was added to the team successfully");
        }
    }
}
