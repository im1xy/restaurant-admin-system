package GUI.window;

import LoggerManager.LoggerManager;
import Mailer.Mailer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public abstract class Window {
    protected Parent window;
    protected FXMLLoader loader;
    protected abstract String getFxmlPath();
    protected abstract Stage buildStage();

    public Parent loadFXML() {
        try {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource(getFxmlPath()));
            window = loader.load();

            return window;
        } catch (IOException e) {
            LoggerManager.logger.severe("Could Not Load Window from: " + getFxmlPath());
            new Mailer().sendMessage("WINDOW LOAD ERROR", "Failed to load Window", new File("logINFO.txt"));
            throw new RuntimeException(e);
        }
    }

    public int show() {
        buildStage().show();
        return 1;
    }

    public <T> T getController() {
        return loader.getController();
    }
}
