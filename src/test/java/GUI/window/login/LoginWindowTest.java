package GUI.window.login;

import GUI.GUITest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class LoginWindowTest extends GUITest {
    private LoginWindow loginWindow;

    @Override
    @BeforeEach
    public void setUp() throws SQLException {
        loginWindow = new LoginWindow();
        loginWindow.loadFXML();
    }

    @Test
    public void testGetController() {
        LoginController result = loginWindow.getController();
        Assertions.assertNotNull(result);
    }

    @Test
    public void testGetFxmlPath() {
        assertEquals("./fxmlForms/loginFXML/loginForm.fxml", loginWindow.getFxmlPath());
    }
}
