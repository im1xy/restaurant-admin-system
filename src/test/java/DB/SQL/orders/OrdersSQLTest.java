package DB.SQL.orders;

import DB.SQL.SQLTest;
import dataObjects.Order;
import dataObjects.OrderDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.SQLException;

import static DB.SQL.orders.OrdersSQL.getOrderDetailsList;
import static DB.SQL.orders.OrdersSQL.getOrderList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OrdersSQLTest extends SQLTest {
    @Test
    public void testGetOrderList() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Order order1 = new Order(1, "test1", 10, new Date(2004,10,10));
        Order order2 = new Order(2, "test2", 20, new Date(2005,12,11));

        Mockito.when(resultSet.getInt("ID")).thenReturn(order1.getId()).thenReturn(order2.getId());
        Mockito.when(resultSet.getDouble("Total")).thenReturn(order1.getTotal()).thenReturn(order2.getTotal());
        Mockito.when(resultSet.getDate("Date")).thenReturn(order1.getDate()).thenReturn(order2.getDate());
        Mockito.when(resultSet.getString("Cashier")).thenReturn(order1.getCashier()).thenReturn(order2.getCashier());

        ObservableList<Order> orderList = FXCollections.observableArrayList();
        orderList.add(order1);
        orderList.add(order2);

        ObservableList<Order> resultList = getOrderList();
        assertEquals(resultList, orderList);
    }

    @Test
    public void testGetOrderDetailsList() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        OrderDetails order1 = new OrderDetails("test1", 10);
        OrderDetails order2 = new OrderDetails("test2", 20);

        Mockito.when(resultSet.getString("Name")).thenReturn(order1.getMenuItem()).thenReturn(order2.getMenuItem());
        Mockito.when(resultSet.getInt("Quantity")).thenReturn(order1.getQuantity()).thenReturn(order2.getQuantity());

        ObservableList<OrderDetails> orderList = FXCollections.observableArrayList();
        orderList.add(order1);
        orderList.add(order2);

        ObservableList<OrderDetails> resultList = getOrderDetailsList(1);
        assertEquals(resultList, orderList);
    }
}