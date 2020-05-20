package UILayer.Controllers;

import UILayer.Main;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

public class MyAppControllerTest extends AbstractControllerTest {
    private Button addAssent;


    @Before
    public void setUp()
    {
        addAssent = lookup("#addAssent").queryButton();

    }


    @Test
    public void testButtons(){
        verifyThat("#addAssent", hasText("add assent"));

    }
}