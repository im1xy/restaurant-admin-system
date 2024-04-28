package GUI.panel.orders;

import GUI.GUITest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrdersPanelTest extends GUITest {
    private OrdersPanel ordersPanelUnderTest;

    @Override
    @BeforeEach
    public void setUp() throws SQLException {
        super.setUp();
        ordersPanelUnderTest = new OrdersPanel();
        ordersPanelUnderTest.load();
    }

    @Test
    public void testGetController() {
        assertNotNull(ordersPanelUnderTest.getController());
    }

    @Test
    public void testGetFxmlPath() {
        assertEquals("./fxmlForms/systemFXML/orders.fxml", ordersPanelUnderTest.getFxmlPath());
    }
}
