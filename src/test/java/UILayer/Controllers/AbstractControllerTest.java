package UILayer.Controllers;

import UILayer.Main;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.BeforeClass;
import org.testfx.framework.junit.ApplicationTest;

public abstract class AbstractControllerTest extends ApplicationTest {
    @BeforeClass
    public static void setUpClass() throws Exception {
        ApplicationTest.launch(Main.class);
    }

    @Override
    public void start(Stage stage) {
        stage.show();
    }
}
