package GUI;

import DB.Database;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class GUITest extends ApplicationTest {
    protected static MockedStatic<Database> mockDB = Mockito.mockStatic(Database.class, Mockito.RETURNS_DEEP_STUBS);
    protected static PreparedStatement statement;
    protected static ResultSet resultSet;

    public void setUp() throws SQLException {
        resultSet = Mockito.mock(ResultSet.class);
        statement = Mockito.mock(PreparedStatement.class);
        Connection dbConnect = Mockito.mock(Connection.class);

        Mockito.when(statement.executeQuery()).thenReturn(resultSet);

        Mockito.when(dbConnect.prepareStatement(any())).thenReturn(statement);
        mockDB.when(() -> Database.connectDB()).thenReturn(dbConnect);
    }
}
