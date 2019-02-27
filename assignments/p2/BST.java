import java.util.List;

public class BST<K extends Comparable<K>, V> implements BSTADT<K, V> {

  // Tip: Use protected fields so that they may be inherited by AVL
  protected BSTNode<K, V> root;
  protected int numKeys; // number of keys in BST

  // Must have a public, default no-arg constructor
  public BST() {
    this.numKeys = 0;
    this.root = null;
  }

  /*
   * (non-Javadoc)
   * 
   * VLR
   */
  @Override
  public List<K> getPreOrderTraversal() {
    int i = 0;
    Object[] ls = new Object[100];

    BSTNode<K, V> current = this.root;

    helperGetPreOrderTraversal(ls, this.root);
    return null;
  }

  private void helperGetPreOrderTraversal(Object[] ls, BSTNode<K, V> current) {
    // base case
    if (current == null)
      return;
    // Add current to list
    // visit left
    helperGetPreOrderTraversal(ls, current.left);
    // visit right
    helperGetPreOrderTraversal(ls, current.right);

  }

  @Override
  public List<K> getPostOrderTraversal() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * L , R, V
   */

  private List<K> helperGetPostOrderTraversal(Object[] ls, BSTNode<K, V> current) {
    helperGetPreOrderTraversal(ls, current.left);
    // visit right
    helperGetPreOrderTraversal(ls, current.right);
    // base case
    if (current == null)
      return null;
    // Add current to list
    // visit left

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see SearchTreeADT#getLevelOrderTraversal()
   */
  @Override
  public List<K> getLevelOrderTraversal() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see SearchTreeADT#getInOrderTraversal()
   */
  @Override
  public List<K> getInOrderTraversal() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    // Check for Exeption rules
    if (contains(key))
      throw new DuplicateKeyException();
    else if (key == null)
      throw new IllegalNullKeyException();

    // Start loop for finding null child
    BSTNode<K, V> current = this.root;
    while (current != null) {
      if (current.key.compareTo(key) < 0) {
        if (current.right == null) {
          current.right = new BSTNode<K, V>(key, value);
          return;
        }
        current = current.right;
      }

      else if (current.key.compareTo(key) > 0) {
        if (current.left == null) {
          current.left = new BSTNode<K, V>(key, value);
          return;
        }
        current = current.left;
      }
    }
    this.numKeys += 1;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#remove(java.lang.Comparable)
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null)
      throw new IllegalNullKeyException();
    else if (!contains(key))
      throw new KeyNotFoundException();

    BSTNode<K, V> parent;
    BSTNode<K, V> current = this.root;

    while (current.key.compareTo(key) != 0) {
      if (current.key.compareTo(key) < 0) {
        parent = current;
        current = current.right;
      } else {
        parent = current;
        current = current.left;
      }
    }

    return false;
  }

  private boolean helperRemove(BSTNode<K, V> parent, BSTNode<K, V> node) {
    // If node to remove has no children
    if (numChildren(node) == 0) {
      if (node.key.compareTo(parent.key) < 0)
        parent.left = null;
      else
        parent.right = null;
    }
    if (numChildren(node) == 2 && node.key.compareTo(parent.key) < 0) {
      BSTNode<K, V> successor = node.right;

    } else {

    }

    return false;
  }

  private int numChildren(BSTNode<K, V> node) {
    if (node.left != null && node.right != null)
      return 2;
    if (node.left == null && node.right == null)
      return 0;
    else
      return 1;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#get(java.lang.Comparable)
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    BSTNode<K, V> current = this.root;
    while (current != null) {
      if (current.key == key)
        return current.value;
      else if (current.key.compareTo(key) < 0)
        current = current.right;
      else if (current.key.compareTo(key) > 0)
        current = current.left;
    }
    throw new KeyNotFoundException();
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#contains(java.lang.Comparable)
   */
  @Override
  public boolean contains(K key) throws IllegalNullKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }

    BSTNode<K, V> current = this.root;

    while (current != null) {
      if (current.key.compareTo(key) == 0)
        return true;
      else if (current.key.compareTo(key) < 0)
        current = current.right;
      else if (current.key.compareTo(key) > 0)
        current = current.left;
    }
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#numKeys()
   */
  @Override
  public int numKeys() {
    return this.numKeys;
  }

  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getKeyAtRoot()
   */
  @Override
  public K getKeyAtRoot() {
    return this.root.key;
  }

  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getKeyOfLeftChildOf(java.lang.Comparable)
   */
  @Override
  public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null)
      throw new IllegalNullKeyException();
    else if (!contains(key))
      throw new KeyNotFoundException();

    BSTNode<K, V> current = this.root;
    K foundKey = null;

    while (current != null) {
      if (current.key.compareTo(key) == 0)
        foundKey = current.left.key;
      else if (current.key.compareTo(key) < 0)
        current = current.right;
      else if (current.key.compareTo(key) > 0)
        current = current.left;

    }
    return foundKey;
  }

  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getKeyOfRightChildOf(java.lang.Comparable)
   */
  @Override
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null)
      throw new IllegalNullKeyException();
    else if (!contains(key))
      throw new KeyNotFoundException();

    BSTNode<K, V> current = this.root;
    K foundKey = null;

    while (current != null) {
      if (current.key.compareTo(key) == 0)
        foundKey = current.right.key;
      else if (current.key.compareTo(key) < 0)
        current = current.right;
      else if (current.key.compareTo(key) > 0)
        current = current.left;
    }
    return foundKey;
  }

  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getHeight()
   */
  @Override
  public int getHeight() {
    if (root == null) {
      return 0;
    }

    else if (root.left == null && root.right == null) {
      return 1;
    }

    else
      return helperGetHeight(root);
  }

  private int helperGetHeight(BSTNode<K, V> current) {
    int left = 0, right = 0;
    if (current == null) {
      return 0;
    } else if (current.left != null) {
      left = 1 + helperGetHeight(current.left);
    }

    else if (current.right != null) {
      right = 1 + helperGetHeight(current.right);
    }
    return Math.max(left, right);
  }

}
