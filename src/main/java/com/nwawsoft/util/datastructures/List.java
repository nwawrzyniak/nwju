package com.nwawsoft.util.datastructures;

import com.nwawsoft.util.tools.DebugPrinter;

/**
 *
 */
public class List {
    private Node first, tail, current;
    private int length;

    /**
     * Eine leere Liste wird erzeugt.
     */
    public List() {
        tail = new Node(null); // Dummy
        first = tail;
        tail.setNext(tail);
        /* Der next-Zeiger des hinteren Dummy-Elementes
         * zeigt auf das vorangehende Element.
         */
        current = first;
        length = 0;
    }

    /**
     * Die Anfrage liefert den Wert true, wenn die Liste
     * keine Objekte enthaelt, sonst liefert sie den Wert false.
     *
     * @return true, wenn die Liste leer ist, sonst false
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Completely empties the list.
     */
    public void clear() {
        this.toFirst();
        while (this.hasAccess()) {
            this.remove();
        }
    }

    /**
     * Die Anfrage liefert den Wert true, wenn es ein
     * aktuelles Objekt gibt, sonst
     * liefert sie den Wert false.
     *
     * @return true, falls Zugriff moeglich, sonst false
     */
    public boolean hasAccess() {
        return (!this.isEmpty()) && (current != tail);
    }

    /**
     * Falls die Liste nicht leer ist, es ein aktuelles
     * Objekt gibt und dieses nicht das letzte Objekt der
     * Liste ist, wird das dem aktuellen Objekt in der Liste
     * folgende Objekt zum aktuellen Objekt, andernfalls gibt
     * es nach Ausfuehrung des Auftrags kein aktuelles Objekt,
     * d.h. hasAccess() liefert den Wert false.
     */
    public void next() {
        if (this.hasAccess())
            current = current.getNext();
    }

    /**
     * Falls die Liste nicht leer ist, wird das erste
     * Objekt der Liste aktuelles Objekt.
     * Ist die Liste leer, geschieht nichts.
     */
    public void toFirst() {
        if (!this.isEmpty())
            current = first;
    }

    /**
     * Falls die Liste nicht leer ist, wird das
     * letzte Objekt der Liste aktuelles Objekt.
     * Ist die Liste leer, geschieht nichts.
     */
    public void toLast() {
        if (!this.isEmpty())
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
        if (this.hasAccess())
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
        if (pObject != null && this.hasAccess())
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
            if (this.isEmpty())
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
            if (this.isEmpty())
                this.append(pObject);
            else if (this.hasAccess()) {
                lPos = current;
                lNewNode = new Node(pObject);
                lNewNode.setNext(current);
                if (lPos == first)
                    first = lNewNode;
                else {
                    this.toFirst();
                    lFront = current;
                    while (this.hasAccess() & !(current == lPos)) {
                        lFront = current;
                        this.next();
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
        Node lFront, lPos;
        if (this.hasAccess()) {
            lPos = current;
            this.toFirst();
            lFront = current;
            while (this.hasAccess() & !(current == lPos)) {
                lFront = current;
                this.next();
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
            if (this.isEmpty()) {
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
        if (this.hasAccess()) {
            if (current == first) {
                first = current.getNext();
                if (current.getNext() == tail)
                    tail.setNext(first);
                current = first;
            } else {
                lPos = current;
                this.toFirst();
                lFront = current;
                while (this.hasAccess() && !(current == lPos)) {
                    lFront = current;
                    this.next();
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
        this.toFirst();
        while (this.hasAccess()) {
            amount++;
            this.next();
        }
        this.toFirst();
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
        this.toFirst();
        while (!this.isEmpty()) {
            temp.push(this.getObject());
            this.remove();
        }
        while (!temp.isEmpty()) {
            this.append(temp.top());
            temp.pop();
        }
        this.toFirst();
    }

    /**
     * Prints all entries of the list, one line per entry, using .toString() to get its String representation.
     */
    public void print() {
        this.toFirst();
        while (this.hasAccess()) {
            try {
                System.out.println(this.getObject().toString());
            } catch (Exception e) {
                DebugPrinter.dp(this, "Couldn't print " + this.getObject() + "'s String representation. Skipping.");
            }
        }
        this.toFirst();
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
    public boolean isTypeOrNull(Class c) {
        this.toFirst();
        while (this.hasAccess()) {
            if (this.getObject() == null || c.isInstance(this.getObject())) {
                this.next();
            } else {
                return false;
            }
        }
        this.toFirst();
        return true;
    }

    /**
     * Sets the access pointer to the start of the list and returns whether all its contents are objects of the
     * specified class.
     *
     * @param c an object of the Class to check the List's content objects against.
     * @return true if all objects are of the specified type, false if any element got a different type.
     */
    public boolean isType(Class c) {
        this.toFirst();
        while (this.hasAccess()) {
            if (c.isInstance(this.getObject())) {
                this.next();
            } else {
                return false;
            }
        }
        this.toFirst();
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
    public static boolean sharesNoEntry(List a, List b) {
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
     * Note that this has a run-time of O(this.count()*b.count()).
     * The 'current' reference of both List objects gets gets set to their respective first elements.
     *
     * @param l the List to compare to
     * @return true if no match was found. Else false.
     */
    public boolean sharesNoEntry(List l) {
        return sharesNoEntry(this, l);
    }

    /**
     * Private class Node.
     * An object of this class can contain up to one Object and can know up to one Node object.
     */
    private class Node {
        private Object contentObj;
        private Node nextNode;

        public Node(Object pContent) {
            contentObj = pContent;
            nextNode = null;
        }

        public void setContent(Object pContent) {
            contentObj = pContent;
        }

        public Object getContent() {
            return contentObj;
        }

        public Node getNext() {
            return nextNode;
        }

        public void setNext(Node pNext) {
            nextNode = pNext;
        }
    }
}
