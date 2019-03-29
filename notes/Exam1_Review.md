# 2019-03-06

## 2-3 Trees

- A b-tree with bf = 3
- Guarantess a log(N) complexity.

### Tree questions

| Tree     | Insert | Delete                   |
| -------- | ------ | ------------------------ |
| BST      | yes    | yes                      |
| AVL      | yes    | yes and fix              |
| RB       | yes    | not a black leaf and fix |
| 2-3 Tree | yes    | yes                      |

## Red-Black trees:

- No always height Balanced, not at all like AVL.

#### Balanced vs Height-Balalnced:

- **Height-Balanced** is when every node in a tree has 1,0,-1 height.
- **Balanced**, also known as self-balancing, is more general, maintains an overall height of O(logN)

# 2019-03-07

## Actual Review

### JUnit

- TTD is when you write test first and then code.
- Use Junit assertions to check if pass or fail
- Write a main method to run all tests

### Inner classes

- Usually private and its members too.
- Meant to keep common classes together

### Trees:

###### Height

```java
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
```

##### Delete

- If from left of root:
  - Replace with predecessor, smallest of right subtree
- If from right of root:
  - Replace with sucessor, largest of left subtree

##### Tree Defintions
1. **Perfect**: all internal nodes have k-children, all leaves at same level, perfect like a triangle.
2. **Complete**: All internal nodes have k-children, leaves at same depth but left aligned.
3. **Height-Balanced**: all nodes have height of -1, 0, or 1.
4. **Balanced**: 

##### Code for Balancing

```java
// ------ Check for Balance Factor --------- //
// Check if current node needs a rotate. If bf is less than -1, left rotate
    if (balanceFactor(node) < -1) {
      // Check if its right node has bf above 1
      // Why? Because it means that this node is causing the imbalance from right
      // subtree
      // Rotate to right
      if (balanceFactor(node.right) > 0) {
        node.right = rightRotate(node.right);
        // Reassign height
        node.right.height = getHeight(node.right);
      }
      // Do the rotate on current node still
      node = leftRotate(node);
    }

    // Check if current node needs a rotate. If bf is more than 1, right rotate
    if (balanceFactor(node) > 1) {
      // Check if its left node has bf below -1
      // Why? Because it means that this node is causing the imbalance from left
      // subtree
      // Rotate to Left
      if (balanceFactor(node.left) < 0) {
        node.left = leftRotate(node.left);
        // Reassign height
        node.left.height = getHeight(node.left);
      }
      // Do original rotate
      node = rightRotate(node);
    }

/**
   * Method for left rotating a subtree.
   * 
   * @param node - BSTNode that needs to be balanced
   * @return rightChild - return the rotated node
   */
  public BSTNode<K, V> leftRotate(BSTNode<K, V> node) {
    // Grab right child
    BSTNode<K, V> rightChild = node.right;
    // Split left subtree of right child, even if null just do it.
    BSTNode<K, V> leftSubTreeRightChild = rightChild.left;
    // Give the left subtree to the right of node
    node.right = leftSubTreeRightChild;
    // Promote the rightChild by giving current node to left of Rchild
    rightChild.left = node;
    // Return it
    return rightChild;
  }

/**
   * Method for right rotating a subtree
   * 
   * @param node - BSTNode that needs to be balanced
   * @return node - return balanced node
   */
  public BSTNode<K, V> rightRotate(BSTNode<K, V> node) {
    // Grab left child of node
    BSTNode<K, V> leftChild = node.left;
    // Split right subtree of child node
    BSTNode<K, V> rightSubTreeLeftChild = leftChild.right;

    // Set current node.left to the split subtree
    node.left = rightSubTreeLeftChild;

    // Promote child by giving node to its right
    leftChild.right = node;
    // Return balanced node with child as pseudoroot
    return leftChild;
  }
```
#### Code for traversals

