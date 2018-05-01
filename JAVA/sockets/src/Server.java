import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private ServerSocket serverSocket;
    private static final DBHandler serverDB = new DBHandler();

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            String[] hist = serverDB.getMessageHistory();
            if(hist != null) {
                for(int i = hist.length-1; i >= 0; i--) {
                    System.out.println(hist[i]);
                }
            }
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
