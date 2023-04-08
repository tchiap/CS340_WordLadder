/**
 * Tom Chiapete
 * April 18, 2006
 * WordLadder Project
 * 
 * Through use of a vector and priority queue, we can create 
 * for word ladders using a search algorithm.
 * 
 * Known bugs:  Probably none.
 * 
 * Imports java.util and .io for scanners and input output.
 */


import java.util.*;
import java.io.*;

public class WordLadder
{

    /**
     * findNodeDifference() method
     * Find the difference strings by taking in two strings and 
     * comparing the characters one by one.
     */
    private static int countDifference(String first, String next)
    {
        
        // Counter variable.
        int acc = 0;
        int temp = 1;
        
        // Crawl through the strings length ( they should match)
        for(int x = 0; x < first.length(); x++)
        {
            // If the characters at position x don't match, increment the counter.
            if(next.charAt(x) != first.charAt(x))
            {
                acc = acc + 1;
            }
        }
        
        // Return the counter after done with for loop.
        return acc;
    }
    
    
    /**
     * main() method
     * This does the input work for us, plus detects the size of 
     * the two strings being inputed.  If they don't match, we 
     * have a prblem and we can't go on any futher.
     * Add what we need to the vector.  Search for results.
     */
    public static void main(String [] args)
    {
        
        // Need a scanner for input
        Scanner input = new Scanner(System.in);
        
        // I'm using a vector (generic type WordNode) because of the Vector's simple methods.
        Vector<WordNode> wordListVector = new Vector<WordNode>();
        
        // Input the first word.
        System.out.print("Enter starting word:  ");
        String startingString = input.next();
        
        // Input the second word.
        System.out.print("Enter ending word:  ");
        String endingString = input.next();
        
        // Check to see if our inputted string lengths match, otherwise
        // output an error.
        if(startingString.length() != endingString.length())
        {
            System.out.println("Please enter words of the same length.");
            System.exit(0);
        }
        
        // Make a call to inputFileAddToVector
        // This brings in the file and adds it the vector.
        inputFileAddToVector(startingString ,wordListVector);
        
        // Search method.  Takes in our start and end strings, and our vector.
        searchWordList(startingString, endingString, wordListVector);
    }


    /**
     * printResult() method
     * This takes in a vector which is our word list and a word node.
     * It prints out the results in order.
     */
    public static void printResult(Vector wordList, WordNode wordNode)
    {
        // Until the parent would go to -1, crawl through this loop, meaning 
        // we still have words to output, or we still have more to the ladder.
        for(int x = 0; wordNode.ancestor != -1; )
        {
            // I need to append to the string, so I'll use a string buffer.
            // Separate ladder words with commas
            System.out.print(
                (new StringBuffer()).append(wordNode.word).
                                                append(" , ").toString());
                                                
            // Get the word node from the word list.
            int parent = wordNode.ancestor;
            wordNode = (WordNode)wordList.get(parent);
        }

        // Output the word data value of word node.
        System.out.println(wordNode.word);
    }

    /** 
     * stringEquals() method
     * Check to see how equivalent two strings are by comparing their characters 
     * at every position.
     * Both first string and second string should have the same length upon
     * input.
     */
    public static boolean charEquals(String first, String second)
    {
        // Tells whether equal or not
        boolean tempBool = false;

        // Loop through until the end of the word
        for(int x = 0; x < first.length(); x++)
        {
            // Make a comparison for both the words at position x.
            // If so, jump out of the loop by executing a continue keyword.
            if(first.charAt(x) == second.charAt(x))
            {
                continue;
            }
            
            // If our boolean is true, then return false -- not equal
            if(tempBool == true)
            {
                return false;
            }
            
            // Reset and go true.
            tempBool = true;
        }

        // Otherwise charEquals equals true
        return true;
    }

