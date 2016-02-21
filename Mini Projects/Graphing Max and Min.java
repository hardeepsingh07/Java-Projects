// Modified by Hardeep Singh
// Written by Barry Soroka
//
import java.io.*;
////////////////////////////////////////////////////////////////////////////////
class Hw15
{
   private static final int MAXROWS = 40;
   private static final int MAXWIDTH = 60;
//------------------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
	  SinF sin = new SinF(20,0.35);
      makeGraph(sin);
   }
//------------------------------------------------------------------------------
   private static void makeGraph ( Function1D f )
   {
      for ( int i = 0 ; i < MAXROWS ; i++ )
      {
         System.out.printf("%5.1f %5.1f",(double)i,f.valueAt(i));
         double width = (double)f.valueAt(i);
         double high = (double)f.valueAt(0);
		 double low = (double)f.valueAt(0);
         for(int a = 0; a < MAXWIDTH; a++)
		 {
             if(high < f.valueAt(a)) high = f.valueAt(a);
			 if(low > f.valueAt(a)) low = f.valueAt(a);
		 }
		 
		 if ( width > low && width < high)
		    width = (width * 10 + 15) * 1.9;
         for ( int j = 0 ; j < width; j++ ) System.out.print("+");
         System.out.println();
      }
   }
//------------------------------------------------------------------------------
} // end class Hw15
////////////////////////////////////////////////////////////////////////////////
class SinF implements Function1D
{
   private int a;
   private double b;
//-----------------------------------------------------------------------------
   public SinF( int a, double b)
   {
       this.a = a;
	   this.b = b;
   }
//-----------------------------------------------------------------------------
   public double valueAt( double x) { return (x/a)*Math.sin(b*x); }
//-----------------------------------------------------------------------------
}//end class SinF
////////////////////////////////////////////////////////////////////////////////
interface Function1D
{
//------------------------------------------------------------------------------
   public double valueAt ( double x );
//------------------------------------------------------------------------------
} // end interface Function1D
////////////////////////////////////////////////////////////////////////////////
