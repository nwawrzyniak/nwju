package com.nwawsoft.util.datastructures;

/**
 * Data structure for storing and handling data in FIFO order (first in, first out).
 */
public class Queue {
    private Node head, tail;

    /**
     * Creates an empty queue.
     */
    public Queue() {
        head = null;
        tail = null;
    }

    /**
     * Returns whether the queue is empty.
     *
     * @return true, if the queue is empty. Else false.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Appends (enqueues) a new object to the end of the queue.
     *
     * @param object any object.
     */
    public void enqueue(final Object object) {
        if (object != null) {
            Node node = new Node(object, null);
            if (this.isEmpty()) {
                head = node;
            } else {
                tail.setNext(node);
            }
            tail = node;
        }
    }

    /**
     * The object in front of the queue is removed (dequeued) from the queue.
     * If the queue was empty, nothing happens.
     */
    public void dequeue() {
        if (!this.isEmpty()) {
            head = head.getNext();
        }
    }

    /**
     * Returns the object in front of the queue.
     * Returns null if the queue is empty.
     *
     * @return the object in front of the queue. null if empty.
     */
    public Object front() {
        if (this.isEmpty()) {
            return null;
        } else {
            return head.content();
        }
    }

    /**
     * Represents a single object in the {@code Queue}.
     */
    private static class Node {
        private Object content;
        private Node next;

        public Node(final Object content, final Node next) {
            this.content = content;
            this.next = next;
        }

        public void setContent(final Object content) {
            this.content = content;
        }

        public Object content() {
            return content;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return next;
        }
    }
}
