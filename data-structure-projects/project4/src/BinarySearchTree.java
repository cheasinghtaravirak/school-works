/**
 * BinearySearchTree. Represent a binary search tree
 * The student cannot change the public interface
 * 
 * @author Singhtaravirak Chea 
 * @version 12-April-2019
 */
public class BinarySearchTree<E extends Comparable<E>>
{
    TreeNode<E> root; // the root of the tree
    
    /**
     * constructor create a empty binary search tree by setting root to be null
     */
    public BinarySearchTree(){
    	root = null; 
    }
    
    /**
     * search the given data in this binary search tree
     * If the data is found, return a reference to the tree node
     * othewise, return null
     * @param data The target to search
     * @return a TreeNode reference to the node that contains the data
     *         if no node contains data, return null
     */
    public TreeNode<E> search(E data){
    	TreeNode<E> cur = root; 
    	while(cur != null) {
    		if(data.compareTo(cur.getData()) == 0) {
    			return cur;
    		} 
    		else if(data.compareTo(cur.getData()) < 0) {
    			cur = cur.getLeft(); 
    		} 
    		else 
    			cur = cur.getRight(); 
    	}
    	return null; 
    }
    
    /**
     * insert given node to this binary search tree. If this tree 
     * was empty, the given node becomes the root of this tree.
     * @param newNode the given node to be inserted
     */
    public void insert(TreeNode<E> newNode){
    	insert(newNode.getData()); 
    }
    
    /**
     * insert given data to this binary search tree. If this tree 
     * was empty, the given node becomes the root of this tree.
     * @param data the given data to be inserted
     */
    public void insert(E data){
    	TreeNode<E> insertNode = new TreeNode<E>(data); //use data to make a node to insert 
    	if(root == null) {
    		root = insertNode;
    		return; 
    	}
    	TreeNode<E> insertAfter = root; 
    	while(insertAfter != null) {
    		if(insertAfter.getData().compareTo(data) < 0) {//data to insert in bigger, go to right child 
    			if(insertAfter.getRight() == null) { //if no right child, shall insert as its right child 
    				insertAfter.setRight(insertNode);
    				insertNode.setParent(insertAfter);
    				return; 
    			} else 
    				insertAfter = insertAfter.getRight(); //else go to its right child 
    			
    		} else {
    			if(insertAfter.getLeft() == null) {
    				insertAfter.setLeft(insertNode);
    				insertNode.setParent(insertAfter);
    				return; 
    			} else 
    				insertAfter = insertAfter.getLeft(); 
    		}
    	}
    }
    
    private boolean remove(TreeNode<E> nodeToRemove) {
    	//if the node is a leaf, simply detach it from the tree
    	if(nodeToRemove.isLeaf()) {
    		if(nodeToRemove.isRoot()) {
    			root = null; 
    			return true; 
    		}
    		if(nodeToRemove.isLeftChild()) 
    			nodeToRemove.getParent().setLeft(null);
    		else 
    			nodeToRemove.getParent().setRight(null);
    		nodeToRemove.setParent(null);
    	}
    	//else if the node has no left child, remove the node by appropriately connect its
    	//right child and its parent
    	else if(nodeToRemove.getLeft() == null) {
    		nodeToRemove.getRight().setParent(nodeToRemove.getParent()); 
    		if(nodeToRemove.isRoot()) {
    			root = nodeToRemove.getRight(); 
    		}
    		else if(nodeToRemove.isLeftChild()) 
    			nodeToRemove.getParent().setLeft(nodeToRemove.getRight());
    		else 
    			nodeToRemove.getParent().setRight(nodeToRemove.getRight()); 
    		nodeToRemove.setParent(null); //detach it from the tree 
    		nodeToRemove.setRight(null);
    	}
    	//else (the node has two chidren) 
    	else {
    		//find its successor, i.e. the node contain smallest data that is greater than this data
    		TreeNode<E> succ = successor(nodeToRemove); 
    		//we copy the successor's data to this node
    		nodeToRemove.setData(succ.getData());
    		//detach the successor from the tree
    		return remove(succ); 
    	}
    	return true; 
    }
    
    /**
     * remove the given data from this binary search tree and return
     * true. If the data is not in the tree, return false
     */
    public boolean remove(E data){
    	//find the node that contains the data, if no such node, return false 
    	TreeNode<E> nodeToRemove = search(data); 
    	if(nodeToRemove == null) 
    		return false; 
    	return remove(nodeToRemove); 
    }
    
    /**
     * return a string representation of the tree
     * @return a String representation of the tree
     */
    public String toString(){
    	return "(" + toString(root) + ")"; 
    }
    
    private String toString(TreeNode<E> node) {
    	if(node == null) 
    		return "_"; 
    	return node.getData() + "(" + toString(node.getLeft()) + "," + toString(node.getRight()) + ")"; 
    }
    /**
     * return true if the tree is empty. False otherwise
     * @return true if the tree is empty; false othewise
     */
    public boolean isEmpty(){
    	return root == null; 
    }
    
    /**
     * return the height of the tree. Notice the height is defined as
     * the length of the longest path from nodes to root
     * @return the height of the tree
     */
    public int height(){
    	return height(root); 
    }
    
    private int height(TreeNode<E> node) {
    	if(node == null || node.isLeaf()) 
    		return 0; //empty tree or leaf node, height is 0 
    	return 1 + Math.max(height(node.getLeft()), height(node.getRight())); 
    }
    
    /**
     * return the number of nodes in the tree
     * @return the number of nodes in this tree
     */
    public int size(){
    	return size(root); 
    }
    
    //return the size of a subtree that rooted at a given node
    private int size(TreeNode<E> node) {
    	if(node == null) 
    		return 0; 
    	return size(node.getLeft()) + size(node.getRight()) + 1; 
    }
    
    public TreeNode<E> successor(TreeNode<E> node) {
    	if(node.getRight() == null) {//has no right child
    		while(node.getParent() != null) {
    			if(node.isLeftChild()) 
    				return node.getParent(); //its parent is the smallest one that is greater than it
    			else 
    				node = node.getParent(); 
    		}
    		return null; 
    	} else {
    		node = node.getRight(); 
    		while(node.getLeft() != null) { //find the left most leaf of the subtree 
    			node = node.getLeft(); 
    		}
    		return node; 
    	}
    }
}







