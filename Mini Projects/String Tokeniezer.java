// Written by Hardeep Singh
//
// Using StringTokenizer.
//
import java.io.*;
import java.util.StringTokenizer;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
class Hw11
{
//-----------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
	   Scanner kb =  new Scanner(System.in);
	   System.out.print("Filename? ");
	   String infile = kb.nextLine();
	   Scanner sc = new Scanner(new File(infile));
	   String oldfile = " ";
	   String newfile = " ";
	   while(sc.hasNext())
	   {
		  oldfile += sc.nextLine().toLowerCase() + " ";
	   }
	   int count = 0;
	   for(char c: oldfile.toCharArray())
	   {
	   	   if(Character.isLetter(c)) count++;
	       if(Character.isLetter(c) || c == ' ') newfile += c;
	   }
	   int wCount = 0;
	   int theCount = 0;
	   int aCount = 0;
	   StringTokenizer nf = new StringTokenizer(newfile);
	   StringTokenizer nf1 = new StringTokenizer(newfile);
	   StringTokenizer nf2 = new StringTokenizer(newfile);
	   while(nf.hasMoreElements())
	   {
		   if(nf.nextToken().equals("the")) theCount++;
		   if(nf1.nextToken().equals("a"))  aCount++;
		   if(nf2.nextToken().equals("an")) aCount++;
		   wCount++;
	   }
	   double AverageWord = ((count * 1.0) / wCount );
	   double theFrequency = ((theCount * 1.0) / wCount);
	   double aFrequency = ((aCount * 1.0) / wCount);
	   System.out.println("letters " + count);
	   System.out.println("words " + wCount);
	   System.out.printf("average word length %.2f\n",AverageWord);
	   System.out.printf("a/an " + aCount + " frequency %.3f\n", aFrequency);
	   System.out.printf("the  " + theCount + " frequency %.3f\n", theFrequency);   	   
   }
 //-----------------------------------------------------------------------
 } // end class Hw11
 /////////////////////////////////////////////////////////////////////////