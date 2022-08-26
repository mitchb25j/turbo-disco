/**
  * This is my code!  It's goal is to accept command line arguments from the user.
  * CS 312 - Assignment 9
  * @author Mitchell Bardsley
  * @version 1.1  12/5/2020 
  */ 

import java.nio.file.Paths;
import java.nio.file.Path;

import java.util.HashSet;
import java.util.HashMap;

import java.util.Scanner;

public class CLI
{
    
    protected String userQuery;
    
    protected InvertedIndex ii = new InvertedIndex( );

    /* gives the user a usage message when they try to run the CLI (O(1))
     * @param none
     * @return none
     */
    public void usage( )
    {
    
        System.out.println( "Usage: java CLI <stoplist> <docs>" );
        
    }

    /* constructs a CLI object (O(n^2))
     * @param a string array of arguments
     * @return none
     */
    public CLI( String [] args )
    {
        
        if ( args.length == 0 )
        {
        
            usage( );
            return;
        
        }
        
        if ( args[1].contains( ".txt" ) )
        {

            int i = 1;
            while ( i < args.length )
            {
            
                Path p = Paths.get( "/home/mabardsley/cs312/r-for-retrieval-mitchb25j/testing/" + args[i] );
                String fileName = p.getFileName( ).toString( );
                
                Document newDoc = new Document( fileName );
                
                for ( String word : newDoc )
                {
                
                    ii.addDocument( word, newDoc );
                    
                }
                
                i++;
                
            }
            
        }
        
        Scanner scan = new Scanner( System.in );
        
        while ( userQuery != null )
        {
        
            userQuery = scan.nextLine( );
            
            if ( userQuery == "@@debug" )
            {
        
                System.out.println( "The inverted index contains " + ii.displayIndex( ) );
            
            }
            
            else
            {
            
                if ( userQuery.contains( " " ) )
                {
                
                    String[] queryStrings = userQuery.split( " " );
                
                    HashSet<String> userQuerySet  = new HashSet<String>( );
                    
                    for ( String word : queryStrings )
                    {
                    
                        userQuerySet.add( word );
                        
                    }
                                
                    HashSet<Document> multiWordQuery = new HashSet<Document>( );
                
                    multiWordQuery = ii.buildMultiWordQuery( userQuerySet, ii.documents );
                    
                    for ( Document doc : multiWordQuery )
                    {
                    
                        System.out.print( doc.myName( ) + " " );
                        
                        System.out.println( "--- found in " + 
                        (multiWordQuery == null ? 0 : multiWordQuery.size( ) ) + "documents" );
                    
                    }
                    
                    long startTime = System.currentTimeMillis( );
                    long stopTime = System.currentTimeMillis( );
                    long elapsedTime = stopTime - startTime;
                    
                    System.out.println( "@@ multi-word query took " + elapsedTime + "ms" );
                
                }
                
                else if ( ! userQuery.contains( " " ) )
                {
                
                    HashSet<Document> singleWordQuery = new HashSet<Document>( );
                
                    singleWordQuery = ii.buildSingleWordQuery( userQuery, ii.documents );
                    
                    for ( Document doc : singleWordQuery )
                    {
                    
                        System.out.print( doc.myName( ) + " " );
                    
                    }
                    
                    long startTime = System.currentTimeMillis( );
                    long stopTime = System.currentTimeMillis( );
                    long elapsedTime = stopTime - startTime;
                    
                    System.out.println( "@@ single-word query took " + elapsedTime + "ms" );
                    
                }
                
            }
            
        }
        
    }
    
    /* runs the program (O(1))
     * @param a string array of arguments
     * @return none
     */
    public static void main( String [] args )
    {
    
        CLI retrievalCLI = new CLI( args );
    
    } 

}