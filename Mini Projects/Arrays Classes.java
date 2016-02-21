// Written by Hardeep Singh
//
// description of this program
//
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
/////////////////////////////////////////////////////////////////////////
class Hw18
{
//-----------------------------------------------------------------------
   public static void main ( String [] args ) throws Exception
   {
	   Items [] items = new Items[7];

	   items[0] = new UnitItem("tissue",        1.22);
	   items[1] = new BulkItem("chicken,wings", 2.57);
	   items[2] = new BulkItem("chicken,whole", 1.80);
	   items[3] = new UnitItem("jello",         2.89);
	   items[4] = new UnitItem("crackers",      2.95);
	   items[5] = new BulkItem("bananas",       1.42);
	   items[6] = new UnitItem("broth",         0.97);
	   
	   Scanner kb= new Scanner(System.in);
	   int index = 0;
	   double price, total=0;
	   String item = "";
	   while(index != -1)
	   {
	      System.out.print("\nItem? ");
	      item= kb.nextLine();
	      index = indexOf(item,items);
	      if (index == -2)
	    	  System.out.println("Sorry, but we don't carry that item here.");
	      else
	        if(index != -1)
	        {
	           price=items[index].askMultiplyPrint();
	           System.out.printf("Cost is $%2.2f \n",price);
	           total += price;
	        }
	   } 
       System.out.printf("\nYour total cost is $%2.2f",total);
       System.out.println("\nThank you for shopping with Java!");
	   }
//----------------------------------------------------------------------- 
    public static int indexOf(String name, Items[] items)
    {
	   for(int i= 0;i < items.length;i++)
	   {
	      if(items[i].getName().equals(name))
	    	  return i;
	   }
	   if(name.equals("quit"))
	         return -1;
	   return -2;
	   }
//-----------------------------------------------------------------------
} // end class Hw18
/////////////////////////////////////////////////////////////////////////
abstract class Items
{
	private String name;
//-----------------------------------------------------------------------
	public Items ( String name ) 
	{
		this.name = name;
	}
//-----------------------------------------------------------------------
	public String getName() { return name;}
//-----------------------------------------------------------------------
	abstract public double askMultiplyPrint();
//-----------------------------------------------------------------------
} // end class Items
/////////////////////////////////////////////////////////////////////////
class UnitItem extends Items
{
	private double unitPrice;
//-----------------------------------------------------------------------
	public UnitItem ( String name, double unitPrice)
	{
		super(name);
		this.unitPrice = unitPrice;
	}
//-----------------------------------------------------------------------
	public double askMultiplyPrint()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Quantity desired? ");
		int quantity = keyboard.nextInt();
		return (quantity * unitPrice);
	}
//-----------------------------------------------------------------------
} // end class UnitItem
/////////////////////////////////////////////////////////////////////////
class BulkItem extends Items
{
	private double pricePerPound;
//-----------------------------------------------------------------------
	public BulkItem ( String name, double pricePerPound)
	{
		super(name);
		this.pricePerPound = pricePerPound;
	}
//-----------------------------------------------------------------------
	public double askMultiplyPrint()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Pounds desired? ");
		double pounds = keyboard.nextDouble();
		return (pounds * pricePerPound);
	}
//-----------------------------------------------------------------------
} // end class UnitItem
/////////////////////////////////////////////////////////////////////////