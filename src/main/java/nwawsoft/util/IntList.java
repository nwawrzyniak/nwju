package nwawsoft.util;

/**
 * Works like List but ignores objects except Integer objects and List objects that are not purely made out of
 * Integers.
 */
public class IntList extends List {
    @Override
    public void setObject(Object object) {
        if (object instanceof Integer) {
            super.setObject(object);
        } else {
            DebugPrinter.dp(this, "This is not an Integer. It is " + object + ". Ignored.");
        }
    }

    @Override
    public void append(Object object) {
        if (object instanceof Integer) {
            super.append(object);
        } else {
            DebugPrinter.dp(this, "This is not an Integer. It is " + object + ". Ignored.");
        }
    }

    @Override
    public void insert(Object object) {
        if (object instanceof Integer) {
            super.insert(object);
        } else {
            DebugPrinter.dp(this, "This is not an Integer. It is " + object + ". Ignored.");
        }
    }

    @Override
    public void concat(List pList) {
        if (pList instanceof IntList) {
            super.concat(pList);
        } else {
            pList.toFirst();
            while (pList.hasAccess()) {
                if (pList.getObject() instanceof Integer) {
                    this.append(pList.getObject());
                } else {
                    DebugPrinter.dp(this, "This is not an Integer. It is " + pList.getObject() + ". Ignored.");
                }
                next();
            }
        }
    }

    /**
     * Prints the whole IntList one Integer per line and moves the 'current' reference to the first entry.
     */
    @Override
    public void print() {
        super.toFirst();
        while (super.hasAccess()) {
            System.out.println(super.getObject());
            super.next();
        }
        super.toFirst();
    }

    /**
     * // ToDo doc
     *
     * @param ints
     * @return
     */
    public static int multiplyValues(IntList ints) {
        int product = 1;
        if (ints != null) {
            ints.toFirst();
            if (ints.hasAccess()) {
                while (ints.hasAccess()) {
                    product *= (Integer) ints.getObject();
                    ints.next();
                    if (product == 0) {
                        return 0;
                    }
                }
            } else {
                throw new IllegalArgumentException("IntList ints may not be empty.");
            }
        } else {
            throw new IllegalArgumentException("IntList ints may not be null.");
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
