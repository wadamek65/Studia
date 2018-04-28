import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Lawn extends JPanel implements Runnable {
    private Grass[] grassArray;
    private Snail[] snailArray;
    private Thread[] grassThreads;
    private Thread[] snailThreads;
    private int refreshSpeed;
    private BufferedImage snailImage;

    Lawn(int speed, int grassAmount, int snailAmount, int grassGrowth, int snailAppetite, int snailEatingSpeed) {
        refreshSpeed = speed;

        grassArray = new Grass[grassAmount];
        grassThreads = new Thread[grassAmount];

        snailArray = new Snail[snailAmount];
        snailThreads = new Thread[snailAmount];

        for (int i = 0; i < grassAmount; i++) {
            grassArray[i] = new Grass(grassGrowth, snailEatingSpeed);
            grassThreads[i] = new Thread(grassArray[i]);
        }

        for (int i = 0; i < snailAmount; i++) {
            snailArray[i] = new Snail(grassAmount, grassArray, snailAppetite, snailEatingSpeed);
            snailThreads[i] = new Thread(snailArray[i]);
        }

        try {
            snailImage = ImageIO.read(getClass().getResource("/images/snail.png"));
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public void run() {
        for (Thread g : grassThreads) g.start();
        for (Thread s : snailThreads) s.start();
        while(true) {
            getLawnStatus();
            repaint();
            try {
                Thread.sleep(refreshSpeed);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void getLawnStatus() {
        for (Grass g : grassArray) {
            System.out.print(g.getGrassStatus());
            System.out.print(' ');
        }
        System.out.println();
        for (Grass g : grassArray) {
            if (g.getOccupation()) {
                System.out.print('^');
            } else {
                System.out.print(' ');
            }
            System.out.print(' ');
        }
        System.out.println();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int x = 0;
        int y = 0;
        for (Grass grass : grassArray) {
            Color growthLevelColor = new Color(0, 100+15*grass.getGrassStatus(), 0);
            g.setColor(growthLevelColor);
            g.fillRect(x, y, 40, 40);

            g.setColor(Color.BLACK);
            g.drawRect(x, y, 40, 40);

            if (grass.getOccupation()) g.drawImage(snailImage, x, y, this);

            if (x < 360) x += 40;
            else {
                x = 0;
                y += 40;
            }
        }
        int deadSnails = 0;
        int starvingSnails = 0;
        for (int i = 0; i < snailArray.length; i++) {
            if (!snailThreads[i].isAlive()) deadSnails++;
            if (snailArray[i].getStarvingLevel() > 0) starvingSnails++;
        }
        int aliveSnails = snailArray.length - deadSnails;

        System.out.println("Alive snails: "+String.valueOf(aliveSnails));
        System.out.println("Starving snails: "+String.valueOf(starvingSnails));
        System.out.println("Dead snails: "+String.valueOf(deadSnails));

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }
}
