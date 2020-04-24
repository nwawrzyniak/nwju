package nwawsoft.util.datastructures;

import nwawsoft.util.tools.DebugPrinter;

/**
 * Tree data structure that can contain up to 2 additional {@code Tree}s.
 * Every {@code BinaryTree} can be a root itself, can contain one or no {@code content} object and none, one or two
 * additional {@code Tree} objects.
 * These can be any {@code Tree} objects and do not have to be {@code BinaryTree} objects.
 */
public class BinaryTree extends Tree {
    private Tree leftTree;
    private Tree rightTree;
    private Object content;

    /**
     * Creates an empty BinaryTree.
     */
    public BinaryTree() {
        leftTree = null;
        rightTree = null;
        content = null;
    }

    /**
     * Creates a {@code BinaryTree} and sets the left and right tree. Contents may be null.
     *
     * @param leftTree the Tree object to go in the leftTree storage.
     * @param rightTree the Tree object to go in the rightTree storage.
     */
    public BinaryTree(final Tree leftTree, final Tree rightTree) {
        this.leftTree = leftTree;
        this.rightTree = rightTree;
        content = null;
    }

    /**
     * Creates a {@code BinaryTree} and sets the left and right tree. Contents may be null.
     *
     * @param leftTree the Tree object to go in the leftTree storage.
     * @param rightTree the Tree object to go in the rightTree storage.
     * @param content any object of any type.
     */
    public BinaryTree(final Tree leftTree, final Tree rightTree, final Object content) {
        this.leftTree = leftTree;
        this.rightTree = rightTree;
        this.content = content;
    }

    /**
     * Creates a new {@code BinaryTree} from the first two objects of treeList and sets them as leftTree and rightTree,
     * respectively.
     * Exists only because the normal {@code Tree} can be created with a child list as well.
     *
     * @param treeList a list of Tree objects.
     * @deprecated Why would anyone want to create a {@code BinaryTree} from a {@code List}?! Just use any other
     * constructor.
     */
    @Deprecated
    public BinaryTree(final List treeList) {
        if (treeList.getLength() >= 2) {
            treeList.toFirst();
            leftTree = (Tree) treeList.getObject();
            treeList.next();
            rightTree = (Tree) treeList.getObject();
        } else if (treeList.getLength() == 1) {
            treeList.toFirst();
            leftTree = (Tree) treeList.getObject();
            DebugPrinter.dp(this, "I only got one Tree object from treeList, so I will only fill leftTree and set " +
                    "rightTree to null.");
            rightTree = null;
        } else {
            leftTree = null;
            rightTree = null;
            DebugPrinter.dp(this, "I got no Tree objects from treeList, so I will set leftTree and rightTree to null.");
        }
        content = null;
    }

    public Tree getLeftTree() {
        return leftTree;
    }

    public void setLeftTree(final Tree leftTree) {
        this.leftTree = leftTree;
    }

    public Tree getRightTree() {
        return rightTree;
    }

    public void setRightTree(final Tree rightTree) {
        this.rightTree = rightTree;
    }

    /**
     * Returns the {@code Tree} with the specified index, starting from 0.
     *
     * @param index 0 for the first tree, 1 for the second tree.
     * @return the Tree object with the specified index.
     */
    @Override
    public Tree getTree(final int index) {
        if (index == 0) {
            return leftTree;
        } else if (index == 1) {
            return rightTree;
        } else {
            DebugPrinter.dp(this, "Tree index out of bounds. Has to be 0 or 1. Value was " + index + ". Returning " +
                    "null.");
            return null;
        }
    }

    /**
     * Returns the stored object.
     *
     * @return null if content is empty, otherwise the content object.
     */
    @Override
    public Object getContent() {
        return content;
    }

    /**
     * Sets an object into the Tree's content storage.
     *
     * @param content any object of any type. May be null.
     */
    @Override
    public void setContent(final Object content) {
        this.content = content;
    }

    /**
     * Sets the passed Tree object to the next available slot (starting with the left). If there is none, it is ignored.
     *
     * @param t a Tree object.
     * @deprecated use setLeftTree/setRightTree/getLeftTree/getRightTree instead.
     */
    @Override
    @Deprecated
    public void addTree(final Tree t) {
        if (leftTree == null) {
            leftTree = t;
        } else if (rightTree == null) {
            rightTree = t;
        } else {
            DebugPrinter.dp(this, "No empty space found for tree object " + t);
        }
    }

    /**
     * Does nothing.
     *
     * @param index the index of nothing, because this shouldn't be used.
     * @deprecated use setLeftTree/setRightTree/getLeftTree/getRightTree instead.
     */
    @Override
    @Deprecated
    public void removeTree(final int index) {
        DebugPrinter.dp(this, "Not implemented for BinaryTrees.");
    }

    /**
     * Returns the amount of non-null tree objects in the {@code BinaryTree}.
     * This can be either 0, 1 or 2.
     *
     * @return the amount of tree objects in this {@code BinaryTree}.
     */
    @Override
    public int getSubTreeCount() {
        int count = 0;
        if (leftTree != null) {
            count++;
        }
        if (rightTree != null) {
            count++;
        }
        return count;
    }
}
