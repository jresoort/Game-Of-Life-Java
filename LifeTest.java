public class LifeTest {

    private static final int TIMES = 1_000_000;

    public static void main(String[] args) {
        Life life = new Life();

        life.drawWorld();
        loopTimes(life, TIMES);
        life.drawWorld();
        if(life.currentlyAlive(2,2)){
            throw new IllegalStateException("expected dead");
        }
        if(!life.currentlyAlive(3,3)){
            throw new IllegalStateException("expected alive");
        }
        if(!life.currentlyAlive(4,4)){
            throw new IllegalStateException("expected alive");
        }
        if(life.currentlyAlive(5,5)){
            throw new IllegalStateException("expected dead");
        }

        System.out.println("^^^^^ TEST PASSED ^^^^^");
    }

    private static void loopTimes(Life life, int times) {
        for (int i = 0; i< times; i++) {
            life.nextGeneration();
            System.out.println("Generations  =  " + i);
        }
    }

}
