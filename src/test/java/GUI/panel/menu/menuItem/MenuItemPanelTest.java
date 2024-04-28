package GUI.panel.menu.menuItem;

import GUI.GUITest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MenuItemPanelTest extends GUITest {

    private MenuItemPanel menuItemPanelUnderTest;

    @Override
    @BeforeEach
    public void setUp() throws SQLException {
        menuItemPanelUnderTest = new MenuItemPanel();
        menuItemPanelUnderTest.load();
    }

    @Test
    public void testGetController() {
        assertNotNull(menuItemPanelUnderTest.getController());
    }

    @Test
    public void testGetFxmlPath() {
        assertEquals("./fxmlForms/systemFXML/menuItemCard.fxml", menuItemPanelUnderTest.getFxmlPath());
    }
}
