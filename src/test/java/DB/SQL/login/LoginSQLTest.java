package DB.SQL.login;

import DB.SQL.SQLTest;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static DB.SQL.login.LoginSQL.isDataInDatabase;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class LoginSQLTest extends SQLTest {

    @Test
    public void testIsDataInDatabase() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        assertTrue(isDataInDatabase("test1", "test2"));
    }
}