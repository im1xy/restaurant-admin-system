package GUI.panel.menu.menuPreview;

import DB.SQL.menu.MenuSQL;
import GUI.panel.menu.menuItem.MenuItemController;
import GUI.panel.menu.menuItem.MenuItemPanel;
import dataObjects.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuPreviewController implements Initializable {
    @FXML private AnchorPane menuPreviewForm;
    @FXML private GridPane menuGridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMenu();
    }

    public void loadMenu() {
        clearGridPane();

        ObservableList<MenuItem> itemList = FXCollections.observableArrayList();
        itemList.addAll(MenuSQL.getMenuItems());

        for (int i = 0; i < itemList.size(); i++) {
            MenuItemPanel menuItemPanel = new MenuItemPanel();
            AnchorPane card = menuItemPanel.load();
            MenuItemController controller = menuItemPanel.getController();
            controller.setCardInformation(itemList.get(i));
            menuGridPane.addRow(i, card);
        }
    }

    public void clearGridPane() {
        menuGridPane.getChildren().clear();
    }
}
