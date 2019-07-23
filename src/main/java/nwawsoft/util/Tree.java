package nwawsoft.util;

/**
 * Simple Tree structure. Every Tree can be a root itself, can contain one or no 'content' object and none to infinitely
 * many additional 'Tree' objects.
 */
public class Tree {
    private List treeList;
    private Object content;
    private int subTreeCount;

    /**
     * Creates an empty Tree object.
     */
    public Tree() {
        treeList = new List();
        content = null;
        subTreeCount = 0;
    }

    /**
     * Creates a Tree object that stores a passed List of Tree objects.
     *
     * @param pTreeList a List of Tree objects.
     */
    public Tree(final List pTreeList) {
        if (pTreeList.isTypeOrNull(this.getClass())) {
            this.treeList = pTreeList;
            subTreeCount = treeList.count();
        } else {
            removeNonTrees(pTreeList);
        }
        content = null;
    }

    /**
     * Removes all objects that are not from type Tree from treeList. Also sets subTreeCount to treeList.count().
     *
     * @param treeList a List of Tree objects with or without non-Tree objects.
     */
    private void removeNonTrees(final List treeList) {
        DebugPrinter.dp(this, "Found non-Tree objects in treeList. Removing these now.");
        treeList.toFirst();
        while (treeList.hasAccess()) {
            if (treeList.getObject() instanceof Tree) {
                treeList.next();
            } else {
                treeList.remove();
            }
        }
        subTreeCount = treeList.count();
    }

    /**
     * Adds the specified Tree object to treeList and increases subTreeCount by 1 to keep it synchronized.
     *
     * @param t a Tree object.
     */
    public void addTree(final Tree t) {
        treeList.append(t);
        subTreeCount++;
    }

    /**
     * Removes the specified Tree object from treeList and decreases subTreeCount by 1 to keep it synchronized.
     *
     * @param index the index of the tree object to delete, starting from 0.
     */
    public void removeTree(final int index) {
        if (subTreeCount > index) {
            goTo(index);
            treeList.remove();
            subTreeCount--;
        } else {
            DebugPrinter.dp(this, "Tree index out of bounds. The Tree object only got " + subTreeCount +
                    " sub-trees. Tried to access object with index " + index + " (which is object " + (index+1) +
                    ")\nDoing nothing now.");
        }
    }

    /**
     * Returns the content of the Tree object.
     *
     * @return the content of the Tree object.
     */
    public Object getContent() {
        return content;
    }

    /**
     * Sets the content of the Tree object.
     *
     * @param content the content of the Tree object.
     */
    public void setContent(final Object content) {
        this.content = content;
    }

    /**
     * Returns the tree with the specified index, starting from 0.
     *
     * @param index the index of the desired Tree object.
     * @return the Tree object with the specified index.
     */
    public Tree getTree(final int index) {
        if (treeList.count() > index) {
            goTo(index);
            return (Tree)treeList.getObject();
        } else {
            DebugPrinter.dp(this, "Tree index out of bounds. The Tree object only got " + subTreeCount +
                    " sub-trees. Tried to access object with index " + index + " (which is object " + (index+1) +
                    ")\nReturning nothing now.");
        }
        return null;
    }

    /**
     * Returns the treeList of the Tree object.
     * This should never be manually modified. Instead, use the other methods of this class to ensure synchronization
     * between treeList and subTreeCount.
     *
     * @return the List of Tree objects this Tree object contains.
     * @deprecated this is only for easier testing purposes but modifying the internal list via this call may
     * invalidate the integrity of the synchronization between treeList and subTreeCount.
     */
    @Deprecated
    public List getTrees() {
        return treeList;
    }

    /**
     * Sets the access pointer of the internal List object treeList to the specified index.
     * If index > subTreeCount treeList will point to no content.
     *
     * @param index the index of the desired Tree object.
     */
    private void goTo(final int index) {
        treeList.toFirst();
        for (int i = 0; i < index; i++) {
            treeList.next();
        }
    }

    /**
     * Returns the amount of elements in treeList.
     *
     * @return the amount of Tree objects in treeList.
     */
    public int getSubTreeCount() {
        return subTreeCount;
    }
}
