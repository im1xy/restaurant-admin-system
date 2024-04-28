package GUI.window.system;

import GUI.GUITest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class SystemWindowTest extends GUITest {
    private SystemWindow systemWindow;

    @Override
    @BeforeEach
    public void setUp() throws SQLException {
        super.setUp();
        systemWindow = new SystemWindow();
        systemWindow.loadFXML();
    }

    @Test
    public void testGetController() {
        SystemController result = systemWindow.getController();
        Assertions.assertNotNull(result);
    }

    @Test
    public void testGetFxmlPath() {
        assertEquals("./fxmlForms/systemFXML/mainForm.fxml", systemWindow.getFxmlPath());
    }
}
