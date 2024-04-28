package GUI.panel.dashboard;

import GUI.GUITest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DashboardPanelTest extends GUITest {

    private DashboardPanel dashboardPanelUnderTest;

    @Override
    @BeforeEach
    public void setUp() throws SQLException {
        super.setUp();
        dashboardPanelUnderTest = new DashboardPanel();
        dashboardPanelUnderTest.load();
    }

    @Test
    public void testGetController() {
        assertNotNull(dashboardPanelUnderTest.getController());
    }

    @Test
    public void testGetFxmlPath() {
        assertEquals("./fxmlForms/systemFXML/dashboard.fxml", dashboardPanelUnderTest.getFxmlPath());
    }
}
