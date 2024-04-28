package DB.SQL.inventory;

import DB.Database;
import dataObjects.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface InventorySQL {
    static boolean isProductInDatabase(Product product) {
        Connection dbConnect = Database.connectDB();
        String selectQuery = "SELECT Name FROM products WHERE Name = ?";

        try {
            PreparedStatement statement = dbConnect.prepareStatement(selectQuery);
            statement.setString(1, product.getName());
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static Integer getProductStock(Product product) {
        Connection dbConnect = Database.connectDB();
        String selectQuery = "SELECT Name, Stock FROM products WHERE Name = ?";

        try {
            PreparedStatement statement = dbConnect.prepareStatement(selectQuery);
            statement.setString(1, product.getName());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            return resultSet.getInt("Stock");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void addProduct(Product product) {
        Connection dbConnect = Database.connectDB();
        String insertQuery = "INSERT INTO products (Name, Stock) VALUES (?, ?)";

        try {
            PreparedStatement statement = dbConnect.prepareStatement(insertQuery);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getStock());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void updateProduct(Product product, Integer oldStock) {
        Connection dbConnect = Database.connectDB();
        String updateQuery = "UPDATE products SET Stock = ? WHERE Name = ?";

        try {
            PreparedStatement statement = dbConnect.prepareStatement(updateQuery);
            statement.setInt(1, product.getStock() + oldStock);
            statement.setString(2, product.getName());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static ObservableList<Product> getInventoryList() {
        ObservableList<Product> inventoryList = FXCollections.observableArrayList();
        Connection dbConnect = Database.connectDB();
        String selectQuery = "SELECT Name, Stock FROM products;";

        try {
            PreparedStatement statement = dbConnect.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                String productName = resultSet.getString("Name");
                int productStock = resultSet.getInt("Stock");

                inventoryList.add(new Product(productName, productStock));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return inventoryList;
    }
}
