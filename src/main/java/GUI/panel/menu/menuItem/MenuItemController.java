package GUI.panel.menu.menuItem;

import dataObjects.MenuItem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuItemController implements Initializable {
    @FXML private AnchorPane mainForm;
    @FXML private Label nameLabel;
    @FXML private Label priceLabel;
    @FXML private Label typeLabel;
    @FXML private CheckBox availabilityCheckBox;
    @FXML private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        availabilityCheckBox.setMouseTransparent(true);
        availabilityCheckBox.setFocusTraversable(false);
    }

    public void setCardInformation(MenuItem menuItem) {
        nameLabel.setText(menuItem.getName());
        priceLabel.setText(String.valueOf(menuItem.getPrice()));
        typeLabel.setText(menuItem.getType());
        availabilityCheckBox.setSelected(menuItem.getAvailability());
        availabilityCheckBox.setText(menuItem.getAvailability() ? "Available" : "Not Available");
        imageView.setImage(new Image(menuItem.getImagePath(), imageView.getFitHeight(), imageView.getFitHeight(), false, true));
    }

    public void changeSelectionColor() {
        mainForm.setStyle("-fx-background-color: linear-gradient(to bottom right, rgba(2,0,36,1) 0%, rgba(16,16,193,1) 50%, rgba(0,212,255,1) 100%);");
    }

    public void resetSelectionColor() {
        mainForm.setStyle(null);
    }
}
