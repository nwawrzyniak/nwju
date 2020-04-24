package nwawsoft.util.datastructures;

import nwawsoft.util.tools.DebugPrinter;

/**
 * Works like List but ignores objects except Strings and List objects that are not purely made out of Strings.
 */
public class StringList extends List {

    /**
     * Creates a new StringList object.
     */
    public StringList() {
        super();
    }

    /**
     * Sets the Object where the 'current' reference is, but only if it is a String.
     *
     * @param object an Object to set to the 'current' reference of the StringList.
     */
    @Override
    public void setObject(Object object) {
        if (object instanceof String) {
            super.setObject(object);
        }
    }

    /**
     * Appends the Object to the end of the StringList, but only if it is a String.
     *
     * @param object an Object to append to the StringList.
     */
    @Override
    public void append(Object object) {
        if (object instanceof String) {
            super.append(object);
        }
    }

    /**
     * Inserts the Object in front of where the 'current' reference is, but only if it is a String.
     *
     * @param object an Object to insert into the StringList.
     */
    @Override
    public void insert(Object object) {
        if (object instanceof String) {
            super.insert(object);
        }
    }

    /**
     * Concatenates the specified List to the end of the List making this call. Ignores all non-String values.
     *
     * @param pList a List whose contents shall be appended to the StringList.
     */
    @Override
    public void concat(final List pList) {
        if (pList instanceof StringList) {
            super.concat(pList);
        } else {
            pList.toFirst();
            while (pList.hasAccess()) {
                if (pList.getObject() instanceof String) {
                    this.append(pList.getObject());
                } else {
                    DebugPrinter.dp(this, "This is not a String. It is " + pList.getObject() + ". Ignored.");
                }
                next();
            }
        }
    }

    /**
     * Prints the whole StringList one String per line and moves the 'current' reference to the first entry.
     */
    @Override
    public void print() {
        super.toFirst();
        while (super.hasAccess()) {
            System.out.println((String)super.getObject());
            super.next();
        }
        super.toFirst();
    }

    /**
     * Prints the whole StringList with a maximum of outputAmount Strings one String per line and moves the 'current'
     * reference to the first entry.
     *
     * @param outputAmount the maximum number of entries to print, starting at the beginning.
     */
    public void print(final int outputAmount) {
        super.toFirst();
        int count = 0;
        while (super.hasAccess() && count <= outputAmount) {
            count++;
            System.out.println((String)super.getObject());
            super.next();
        }
        super.toFirst();
    }

    /**
     * Prints the rest of the StringList, starting from where the 'current' reference was, one String per line and
     * moves the 'current' reference to the first entry.
     */
    public void printHere() {
        while (super.hasAccess()) {
            System.out.println((String)super.getObject());
            super.next();
        }
        super.toFirst();
    }

    /**
     * Prints the rest of the StringList with a maximum of outputAmount Strings, starting from where the 'current'
     * reference was, one String per line and moves the 'current' reference to the first entry.
     *
     * @param outputAmount the maximum number of entries to print, starting where the 'current' reference is.
     */
    public void printHere(final int outputAmount) {
        int count = 0;
        while (super.hasAccess() && count <= outputAmount) {
            System.out.println((String)super.getObject());
            super.next();
            count++;
        }
        super.toFirst();
    }
}
