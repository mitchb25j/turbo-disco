/**
  * This is my code!  It's goal is to create a list of stopwords as a stoplist.
  * CS 312 - Assignment 9
  * @author Mitchell Bardsley
  * @version 1.0  12/5/2020 
  */ 
  
import java.util.HashSet;
  
public class StopList
{

    protected HashSet<String> stopWords;
    
    /* constructs a StopList object with a HashSet of stopwords (O(1))
     * @param none
     * @return none
     */
    public StopList( )
    {
    
        stopWords = new HashSet<String>( );
    
    }

    /* determines whether a word is a stopword (O(n))
     * @param a String word from a document
     * @return true or false
     */
    public boolean isStopWord( String docWord )
    {
    
        for ( String stopWord : stopWords )
        {
        
            if ( stopWord.equals( docWord ) )
                return true;
        
        }
        
        return false;
    
    }

}