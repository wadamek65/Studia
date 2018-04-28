import java.util.Random;

public class Grass implements Runnable {
    private boolean isOccupied = false;
    private int grassStatus;
    private int maxGrassStatus = 9;
    private int grassGrowth;
    private int snailEatingSpeed;

    Grass(int growth, int appetite) {
        Random generator = new Random();
        grassStatus = generator.nextInt(maxGrassStatus);
        grassGrowth = growth;
        snailEatingSpeed = appetite;
    }

    @Override
    public void run() {
        while(true) {
            if (!isOccupied && grassStatus < maxGrassStatus) {
                grassStatus++;
            }
            try {
                Thread.sleep(grassGrowth);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public int getGrassStatus() {
        return grassStatus;
    }

    public boolean getOccupation() {
        return isOccupied;
    }

    public void setOccupation(boolean occupation) {
        isOccupied = occupation;
    }

    public void decrementStatus() {
        if (grassStatus > 0) {
            grassStatus--;
        }
    }
}
