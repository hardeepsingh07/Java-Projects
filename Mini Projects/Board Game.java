// Written by Hardeep Singh
//
// description of this program
//
import java.io.*;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
class Hw07
{
//-----------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {

	   Board b =  new Board (50,50);
	   for(int r = 20; r < 40; r++)
          for(int c = 20; c < 40; c++)
              b.set(r,c);
		System.out.println("Generation 0");
		b.print('.', 'x');
		System.out.println("Generation 1");
		b=b.next();
		b.print('.','x');
		System.out.println("Generation 2");
		b=b.next();
		b.print('.','x');
		System.out.println("Generation 3");
		b=b.next();
		b.print('.','x');
		System.out.println("Generation 10");
		for(int x = 0; x < 6; x++)
		{
			b=b.next();
		}
		b.print('.','x');
		System.out.println("Generation 20");
		for(int t = 0; t < 9; t++)
		{
			b=b.next();
		}
		b.print('.','x');
   }
//-----------------------------------------------------------------------
} // end class Hw07
/////////////////////////////////////////////////////////////////////////
class Board
{
    private char[][] a;
	private int rmax;
	private int cmax;
	//-----------------------------------------------------------------------
	public Board ( int rmax, int cmax)
	{
		this.rmax = rmax;
	  	this.cmax = cmax;
	  	a = new char[rmax][cmax];
	  	for(int i = 0; i < rmax; i++)
	  		for(int j = 0; j < cmax; j++) 
	  			a[i][j] = '0';
	}
//-----------------------------------------------------------------------
	public void print()
	{
		for(int r = 0; r < rmax; r++)
		{
			for(int c = 0; c < cmax; c++)
				{
					System.out.print(a[r][c]);
				}
			System.out.println();
	    }
	}
//-----------------------------------------------------------------------
	public void print( char char0, char char1)
	{
			for(int r = 0; r < rmax; r++)
			{
			for(int c = 0; c < cmax; c++)
			{
				if(a[r][c] == '0') System.out.println(char0);
				if(a[r][c] == '1') System.out.println(char1);
			}
		System.out.println();
		}
	}
//-----------------------------------------------------------------------
	public Board next()
	{
		Board d = new Board(rmax,cmax);
		for(int r = 1; r < rmax-1; r++)
		{
		   for(int c = 1; c < cmax-1; c++)
		   {
		      int neighbour = 0;
			  for(int r1 = r-1; r1 <= r+1; r1++)
			  {
			     for(int c1 = c-1; c1 <= c+1; c1++)
				 {
				    if(a[r1][c1] == '1')
					neighbour++;
				 }
			  }
			  if(a[r][c]=='1')  neighbour -= 1;
			  if(a[r][c]=='1' &&( neighbour == 3 || neighbour == 2))
			  {
			     d.set(r, c);
			  }
			  if(a[r][c]=='1' && neighbour == 3)
			  {
			     d.set(r, c);
			  }
			  if(a[r][c]=='1' && (neighbour > 3 || neighbour < 2))
			  {
			     d.set(r, c);
			  }
		   }
		}
		return d;
	}
//-----------------------------------------------------------------------
	public void set ( int row , int col)
	{
        a[row][col]  = '1';
	}
//-----------------------------------------------------------------------
	public void reset ( int row, int col)
	{
		a[row][col] = '0';
	}
//-----------------------------------------------------------------------
}//end class Board
/////////////////////////////////////////////////////////////////////////
		

