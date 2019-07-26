package nwawsoft.util;

/**
 * Works like list but ignores objects except Integers and lists that are not purely made out of
 * Integers.
 */
public class IntList extends List {
    @Override
    public void setObject(Object object) {
        if (object instanceof Integer) {
            super.setObject(object);
        }
    }

    @Override
    public void append(Object object) {
        if (object instanceof Integer) {
            super.append(object);
        }
    }

    @Override
    public void insert(Object object) {
        if (object instanceof Integer) {
            super.insert(object);
        }
    }

    @Override
    public void concat(List pList) {
        if (pList instanceof IntList) {
            super.concat(pList);
        }
    }

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
