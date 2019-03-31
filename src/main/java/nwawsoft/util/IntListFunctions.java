package nwawsoft.util;

public class IntListFunctions extends ListFunctions {
    /**
     * // ToDo doc
     *
     * @param ints
     * @return
     */
    public static int multiplyValues(IntList ints) {
        int product = 1;
        ints.toFirst();
        while (ints.hasAccess()) {
            product *= (Integer) ints.getObject();
            ints.next();
        }
        return product;
    }

    /**
     * // ToDo doc
     *
     * @param ints
     * @return
     */
    public static int addValues(IntList ints) {
        int sum = 0;
        ints.toFirst();
        while (ints.hasAccess()) {
            sum += (Integer) ints.getObject();
            ints.next();
        }
        return sum;
    }
}
