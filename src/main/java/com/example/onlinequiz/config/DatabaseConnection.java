/**
 * Configuration package for the Online Quiz application's database connection management.
 * Contains classes responsible for establishing and managing database connections.
 */

// Package declaration
package main.java.com.example.onlinequiz.config;

// Import statements for necessary classes
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static Connection connection;
    private static Properties properties;

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws IOException, SQLException {
        if (connection == null) {
            try {
                // Load properties only once
                if (properties == null) {
                    propterties = new Properties();
                    InputStream inputStream = DatabaseConnection.class.getClassLoader()
                            .getResourceAsStream("com/example/onlinequiz/config/config.properties");

                    if (inputStream == null) {
                        throw new IOException("config.properties  file not found");
                    }
                    properties.load(inputStream);
                }
                String url = properties.getProperty("db.url");
                String user = properties.getProperty("db.user");
                String password = properties.getProperty("db.password");
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new SQLException("Unable to establish database connection: " + e.getMessage(), e);
            }
        }
        return connection;
    }

    // Close the connection
    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close(); // Close the connection if it is not null to avoid memory leak
            connection = null; // Set the connection to null after closing it to avoid further usage
        }
    }
}
