package DB;

import DB.SQL.SQLTest;
import org.junit.Test;

import java.sql.Connection;

import static junit.framework.TestCase.assertNotNull;

public class DatabaseTest extends SQLTest {
    @Test
    public void testConnectDB() {
        final Connection result = Database.connectDB();
        assertNotNull(result);
    }
}
