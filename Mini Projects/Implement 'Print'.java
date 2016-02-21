// Modified by YOUR NAME HERE
// Written by Dr Barry Soroka
//
// Solves CS 141, Winter 2013, Homework 17.
//
import java.io.*;
//////////////////////////////////////////////////////////////////
class Hw17
{
//----------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
      CPS cps = new CPS("foo");

      cps.setColumnWidth(6);
      cps.print("hello");
      cps.print(5);
      cps.print('a');
      cps.print("too big");
      cps.println(37.3);

      for ( int w = 0 ; w < 9 ; w++ )
      {
         cps.setColumnWidth(w);
         for ( int i = 0 ; i < 9 ; i++ ) cps.print(i);
         cps.println();
      }

      for ( int w = 0 ; w < 10 ; w++ )
      {
         cps.setColumnWidth(w);
         for ( char c : "abcdefg".toCharArray() ) cps.print(c);
         cps.println();
      }

      cps.println();
      cps.setColumnWidth(4);
      for ( int i = 0 ; i < 20 ; i++ )
      {
         cps.print((int)Math.pow(2,i));
         if ( i % 3 == 0 ) cps.println();
      }
      cps.println();
   }
//----------------------------------------------------------------   
} // end class Hw17
//////////////////////////////////////////////////////////////////
class CPS extends PrintStream
{
	protected static String s;
	protected static int columnWidth = 8;
//----------------------------------------------------------------	
	public CPS (String s) throws FileNotFoundException  
	{
		super(new File (s));
	}
//----------------------------------------------------------------
	public void print(String s)
	{ 
		int f = columnWidth - s.length();
		if(s.length() > columnWidth) super.print(repeat('*', columnWidth));
		else super.print(s + padRight("", f));
	}
//----------------------------------------------------------------
   public void print(int i)
   {
      String zString = i + "";
      int z = columnWidth - zString.length();
	  if(zString.length() > columnWidth) super.print(zString + repeat('*', z));
	  else super.print(zString + padRight("", z));
   }
//----------------------------------------------------------------
   public void print(char c)
   {
      String oString = c + "";
      int o = columnWidth - oString.length();
	  if(oString.length() > columnWidth) super.print(repeat('*', columnWidth));	     
	  else super.print(oString + padRight("", o)); 
   }
//----------------------------------------------------------------
   public void print(double d)
   {
      String uString = d + "";
      int u = columnWidth - uString.length();      
      if(uString.length() > columnWidth) super.print(repeat('*', columnWidth));
      else super.print(uString + padRight("", u));
   }
//----------------------------------------------------------------
   public void println(String s1)
   {
	   super.println();
   }
//----------------------------------------------------------------
   public void println(int i1)
   {
	   print(i1);
	   super.println();
   }
//----------------------------------------------------------------
   public void println( char c1)
   {
	   print(c1);
	   super.println();
   }
//----------------------------------------------------------------
   public void println( double d1)
   { 
	   print(d1);
	   super.println(); 
   }
//----------------------------------------------------------------
   public void setColumnWidth(int newWidth) 
   {
	  if(newWidth < 0)
		  throw new IllegalArgumentException("Width can not be negative number");
	  else columnWidth = newWidth;
   	}
//-------------------------------------------------------------------
   private static String repeat( char c , int a)
   {
      String repeat = " ";
      for ( int i = 0 ; i < a; i++) repeat += c;
	  return repeat;
   }
//-------------------------------------------------------------------
   private static String padRight( String s, int b)
   {
      String padRight = s;
      for( int i = s.length(); i < b; i++) padRight += " ";
	  return padRight;
   }
//-------------------------------------------------------------------
///////////////////////////////////////////////////////////////////
}//end class CPS
//////////////////////////////////////////////////////////////////
