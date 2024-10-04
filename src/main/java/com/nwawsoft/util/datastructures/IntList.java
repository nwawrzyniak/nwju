package com.nwawsoft.util.datastructures;

import com.nwawsoft.util.tools.DebugPrinter;

/**
 * Works like {@code List} but ignores objects except {@code Integer}.
 */
public class IntList extends List {
    @Override
    public void setObject(final Object o) {
        if (o instanceof Integer) {
            super.setObject(o);
        } else {
            DebugPrinter.dp(this, "This is not an Integer. It is " + o + ". Ignored.");
        }
    }

    @Override
    public void append(final Object o) {
        if (o instanceof Integer) {
            super.append(o);
        } else {
            DebugPrinter.dp(this, "This is not an Integer. It is " + o + ". Ignored.");
        }
    }

    @Override
    public void insert(final Object o) {
        if (o instanceof Integer) {
            super.insert(o);
        } else {
            DebugPrinter.dp(this, "This is not an Integer. It is " + o + ". Ignored.");
        }
    }

    /**
     * Appends all Integers in l to the IntList.
     * After this, l.hasAccess() will be false.
     *
     * @param l any list.
     */
    @Override
    public void concat(final List l) {
        if (l instanceof IntList) {
            super.concat(l);
        } else {
            l.toFirst();
            while (l.hasAccess()) {
                if (l.getObject() instanceof Integer) {
                    this.append(l.getObject());
                } else {
                    DebugPrinter.dp(this, "This is not an Integer. It is " + l.getObject() + ". Ignored.");
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
     * Returns the value at the current position.
     *
     * @return the value at the current position.
     */
    public int get() {
        return (Integer)super.getObject();
    }

    /**
     * Alias for get().
     *
     * @return the value at the current position.
     */
    public int getValue() {
        return get();
    }

    /**
     * Alias for get().
     *
     * @return the value at the current position.
     */
    public int getContent() {
        return get();
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
                    if (product == 0) return 0;
                }
            } else throw new IllegalArgumentException("IntList ints may not be empty.");
        } else throw new IllegalArgumentException("IntList ints may not be null.");
        return product;
    }

    /**
     * Sums up all values of an IntList and returns the result.
     * The
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
