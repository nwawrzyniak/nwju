package nwawsoft.util.datastructures;

/**
 * Data structure for storing and handling data in FILO order (first in, last out).
 */
public class Stack {
    private Node head;

    /**
     * Creates an empty stack.
     */
    public Stack() {
        head = null;
    }

    /**
     * Returns whether the stack is empty.
     *
     * @return true, if the stack is empty. Else false.
     */
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Puts (pushes) a new object on the top of the stack.
     *
     * @param object any object.
     */
    public void push(final Object object) {
        if (object != null) {
            Node node = new Node(object);
            node.setNext(head);
            head = node;
        }
    }

    /**
     * The upmost object is removed (popped) from the stack.
     * If the stack was empty, nothing happens.
     */
    public void pop() {
        if (!this.isEmpty())
            head = head.getNext();
    }

    /**
     * Returns the upmost object of the stack.
     * Returns null if the stack is empty.
     *
     * @return the upmost object of the stack. null if empty.
     */
    public Object top() {
        if (!this.isEmpty())
            return head.getContent();
        else
            return null;
    }

    /**
     * Removes (pops) the upmost object from the stack and returns the new upmost object.
     *
     * @return the upmost object of the stack after a pop.
     */
    public Object popAndTop() {
        if (!this.isEmpty()) {
            pop();
            return head.getContent();
        }
        else {
            return null;
        }
    }

    /**
     * Removes (pops) the upmost object from the stack and returns it.
     *
     * @return the upmost object of the stack before the pop.
     */
    public Object topAndPop() {
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
     * Represents a single object in the {@code Stack}.
     */
    private static class Node {
        private Object content = null;
        private Node nextNode = null;

        public Node(final Object pObject) {
            content = pObject;
            nextNode = null;
        }

        public Node getNext() {
            return nextNode;
        }

        public void setNext(final Node pNext) {
            nextNode = pNext;
        }

        public Object getContent() {
            return content;
        }
    }
}
