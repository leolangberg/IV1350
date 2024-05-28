package se.kth.IV1350.progExe.HigherGradeTask2;

/**
 * Normal usage of Random. 
 * Has a reference to Random in class. 
 */
public class Composition {
    
    private Random random;

    /**
     * Creates new instance of Composition class
     * containing a reference to Random.
     */
    public Composition() {
        this.random = new Random();
    }

    /**
     * Passes the method along to the orginial Random method via
     * reference.
     * @return float.
     */
    public float nextFloat() {
        return random.nextFloat();
    }

    /**
     * Randomly chooses a number between 1 and 6.
     * @return dice number 1-6.
     */
    public int diceRoll() {
        int a = random.nextInt(6);
        return a + 1;
    }
}
