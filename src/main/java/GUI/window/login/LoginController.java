package GUI.window.login;

import DB.SQL.login.LoginSQL;
import GUI.window.system.SystemController;
import LoggerManager.LoggerManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import GUI.window.system.SystemWindow;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML private AnchorPane sideForm;
    @FXML private AnchorPane loginForm;
    @FXML private TextField usernameInput;
    @FXML private PasswordField passwordInput;
    @FXML private Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void login() {
        if (!isInputValid()) {
            showErrorAlert();
            return;
        }

        String username = usernameInput.getText();
        String password = passwordInput.getText();

        if (LoginSQL.isDataInDatabase(username, password)) {
            loginSuccess();
            this.closeWindow();
        }
        else {
            loginFailure();
        }
    }

    public boolean isInputValid() {
        return !(usernameInput.getText().isEmpty() ||
                passwordInput.getText().isEmpty());
    }

    private void loginSuccess() {
        SystemWindow adminSystemWindow = new SystemWindow();
        adminSystemWindow.loadFXML();
        adminSystemWindow.show();
    }

    private void loginFailure() {
        this.showErrorAlert();
    }

    private void closeWindow() {
        ((Stage) loginForm.getScene().getWindow()).close();
    }

    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Incorrect Username/Password");
        alert.showAndWait();
    }
}
