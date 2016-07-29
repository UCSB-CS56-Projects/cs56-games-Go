//package edu.ucsb.cs56.W14.kwwham.cp1

/**
   An interface to represent a place to send messages. Used in GoComponent
   @author Chunqing Liu and Xingyuan Zhang
   @author David Winkenwerder and Dustin Henderson
   @version CS56 Summer 2016
   @see GoComponent
*/
public class SystemOutMessageDestination implements MessageDestination
{
    /**
       Get this string to the user via System.out
       @param msg message to display to user
    */
    
    public void append(String msg)
    {
    	System.out.println(msg);
    }
}
