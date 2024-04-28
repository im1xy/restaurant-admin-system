package GUI.panel.inventory;

import GUI.GUITest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InventoryPanelTest extends GUITest {

    private InventoryPanel inventoryPanelUnderTest;

    @Override
    @BeforeEach
    public void setUp() throws SQLException {
        super.setUp();
        inventoryPanelUnderTest = new InventoryPanel();
        inventoryPanelUnderTest.load();
    }

    @Test
    public void testGetController() {
        assertNotNull(inventoryPanelUnderTest.getController());
    }

    @Test
    public void testGetFxmlPath() {
        assertEquals("./fxmlForms/systemFXML/inventory.fxml", inventoryPanelUnderTest.getFxmlPath());
    }
}
