package spelling;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E extends Comparable<? super E>> {
	TreeNode<E> root;
	
	public BinaryTree(TreeNode<E> node){
		this.root = node;
	}
	
	private void preOrder(TreeNode<E> node){
		if(node != null){
			node.visit();
			preOrder(node.getLeftChild());
			preOrder(node.getRightChild());
		}
	}
	
	private void levelOrder(TreeNode<E> node){
		if(node != null){
			if(node == root){
				node.visit();
			}
			if(node.getLeftChild() != null){
				node.getLeftChild().visit();
				node.getRightChild().visit();
			}
			levelOrder(node.getLeftChild());
			levelOrder(node.getRightChild());
		}
	}
	
	public void preOrder(){
		this.preOrder(root);
	}
	
//	public void levelOrder(){
//		this.levelOrder(root);
//	}
	
	public void levelOrder(){
		Queue< TreeNode<E> > q = new LinkedList < TreeNode<E> >();
		q.add(root);
		while(!q.isEmpty()){
			TreeNode<E> curr = q.remove();
			if(curr != null){
				curr.visit();
				q.add(curr.getLeftChild());
				q.add(curr.getRightChild());
			}
		}
	}
	
	//BST Search
	private boolean search(TreeNode<E> par,E toFind){
		int comp;
		while(par != null){
			comp = toFind.compareTo(par.getValue());
			if(comp == 0){ return true; }
			if(comp < 0){ search(par.getLeftChild(), toFind); }
			if(comp > 0){ search(par.getRightChild(), toFind); }
		}
		return false;
	}
	
	//BST from Coursera Support video
	public boolean contains(E toFind){
		TreeNode<E> curr = root;
		int comp;
		while(curr != null){
			comp = toFind.compareTo(curr.getValue());
			if(comp < 0){
				curr = curr.getLeftChild();
			}
			else if(comp > 0){
				curr = curr.getRightChild();
			}
			else{
				return true;
			}
		}
		return false;
	}
	
	//Inserting into a BST
	private boolean insert(TreeNode<E> curr, E toAdd){
		int comp;
		comp = toAdd.compareTo(curr.getValue());
		if(comp < 0){
			TreeNode<E> temp = curr.getLeftChild();
			if(temp == null){
				curr.addLeftChild(toAdd);
			}
			else{
				insert(temp, toAdd);
			}
		}
		else if(comp > 0){
			TreeNode<E> temp = curr.getRightChild();
			if(temp == null){
				curr.addRightChild(toAdd);
			}
			else{
				insert(temp, toAdd);
			}
		}
		else{
			return false;
		}
		return true;
	}
	
	//From the coursera instructors
	public boolean insert(E toInsert){
		TreeNode<E> curr = root;
		int comp = toInsert.compareTo(curr.getValue());
		while(comp < 0 && curr.getLeftChild() != null ||
				comp > 0 && curr.getRightChild() != null)
		{
			if(comp < 0){
				curr.getLeftChild();
			}
			else{
				curr.getRightChild();
			}
			comp = toInsert.compareTo(curr.getValue());
		}
		if(comp < 0)
			curr.addLeftChild(toInsert);
		else if(comp >0)
			curr.addRightChild(toInsert);
		else
			return false;
		return true;
	}
	
	public boolean delete(E toDelete){
		TreeNode<E> curr = root;
		int comp = toDelete.compareTo(curr.getValue());
		while(curr != null){
			if(comp < 0){
				curr = curr.getLeftChild();
			}
			else if(comp > 0){
				curr = curr.getRightChild();
			}
			else{
				curr = null;
				return true;
			}
			comp = toDelete.compareTo(curr.getValue());
		}
		return false;
	}
	
	public static void main(String[] args){		
		TreeNode<Character> node1 = new TreeNode<Character>('A', null);
		node1.addLeftChild('B');
		node1.addRightChild('C');
		node1.getLeftChild().addLeftChild('D');
		node1.getLeftChild().addRightChild('E');
		node1.getRightChild().addLeftChild('F');
		node1.getRightChild().addRightChild('G');
		BinaryTree<Character> tree = new BinaryTree<Character>(node1);
		tree.preOrder();
		System.out.println("");
		tree.levelOrder();
	}
}
