package dataObjects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class MenuItemTest {

    private MenuItem menuItemUnderTest;

    @Before
    public void setUp() throws Exception {
        menuItemUnderTest = new MenuItem(0, "name", "type", 0.0, "imagePath", false);
    }

    @Test
    public void testGetId() {
        assertEquals(Integer.valueOf(0), menuItemUnderTest.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("name", menuItemUnderTest.getName());
    }

    @Test
    public void testGetType() {
        assertEquals("type", menuItemUnderTest.getType());
    }

    @Test
    public void testGetPrice() {
        assertEquals(0.0, menuItemUnderTest.getPrice(), 0.0001);
    }

    @Test
    public void testGetImagePath() {
        assertEquals("imagePath", menuItemUnderTest.getImagePath());
    }

    @Test
    public void testGetAvailability() {
        assertFalse(menuItemUnderTest.getAvailability());
    }

    @Test
    public void testEquals() {
        assertFalse(menuItemUnderTest.equals("object"));
    }
}
