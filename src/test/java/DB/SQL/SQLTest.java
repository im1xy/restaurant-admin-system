package DB.SQL;


import DB.Database;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;

public class SQLTest {
    protected static MockedStatic<Database> mockDB;
    protected static PreparedStatement statement;
    protected static ResultSet resultSet;

    @BeforeClass
    public static void setUp() throws SQLException {
        mockDB = Mockito.mockStatic(Database.class);
        resultSet = Mockito.mock(ResultSet.class);
        statement = Mockito.mock(PreparedStatement.class);
        Connection dbConnect = Mockito.mock(Connection.class);

        Mockito.when(statement.executeQuery()).thenReturn(resultSet);

        Mockito.when(dbConnect.prepareStatement(any())).thenReturn(statement);
        mockDB.when(() -> Database.connectDB()).thenReturn(dbConnect);
    }

    @AfterClass
    public static void close() {
        mockDB.close();
    }
}
