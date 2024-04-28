package GUI.window.system;

import GUI.window.Window;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SystemWindow extends Window {

    @Override
    protected String getFxmlPath() {
        return "./fxmlForms/systemFXML/mainForm.fxml";
    }

    protected Stage buildStage() {
        Stage stage = new Stage();
        stage.setTitle("Restaurant Admin System");
        stage.setMinWidth(1100);
        stage.setMinHeight(750);
        stage.setScene(new Scene(window));

        return stage;
    }
}
