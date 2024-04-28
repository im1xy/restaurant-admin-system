package DB.SQL.login;

import DB.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface LoginSQL {
    static boolean isDataInDatabase(String username, String password) {
        Connection dbConnect = Database.connectDB();
        String selectQuery = "SELECT Username, Password FROM logininformation WHERE Username = ? and Password = ?";
        try {
            PreparedStatement statement = dbConnect.prepareStatement(selectQuery);
            statement.setString(1, username);
            statement.setString(2, password);

            return statement.executeQuery().next();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
