import java.util.Random;

public class Snail implements Runnable {
    private int grassSlots;
    private Grass[] grassArray;
    private int hunger;
    private int starving = 0;
    private int snailAppetite;
    private int snailEatingSpeed;
    private boolean isEating = false;
    Random gen = new Random();

    Snail(int grass, Grass[] grassArr, int appetite, int speed) {
        grassSlots = grass;
        grassArray = grassArr;
        hunger = gen.nextInt(10);
        snailAppetite = appetite;
        snailEatingSpeed = speed;
    }

    @Override
    public void run() {
        while(true) {
            if (starving >= 10) return;

            if (hunger < 5) {
                eat();
            }

            if (!isEating) {
                if (hunger > 0) hunger--;
                else starving++;

                try {
                    Thread.sleep(snailAppetite);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    System.out.println("interrupted");
                }
            }
        }
    }

    private boolean eat() {
        Grass notOccupied = findNotOccupied();
        if (notOccupied != null) {
            isEating = true;
            notOccupied.setOccupation(true);
            while (hunger < 10 && notOccupied.getGrassStatus() > 0) {
                hunger ++;
                notOccupied.decrementStatus();
                try {
                    Thread.sleep(snailEatingSpeed);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            notOccupied.setOccupation(false);
            isEating = false;
            return true;
        }
        return false;
    }

    private Grass findNotOccupied() {
        for (Grass g : grassArray) {
            if (!g.getOccupation() && g.getGrassStatus() > 5) return g;
        }
        return null;
    }

    public int getStarvingLevel() {
        return starving;
    }
}
