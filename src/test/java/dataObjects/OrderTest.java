package dataObjects;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class OrderTest {

    private Order orderUnderTest;

    @Before
    public void setUp() throws Exception {
        orderUnderTest = new Order(0, "cashier", 0.0, Date.valueOf(LocalDate.of(2020, 1, 1)));
    }

    @Test
    public void testGetId() {
        assertEquals(0, orderUnderTest.getId());
    }

    @Test
    public void testGetCashier() {
        assertEquals("cashier", orderUnderTest.getCashier());
    }

    @Test
    public void testGetTotal() {
        assertEquals(0.0, orderUnderTest.getTotal(), 0.0001);
    }

    @Test
    public void testGetDate() {
        assertEquals(Date.valueOf(LocalDate.of(2020, 1, 1)), orderUnderTest.getDate());
    }

    @Test
    public void testEquals() {
        assertFalse(orderUnderTest.equals("object"));
    }
}
