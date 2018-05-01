import javax.xml.transform.Result;
import java.sql.*;
import java.text.SimpleDateFormat;

public class DBHandler {
    private Connection connection;
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    DBHandler() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean register(String username, String password) {
        String sql = "INSERT INTO users(username, password, timeJoined) VALUES(?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            String hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
            preparedStatement.setString(2, hashedPassword);
            String currentTime = timeFormat.format(new Timestamp(System.currentTimeMillis()));
            preparedStatement.setString(3, currentTime);
            preparedStatement.executeUpdate();
            return true;
        } catch(Exception ex) {
//            ex.printStackTrace();
            return false;
        }
    }

    public boolean login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                if(org.mindrot.jbcrypt.BCrypt.checkpw(password, resultSet.getString("password"))) return true;
                return false;
            }
            return false;
        } catch (Exception ex) {
//            ex.printStackTrace();
            return false;
        }
    }

    public void logMessage(String message) {
        String sql = "INSERT INTO chatHistory(message) VALUES(?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, message);
            preparedStatement.executeUpdate();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public String[] getMessageHistory() {
        String sql = "SELECT * FROM chatHistory ORDER BY message DESC LIMIT 10";
        try {
            String[] resultArray = new String[10];
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            int i = 0;
            while(resultSet.next()) {
                resultArray[i++] = resultSet.getString("message");
            }
            return resultArray;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
