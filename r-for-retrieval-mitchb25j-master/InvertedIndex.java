/**
  * This is my code!  It's goal is to create an inverted index of documents by what query words they contain.
  * CS 312 - Assignment 9
  * @author Mitchell Bardsley
  * @version 1.3  12/5/2020 
  */ 
  
import java.nio.file.Paths;
import java.nio.file.Path;

import java.util.HashMap;
import java.util.HashSet;

public class InvertedIndex
{

    protected HashMap<String, HashSet<Document>> indexOfDocs;
    
    protected HashSet<Document> documents = new HashSet<Document>( );
    
    protected StopList stopList = new StopList( );
    
    /* constructs an InvertedIndex object with a HashMap index (O(1))
     * @param none
     * @return none
     */
    public InvertedIndex( )
    {
    
        indexOfDocs = new HashMap<String, HashSet<Document>>( );
        
    }

    /* adds a document to the document HashSet, then the HashSet to the index (O(1))
     * @param a String word and a Document document object
     * @return none
     */
    public void addDocument( String word, Document doc )
    {
        
        documents.add( doc );
        
        indexOfDocs.put( word, documents );
    
    }

    /* builds a HashSet of documents using a single-word query for them (O(n^2))
     * @param the String queryWord and a HashSet of documents to look through
     * @return a HashSet of documents for the user's single-word query
     */
    public HashSet<Document> buildSingleWordQuery( String queryWord, HashSet<Document> documents )
    {
    
        HashSet<Document> singleWordQuery = new HashSet<Document>( );
    
        for ( Document doc : documents )
        {
        
            for ( String docWord : doc )
            {
        
                if ( docWord.equals( queryWord ) )
                {
                
                    if ( ! stopList.isStopWord( queryWord ) )
                        singleWordQuery.add( doc );
                
                }
                
            }
            
        }
        
        return singleWordQuery;
                
    }

    /* builds a HashSet of documents using a multi-word query for them (O(n^3))
     * @param the String queryWord and a HashSet of documents to look through
     * @return a HashSet of documents for the user's multi-word query
     */
    public HashSet<Document> buildMultiWordQuery( HashSet<String> queryWords, HashSet<Document> documents )
    {
    
        HashSet<Document> multiWordQuery = new HashSet<Document>( );
    
        for ( Document doc : documents )
        {
        
            for ( String docWord : doc )
            {
        
                for ( String queryWord : queryWords )
                {
                
                    if ( docWord.equals( queryWord ) )
                    {
                    
                        if ( ! stopList.isStopWord( queryWord ) )
                            multiWordQuery.add( doc );
                    
                    }
                
                }
            
            }
        
        }
        
        return multiWordQuery; 
    
    }
    
    /* translates the index into a printable String for the CLI (O(n))
     * @param none
     * @return a String of the index
     */
    public String displayIndex( )
    {
    
        String indexString = "";
    
        for ( String name : indexOfDocs.keySet( ) )
        {
        
            String wordKey = name;
            String docsValue = indexOfDocs.get( name ).toString( );
            
            indexString += ( wordKey + " : " + docsValue ) ;
        
        }
        
        return indexString;
    
    }
    
}