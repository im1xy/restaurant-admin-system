package GUI.panel.inventory;

import DB.SQL.inventory.InventorySQL;
import LoggerManager.LoggerManager;
import dataObjects.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {
    @FXML private AnchorPane inventoryForm;
    @FXML private TableView<Product> productsTable;
    @FXML private TableColumn<Product, String> productNameColumn;
    @FXML private TableColumn<Product, Integer> stockColumn;
    @FXML private TableView<Product> previewTable;
    @FXML private TableColumn<Product, String> productNamePreviewColumn;
    @FXML private TableColumn<Product, Integer> stockPreviewColumn;
    @FXML private TextField nameInput;
    @FXML private TextField stockInput;
    @FXML private Button addButton;
    @FXML private Button deleteButton;
    @FXML private Button resetButton;
    @FXML private Button loadToDatabaseButton;
    @FXML private Button clearButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initProductsTableProperties();
        initPreviewTableProperties();
        loadInventory();
    }

    public void loadInventory() {
        productsTable.setItems(InventorySQL.getInventoryList());
    }

    public void addProductToPreview() {
        if (!isInputValid()) {
            showErrorAlert();
            return;
        }

        String name = nameInput.getText();
        String stockStr = stockInput.getText();
        Product product = new Product(name, Integer.parseInt(stockStr));

        if (previewTable.getItems().contains(product)) {
            return;
        }

        previewTable.getItems().add(product);
    }

    public void resetPreviewTable() {
        previewTable.getItems().clear();
    }

    public void deleteSelectedProducts() {
        ObservableList<Product> selectedItems = previewTable.getSelectionModel().getSelectedItems();
        previewTable.getItems().removeAll(selectedItems);
    }

    public void clearInputs() {
        nameInput.clear();
        stockInput.clear();
    }

    public void loadToDatabase() {
        ObservableList<Product> productsList = previewTable.getItems();
        if (productsList.isEmpty()) {
            showErrorAlert();
            return;
        }

        for (Product product : productsList) {
            if (InventorySQL.isProductInDatabase(product)) {
                InventorySQL.updateProduct(product, InventorySQL.getProductStock(product));
            }
            else {
                InventorySQL.addProduct(product);
            }
        }

        resetPreviewTable();
        clearInputs();
        loadInventory();

        LoggerManager.logger.info("Updated Product Table in Database");
    }

    private void initPreviewTableProperties() {
        productNamePreviewColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        stockPreviewColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        previewTable.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
    }

    private void initProductsTableProperties() {
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    private boolean isInputValid() {
        return !(nameInput.getText().isEmpty() ||
                stockInput.getText().isEmpty());
    }

    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Incorrect Data Input");
        alert.showAndWait();
    }
}
