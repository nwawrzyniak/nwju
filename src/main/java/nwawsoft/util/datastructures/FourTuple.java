package nwawsoft.util.datastructures;

/**
 * A data type for storing, passing and working with four objects of any type.
 */
public class FourTuple {
    private Object object1;
    private Object object2;
    private Object object3;
    private Object object4;

    /**
     * Creates an empty FourTuple. All objects are initialized to null.
     */
    public FourTuple() {
        object1 = null;
        object2 = null;
        object3 = null;
        object4 = null;
    }

    /**
     * Creates a FourTuple from the specified objects. Objects may be null.
     *
     * @param object1 the first object.
     * @param object2 the second object.
     * @param object3 the third object.
     * @param object4 the fourth object.
     */
    public FourTuple(Object object1, Object object2, Object object3, Object object4) {
        this.object1 = object1;
        this.object2 = object2;
        this.object3 = object3;
        this.object4 = object4;
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

    public Object getObject4() {
        return object4;
    }

    public void setObject4(Object object4) {
        this.object4 = object4;
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

    /**
     * Returns the class/type of the fourth object.
     *
     * @return a reflection of the class of 'object4'.
     */
    public Class objectFourClass() {
        return object4.getClass();
    }
}
