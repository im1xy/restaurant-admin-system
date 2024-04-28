package DB.SQL.dashboard;

import DB.SQL.SQLTest;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static DB.SQL.dashboard.DashboardSQL.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class DashboardSQLTest extends SQLTest {

    @Test
    public void testCalculateTotalCustomers() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(10);
        assertEquals(calculateTotalCustomers(), 10);
    }

    @Test
    public void testCalculateTodaysCustomers() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(10);
        assertEquals(calculateTodaysCustomers(), 10);
    }

    @Test
    public void testCalculateTotalIncome() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(10);
        assertEquals(calculateTotalIncome(), 10);
    }

    @Test
    public void testCalculateTodaysIncome() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(10);
        assertEquals(calculateTodaysIncome(), 10);
    }

    @Test
    public void testCalculateTotalSoldItems() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(10);
        assertEquals(calculateTotalSoldItems(), 10);
    }
}