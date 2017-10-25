package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		// Split the source text into an array so that you can loop on all words
		String[] trainingData = sourceText.split(" ");
		
		//If empty string, return as there's nothing to train on
		if(sourceText.length() == 0){ return; }
		
		//Initialize starter and prevWord
		starter = trainingData[0];
		
		ListNode prevWord = new ListNode(starter);				
		//Loop over trainingData and update wordLists 
		for(int i = 1; i < trainingData.length; i++){
			ListNode w = new ListNode(trainingData[i]);
			//Check if w is in wordList
			
			if(wordList.contains(prevWord)){
				//add "w" as to prevWord's nextWords by looping through list till you get to prevWord
				ListIterator<ListNode> list = wordList.listIterator();
				while(list.hasNext()){
					ListNode item = list.next();
					if(item.getWord().equals(prevWord.getWord())){
						item.addNextWord(w.getWord());					
					}
				}				
			}
			else{
				prevWord.addNextWord(w.getWord());
				wordList.add(prevWord);
			}			
			prevWord = w;			
		}
		//Add starter to the nextWords list of the last word in the wordList.
		if(wordList.contains(prevWord)){
			ListIterator<ListNode> list = wordList.listIterator();
			while(list.hasNext()){
				ListNode item = list.next();
				if(item.getWord().equals(prevWord.getWord())){
					item.addNextWord(starter);					
				}
			}			
		}
		else{
			prevWord.addNextWord(starter);
			wordList.add(prevWord);
		}
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		//If 0 words are requested or negative value is requested, return empty output
		if(numWords <= 0){ return ""; }
		
		//Else return requested count
		String currWord = starter;
		String output = "";
		output += currWord;
		numWords--;
		while(numWords > 0){
			//If wordList is empty, return starter numWords times.
			if(wordList.size() == 0){
				return output;
			}
			
			//If wordList has objects then generate text
			ListIterator<ListNode> list = wordList.listIterator();
			while(list.hasNext()){
				ListNode node = list.next();
				if(currWord.equals(node.getWord())){
					String word = node.getRandomNextWord(rnGenerator);
					output += " ";
					output += word;
					numWords--;
					currWord = word;
					break;
				}		
			}
		}
		
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		starter = "";
		wordList.clear();
		train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{		
//		Random test = new Random(42);
//		System.out.println(test.nextInt());
		
//		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random());
		String textString = "Hello. Hello there. This is a test. Hello there. Hello Bob. Test again.";
//		String textString = "hi there hi Leo";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
//		System.out.println(gen.generateText(0) + "\n");
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
//		System.out.println(textString2);
//		gen.retrain(textString2);
		gen.train("");
		System.out.println(gen);
//		System.out.println(gen);
//		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		int index = generator.nextInt(nextWords.size());
		return nextWords.get(index);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
	@Override
	public boolean equals(Object obj){
		ListNode other = (ListNode) obj;
		if(this.word.equals(other.getWord())){
			return true;
		}
		else{
			return false;
		}		
	}	
}


