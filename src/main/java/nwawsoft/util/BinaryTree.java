package nwawsoft.util;

/**
 * Simple Tree structure. Every Tree can be a root itself, can contain one or no 'content' object and none, one or two
 * additional 'Tree' objects. These can be any 'Tree' objects and do not have to be 'BinaryTree' objects.
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
     * Creates a BinaryTree and sets the left and right tree. Contents may be null.
     *
     * @param leftTree the Tree object to go in the leftTree storage.
     * @param rightTree the Tree object to go in the rightTree storage.
     */
    public BinaryTree(Tree leftTree, Tree rightTree) {
        this.leftTree = leftTree;
        this.rightTree = rightTree;
        content = null;
    }

    /**
     * Creates a BinaryTree and sets the left and right tree. Contents may be null.
     *
     * @param leftTree the Tree object to go in the leftTree storage.
     * @param rightTree the Tree object to go in the rightTree storage.
     * @param content any object of any type.
     */
    public BinaryTree(Tree leftTree, Tree rightTree, Object content) {
        this.leftTree = leftTree;
        this.rightTree = rightTree;
        this.content = content;
    }

    /**
     * WHY WOULD YOU WANT TO CREATE A BINARY TREE FROM A LIST?! *sigh* there you go...
     *
     * @param treeList a list of Tree objects.
     * @deprecated why would anyone want this?
     */
    @Deprecated
    public BinaryTree(List treeList) {
        treeList.toFirst();
        leftTree = (Tree)treeList.getObject();
        treeList.next();
        rightTree = (Tree)treeList.getObject();
    }

    public Tree getLeftTree() {
        return leftTree;
    }

    public void setLeftTree(Tree leftTree) {
        this.leftTree = leftTree;
    }

    public Tree getRightTree() {
        return rightTree;
    }

    public void setRightTree(Tree rightTree) {
        this.rightTree = rightTree;
    }

    /**
     * Returns the tree with the specified index, starting from 0.
     *
     * @param index 0 for the first tree, 1 for the second tree.
     * @return the Tree object with the specified index.
     */
    @Override
    public Tree getTree(int index) {
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
    public void setContent(Object content) {
        this.content = content;
    }

    /**
     * Sets the passed Tree object to the next available Tree slot. If there is none, it is ignored.
     *
     * @param t a Tree object.
     * @deprecated use setLeftTree/setRightTree/getLeftTree/getRightTree instead.
     */
    @Override
    @Deprecated
    public void addTree(Tree t) {
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
    public void removeTree(int index) {
        DebugPrinter.dp(this, "This shouldn't be used.");
    }

    /**
     * Returns the amount of non-null Tree objects in the BinaryTree.
     * This can be either 0, 1 or 2.
     *
     * @return the amount of Tree objects in this BinaryTree.
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
