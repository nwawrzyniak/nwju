package com.nwawsoft.util.datastructures;

import com.nwawsoft.util.tools.DebugPrinter;

/**
 * Works like {@code List} but ignores objects except {@code Integer} objects and {@code List} objects that are not
 * purely made out of {@code Integer}s.
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
     * Multiplies all values of an IntList and returns the result.
     *
     * @param ints any IntList.
     * @return the product of all values of ints.
     */
    public static int multiplyValues(final IntList ints) {
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
     * Sums up all values of an IntList and returns the result.
     *
     * @param ints any IntList.
     * @return the sum of all values of ints.
     */
    public static int addValues(final IntList ints) {
        int sum = 0;
        ints.toFirst();
        while (ints.hasAccess()) {
            sum += (Integer) ints.getObject();
            ints.next();
        }
        return sum;
    }
}
