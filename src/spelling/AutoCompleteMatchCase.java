package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteMatchCase implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteMatchCase()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
		/*
		 * Plan for this challenge
		 * 	- Connect to a dictionary API and add in our data structure dictionary, all words in the English language that are capitalized.
		 * 		- Downside is that requires/takes a lot of space
		 * 	- Without a pre-built dictionary, it becomes really hard to know how to differentiate between Christine and christine
		 */
		
	    //Need boolean to check if there was any insert which means a word was added
		boolean wordAdded = false;	
		
		//array of characters to add to trie
		char[] array = word.toCharArray();
		
		//Need TrieNode to check after every loop
		TrieNode nodeToCheck = root;

		for(char c : array){
			TrieNode node = nodeToCheck.getChild(c);
			if(node == null){
				nodeToCheck = nodeToCheck.insert(c);
				wordAdded = true;
			}else{
				nodeToCheck = node;
			}
		}
		
		//Check if the last node is a word, if it's not make it a word and increment size
		if(!nodeToCheck.endsWord()){
			nodeToCheck.setEndsWord(true);
			size++;
		}
		
		//Set the last node to be a word, if there was an insert
		if(wordAdded){			
			return true;
		}
		else{
		    return false;
		}		
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return this.size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
		String lowercase = s.toLowerCase();
		char[] array = lowercase.toCharArray();
		TrieNode nodeToCheck = root;
		
		//Traverse the TrieNode for each character in array till you get to the end, and see if the end is a word
		for(char c : array){			
			if(nodeToCheck.getChild(c) == null){
				return false;
			}
			nodeToCheck = nodeToCheck.getChild(c);
		}
		
		return nodeToCheck.endsWord();
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 
    	 //Need a List<String> to be returned
    	 List<String> predictions = new ArrayList<>();
    	 
    	 //Loop to find stem in the trie
    	 boolean stemFound = true;
    	 char[] array = prefix.toLowerCase().toCharArray();    	 
    	 TrieNode nodeToCheck = root;
 		
 		//Traverse the TrieNode for each character in array till you find all characters in the stem
    	 for(char c : array){
    		 if(nodeToCheck.getChild(c) == null){
    			 stemFound = false;
    			 break;
			 }
    		 nodeToCheck = nodeToCheck.getChild(c);
		 }
    	 
    	 //Breadth-first all over the trie
    	 Queue<TrieNode> q = new LinkedList <TrieNode>();
    	 q.add(nodeToCheck);
    	 if(stemFound){
    		 while(!q.isEmpty() && numCompletions > 0){
    			 TrieNode node = q.poll();
    			 if(node.endsWord()){
    				 predictions.add(node.getText());
    				 numCompletions--;
    			 }
    			 //Add node's all children
    			 Set<Character> keySet = node.getValidNextCharacters();
    			 for(Character child : keySet){
    				 q.add(node.getChild(child));    				 
    			 }
    		 }
    	 }
    	 
         return predictions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}