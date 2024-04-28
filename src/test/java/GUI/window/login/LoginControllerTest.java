package GUI.window.login;

import GUI.GUITest;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginControllerTest extends GUITest {
    private LoginWindow loginWindow;
    private LoginController loginController;
    private Parent loginParent;

    @Override
    @BeforeEach
    public void setUp() throws SQLException {
        loginWindow = new LoginWindow();
        loginParent = loginWindow.loadFXML();
        loginController = loginWindow.getController();
    }

    @Test
    public void testLogin() {
        TextField username = from(loginParent).lookup("#usernameInput").query();
        TextField password = from(loginParent).lookup("#passwordInput").query();

        assertFalse(loginController.isInputValid());

        username.setText("saralee");
        password.setText("zxcvb");

        assertTrue(loginController.isInputValid());
    }
}
