/**
 * Tom Chiapete
 * April 18, 2006
 * WordLadder Project
 * 
 * Creates a WordNodes that are used in the vector and priority 
 * queue.
 * 
 * Implements comparable to compare distances and differences.
 */


public class WordNode implements Comparable
{

    // Instance variables.
    
    // I declared them protected so WordLadder can access these
    // but nothing else can.
    
    // I made other getters and setters, but they can be a pain 
    // to use in a big project like this.  I called them by their 
    // variable name.
    
    // Amount of difference 
    protected int difference;
    
    // Holds the actual word.
    protected String word;
    
    // Holds its higher level.
    protected int ancestor;
    
    // Holds its positions.
    protected int position;
    protected int position2;
    
    // If the node has been visited, then this is either true or false.
    protected boolean seen;
    
    // Holds the distance in between.
    protected int distance;
    
    /**
     * setSeen() method
     * Sets the seen value.
     */
    public void setSeen(boolean bool)
    {
        this.seen = bool;
    }
    
    /**
     * setWord() method
     * Sets the word.
     */
    public void setSeen(String string)
    {
        word = string;
    }    
    
    
    /**
     * compareTo() method
     * Will compare an object of type object cast as a WordNode.
     */
    public int compareTo(Object object)
    {
        int compared = compareTo((WordNode) object);
        
        // Returned compared.
        return compared;
    }

    /**
     * WordNode() constructor
     * Sets the defaults.
     */
    public WordNode()
    {
        this.word = ""; // empty word.
        this.position = 0;
        this.ancestor = -1; // nothing
        this.seen = false; // not seen
        this.distance = 0; // distance is nothing
        this.difference = 0; // no difference
    }
    
    /**
     * WordNode() constructor
     * Creates a node based on arguments word and position.
     */
    public WordNode(String word, int position)
    {
        // Not seen yet
        this.seen = false;
        
        // Set the word equal to the word in the parameter.
        this.word = word;
        
        // Set the position equal to the position in the paramter.
        this.position = position;
        
        // Its upper level is -1
        this.ancestor = -1;
        
        // No distance of nodes or difference initially.
        this.distance = 0;
        this.difference = 0;
    }

    /**
     * setDifference() method
     * Sets the difference.
     */
    public void setDifference(int num)
    {
        this.difference = num;
    }
    
    /**
     * compareTo() method 
     * Takes in a word node and compares it to this word node.
     */
    public int compareTo(WordNode wordNode)
    {
        // Store the distance and the difference.
        int temp1 = distance + difference;
        
        // Store the arguments object's distance and add
        // it to the arguement's difference.
        int wordNodeTemp = (int)(wordNode.distance + wordNode.difference);
        
        // Subtract to make a comparsion value.
        int subtracted = temp1 - wordNodeTemp;
        
        // When our comparison isn't zero, return that value
        if(subtracted != 0)
        {
            return subtracted;
        }
        
        // When it does equal 0, take the arguments distance minus 
        // this nodes distance.
        else
        {
            return (int) distance - wordNode.distance;
        }
    }
    
    /**
     * getWord() method
     * Take the word out of the node and returns it.
     */
    public String getWord()
    {
        return word;   
    }
    
}