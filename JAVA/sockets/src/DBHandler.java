import java.sql.*;

public class DBHandler {
    private Connection connection;

    DBHandler() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
