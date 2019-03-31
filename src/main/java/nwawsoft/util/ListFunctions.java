package nwawsoft.util;

public class ListFunctions {
    /**
     * Checks whether two lists have no entries that fit Object.equals().
     *
     * @param a the first List
     * @param b the second List
     * @return true if no match was found. Else false.
     */
    public static boolean sharesNoEntry(List a, List b) {
        boolean foundMatch = false;
        a.toFirst();
        b.toFirst();
        while (a.hasAccess()) {
            while (b.hasAccess()) {
                if (a.getObject().equals(b.getObject())) {
                    foundMatch = true;
                }
                b.next();
            }
            b.toFirst();
            a.next();
        }
        return !foundMatch;
    }
}
