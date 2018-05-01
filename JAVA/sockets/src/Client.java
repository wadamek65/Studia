import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket clientSocket;
    private static PrintWriter output;
    private static BufferedReader input;
    private static Thread inputReader;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            output = new PrintWriter(clientSocket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            inputReader = new ClientReadingThread(input);
        } catch(IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public static void stopConnection() {
        try {
            input.close();
            output.close();
            clientSocket.close();
        } catch(IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public static void setupConnection() {
        Client newClient = new Client();
        newClient.startConnection("127.0.0.1", 6666);
        inputReader.start();
        Scanner systemReader = new Scanner(System.in);
        String clientMessage, outputMessage;
        clientMessage = systemReader.next();
        while (clientMessage != null) {
            output.println(clientMessage);
            if (!inputReader.isAlive()) {
                stopConnection();
                return;
            }
            clientMessage = systemReader.next();
        }

//        try {
//            while (!(outputMessage = input.readLine()).equals("Done.")) {
//                System.out.println(outputMessage);
//                if ((inputMessage = systemReader.next()) != null) output.println(inputMessage);
//            }
//            System.out.println(input.readLine());
//        } catch(IOException ex) {
//            System.out.println(ex.toString());
//        }
//        while((inputMessage = systemReader.next()) != null) {
//            String response = newClient.sendMessage(inputMessage);
//            System.out.println(response);
//            if (response.equals("Disconnected.")) {
//                stopConnection();
//                break;
//            }
//        }

    }

    public static void main(String[] args) {
        setupConnection();
    }
}
