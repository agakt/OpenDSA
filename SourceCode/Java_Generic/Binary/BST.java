/* *** ODSATag: BST *** */
// Binary Search Tree implementation
class BST<K extends Comparable<K>,E extends Comparable<? super E>> {
  private BSTNode<E> root; // Root of the BST
  private int nodecount; // Number of nodes in the BST

  // constructor
  BST() { root = null; nodecount = 0; }

  // Reinitialize tree
  public void clear() { root = null; nodecount = 0; }

  // Insert a record into the tree.
  // Records can be anything, but they must be Comparable
  // e: The record to insert.
  public void insert(E e) {
    root = inserthelp(root, e);
    nodecount++;
  }

  // Remove a record from the tree
  // k: The key value of record to remove
  // Returns the record removed, null if there is none.
  public E remove(K k) {
    E temp = findhelp(root, k); // First find it
    if (temp != null) {
      root = removehelp(root, k); // Now remove it
      nodecount--;
    }
    return temp;
  }

  // Return the record with key value k, null if none exists
  // k: The key value to find
  public E find(K k) { return findhelp(root, k); }

  // Return the number of records in the dictionary
  public int size() { return nodecount; }
/* *** ODSAendTag: BST *** */

  // Return a record that matches key value k
/* *** ODSATag: findhelp *** */
  private E findhelp(BSTNode<E> rt, K k) {
    if (rt == null) return null;
    if (rt.element().compareTo(k) > 0)
      return findhelp(rt.left(), k);
    else if (rt.element().compareTo(k) == 0)
      return rt.element();
    else return findhelp(rt.right(), k);
  }
/* *** ODSAendTag: findhelp *** */


  // Return the current subtree,
  // modified to contain the new item
/* *** ODSATag: inserthelp *** */
  private BSTNode<E> inserthelp(BSTNode<E> rt, E e) {
    if (rt == null) return new BSTNode<E>(e);
    if (rt.element().compareTo(e) >= 0)
      rt.setLeft(inserthelp(rt.left(), e));
    else
      rt.setRight(inserthelp(rt.right(), e));
    return rt;
  }
/* *** ODSAendTag: inserthelp *** */

/* *** ODSATag: deletemax *** */
  // Delete the maximum valued element in a subtree
  private BSTNode<E> deletemax(BSTNode<E> rt) {
    if (rt.right() == null) return rt.left();
    rt.setRight(deletemax(rt.right()));
    return rt;
  }
/* *** ODSAendTag: deletemax *** */

/* *** ODSATag: getmax *** */
  // Get the maximum valued element in a subtree
  private BSTNode<E> getmax(BSTNode<E> rt) {
    if (rt.right() == null) return rt;
    return getmax(rt.right());
  }
/* *** ODSAendTag: getmax *** */

  // Remove a node with key value k
  // Return the tree with the node removed
/* *** ODSATag: removehelp *** */
  private BSTNode<E> removehelp(BSTNode<E> rt, K k) {
    if (rt == null) return null;
    if (rt.element().compareTo(k) > 0)
      rt.setLeft(removehelp(rt.left(), k));
    else if (rt.element().compareTo(k) < 0)
      rt.setRight(removehelp(rt.right(), k));
    else { // Found it
      if (rt.left() == null) return rt.right();
      else if (rt.right() == null) return rt.left();
      else { // Two children
        BSTNode<E> temp = getmax(rt.left());
        rt.setElement(temp.element());
        rt.setLeft(deletemax(rt.left()));
      }
    }
    return rt;
  }
/* *** ODSAendTag: removehelp *** */

/* *** ODSATag: printhelp *** */
  private void printhelp(BSTNode<E> rt) {
    if (rt == null) return;
    printhelp(rt.left());
    printVisit(rt.element());
    printhelp(rt.right());
  }
/* *** ODSAendTag: printhelp *** */

// Used for testing
  private void printVisit(E e) { System.out.print(e + " "); }

// Used for testing
public BSTNode<E> root() {
  return root;
}
}