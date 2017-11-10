package spelling;

import spelling.TreeNode;

public class TreeNode<E> {
	private E value;
	private TreeNode<E> parent;
	private TreeNode<E> left;
	private TreeNode<E> right;
	
	//constructor
	public TreeNode(E val, TreeNode<E> par){
		this.value = val;
		this.parent = par;
		this.left = null;
		this.right = null;
	}
	
	//Getters
	public TreeNode<E> getLeftChild(){
		return this.left;
	}
	
	public TreeNode<E> getRightChild(){
		return this.right;
	}
	
	//Setters
	public TreeNode<E> addLeftChild(E child){
		this.left = new TreeNode<E>(child, this);
		return this.left;
	}
	
	public TreeNode<E> addRightChild(E child){
		this.right = new TreeNode<E>(child, this);
		return this.right;
	}
	
	public void visit(){
		System.out.print(this.value);
	}
	
	public E getValue(){
		return this.value;
	}

}
