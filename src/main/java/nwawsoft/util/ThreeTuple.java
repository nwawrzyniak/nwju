package nwawsoft.util;

/**
 * Simple data type for storing, passing and working with three objects of any type.
 */
public class ThreeTuple {
    private Object object1;
    private Object object2;
    private Object object3;

    /**
     * Creates an empty ThreeTuple. All objects are initialized to null.
     */
    public ThreeTuple() {
        object1 = null;
        object2 = null;
        object3 = null;
    }

    /**
     * Creates a ThreeTuple from the specified objects. Objects may be null.
     *
     * @param object1 the first object.
     * @param object2 the second object.
     * @param object3 the third object.
     */
    public ThreeTuple(Object object1, Object object2, Object object3) {
        this.object1 = object1;
        this.object2 = object2;
        this.object3 = object3;
    }

    public Object getObject1() {
        return object1;
    }

    public void setObject1(Object object1) {
        this.object1 = object1;
    }

    public Object getObject2() {
        return object2;
    }

    public void setObject2(Object object2) {
        this.object2 = object2;
    }

    public Object getObject3() {
        return object3;
    }

    public void setObject3(Object object3) {
        this.object3 = object3;
    }

    /**
     * Returns the class/type of the first object.
     *
     * @return a reflection of the class of 'object1'.
     */
    public Class objectOneClass() {
        return object1.getClass();
    }

    /**
     * Returns the class/type of the second object.
     *
     * @return a reflection of the class of 'object2'.
     */
    public Class objectTwoClass() {
        return object2.getClass();
    }

    /**
     * Returns the class/type of the third object.
     *
     * @return a reflection of the class of 'object3'.
     */
    public Class objectThreeClass() {
        return object3.getClass();
    }
}
