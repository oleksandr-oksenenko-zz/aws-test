package net.crew4ok;

import org.postgresql.Driver;
import spark.servlet.SparkApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static spark.Spark.get;

public class Bootstrap implements SparkApplication {

    public static void main(String[] args) {
        initRoutes();
    }

    @Override
    public void init() {
        initRoutes();
    }

    private static void initRoutes() {
        get("/", (req, res) -> "Hello, world!");
        get("/db", (req, res) -> {
            Class.forName("org.postgresql.Driver");

            String jdbcConnectionString = getJdbcConnectionString();
            System.out.println(jdbcConnectionString);

            Connection conn = null;
            try {
                conn = DriverManager.getConnection(jdbcConnectionString);

                if (conn != null) {
                    return "db works!";
                }
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
            return "db doesn't work!";
        });
    }

    private static String getJdbcConnectionString() {
        String dbName = System.getProperty("RDS_DB_NAME");
        String userName = System.getProperty("RDS_USERNAME");
        String password = System.getProperty("RDS_PASSWORD");
        String hostname = System.getProperty("RDS_HOSTNAME");
        String port = System.getProperty("RDS_PORT");
        String jdbcUrl = "jdbc:postgresql://" + hostname + ":" +
                port + "/" + dbName + "?user=" + userName + "&password=" + password;

        return jdbcUrl;
    }
}
