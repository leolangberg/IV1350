package se.kth.IV1350.progExe.HigherGradeTask2;

/**
 * Uses inheritance to override certain functions of the Random library.
 */
public class Inheritance extends Random {
    

    public Inheritance() {};


    /**
     * Overrides the previous implementation of nextInt() method in Random 
     * with this new implementation.
     * (Example to show how Inheritance could be used).
     * @return float.
     */
    @Override
    public float nextFloat()
    {
        return next(24) / ((float) (1 << 24));
    }

    /**
     * Randomly chooses a number between 1 and 6.
     * @return dice number 1-6.
     */
    public int diceRoll() {
        int a = nextInt(6);
        return a + 1;
    }
}
