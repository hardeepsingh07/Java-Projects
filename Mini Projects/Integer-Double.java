// Written by Hardeep Singh
//
// description of this program
//
import java.io.*;
import java.util.*;
/////////////////////////////////////////////////////////////////////////
class Hw19
{
//-----------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
	  Scanner kb =  new Scanner(System.in);
	  while(true)
	  {
		  System.out.print("Enter a String: ");
		  String s = kb.nextLine();
		  isIntDouble(s);	 
	  }
   }
//-----------------------------------------------------------------------
   private static void isIntDouble( String s)
   {
	   try
	   {
		  Double.parseDouble(s);
		  System.out.println("\"" + s + "\"" + " is a double.");
		  Integer.parseInt(s);
		  System.out.println("\"" + s + "\"" + " is an int.");
		  System.out.println();
	   }
	   catch (Exception e)
	   {
		   System.out.println();
	   }
   }
//-----------------------------------------------------------------------
} // end class Hw19
/////////////////////////////////////////////////////////////////////////