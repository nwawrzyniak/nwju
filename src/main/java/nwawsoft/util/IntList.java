package nwawsoft.util;

/**
 * Works like list but ignores objects except Integers and lists that are not purely made out of
 * Integers.
 * <p>
 * Created by Ernst on 11.07.2017.
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
}