```java
/**
   * Helper method for level order traversal. Gets keys from all levels in order.
   * 
   * @param ls - list to hold keys
   */
  private void helperGetLevelOrderTraversal(ArrayList<K> ls) {
    // index for going through key list
    int i = 0;
    // Temp list to hold NODES< cannot use given ls in parameter
    ArrayList<BSTNode<K, V>> tempLs = new ArrayList<BSTNode<K, V>>();
    // Add root to temp
    tempLs.add(this.root);

    // While temp size is not equal to numKeys
    // Go through tempLs
    while (tempLs.size() != this.numKeys) {
      // Grab node
      BSTNode<K, V> tempNode = tempLs.get(i);
      // Check for children, if so add them to ls
      if (tempNode.left != null) {
        tempLs.add(tempNode.left);
        ls.add(tempNode.left.key);
      }
      if (tempNode.right != null) {
        tempLs.add(tempNode.right);
        ls.add(tempNode.right.key);
      }
      // Increment index to get other node and add their children
      i += 1;

    }

    /**
   * Helper method for performing inOrder traversal. Traversal is L, V, R
   * 
   * @param ls      - list to hold keys
   * @param current - node being recursed
   */
  private void helperGetInOrderTraversal(ArrayList<K> ls, BSTNode<K, V> current) {
    // Visit Left
    if (current.left != null)
      helperGetInOrderTraversal(ls, current.left);
    // Add current
    ls.add(current.key);
    // Visit Right
    if (current.right != null)
      helperGetInOrderTraversal(ls, current.right);
    return;

  }

/**
   * Recursive method for PostOrder Traversal. Traversal is L,R,V
   * 
   * @param ls      - list to hold keys
   * @param current - node being recursed
   */
  private void helperGetPostOrderTraversal(ArrayList<K> ls, BSTNode<K, V> current) {
    // Visit Left
    if (current.left != null)
      helperGetPostOrderTraversal(ls, current.left);
    // visit right
    if (current.right != null)
      helperGetPostOrderTraversal(ls, current.right);
    // Visit once left and right are null
    ls.add(current.key);
    return;

  }

/**
   * Recursive method for performing PreOrder traversal. Traversal is V,L,R
   * 
   * @param ls      - list object to hold keys
   * @param current - node being recursed
   */
  private void helperGetPreOrderTraversal(ArrayList<K> ls, BSTNode<K, V> current) {
    // base case
    if (current == null)
      return;
    // Add current to list
    ls.add(current.key);
    // Visit left
    if (current.left != null)
      helperGetPreOrderTraversal(ls, current.left);
    // Visit right
    if (current.right != null)
      helperGetPreOrderTraversal(ls, current.right);
    return;
  }
```
#### RED-BLACK TREES:

##### Properties:

1. root: always balck
2. black: same number of black nodes from root to leaf.
3. red: always had black children

##### Insertion:

1. Always insert **red** leaf.
2. Check *parent* node **sibling**:.
   1. if **black**:
      -  Trinode restructuring by (Will not cause violation):
         -  Finding median of new node, P and Grandparent
         -  Make median parent and color **black**
         -  Make children red.
   2.  if **red**:
       -   Recolor nodes by (Can cause **red** violation):
           -   Change parent and its sibling to black.
           -   Change Grandparent to red unless root.
           -   Check G for red violation recursively.

##### Deletion:

- If deleting **red leaf** -> nothing happens
- " " **red interior** -> find sucessor. red nodes must always have two children or be leaf, if leaf you are done!.
  - Else, if sucessor has one child, must be **black**. Move it up and then replace successor down there with its child and make it black.
- " " **black node** with one child -> replace with **red** child and recolor child to black.
- " " **black leaf** -> make black null and check **sibling**:
    1. If **sibling** is **black**:
       - If sibling **child** is red:
          - One child on opposite subtree as sibling from parent, rotate recolor then rotate again
          - If two child bring sibling up with, divide sibling children. New Parent is red and children black
       - If sibling **child(s)** are black:
          - rotate, recolor parent to red. children black
    2. If **sibling** is **red**:
       - move sibling up to get black new sibling. ONLY RECOLOR NEW SIBLING AND PARENT.

#### B-Trees

###### Properties:

  - Fixed maximum children per node.
  - Insertions done at leaf.
  - All leaves at same height
  - Grows upwards

  - minkeys = (k-1)/2, max = k - 1
  - height = O(logbf(N))

##### Insertions:

- If key list is larger or equal to bf:
  - find median
  - split into new nodes.
  - move keys less than median to left, and higher than median to right. 

##### Deletions
1. Check that node does not have less than minKeys allowed.If in leaf then just delete it/
2. However,if **key** is in an **internal node** consider the following cases:
    1. If the child after the **key** has more than minimum of keys, grab **predecessor** (after) from here and move it up.
    2. **If** the child node for predecessor has **minimum** keys, Grab **successor** (before) instead. 
    3. **If** both are at minimum, merge them together and delete key from node.
3. 

- Simple way:
  - Try to steal from parent and replace with sibling. If sibling is at minimum, bring parent down and merge. Check **top null** and fix.


#### Hashtables


