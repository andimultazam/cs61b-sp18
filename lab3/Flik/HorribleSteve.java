/**
 * This class is intended to test Flik.java.
 * @author andimultazam
 */
public class HorribleSteve {
    /**
     * maximum number to test.
     */
    private static final int MAX_SIZE = 500;
    /**
     * main methods to start the test.
     * @param args input from user
     */
    public static void main(String [] args) {
        int i = 0;
        for (int j = 0; i < MAX_SIZE; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
                break;
            }
        }
        System.out.println("i is " + i);
    }
}
