package GUI.window.system;

import GUI.panel.dashboard.DashboardPanel;
import GUI.panel.inventory.InventoryPanel;
import GUI.panel.menu.menuPreview.MenuPreviewPanel;
import GUI.panel.menu.menuUpdate.MenuUpdatePanel;
import GUI.panel.orders.OrdersPanel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import GUI.window.login.LoginWindow;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SystemController implements Initializable {
    @FXML private AnchorPane mainForm;
    @FXML private AnchorPane panelsPanel;

    @FXML private Button dashboardButton;
    @FXML private Button menuButton;
    @FXML private Button inventoryButton;
    @FXML private Button ordersButton;
    @FXML private Button logoutButton;
    @FXML private Button updateMenuButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDashboard();
    }

    public void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setContentText("Are you sure to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                LoginWindow loginWindow = new LoginWindow();
                loginWindow.loadFXML();
                loginWindow.show();
                this.closeWindow();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDashboard() {
        clearPanel();
        loadNewPanel(new DashboardPanel().load());
    }

    public void loadInventory() {
        clearPanel();
        loadNewPanel(new InventoryPanel().load());
    }

    public void loadMenuPreview() {
        clearPanel();
        loadNewPanel(new MenuPreviewPanel().load());
    }

    public void loadMenuUpdate() {
        clearPanel();
        loadNewPanel(new MenuUpdatePanel().load());
    }

    public void loadOrders() {
        clearPanel();
        loadNewPanel(new OrdersPanel().load());
    }

    private void loadNewPanel(AnchorPane pane) {
        panelsPanel.getChildren().add(pane);
    }

    private void clearPanel() {
        panelsPanel.getChildren().clear();
    }

    private void closeWindow() {
        ((Stage) mainForm.getScene().getWindow()).close();
    }
}
