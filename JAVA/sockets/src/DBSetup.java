import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBSetup {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:test.db");
            Statement statement = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS chatHistory (message TEXT NOT NULL)";
            String sql2 = "CREATE TABLE IF NOT EXISTS users (username TEXT NOT NULL UNIQUE, password TEXT NOT NULL, timeJoined TEXT NOT NULL)";
            statement.executeUpdate(sql);
            statement.executeUpdate(sql2);
            con.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
