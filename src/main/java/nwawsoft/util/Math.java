package nwawsoft.util;

public class Math {
    DebugPrinter dp = new DebugPrinter();

    /**
     * Rounds the specified double down (2.001 - 2.499 -> 2) or up (2.5 - 2.999 -> 3) and returns the result as an int.
     *
     * @param pDouble the double to round.
     * @return the corresponding int
     */
    public int roundDoubleToInt(double pDouble) {
        int y;
        double decimal = pDouble % 10; // make leading part before decimal point exactly 1 digit long
        char digitAfterDecimalPoint = Double.toString(decimal).charAt(2);
        if (digitAfterDecimalPoint <= '4') {
            y = (int) pDouble;
        } else {
            y = (int) pDouble + 1;
        }
        return y;
    }
}
