// Written by Hardeep Singh
//
// Takeuchi Funtion.
//
import java.io.*;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
class Hw24
{
   private static int count = 0;
   private static int subtractionCount = 0;
//-----------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
	   System.out.println("Method(tak) returns a result of: " + tak(18, 12, 6) + ".");
	   System.out.println("Method(tak) was called: " + count + ".");
	   System.out.println("Method(tak) have subratction by 1: " + subtractionCount + ".");	   
   }
//-----------------------------------------------------------------------
   public static int tak(int x, int y, int z)
   {
	   count++;
	   if(x <= y) 
	   {
		 subtractionCount++; 
		 return z;
	   } 
	   return tak(tak(x-1, y, z), tak(y-1, z, x), tak(z-1, x, y));
   }
//-----------------------------------------------------------------------
} // end class Hw24
/////////////////////////////////////////////////////////////////////////