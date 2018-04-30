import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ServerClientHandler extends Thread{
    private Socket clientSocket;
    private PrintWriter output;
    private BufferedReader input;
    private String username;
    private static final DBHandler serverDB = new DBHandler();
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    ServerClientHandler(Socket socket) {
        try {
            clientSocket = socket;
            output = new PrintWriter(clientSocket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch(IOException ex) {
            System.out.println(ex.toString());
        }
    }

    private void printServerMessage(String msg) {
        String currentTime = timeFormat.format(new Timestamp(System.currentTimeMillis()));
        System.out.println(currentTime+' '+username+": "+msg);
    }

    private void stopConnection() {
        String currentTime = timeFormat.format(new Timestamp(System.currentTimeMillis()));
        try {
            input.close();
            output.close();
            clientSocket.close();
            System.out.println(currentTime+' '+username+" has disconnected!");
        } catch(IOException ex) {
            System.out.println(currentTime+' '+username+" has disconnected!");
        }
    }

    private String getCorrectInput() {
        String clientMessage;
        try {
            clientMessage = input.readLine();
            while (clientMessage.length() <= 0 || !clientMessage.matches("^[a-zA-Z0-9]*$")) {
                output.println("Incorrect input.");
                clientMessage = input.readLine();
            }
            return clientMessage;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void run() {
        try {
            output.println("Welcome to the server. Do you want to log in or register? Type in 'login' or 'register'.");
            String clientMessage;
            while((clientMessage = input.readLine()) != null ) {
                if(clientMessage.equals("login")) {
                    output.println("Type in your username.");
                    String user = getCorrectInput();
                    output.println("Type in your password.");
                    String password = getCorrectInput();
                    // AUTHENTICATE
                } else if(clientMessage.equals("register")) {
                    output.println("Type in your desired login username.");
                    String username = getCorrectInput();
                    output.println("Type in your password.");
                    String password = getCorrectInput();
                    output.println("Re-type your password.");
                    String rePassword = getCorrectInput();
                    // REGISTER
                } else {
                    output.println("Please type in 'login' or 'register'.");
                }
            }

            stopConnection();
        } catch(IOException ex) {
            String currentTime = timeFormat.format(new Timestamp(System.currentTimeMillis()));
            System.out.println(currentTime+' '+username+" has disconnected!");
        }
    }
}
