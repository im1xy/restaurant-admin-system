package GUI.panel.menu.menuUpdate;

import DB.SQL.menu.MenuSQL;
import LoggerManager.LoggerManager;
import dataObjects.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MenuUpdateController implements Initializable {
    @FXML private AnchorPane menuUpdateForm;
    @FXML private TextField productNameInput;
    @FXML private ComboBox<String> typeComboBox;
    @FXML private TextField priceInput;
    @FXML private ComboBox<String> statusComboBox;
    @FXML private Button deleteButton;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button clearButton;
    @FXML private Button importButton;
    @FXML private ImageView imageView;
    @FXML private TableView<MenuItem> itemTable;
    @FXML private TableColumn<MenuItem, Integer> idColumn;
    @FXML private TableColumn<MenuItem, String> nameColumn;
    @FXML private TableColumn<MenuItem, String> typeColumn;
    @FXML private TableColumn<MenuItem, Double> priceColumn;
    @FXML private TableColumn<MenuItem, String> imagePathColumn;
    @FXML private TableColumn<MenuItem, Boolean> availabilityColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initItemTableProperties();
        initControls();
        loadMenu();
    }

    public void loadMenu() {
        itemTable.setItems(MenuSQL.getMenuItems());
    }

    private void initControls() {
        statusComboBox.setItems(getStatuses());
        typeComboBox.setItems(MenuSQL.getTypes());
    }


    public void addItem() {
        if (!isInputValid()) {
            showErrorAlert();
            return;
        }

        MenuItem item = buildMenuItem();
        MenuSQL.addItem(item);
        loadMenu();

        LoggerManager.logger.info("Added new Item to Database: " + item.getName());
    }

    public void updateSelectedItem() {
        MenuItem selectedItem = itemTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;
        }
        if (!isInputValid()) {
            showErrorAlert();
            return;
        }

        MenuSQL.updateItem(selectedItem.getId(), buildMenuItem());
        loadMenu();

        LoggerManager.logger.info("Updated Item at Database: " + selectedItem.getName());
    }

    public void deleteSelectedItem() {
        MenuItem selectedItem = itemTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;
        }

        try {
            MenuSQL.deleteItem(selectedItem.getId());
            loadMenu();
        }
        catch (SQLException e) {
            LoggerManager.logger.severe("Failed to Delete Item from Database");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Cannot delete this item, because it was bought at least once");
            alert.showAndWait();
        }

        LoggerManager.logger.info("Deleted Item from Database: " + selectedItem.getName());
    }

    public void loadItemToControls() {
        MenuItem selectedItem = itemTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;
        }

        productNameInput.setText(selectedItem.getName());
        priceInput.setText(String.valueOf(selectedItem.getPrice()));
        typeComboBox.setValue(selectedItem.getType());
        statusComboBox.setValue(selectedItem.getAvailability() ? "Available" : "Not Available");
        imageView.setImage(new Image(selectedItem.getImagePath()));
    }

    public void clearControls() {
        productNameInput.clear();
        priceInput.clear();
        statusComboBox.setValue("");
        typeComboBox.setValue("");
        imageView.setImage(null);
    }

    public void importImage() {
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg"));
        File file = openFile.showOpenDialog(null);

        if (file != null) {
            imageView.setImage(new Image(file.toURI().toString(), imageView.getFitHeight(), imageView.getFitWidth(), false, false));
        }
    }

    private MenuItem buildMenuItem() {
        String name = productNameInput.getText();
        String type = typeComboBox.getValue();
        Double price = Double.valueOf(priceInput.getText());
        String imagePath = imageView.getImage().getUrl();
        boolean availability = statusComboBox.getValue().equals("Available");

        return new MenuItem(null, name, type, price, imagePath, availability);
    }

    private void initItemTableProperties() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        imagePathColumn.setCellValueFactory(new PropertyValueFactory<>("imagePath"));
        availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));
    }

    private ObservableList<String> getStatuses() {
        ObservableList<String> statuses = FXCollections.observableArrayList();
        statuses.addAll(Arrays.asList("", "Not Available", "Available"));

        return statuses;
    }

    private boolean isInputValid() {
        return !(productNameInput.getText().isEmpty() ||
                priceInput.getText().isEmpty() ||
                typeComboBox.getSelectionModel().getSelectedItem().isEmpty() ||
                statusComboBox.getSelectionModel().getSelectedItem().isEmpty() ||
                imageView.getImage().getUrl().isEmpty());
    }

    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Incorrect Data Input");
        alert.showAndWait();
    }
}
