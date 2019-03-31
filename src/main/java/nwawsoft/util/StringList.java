package nwawsoft.util;

/**
 * Works like list but ignores objects except Strings and lists that are not purely made out of
 * Strings.
 * <p>
 * Created by Ernst on 11.07.2017.
 */
public class StringList extends List {
    @Override
    public void setObject(Object object) {
        if (object instanceof String) {
            super.setObject(object);
        }
    }

    @Override
    public void append(Object object) {
        if (object instanceof String) {
            super.append(object);
        }
    }

    @Override
    public void insert(Object object) {
        if (object instanceof String) {
            super.insert(object);
        }
    }

    @Override
    public void concat(List pList) {
        if (pList instanceof StringList) {
            super.concat(pList);
        }
    }

    /**
     * Prints the whole StringList one String per line and moves the access pointer to the first
     * object.
     */
    public void print() {
        super.toFirst();
        while (super.hasAccess()) {
            System.out.println((String) super.getObject());
            super.next();
        }
        super.toFirst();
    }
}
