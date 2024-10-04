package com.nwawsoft.util.datastructures;

import com.nwawsoft.util.tools.DebugPrinter;

/**
 * List data structure.
 * Can be used with any object or extended for a specific type as in the examples IntList and StringList.
 */
public class List {
    private Node first, tail, current;
    private int length;

    /**
     * Creates an empty list.
     */
    public List() {
        tail = new Node(null); // Dummy
        first = tail;
        tail.setNext(tail);
        current = first;
        length = 0;
    }

    /**
     * Returns whether the list is empty.
     *
     * @return true if list is empty. Else false.
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Completely empties the list.
     */
    public void clear() {
        toFirst();
        while (hasAccess()) {
            remove();
        }
        length = 0; // technically unneeded since remove() decreases length anyway.
    }

    /**
     * Returns whether a currently selected object exists.
     *
     * @return true if a currently selected object exists. Else false.
     */
    public boolean hasAccess() {
        return (!isEmpty()) && (current != tail);
    }

    /**
     * If the list is not empty, a currently selected object exists and the currently selected object is not the last
     * element in the list the current pointer moves to the next object.
     * If any of these conditions don't apply there
     * will not be a currently selected object after execution.
     */
    public void next() {
        if (hasAccess())
            current = current.getNext();
    }

    /**
     * Moves the currently selected object pointer to the first object of the list.
     */
    public void toFirst() {
        if (!isEmpty())
            current = first;
    }

    /**
     * Moves the currently selected object pointer to the last object of the list.
     */
    public void toLast() {
        if (!isEmpty())
            current = tail.getNext();
    }

    /**
     * Returns the content of the currently selected object if a currently selected object exists.
     * Else returns null.
     *
     * @return the content of the currently selected object if a currently selected object exists. Else null.
     */
    public Object getObject() {
        if (hasAccess())
            return current.getContent();
        else
            return null;
    }

    /**
     * Replaces the content of the currently selected object with the specified content if there is any.
     * If there is none, nothing happens.
     *
     * @param o any object.
     */
    public void setObject(final Object o) {
        if (o != null && hasAccess())
            current.setContent(o);
    }

    /**
     * Adds the specified object to the end of the list.
     * The currently selected object remains unchanged.
     * If the list was empty the object is added and there is still no currently selected object.
     * If the specified object is null it is not added.
     *
     * @param o any object.
     */
    public void append(final Object o) {
        if (o != null) {
            Node lNewNode, lPos0;
            lPos0 = current;
            lNewNode = new Node(o);
            lNewNode.setNext(tail);
            if (isEmpty())
                first = lNewNode;
            else {
                Node lPrevious = tail.getNext();
                lPrevious.setNext(lNewNode);
            }
            tail.setNext(lNewNode);
            current = lPos0;
            length++;
        }
    }

    /**
     * If there is a currently selected object the specified element is added before it.
     * The currently selected object remains unchanged.
     * If the list is empty the specified object is added and there is still no currently selected object.
     * If there is no currently selected object and the list is non-empty or if the specified object is null nothing
     * happens.
     *
     * @param o any object.
     */
    public void insert(final Object o) {
        if (o != null) {
            Node lNewNode, lFront, lPos;
            if (isEmpty())
                append(o);
            else if (hasAccess()) {
                lPos = current;
                lNewNode = new Node(o);
                lNewNode.setNext(current);
                if (lPos == first)
                    first = lNewNode;
                else {
                    toFirst();
                    lFront = current;
                    while (hasAccess() & !(current == lPos)) {
                        lFront = current;
                        next();
                    }
                    lFront.setNext(lNewNode);
                }
                current = lPos;
            }
            length++;
        }
    }

    /**
     * If there is a currently selected object the previous object becomes the currently selected object.
     * If the object was the first object of the list or if there is no currently selected object nothing happens.
     */
    public void previous() {
        Node lPos;
        if (hasAccess()) {
            lPos = current;
            toFirst();
            while (hasAccess() & !(current == lPos)) {
                next();
            }
            current = lPos;
        }
    }

    /**
     * Concatenates a specified list to the list.
     * The currently selected object remains unchanged.
     * If the specified list is null or empty nothing happens.
     *
     * @param l any list.
     */
    public void concat(final List l) {
        Node lCurrent1, lCurrent2, lPos0;
        if (l != null && !l.isEmpty()) {
            if (isEmpty()) {
                first = l.first;
                tail = l.tail;
                current = tail;
            } else {
                lPos0 = current;
                current = tail.getNext();
                lCurrent1 = current;
                l.toFirst();
                current = l.current;
                lCurrent2 = l.current;
                lCurrent1.setNext(lCurrent2);
                if (lPos0 != tail)
                    current = lPos0;
                else
                    current = l.tail;
                tail = l.tail;
            }
            length += l.getLength();
        }
    }

