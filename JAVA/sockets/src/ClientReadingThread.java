import java.io.BufferedReader;

public class ClientReadingThread extends Thread {
    private BufferedReader reader;
    ClientReadingThread(BufferedReader inputReader) {
        reader = inputReader;
    }

    public void run() {
        try {
            String serverMessage;
            while ((serverMessage = reader.readLine()) != null) {
                if (serverMessage.equals("done")) return;
                System.out.println(serverMessage);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
