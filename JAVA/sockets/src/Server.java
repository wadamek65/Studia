import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private ServerSocket serverSocket;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while(true) new ServerClientHandler(serverSocket.accept()).start();
        } catch(IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start(6666);
    }
}
