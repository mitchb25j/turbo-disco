/**
  * This is my code!  It's goal is to create a document and an iterator for its words.
  * CS 312 - Assignment 9
  * @author Mitchell Bardsley
  * @version 1.1  12/5/2020 
  */ 

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

import java.util.Iterator;
import java.util.HashSet;

public class Document implements Iterable<String>
{
    
    protected String name;
    
    protected HashSet<String> fileWords;
    
    protected String asRead;
    
    /* constructs a Document object with a name and a HashSet of the file's original text (O(n))
     * @param a String name of the document
     * @return none
     */
    public Document( String name )
    {
    
        this.name = name;
        
        fileWords = new HashSet<String>( );
        
        try
        {
        
            BufferedReader br;
            br = new BufferedReader(new FileReader( name ));
            
            while ( asRead != null )
            {
            
                fileWords.add( asRead );
                
                asRead = new Scanner( br ).useDelimiter( "\\A" ).next( );
                
            }
            
            br.close( );
            
        }
        
        catch (Exception ex)
        {
        
            System.err.println( "Error occurred while reading file." );
            
        }
    }
    
    /* creates an Iterator for Documents for use in other classes (O(1))
     * @param none
     * @return an Iterator of Strings for Document objects
     */
    public Iterator<String> iterator( )
    {
    
        return new Scanner( asRead ).useDelimiter("[^a-zA-Z]+");
        
    }

    /* gives the name of the Document (O(1))
     * @param the String queryWord and a HashSet of documents to look through
     * @return a HashSet of documents for the user's multi-word query
     */
    public String myName( )
    {
    
        return name;
    
    }
    
    

}