import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class Application {
    Connection con;

    Application() {
        this.connectToDB();
        if (con == null) {
            System.out.println("Can't connect to server...");
        }
    }

    private void connectToDB() {
        MysqlDataSource ds = getMySQLDataSource();

            try {
                con = ds.getConnection();
                System.out.println("Connected");
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }
    private static MysqlDataSource getMySQLDataSource() {
        //Keeping properties in another folder for username and so on...
        Properties props = new Properties();
        //Path for the file
        String fileName = "src/main/resources/db.properties";
        //Loading in properties file
        try (FileInputStream in = new FileInputStream(fileName)) {
            props.load(in);
            System.out.println(in);
        } catch (Exception e) {
            System.out.println(e);
        }
        //Creating datasource for better performance and scalability.
        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL(props.getProperty("mysql.url"));
        ds.setUser(props.getProperty("mysql.username"));
        ds.setPassword(props.getProperty("mysql.password"));

        return ds;
    }
}
