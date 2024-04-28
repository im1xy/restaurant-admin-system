package DB.SQL.orders;

import DB.Database;
import dataObjects.Order;
import dataObjects.OrderDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public interface OrdersSQL {
    static ObservableList<Order> getOrderList() {
        ObservableList<Order> orderList = FXCollections.observableArrayList();
        Connection dbConnect = Database.connectDB();
        String selectQuery =
                """
                select\s
                	 orders.ID,
                     orders.Total,
                     orders.Date,
                     concat(employees.FirstName, " ", employees.LastName) as Cashier
                from\s
                	orders inner join employees
                		on orders.IssuedByEmployee_ID = employees.ID
                """;

        try {
            PreparedStatement statement = dbConnect.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("ID");
                double total = resultSet.getDouble("Total");
                Date date = resultSet.getDate("Date");
                String cashier = resultSet.getString("Cashier");

                orderList.add(new Order(id, cashier, total, date));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orderList;
    }

    static ObservableList<OrderDetails> getOrderDetailsList(int id) {
        ObservableList<OrderDetails> orderDetailsList = FXCollections.observableArrayList();
        Connection dbConnect = Database.connectDB();
        String selectQuery =
                """
                select\s
                	menuitems.Name,
                	orderdetails.Quantity
                from\s
                	rest.orderdetails inner join rest.menuitems
                		on orderdetails.MenuItem_ID = menuitems.ID
                where\s
                	orderdetails.Order_ID = ?
                """;

        try {
            PreparedStatement statement = dbConnect.prepareStatement(selectQuery);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                String name = resultSet.getString("Name");
                int quantity = resultSet.getInt("Quantity");

                orderDetailsList.add(new OrderDetails(name, quantity));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orderDetailsList;
    }
}
