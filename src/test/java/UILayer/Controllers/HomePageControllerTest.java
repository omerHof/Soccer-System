package UILayer.Controllers;

import UILayer.Main;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;

public class HomePageControllerTest extends AbstractControllerTest {
    TableView<String[]>  tableView;


    @Before
    public void setUp()
    {
        tableView = lookup("#tab1").queryTableView();

    }

    @Test
    public void testButtons(){
        verifyThat("#tab1", Node::isVisible);

    }

}