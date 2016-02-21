// Modified by Hardeep Singh
// Written by Barry Soroka
//
// Solves CS 141, Winter 2013, Homework 12.
//
import java.io.*;
import java.util.Arrays;
//////////////////////////////////////////////////////////////////////
class Hw12
{
//--------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
      foo("this is a test string");
      System.out.println();
      foo("now is the time for all good men to come to the aid " +
          "of the party");
   }
//--------------------------------------------------------------------
   private static void foo(String t) 
   {
	   String[] n = t.split(" ");
	   Arrays.sort(n);
	   for(String s: n)
		   System.out.println(s);
   }
//--------------------------------------------------------------------


} // end class Hw12
//////////////////////////////////////////////////////////////////////

