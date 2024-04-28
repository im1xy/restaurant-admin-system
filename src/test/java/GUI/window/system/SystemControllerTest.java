package GUI.window.system;

import GUI.GUITest;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SystemControllerTest extends GUITest {
    private SystemWindow systemWindow;
    private SystemController systemController;
    private Parent systemParent;

    @Override
    @BeforeEach
    public void setUp() throws SQLException {
        super.setUp();
        systemWindow = new SystemWindow();
        systemParent = systemWindow.loadFXML();
        systemController = systemWindow.getController();
    }

    @Test
    public void testLogout() {
        Button logout = from(systemParent).lookup("#logoutButton").query();
        assertEquals(logout.getText(), "Logout");
    }

    @Test
    public void testLoadDashboard() {
        systemController.loadDashboard();

        AnchorPane pane = from(systemParent).lookup("#dashboardForm").query();
        assertNotNull(pane);
    }

    @Test
    public void testLoadInventory() {
        systemController.loadInventory();

        AnchorPane pane = from(systemParent).lookup("#inventoryForm").query();
        assertNotNull(pane);
    }

    @Test
    public void testLoadMenuPreview() {
        systemController.loadMenuPreview();

        AnchorPane pane = from(systemParent).lookup("#menuPreviewForm").query();
        assertNotNull(pane);
    }

    @Test
    public void testLoadMenuUpdate() {
        systemController.loadMenuUpdate();

        AnchorPane pane = from(systemParent).lookup("#menuUpdateForm").query();
        assertNotNull(pane);
    }

    @Test
    public void testLoadOrders() {
        systemController.loadOrders();

        AnchorPane pane = from(systemParent).lookup("#ordersForm").query();
        assertNotNull(pane);
    }
}
