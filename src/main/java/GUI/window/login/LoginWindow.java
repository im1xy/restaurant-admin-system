package GUI.window.login;

import GUI.window.Window;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginWindow extends Window {
    @Override
    protected String getFxmlPath() {
        return "./fxmlForms/loginFXML/loginForm.fxml";
    }

    @Override
    protected Stage buildStage() {
        Stage stage = new Stage();
        stage.setTitle("Restaurant Admin System");
        stage.setScene(new Scene(window));
        stage.setResizable(false);

        return stage;
    }
}
