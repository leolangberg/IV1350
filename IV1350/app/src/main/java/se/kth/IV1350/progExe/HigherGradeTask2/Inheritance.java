package se.kth.IV1350.progExe.HigherGradeTask2;

/**
 * Uses inheritance to override certain functions of the Random library.
 */
public class Inheritance extends Random {
    

    public Inheritance() {};


    /**
     * Overrides the previous implementation of nextInt() method in Random 
     * with this new implementation.
     * This implementation uses next(64) instead of next(32) (bits).
     * @return integer.
     */
    @Override
    public int nextInt()
    {
        return next(32);
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
