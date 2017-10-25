package textgen;

import java.util.AbstractList;
import java.util.ListIterator;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 * @return 
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if(element == null){
			throw new NullPointerException("Can't add null values to the list.");
		}else{
			add(size(), element);
			return true;
		}
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		LLNode<E> iterator = head.next;
		int i = 0;
		ListIterator<E> e = listIterator();
		while(e.hasNext() && i <= index && index >= 0 && size > 0){
			if(i == index){
				return iterator.data;				
			}
			i++;
			iterator = iterator.next;
		}
		throw new IndexOutOfBoundsException("");
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		LLNode<E> newElement = new LLNode<E>(element);
		if(index == 0){
			LLNode<E> temp = head.next;
			head.next = newElement;
			newElement.next = temp;
			newElement.prev = head;
			temp.prev = newElement;
			size ++;
		}
		else if(index == size()){
			LLNode<E> temp = tail.prev;
			tail.prev = newElement;
			newElement.next = tail;
			newElement.prev = temp;
			temp.next = newElement;
			size++;
		}
		else if( index < size() && index > 0 ){
			int i = 0;
			LLNode<E> iterator = head.next;
			ListIterator<E> e = listIterator();
			while(e.hasNext() && i <= index){
				if(i == index){
					LLNode<E> temp = iterator.prev;
					temp.next = newElement;
					newElement.prev = temp;
					newElement.next = iterator;
					iterator.prev = newElement;					
				}
				i++;
				iterator = iterator.next;
			}
			size++;									
		}
		else{
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		E returnVal = null;
		if( index < 0 || index > size ){
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
		}
		else if(size == 0){
			throw new IllegalStateException("Illegal State. Can't remove from an empty list");
		}
		else{
			ListIterator<E> e = this.listIterator();
			int i = 0;
			LLNode<E> temp = head.next;
			LLNode<E> previous = null;
			LLNode<E> tempNext = null;
			while(e.hasNext() && i <= index){
				if(i == index){
					returnVal = temp.data;
					previous = temp.prev;
					previous.next = temp.next;
					tempNext = temp.next;
					tempNext.prev = previous;
				}
				temp = temp.next;
				i++;
			}			
		}		
		size--;
		return returnVal;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		E returnVal = null;
		if( index < 0 || index > size ){
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
		}
		else if(size == 0){
			throw new IllegalStateException("Illegal State. Can't set an empty list");
		}
		else{
			ListIterator<E> e = this.listIterator();
			int i = 0;
			LLNode<E> temp = head.next;
			while(e.hasNext() && i <= index){
				if(i == index){
					returnVal = temp.data;
					temp.data = element;
				}
				temp = temp.next;
				i++;
			}			
		}
		return returnVal;
	}
	
	public static void main(String args[]){
//		MyLinkedList<Integer> longerList;
//		longerList = new MyLinkedList<Integer>();
//		for (int i = 0; i < 10; i++)
//		{
//			longerList.add(i);
//		}
//		ListIterator<Integer> e = longerList.listIterator();
//		int j = 0;
//		LLNode<Integer> temp = longerList.head.next;
//		while(e.hasNext()){
//			System.out.println(e.next());
//			System.out.println(longerList.set(j, j+2).toString());
////			System.out.println(temp.data);
////			temp = temp.next;
//			j++;
//		}
//		e = longerList.listIterator();
//		System.out.println("\n\n");
//		while(e.hasNext()){
//			System.out.println(e.next());
//		}
		MyLinkedList<Integer> list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		System.out.println(list1.set(0, 9));
		
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
