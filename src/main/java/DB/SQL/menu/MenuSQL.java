package DB.SQL.menu;

import DB.Database;
import dataObjects.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface MenuSQL {
    static ObservableList<MenuItem> getMenuItems() {
        ObservableList<MenuItem> itemsList = FXCollections.observableArrayList();
        Connection dbConnect = Database.connectDB();
        String selectQuery =
                """
                select\s
                    menuitems.ID,\s
                	Name,\s
                	Type,
                    Price,\s
                    ImagePath,\s
                    isAvailable
                from
                	rest.menuitems inner join rest.menuitemtypes
                		on menuitems.Type_ID = menuitemtypes.ID        
                """;

        try {
            PreparedStatement statement = dbConnect.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String type = resultSet.getString("Type");
                double price = resultSet.getDouble("Price");
                String imagePath = resultSet.getString("ImagePath");
                Boolean availability = resultSet.getBoolean("isAvailable");

                itemsList.add(new MenuItem(id, name, type, price, imagePath, availability));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return itemsList;
    }

    static ObservableList<String> getTypes() {
        ObservableList<String> typeList = FXCollections.observableArrayList();
        typeList.add("");

        Connection dbConnect = Database.connectDB();
        String selectQuery = "select Type from menuitemtypes";

        try {
            PreparedStatement statement = dbConnect.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                typeList.add(resultSet.getString("Type"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return typeList;
    }

    static Integer getTypeID(String type) {
        String selectQuery = "select ID from menuitemtypes where Type = ?";
        Connection dbConnect = Database.connectDB();
        Integer type_id = null;

        try {
            PreparedStatement statement = dbConnect.prepareStatement(selectQuery);
            statement.setString(1, type);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            type_id = resultSet.getInt("ID");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return type_id;
    }

    static void addItem(MenuItem menuItem) {
        Connection dbConnect = Database.connectDB();
        String insertQuery =
                """
                INSERT INTO rest.menuitems
                	(Name, Type_ID, Price, ImagePath, isAvailable)
                values
                	(?, ?, ?, ?, ?)        
                """;

        int typeID = MenuSQL.getTypeID(menuItem.getType());
        try {
            PreparedStatement statement = dbConnect.prepareStatement(insertQuery);
            statement.setString(1, menuItem.getName());
            statement.setInt(2, typeID);
            statement.setString(3, String.valueOf(menuItem.getPrice()));
            statement.setString(4, menuItem.getImagePath());
            statement.setInt(5, menuItem.getAvailability() ? 1 : 0);

            statement.executeUpdate();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void updateItem(Integer id, MenuItem menuItem) {
        Connection dbConnect = Database.connectDB();
        String updateQuery =
                """
                UPDATE
                    menuitems
                SET
                    Name = ?,
                    Type_ID = ?,
                    Price = ?,
                    ImagePath = ?,
                    isAvailable = ?
                WHERE
                    ID = ?
                """;

        int typeID = getTypeID(menuItem.getType());
        try {
            PreparedStatement statement = dbConnect.prepareStatement(updateQuery);
            statement.setString(1, menuItem.getName());
            statement.setInt(2, typeID);
            statement.setString(3, String.valueOf(menuItem.getPrice()));
            statement.setString(4, menuItem.getImagePath());
            statement.setInt(5, menuItem.getAvailability() ? 1 : 0);
            statement.setInt(6, id);

            statement.executeUpdate();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void deleteItem(Integer id) throws SQLException {
        Connection dbConnect = Database.connectDB();
        String deleteQuery = "DELETE FROM menuitems WHERE ID = ?";

        PreparedStatement statement = dbConnect.prepareStatement(deleteQuery);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
