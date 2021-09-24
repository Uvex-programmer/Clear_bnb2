import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectMysql {
    public static Connection con;
    
    ConnectMysql() {
        this.connectToDB();
        if (con == null) {
            System.out.println("Can't connect to server...");
        }
    }
    
    private static MysqlDataSource getMySQLDataSource() {
        //Keeping properties in another folder for username and so on...
        Properties props = new Properties();
        //Path for the file
        String fileName = "Backend/src/main/resources/mysql.properties";
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
    
    public Connection getConnection() {
        if (con == null)
            con = (Connection) new ConnectMysql();
        return con;
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
}