    /**
     * If there is a currently selected object it is removed from the list and the object behind it becomes the new
     * currently selected object.
     * If the last object of the list gets removed there will no longer be a currently selected object.
     * If the list is empty or there is no currently selected object nothing happens.
     */
    public void remove() {
        Node lPos, lFront;
        if (hasAccess()) {
            if (current == first) {
                first = current.getNext();
                if (current.getNext() == tail)
                    tail.setNext(first);
                current = first;
            } else {
                lPos = current;
                toFirst();
                lFront = current;
                while (hasAccess() && !(current == lPos)) {
                    lFront = current;
                    next();
                }
                lFront.setNext(lPos.getNext());
                current = lFront.getNext();
                if (current == tail)
                    tail.setNext(lFront);
            }
            length--;
        }
    }

    /**
     * Counts the elements in the list.
     * The currently selected object gets set to the first object of the List.
     *
     * @return the amount of objects in the list.
     * @deprecated use getLength() instead to not change the currently selected object.
     */
    @Deprecated
    public int count() {
        int amount = 0;
        toFirst();
        while (hasAccess()) {
            amount++;
            next();
        }
        toFirst();
        return amount;
    }

    /**
     * Returns the amount of elements in the list.
     *
     * @return the amount of elements in the list.
     */
    public int getLength() {
        return length;
    }

    /**
     * Inverts the list.
     * The new first object becomes the currently selected object.
     */
    public void reverse() {
        Stack temp = new Stack();
        toFirst();
        while (!isEmpty()) {
            temp.push(getObject());
            remove();
        }
        while (!temp.isEmpty()) {
            append(temp.top());
            temp.pop();
        }
        toFirst();
    }

    /**
     * Prints all entries of the list, one line per entry, using .toString() to get its String representation.
     */
    public void print() {
        toFirst();
        while (hasAccess()) {
            try {
                System.out.println(getObject().toString());
            } catch (Exception e) {
                DebugPrinter.dp(this, "Couldn't print " + getObject() + "'s String representation. Skipping.");
            }
        }
        toFirst();
    }

    /**
     * Returns a String with an amount of spaces equal to ((amount of digits in length) - 1) so it can be used for
     * formatting an index number.
     *
     * @return a String containing white spaces.
     */
    private String getIndexSpacing() {
        StringBuilder spaces = new StringBuilder();
        int spacesAmount = 0;
        int remainingLength = length / 10;
        while (remainingLength > 0) {
            spacesAmount++;
            remainingLength /= 10;
        }
        for (int i = 0; i < spacesAmount; i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }

    /**
     * Returns whether all contents of the list are objects of the specified class or null and sets the currently
     * selected object to the first object.
     *
     * @param c an object of the Class to check the list's content objects against.
     * @return true if all objects are either the specified type or null, false if any element got a different type.
     */
    public boolean isTypeOrNull(final Class c) {
        toFirst();
        while (hasAccess()) {
            if (getObject() == null || c.isInstance(getObject())) {
                next();
            } else {
                return false;
            }
        }
        toFirst();
        return true;
    }

    /**
     * Returns whether all contents of the list are objects of the specified class and sets the currently selected
     * object to the first object.
     *
     * @param c an object of the Class to check the list's content objects against.
     * @return true if all objects are of the specified type, false if any element got a different type.
     */
    public boolean isType(final Class c) {
        toFirst();
        while (hasAccess()) {
            if (c.isInstance(getObject())) {
                next();
            } else {
                return false;
            }
        }
        toFirst();
        return true;
    }

    /**
     * Checks whether two lists have no entries that fit Object.equals().
     * The currently selected object of both lists gets set to their respective first elements.
     *
     * Note that this has a run-time of O(a.getLength()*b.getLength()).
     *
     * @param a any list.
     * @param b a second list.
     * @return true if no match was found. Else false.
     */
    public static boolean sharesNoEntry(final List a, final List b) {
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
        a.toFirst();
        b.toFirst();
        return !foundMatch;
    }

    /**
     * Checks whether the list has no entry that fits Object.equals() for any object in the specified list.
     * The currently selected object of both lists gets set to their respective first elements.
     *
     * Note that this has a run-time of O(length*b.getLength()).
     *
     * @param l any list.
     * @return true if no match was found. Else false.
     */
    public boolean sharesNoEntry(final List l) {
        return sharesNoEntry(this, l);
    }

    /**
     * Represents a list entry. Every Node may have a content and may know a next Node.
     */
    private static class Node {
        private Object content;
        private Node next;

        public Node(final Object o) {
            content = o;
            next = null;
        }

        public void setContent(final Object o) {
            content = o;
        }

        public Object getContent() {
            return content;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(final Node n) {
            next = n;
        }
    }
}
