package DB;

import LoggerManager.LoggerManager;
import Mailer.Mailer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class Database {
    private static String dbProperties = "./src/main/resources/properties/dbCredentials.properties";
    public static Connection connectDB() {
        try {
            Properties properties = getDBProperties();
            Class.forName(properties.getProperty("DB_DRIVER"));

            Connection connection = DriverManager.getConnection(
                    properties.getProperty("DB_URL"),
                    properties.getProperty("DB_USER"),
                    properties.getProperty("DB_PASSWORD"));

            LoggerManager.logger.info("Connected to Database successfully");
            return connection;
        }
        catch (Exception e) {
            LoggerManager.logger.severe("Failed to Connect to Database");
            new Mailer().sendMessage("DATABASE CONNECTION ERROR", "Failed to connect to DB", new File("logINFO.txt"));;
            e.fillInStackTrace();
        }

        return null;
    }

    private static Properties getDBProperties() {
        try {
            FileInputStream db = new FileInputStream(dbProperties);
            Properties properties = new Properties();
            properties.load(db);


            LoggerManager.logger.info("Database properties loaded successfully");
            return properties;
        } catch (IOException e) {
            LoggerManager.logger.severe("Failed to Load Database properties from: " + dbProperties);
            new Mailer().sendMessage("DATABASE CONNECTION ERROR", "Failed to connect to load Properties", new File("logINFO.txt"));;
            throw new RuntimeException(e);
        }
    }
}
