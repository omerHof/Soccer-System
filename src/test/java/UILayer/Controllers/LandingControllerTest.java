package UILayer.Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

public class LandingControllerTest extends AbstractControllerTest{
    JFXButton login;

    @Before
    public void setUp()
    {
        login = (JFXButton) lookup("#logInBTN").queryButton();

    }

    @Test
    public void testButtons() throws InterruptedException {
        clickOn("#logInBTN");
        TimeUnit.SECONDS.sleep(3);



    }

}