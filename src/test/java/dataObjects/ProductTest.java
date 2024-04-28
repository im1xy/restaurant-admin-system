package dataObjects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ProductTest {

    private Product productUnderTest;

    @Before
    public void setUp() throws Exception {
        productUnderTest = new Product("name", 0);
    }

    @Test
    public void testGetName() {
        assertEquals("name", productUnderTest.getName());
    }

    @Test
    public void testGetStock() {
        assertEquals(0, productUnderTest.getStock());
    }

    @Test
    public void testEquals() {
        assertFalse(productUnderTest.equals("object"));
    }
}
