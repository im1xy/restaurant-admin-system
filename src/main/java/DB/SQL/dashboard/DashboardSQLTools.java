package DB.SQL.dashboard;

import DB.Database;
import javafx.scene.chart.XYChart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

interface DashboardSQLTools {
    static XYChart.Series<String, Number> getXYSeriesFromQuery(String selectQuery) {
        Connection dbConnect = Database.connectDB();
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        try {
            PreparedStatement statement = dbConnect.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                series.getData().add(new XYChart.Data<>(resultSet.getString(2), resultSet.getInt(1)));
            }

            return series;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static Integer getIntegerFromQuery(String selectQuery) {
        Connection dbConnect = Database.connectDB();
        try {
            PreparedStatement statement = dbConnect.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
