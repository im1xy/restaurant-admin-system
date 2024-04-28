package GUI.panel.orders;

import DB.SQL.orders.OrdersSQL;
import dataObjects.Order;
import dataObjects.OrderDetails;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class OrdersController implements Initializable {
    @FXML private AnchorPane ordersForm;
    @FXML private TableView<Order> orderTable;
    @FXML private TableColumn<Order, Integer> orderIdColumn;
    @FXML private TableColumn<Order, Double> totalColumn;
    @FXML private TableColumn<Order, Date> dateColumn;
    @FXML private TableColumn<Order, String> cashierColumn;
    @FXML private TableView<OrderDetails> orderDetailsTable;
    @FXML private TableColumn<OrderDetails, String> menuItemColumn;
    @FXML private TableColumn<OrderDetails, Integer> quantityColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initOrderTableProperties();
        initOrderDetailsTableProperties();
        loadOrders();
    }

    public void loadOrders() {
        orderTable.setItems(OrdersSQL.getOrderList());
    }

    public void loadOrderDetails() {
        Order selectedOrder = orderTable.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            return;
        }

        orderDetailsTable.setItems(OrdersSQL.getOrderDetailsList(selectedOrder.getId()));
    }

    private void initOrderTableProperties() {
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        cashierColumn.setCellValueFactory(new PropertyValueFactory<>("cashier"));
    }

    private void initOrderDetailsTableProperties() {
        menuItemColumn.setCellValueFactory(new PropertyValueFactory<>("menuItem"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }
}
