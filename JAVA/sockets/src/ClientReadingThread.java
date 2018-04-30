import java.io.BufferedReader;

public class ClientReadingThread extends Thread {
    private BufferedReader reader;
    ClientReadingThread(BufferedReader inputReader) {
        reader = inputReader;
    }

    public void run() {
        String serverMessage;
        try {
            while ((serverMessage = reader.readLine()) != null) {
                System.out.println(serverMessage);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
