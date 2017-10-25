/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	MyLinkedList<Integer> oneElementList;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		oneElementList = new MyLinkedList<Integer>();
		oneElementList.add(1);
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
//		try {
//			shortList.get(2);
//			fail("Check out of bounds");
//		}
//		catch (IndexOutOfBoundsException e) {
//		
//		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
//		try {
//			longerList.get(LONG_LIST_LENGTH);
//			fail("Check out of bounds");
//		}
//		catch (IndexOutOfBoundsException e) {
//		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{	
		//Remove the first element
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		//Remove from an empty list
		try{
			emptyList.remove(0);
			fail("Can't remove from an empty list");
		}
		catch (IllegalStateException e){
			
		}
		
		//Remove from an out of bound index
		try{
			longerList.remove(15);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e){
			
		}
		
		
		//Remove the last element
		a = longerList.remove(longerList.size() - 1);
		assertEquals("Remove: Check if last elment is removed", a, 9);
		assertEquals("Remove: check size is correct ", 9, longerList.size());
		
		//Remove from a list with one element
		a = oneElementList.remove(0);
		assertEquals("Remove: Check returned element ", a, 1);
		assertEquals("Remove: check size is correct ", 0, oneElementList.size());
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		
		//Add at the end of an empty list
		emptyList.add(15);
		int test = emptyList.get(0);
		assertEquals("AddEnd: Add at the end of an empty list.", 15, test);
		
		//Add at the end of a list with one element
		oneElementList.add(9);
		test = oneElementList.get(1);
		assertEquals("AddEnd: Add at the end of a one element list.", 9, test);
		assertEquals("AddEnd: Check size", oneElementList.size(), 2);
		
		//Add at the end of a list with multiple elements		
		list1.add(5);
		test = list1.get(list1.size() - 1);
		assertEquals("AddEnd: Add at the end of list with multiple elements.", 5, test);
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		//Check size of empty list
		int testSize = emptyList.size();
		assertEquals("Size: check size of empty list.", 0, testSize);
		//Check size of list with 1 element
		testSize = oneElementList.size();
		assertEquals("Size: check size of list with one element.", 1, testSize);
		//Check size of list with 1+ element
		testSize = longerList.size();
		assertEquals("Size: check size of list with more than one element.", 10, testSize);		
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		int a = list1.get(1);
		assertEquals("Add: check if 21 is returned.", 21, a);
		
		//Add to an empty list
		emptyList.add(0, 17);
		a = emptyList.get(0);
		assertEquals("AddAtIndex: Add to an empty list.", 17, a);		
		
		//Add to a list using an out of bound index
		try{
			list1.add(15, 15);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e){
			
		}
		
		//Add to the last element
		shortList.add(shortList.size(), "K");
		String test = shortList.get(shortList.size() - 1);
		assertEquals("AddAtIndex: Add to the last element.", "K", test);		
		
		//Add in the middle and check the moved elements if there are in their respective locations
		int prior = longerList.get(5);
		longerList.add(5, 90);
		int after = longerList.get(6);
		assertEquals("AddAtIndex: Check if an eleemnt is inserted in the list, the right mechanics happen.", after, prior);
		int test1 = longerList.get(5);
		assertEquals("AddAtIndex: Check element inserted in the middle.", 90, test1);
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		//Set integer
		list1.set(0,1);
		int a = list1.get(0);
		assertEquals("Set: Check if 1 is set.", 1, a);
		
		//Set a String to another index
		shortList.set(1, "C");
		String test = shortList.get(1);
		assertEquals("Set: Check if C is set.", "C", test);
		
		//Set check returned value
		int test1 = list1.set(1, 2);
		assertEquals("Set: Check returned value.", 21, test1);
				
		//Set to an out of bound index
		try{
			list1.set(-1, 9);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e){
			
		}
		
		//Set to a positive out of bound index
		try{
			shortList.set(9, "K");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e){
			
		}
		
		//Set to an empty list
		try{
			emptyList.set(0, 4);
			fail("Can't set a value in an empty list");
		}
		catch (IllegalStateException e){
			
		}	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
