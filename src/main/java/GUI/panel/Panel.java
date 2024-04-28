package GUI.panel;

import LoggerManager.LoggerManager;
import Mailer.Mailer;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.File;

public abstract class Panel {
    private FXMLLoader loader;
    protected abstract String getFxmlPath();
    public AnchorPane load() {
        try {
            loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource(getFxmlPath()));
            AnchorPane panel = loader.load();

            return panel;
        } catch (Exception e) {
            LoggerManager.logger.severe("Could Not Load Panel from: " + getFxmlPath());
            new Mailer().sendMessage("PANEL LOAD ERROR", "Failed to load Panel", new File("logINFO.txt"));;
            throw new RuntimeException(e);
        }
    }

    public <T> T getController() {
        return loader.getController();
    }
}
