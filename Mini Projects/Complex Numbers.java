// Modified by Hardeep Singh.
// Written by Barry Soroka.
//
// Solves CS 141, Winter 2013, Homework 9.
//
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
////////////////////////////////////////////////////////////////////////////////
class Hw09
{
//------------------------------------------------------------------------------
   public static void main (String [] args) throws Exception
   {
      test(0,0,"0");
      test(0,1,"i");
      test(1,0,"1");
      test(1,1,"1+i");
      test(1,2,"1+2i");
      test(1,-1,"1-i");
      test(1,-2,"1-2i");
      test(3,4,"3+4i");
      test(0,4,"4i");
      test(0,-4,"-4i");
      test(3,-4,"3-4i");
      test(-3,0,"-3");
   } 
//------------------------------------------------------------------------------
   public static void test ( int real, int imag, String expected )
   {
      String actual = (new Complex(real,imag)).toString();
      System.out.print( (actual.equals(expected)) ? " ok " : "*NO*" );
      System.out.println(" expected \"" + expected +
                         "\" actual was \"" + actual + "\"");
   }
//------------------------------------------------------------------------------
} // end class Hw09
////////////////////////////////////////////////////////////////////////////////
class Complex
{
   private int real;
   private int imag;
//-----------------------------------------------------------------------
   public Complex ( int real , int imag )
   {
      this.real = real;
      this.imag = imag;
   }
//-----------------------------------------------------------------------
   public String toString ()
   {
	   if( real == 0 && imag == 0) return String.format("0");
 	   if( real == 1 && imag == 1) return String.format(real + "+i");
	   if( real == 0 && imag < 0 ) return String.format(imag + "i");
	   if( imag == 0) return String.format(real + "");
	   if( real == 0 && imag == 1) return "i";
	   if( imag == -1) return String.format(real + "-i");
	   if( imag < 0) return String.format(real + "" + imag + "i");
	   if( real == 0) return String.format(imag + "i");
	   return real + "+" + imag + "i";
   }  
//-----------------------------------------------------------------------
} // end class Complex
/////////////////////////////////////////////////////////////////////////
