package dataObjects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class OrderDetailsTest {

    private OrderDetails orderDetailsUnderTest;

    @Before
    public void setUp() throws Exception {
        orderDetailsUnderTest = new OrderDetails("menuItem", 0);
    }

    @Test
    public void testGetMenuItem() {
        assertEquals("menuItem", orderDetailsUnderTest.getMenuItem());
    }

    @Test
    public void testGetQuantity() {
        assertEquals(0, orderDetailsUnderTest.getQuantity());
    }

    @Test
    public void testEquals() {
        assertFalse(orderDetailsUnderTest.equals("object"));
    }
}
