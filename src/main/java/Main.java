import GUI.window.login.LoginWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.loadFXML();
        loginWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
