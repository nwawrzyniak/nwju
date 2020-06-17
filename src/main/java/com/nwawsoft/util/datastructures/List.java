package com.nwawsoft.util.datastructures;

import com.nwawsoft.util.tools.DebugPrinter;

/**
 * List data structure. Can be used with any object or extended for a specific type as in the examples IntList and
 * StringList.
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
     * element in the list the current pointer moves to the next object. If any of these conditions don't apply there
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
     * Falls es ein aktuelles Objekt gibt
     * (hasAccess() == true), wird das aktuelle Objekt
     * zurueckgegeben, andernfalls (hasAccess()== false)
     * gibt die Anfrage den Wert null zurueck.
     *
     * @return Inhaltsobjekt
     */
    public Object getObject() {
        if (hasAccess())
            return current.getContent();
        else
            return null;
    }

    /**
     * Falls es ein aktuelles Objekt gibt (hasAccess() == true)
     * und pObject ungleich null ist, wird das aktuelle Objekt
     * durch pObject ersetzt. Sonst bleibt die Liste unveraendert.
     *
     * @param pObject Inhaltsobjekt
     */
    public void setObject(final Object pObject) {
        if (pObject != null && hasAccess())
            current.setContent(pObject);
    }

    /**
     * Ein neues Objekt pObject wird am Ende der Liste eingefuegt.
     * Das aktuelle Objekt bleibt unveraendert. Wenn die Liste
     * leer ist, wird das Objekt pObject in die Liste eingefuegt
     * und es gibt weiterhin kein aktuelles Objekt
     * (hasAccess() == false). Falls pObject gleich null ist,
     * bleibt die Liste unveraendert.
     *
     * @param pObject Inhaltsobject
     */
    public void append(final Object pObject) {
        if (pObject != null) {
            Node lNewNode, lPos0;
            lPos0 = current;
            lNewNode = new Node(pObject);
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
     * Falls es ein aktuelles Objekt gibt (hasAccess() == true),
     * wird ein neues Objekt vor dem aktuellen Objekt in die
     * Liste eingefuegt. Das aktuelle Objekt bleibt unveraendert.
     * Wenn die Liste leer ist, wird pObject in die Liste eingefuegt
     * und es gibt weiterhin kein aktuelles Objekt
     * (hasAccess() == false). Falls es kein aktuelles Objekt gibt
     * (hasAccess() == false) und die Liste nicht leer ist oder
     * pObject gleich null ist, bleibt die Liste unveraendert.
     *
     * @param pObject Inhaltsobjekt
     */
    public void insert(final Object pObject) {
        if (pObject != null) {
            Node lNewNode, lFront, lPos;
            if (isEmpty())
                append(pObject);
            else if (hasAccess()) {
                lPos = current;
                lNewNode = new Node(pObject);
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
     * Wenn es ein aktuellers Objekt gibt wird das vorherige Objekt
     * zum aktuellen Objekt. Wenn das erste Objekt der Liste aktuelles
     * Objekt ist veraendert sich das aktuelle Objekt nicht.
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
     * Die Liste pList wird an die Liste angehaengt. Das aktuelle Objekt bleibt unveraendert.
     * Falls pList null oder eine leere Liste ist, bleibt die Liste unveraendert.
     *
     * @param pList Liste
     */
    public void concat(final List pList) {
        Node lCurrent1, lCurrent2, lPos0;
        if (pList != null && !pList.isEmpty()) {
            if (isEmpty()) {
                first = pList.first;
                tail = pList.tail;
                current = tail;
            } else {
                lPos0 = current;
                current = tail.getNext();
                lCurrent1 = current;
                pList.toFirst();
                current = pList.current;
                lCurrent2 = pList.current;
                lCurrent1.setNext(lCurrent2);
                if (lPos0 != tail)
                    current = lPos0;
                else
                    current = pList.tail;
                tail = pList.tail;
            }
            length += pList.getLength();
        }
    }

    /**
     * Falls es ein aktuelles Objekt gibt (hasAccess() == true),
     * wird das aktuelle Objekt geloescht und das Objekt hinter
     * dem geloeschten Objekt wird zum aktuellen Objekt. Wird das
     * Objekt, das am Ende der Liste steht, geloescht, gibt es kein
     * aktuelles Objekt mehr (hasAccess() == false). Wenn die Liste
     * leer ist oder es kein aktuelles Objekt gibt (hasAccess() == false),
     * bleibt die Liste unveraendert.
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
     * Counts the elements in the list. The 'current' reference gets set to the first element of the List.
     *
     * @return the amount of elements in the list.
     * @deprecated use getLength() instead to not move the 'current' reference.
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
     * Returns the amount of elements in the List.
     *
     * @return the amount of elements in the list.
     */
    public int getLength() {
        return length;
    }

    /**
     * Inverts the list. The new first object becomes current object.
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
     * Sets the access pointer to the start of the list and returns whether its contents are objects of the specified
     * class or null only.
     *
     * @param c an object of the Class to check the List's content objects against.
     * @return true if all objects are either the specified type or empty, false if any element got a different type.
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
     * Sets the access pointer to the start of the list and returns whether all its contents are objects of the
     * specified class.
     *
     * @param c an object of the Class to check the List's content objects against.
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
     * Note that this has a run-time of O(a.count()*b.count()).
     * The 'current' reference of both List objects gets gets set to their respective first elements.
     *
     * @param a the first List
     * @param b the second List
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
     * Checks whether the list has no entry that fits Object.equals() for any object in the specified List.
     * Note that this has a run-time of O(count()*b.count()).
     * The 'current' reference of both List objects gets gets set to their respective first elements.
     *
     * @param l the List to compare to
     * @return true if no match was found. Else false.
     */
    public boolean sharesNoEntry(final List l) {
        return sharesNoEntry(this, l);
    }

    /**
     * Private class Node.
     * An object of this class can contain up to one Object and can know up to one Node object.
     */
    private static class Node {
        private Object contentObj;
        private Node nextNode;

        public Node(final Object pContent) {
            contentObj = pContent;
            nextNode = null;
        }

        public void setContent(final Object pContent) {
            contentObj = pContent;
        }

        public Object getContent() {
            return contentObj;
        }

        public Node getNext() {
            return nextNode;
        }

        public void setNext(final Node pNext) {
            nextNode = pNext;
        }
    }
}
