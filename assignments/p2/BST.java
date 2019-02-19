import java.util.List; // required for returning List<K>

// TODO: Implement a Binary Search Tree class here
public class BST<K extends Comparable<K>, V> implements BSTADT<K, V> {

  // Tip: Use protected fields so that they may be inherited by AVL
  protected BSTNode<K, V> root;
  protected int numKeys; // number of keys in BST

  // Must have a public, default no-arg constructor
  public BST() {

  }

  /*
   * (non-Javadoc)
   * 
   * VLR
   */
  @Override
  public List<K> getPreOrderTraversal() {
    List<Object> ls = new List<Strin>();

    helperGetPreOrderTraversal(ls, this.root);
    return null;
  }

  private List<K> helperGetPreOrderTraversal(List<K> ls, BSTNode<K, V> current) {

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see SearchTreeADT#getPostOrderTraversal()
   */
  @Override
  public List<K> getPostOrderTraversal() {
    // TODO Auto-generated method stub
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
    if (key == null)
      throw new IllegalNullKeyException();
    else
      helperInsert(key, this.root);
  }

  private void helperInsert(K key, BSTNode<K, V> current) throws DuplicateKeyException {
    if (current.key.compareTo(key) < 0)
      helperInsert(key, current.left);
    else if (current.key.compareTo(key) > 0)
      helperInsert(key, current.right);
    else if (current.key.compareTo(key) == 0)
      throw new DuplicateKeyException();
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#remove(java.lang.Comparable)
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#get(java.lang.Comparable)
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key.compareTo(o))
      return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see DataStructureADT#contains(java.lang.Comparable)
   */
  @Override
  public boolean contains(K key) throws IllegalNullKeyException {
    if (this.root.key.equals(key))
      return true;
    else
      return helperContain(key, this.root);
  }

  private boolean helperContain(K key, BSTNode<K, V> current) {
    if (current == null)
      return false;
    else if (current.key.equals(key))
      return true;
    else if (current.key.compareTo(key) < 1)
      return helperContain(key, current.left);
    else
      return helperContain(key, current.right);

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
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getKeyOfRightChildOf(java.lang.Comparable)
   */
  @Override
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see BSTADT#getHeight()
   */
  @Override
  public int getHeight() {
    // TODO Auto-generated method stub
    return 0;
  }

}
