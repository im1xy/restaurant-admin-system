package GUI.panel.menu.menuPreview;

import GUI.GUITest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MenuPreviewPanelTest extends GUITest {

    private MenuPreviewPanel menuPreviewPanelUnderTest;

    @Override
    @BeforeEach
    public void setUp() throws SQLException {
        super.setUp();
        menuPreviewPanelUnderTest = new MenuPreviewPanel();
        menuPreviewPanelUnderTest.load();
    }

    @Test
    public void testGetController() {
        assertNotNull(menuPreviewPanelUnderTest.getController());
    }

    @Test
    public void testGetFxmlPath() {
        assertEquals("./fxmlForms/systemFXML/menuPreview.fxml", menuPreviewPanelUnderTest.getFxmlPath());
    }
}
