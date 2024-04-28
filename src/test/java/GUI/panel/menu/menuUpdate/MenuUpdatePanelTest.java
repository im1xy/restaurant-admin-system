package GUI.panel.menu.menuUpdate;

import GUI.GUITest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MenuUpdatePanelTest extends GUITest {

    private MenuUpdatePanel menuUpdatePanelUnderTest;

    @Override
    @BeforeEach
    public void setUp() throws SQLException {
        super.setUp();
        menuUpdatePanelUnderTest = new MenuUpdatePanel();
        menuUpdatePanelUnderTest.load();
    }

    @Test
    public void testGetController() {
        assertNotNull(menuUpdatePanelUnderTest.getController());
    }

    @Test
    public void testGetFxmlPath() {
        assertEquals("./fxmlForms/systemFXML/menuUpdate.fxml", menuUpdatePanelUnderTest.getFxmlPath());
    }
}
