import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Lawn l = new Lawn(1000, 20, 20, 1000, 200, 500);
        Thread lawn = new Thread(l);
        lawn.start();
        JFrame myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.add(l);
        myFrame.pack();
        myFrame.setVisible(true);


    }
}
