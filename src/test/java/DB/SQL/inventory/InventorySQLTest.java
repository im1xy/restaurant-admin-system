package DB.SQL.inventory;

import DB.SQL.SQLTest;
import dataObjects.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static DB.SQL.inventory.InventorySQL.*;
import static org.junit.jupiter.api.Assertions.*;


public class InventorySQLTest extends SQLTest {
    @Test
    public void testIsProductInDatabase() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);

        Product product1 = new Product("test1", 10);
        Product product2 = new Product("test2", 20);

        assertTrue(isProductInDatabase(product1));
        assertFalse(isProductInDatabase(product2));
    }

    @Test
    public void testGetProductStock() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt("Stock")).thenReturn(10).thenReturn(20);

        Product product1 = new Product("test1", 10);
        Product product2 = new Product("test2", 20);

        assertEquals(getProductStock(product1), 10);
        assertEquals(getProductStock(product2), 20);
    }

    @Test
    public void testAddProduct() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);

        Product product = new Product("test1", 10);
        addProduct(product);
        assertTrue(isProductInDatabase(product));
    }

    @Test
    public void testUpdateProduct() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);

        Product product = new Product("test1", 10);
        updateProduct(product, 10);
        assertTrue(isProductInDatabase(product));
    }

    @Test
    public void testGetInventoryList() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        Product product1 = new Product("test1", 10);
        Product product2 = new Product("test2", 20);

        Mockito.when(resultSet.getString("Name")).thenReturn(product1.getName()).thenReturn(product2.getName());
        Mockito.when(resultSet.getInt("Stock")).thenReturn(product1.getStock()).thenReturn(product2.getStock());

        ObservableList<Product> inventoryList = FXCollections.observableArrayList();
        inventoryList.add(product1);
        inventoryList.add(product2);

        ObservableList<Product> resultList = getInventoryList();
        assertEquals(resultList, inventoryList);
    }
}