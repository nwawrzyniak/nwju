package nwawsoft.util;

public class Stack {
    private Node head;

    /**
     * Ein leerer Stapel wird erzeugt.
     */
    public Stack() {
        head = null;
    }

    // Stack

    /**
     * Die Anfrage liefert den Wert true, wenn der Stapel
     * keine Objekte enthaelt, sonst liefert sie den Wert false.
     *
     * @return true, falls der Stapel leer ist, sonst false.
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Das Objekt pObject wird oben auf den Stapel gelegt.
     * Falls pObject gleich null ist, bleibt der Stapel unveraendert.
     *
     * @param pObject ist das einzufuegende Objekt.
     */
    public void push(Object pObject) {
        if (pObject != null) {
            Node lNode = new Node(pObject);
            lNode.setNext(head);
            head = lNode;
        }
    }

    /**
     * Das zuletzt eingefuegte Objekt wird von dem Stapel entfernt.
     * Falls der Stapel leer ist, bleibt er unveraendert.
     */
    public void pop() {
        if (!this.isEmpty())
            head = head.getNext();
    }

    /**
     * Die Anfrage liefert das oberste Stapelobjekt.
     * Der Stapel bleibt unveraendert.
     * Falls der Stapel leer ist, wird null zurueck gegeben.
     *
     * @return Inhaltsobjekt
     */
    public Object top() {
        if (!this.isEmpty())
            return head.getContent();
        else
            return null;
    }

    /**
     * Pops the last object from the stack and returns it.
     *
     * @return the latest object from the stack
     */
    public Object popAndTop() {
        if (!this.isEmpty()) {
            Object temp = head.getContent();
            pop();
            return temp;
        }
        else {
            return null;
        }
    }

    /**
     * Wrapper for popAndTop() in case you mixed up the name.
     *
     * @return same as popAndTop().
     */
    public Object TopAndPop() {
        return popAndTop();
    }

    // Node
    private class Node {
        private Object content = null;
        private Node nextNode = null;

        public Node(Object pObject) {
            content = pObject;
            nextNode = null;
        }

        public Node getNext() {
            return nextNode;
        }

        public void setNext(Node pNext) {
            nextNode = pNext;
        }

        public Object getContent() {
            return content;
        }
    } // Ende Node

}
