// Written by Hardeep Singh
//
// description of this program
//
import java.io.*;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
class Hw20
{
//-----------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
	   Scanner kb =  new Scanner(System.in);
	   while(true)
	   {
		   System.out.print("Input file? ");
		   String s = kb.nextLine();
		   isFileFound(s);
	   }
   }
//-----------------------------------------------------------------------
   public static void isFileFound( String filename )
   {
	   try
	   {
		   Scanner sc = new Scanner(new File(filename));
		   int count = 0;
		   while(sc.hasNextLine())
		   { 
			   count++;
			   sc.nextLine();
		   }
		   System.out.println("That file contains " + count + " line(s).");
		   System.out.println();
	   }
	   catch ( Exception e)
	   {
		   System.out.println("Sorry, but that file does not exist.");
		   System.out.println();
	   }
   }
//-----------------------------------------------------------------------
} // end class Hw20
/////////////////////////////////////////////////////////////////////////