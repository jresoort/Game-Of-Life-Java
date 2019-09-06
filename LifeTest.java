import sun.jvm.hotspot.utilities.Assert;


public class LifeTest {

    private static final int TIMES = 1_000_000;

    public static void main(String[] args)
            throws java.lang.InterruptedException{
        Life life = new Life();

        life.drawWorld();
        loopTimes(life, TIMES);
        life.drawWorld();
        Assert.that(!life.currentlyAlive(2,2),"expected dead");
        Assert.that(life.currentlyAlive(3,3),"expected alive");
        Assert.that(life.currentlyAlive(4,4),"expected alive");
        Assert.that(!life.currentlyAlive(5,5),"expected dead");

        System.out.println("^^^^^ TEST PASSED ^^^^^");

    }

    public static void loopTimes(Life life, int times) {
        for (int i = 0; i< times; i++) {
            i++;
            life.nextGeneration();
            System.out.println("Generations  =  " + i);
        }
    }

}
