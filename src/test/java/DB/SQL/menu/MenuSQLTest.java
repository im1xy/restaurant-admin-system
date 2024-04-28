package DB.SQL.menu;

import DB.Database;
import DB.SQL.SQLTest;
import dataObjects.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DB.SQL.menu.MenuSQL.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class MenuSQLTest extends SQLTest {
    @Test
    public void testGetMenuItems() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        MenuItem item1 = new MenuItem(1, "test1", "test1", 10.0, "path1", true);
        MenuItem item2 = new MenuItem(2, "test2", "test2", 20.0, "path2", false);

        Mockito.when(resultSet.getInt("ID")).thenReturn(item1.getId()).thenReturn(item2.getId());
        Mockito.when(resultSet.getString("Name")).thenReturn(item1.getName()).thenReturn(item2.getName());
        Mockito.when(resultSet.getString("Type")).thenReturn(item1.getType()).thenReturn(item2.getType());
        Mockito.when(resultSet.getDouble("Price")).thenReturn(item1.getPrice()).thenReturn(item2.getPrice());
        Mockito.when(resultSet.getString("ImagePath")).thenReturn(item1.getImagePath()).thenReturn(item2.getImagePath());
        Mockito.when(resultSet.getBoolean("isAvailable")).thenReturn(item1.getAvailability()).thenReturn(item2.getAvailability());

        ObservableList<MenuItem> itemsList = FXCollections.observableArrayList();
        itemsList.add(item1);
        itemsList.add(item2);

        ObservableList<MenuItem> resultList = getMenuItems();
        assertEquals(resultList, itemsList);
    }

    @Test
    public void testGetTypes() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        String type1 = "ABC";
        String type2 = "DEF";

        Mockito.when(resultSet.getString("Type")).thenReturn(type1).thenReturn(type2);

        ObservableList<String> typeList = FXCollections.observableArrayList();
        typeList.add("");
        typeList.add(type1);
        typeList.add(type2);

        ObservableList<String> resultList = getTypes();
        assertEquals(resultList, typeList);
    }

    @Test
    public void testGetTypeID() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);

        int id = 1;

        Mockito.when(resultSet.getInt("ID")).thenReturn(id);

        assertEquals(getTypeID("test1"), id);
    }
}