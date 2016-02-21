// Modified by Hardeep Singh
// Written by Barry Soroka
//
// Solves CS 141, Winter 2013, Homework 10.
//
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
//////////////////////////////////////////////////////////////////////
class Hw10
{
//--------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
      Point p1 = new Point(2.1,3.4);
      Point p2 = new Point(5.2,7.3);

      System.out.println();
      System.out.println("p1 is " + p1);
      System.out.println("p2 is " + p2);

      System.out.println();
      System.out.println("p1 == p2      -> " + (p1==p2));
      System.out.println("p1.equals(p2) -> " + p1.equals(p2));

      p2 = new Point(2.1 + 1e-6, 3.4 - 1e-6);

      System.out.println();
      System.out.println("p1 == p2      -> " + (p1==p2));
      System.out.println("p1.equals(p2) -> " + p1.equals(p2));

      p2 = new Point(p1);

      System.out.println();
      System.out.println("p1 == p2      -> " + (p1==p2));
      System.out.println("p1.equals(p2) -> " + p1.equals(p2));

      p2 = p1.copy();

      System.out.println();
      System.out.println("p1 == p2      -> " + (p1==p2));
      System.out.println("p1.equals(p2) -> " + p1.equals(p2));
   }	
//--------------------------------------------------------------------
} // end class Hw10
//////////////////////////////////////////////////////////////////////
class Point
{
	private double x;
	private double y;
//--------------------------------------------------------------------
	public Point(double x, double y) 
	{
		this.x = x;
		this.y = y;
	}
//--------------------------------------------------------------------
	public boolean equals(Point i)
	{
		return(i.x - this.x < 1e-5 && i.y - this.y < 1e-5);
	}
//--------------------------------------------------------------------
	public Point (Point p1)
	{
		new Point(p1.x, p1.y);
	}
//--------------------------------------------------------------------	
	public Point copy() 
	{
		return new Point(this.x, this.y);
	}
//--------------------------------------------------------------------
	public String toString()
	{
		return String.format("Point(%.3f,%.3f)", this.x, this.y);
	}
} // end class Point
//////////////////////////////////////////////////////////////////////

