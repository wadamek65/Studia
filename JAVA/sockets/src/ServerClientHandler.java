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
    private boolean loggedIn = false;
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

    private boolean printServerMessage(String msg) {
        if(msg.equals("/quit")) return false;
        String currentTime = timeFormat.format(new Timestamp(System.currentTimeMillis()));
        String message = currentTime+' '+username+": "+msg;
        System.out.println(message);
        output.println("Message sent.");
        serverDB.logMessage(message);
        return true;
    }

    private void stopConnection() {
        String currentTime = timeFormat.format(new Timestamp(System.currentTimeMillis()));
        try {
            input.close();
            output.close();
            clientSocket.close();
            if (username != null) {
                String message = currentTime+' '+username+" has disconnected!";
                System.out.println(message);
                serverDB.logMessage(message);
            }
        } catch(IOException ex) {
            if (username != null) {
                String message = currentTime+' '+username+" has disconnected!";
                System.out.println(message);
                serverDB.logMessage(message);
            }
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
            while((clientMessage  = input.readLine()) != null ) {
                if(loggedIn) {
                    if(!printServerMessage(clientMessage)) break;
                }

                while(!loggedIn) {
                    if (clientMessage.equals("login")) {
                        clientMessage = " ";
                        output.println("Type in your username.");
                        String user = getCorrectInput();
                        output.println("Type in your password.");
                        String password = getCorrectInput();
                        if (serverDB.login(user, password)) {
                            output.println("Login successful.");
                            username = user;

                            if (username != null) {
                                String currentTime = timeFormat.format(new Timestamp(System.currentTimeMillis()));
                                String message = currentTime+' '+username+" has connected!";
                                System.out.println(message);
                                serverDB.logMessage(message);
                            }
                            loggedIn = true;
                            break;
                        } else {
                            output.println("The password or username is wrong.");
                        }
                    } else if (clientMessage.equals("register")) {
                        clientMessage = " ";
                        output.println("Type in your desired login username.");
                        String user = getCorrectInput();
                        output.println("Type in your password.");
                        String password = getCorrectInput();
                        output.println("Re-type your password.");
                        String rePassword = getCorrectInput();
                        if (!password.equals(rePassword)) {
                            output.println("Passwords don't match.");
                        } else {
                            if (serverDB.register(user, password)) {
                                output.println("Registered successfully.");
                            } else {
                                output.println("There is already a user registered with this username.");
                            }
                        }
                    }
                    output.println("Please type in 'login' or 'register'.");
                    clientMessage = input.readLine();
                }
            }

            stopConnection();
        } catch(IOException ex) {
            String currentTime = timeFormat.format(new Timestamp(System.currentTimeMillis()));
            if (username != null) System.out.println(currentTime+' '+username+" has disconnected!");
        }
    }
}