    /**
     * searchWordList() method
     * Takes in both the strings and the vector word list.
     * Using a suggestion from a professor, and me taking CS405, I thought it 
     * would be best to use a Best First Search.  So this is a very modified 
     * Best First Search, with a few extra operations in it.
     */
    public static void searchWordList(String startWord, String endWord, Vector wordList)
    {
        
        // Counter variable
        int counter = 0;
        
        // Our two word nodes - start and end.
        WordNode wordNodeStart;
        WordNode wordNodeEnd;
        
        // Variables must be initialized otherwise an error is thrown.
        wordNodeStart = null;
        wordNodeEnd = null;
        
        // Set counter to zero.
        counter = 0;
        
        // Retrieve the size of the vector.
        int wordListLength = wordList.size();
        
        // Create a priority queues of WordNodes
        PriorityQueue<WordNode> pq = new PriorityQueue<WordNode>();
        
        // Run through at least once.
        do
        {
            
            // When the counter is greater than or equal to the vector length, break 
            // out and don't execute anything else.
            if(counter >= wordListLength)
                break;
                
            // When the start word is the same as the node's word at the counter, 
            // set wordNodeStart equal to what is retrieved at the counter position 
            // of the word list.
            if(startWord.equals(((WordNode)wordList.get(counter)).word))
            {
                wordNodeStart = (WordNode)wordList.get(counter);
                
                // Break out, because I don't want it to execute anymore code.
                // It'll mess up if I take it out.
                break;
            }
            
            // Increment the counter before we stop or continue the loop again.
            counter = counter + 1;
            
        } 
        // Do the action above while true.
        while(true);
        
        // Well, when the counter is equal to the length of the vector, then 
        // we ran into a wall.  That means the word given was not found.
        if(wordListLength == counter)
        {
            System.out.println("Could not find this on the list..."+  
                "Try entering a word in one of the dictionary files.");

            // Not much else to do, exit the program.
            System.exit(0);
        }
        
        // Instead of incrementing this time through, reset the counter.
        counter = 0;
        
        // Another do while so I know this will be executed at least once.
        do
        {
            
            // When the counter is greater than the length of the vector, 
            // break out and don't execute any more code in this block.
            if(counter >= wordListLength)
                break;
                
            // Instead of the start word, the word at the counter, set 
            // the wordNodeEnd equal to the word node at the counter of the vector.
            if(endWord.equals(((WordNode)wordList.get(counter)).word))
            {
                wordNodeEnd = (WordNode)wordList.get(counter);
                
                // Leave the block.
                break;
            }
            
            // Increment the counter at the end of the do-while.
            counter = counter + 1;
        } 
        
        // Stay in the loop while it is true.
        while(true);
        
        // Again, not in the dictionary.  We've hit the wall.
        if(wordListLength == counter)
        {
            System.out.println("Could not find this on the list..."+  
                "Try entering a word in one of the dictionary files.");
            System.exit(0);
        }
        
        // Set the boolean seen variable equal to true now.
        wordNodeEnd.seen = true;
        
        // Add this end node to the priority queue.
        pq.add(wordNodeEnd);
        
        // While the priority queue is not empty...  stay in this loop
        while(pq.isEmpty() == false) 
        {
            
            // Poll the priority queue that will send back its current word node.
            WordNode current = (WordNode)pq.poll();
            
            // If the current word string equals
            if(current.word.equals(wordNodeStart.word))
            {
                
                // Print our result... We found another word on the word ladder.
                printResult(wordList, current);
                
                // Exit this method because we are done with the printing
                return;
                
            }
            
            // Reset the counter back to zero.
            counter = 0;
            
            // When the counter is less than the vector length,
            // Stay in this loop.
            while(counter < wordListLength) 
            {
                
                // Create another node and retrieve the word node at position counter.
                WordNode node1 = (WordNode)wordList.get(counter);
                
                // When the node hasn't been passed up and the characters match for the 
                // words in both the nodes, set up the node to be put on the priority queue.
                if(node1.seen == false 
                        && charEquals(node1.word, current.word))
                {
                    
                    // Set up the node to be put on the priority queue.
                    
                    // Set the new nodes distance equal to 1 plus the current.
                    node1.distance = current.distance + 1;
                    
                    // Call the count differences method to find out the 
                    // difference between the startWord and the nodes word.
                    node1.difference = countDifference(node1.word, startWord);
                    
                    // Since the node has been passed up, we have to set it to true,
                    // otherwise it won't work.
                    node1.seen = true;
                    
                    // Set the parent equal to the currents position.
                    node1.ancestor = current.position;
                    
                    // Add node to priority queue
                    pq.offer(node1);
                }
                
                // Increment the counter lastly.
                counter = counter + 1;
            }
        }
        
        // Well when nothing is added to the ladder, we should probably say 
        // that the two words don't have a ladder between them.
        System.out.println("I could not find a ladder for you.");
    }
    
    /**
     * inputFileAddToVector() method
     * Takes in the starting word and a vector of the word list 
     * being used.
     */
    public static void inputFileAddToVector(String start, Vector wordList)
    {
        
        // Counter variable
        int counter = 0;
        
        // Try block - try scanning in the file.  If it is not found,
        // throw an exception.
        try
        {
            
            // Current Position
            int position = 0;
            
            // Word variable that is of type string.
            String word;
            
            // Holds the length of the start word
            int startWordLength = start.length();
            
            // Open the file "words.<length of start>"
            // Had to use StringBuffer.  Didn't work with String Class.
            Scanner scan = new Scanner(
                                new File((new StringBuffer())
                                        .append("words.").
                                                append(startWordLength).toString()));
           
           // Scan each word into the word variable.  Add the new word node 
           // Into the vector.
           for( int j = 0 ; scan.hasNext() == true; )
           {
                word = scan.next();
                
                // Add to vector at the position then increment.
                wordList.add(new WordNode(word, position++));
           }

        } // -- end try block
        
        // Oh crap.  Word way too long or something else happened to cause a file not found.
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found");
            
            // Exit the program.
            System.exit(0);
        }
    }

}