import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Hardeep Singh
 * 
 */
public class Shop {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.print("Not enough files.");
		}
		System.out.println("*****Welcome to Online Shopping Cart*****");
		// Initialize Variables
		MyTreeMap<String, Integer> items = new MyTreeMap<String, Integer>();
		MyTreeMap<String, ArrayList<String>> carts = new MyTreeMap<String, ArrayList<String>>();
		String iFile = args[0];
		String oFile = args[1];

		// Read through Item file and store it in the <TreeMap items>
		try {
			BufferedReader read = new BufferedReader(new FileReader(iFile));
			String line;
			while ((line = read.readLine()) != null) {
				String[] iConstruction = line.split(Pattern.quote("$"));
				String temp = iConstruction[0];
				String iName = temp.trim();
				int iPrice = Integer.parseInt(iConstruction[1]);
				items.put(iName, iPrice);
			}
			read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Read through Order file and store it in the <TreeMap carts>
		try {
			BufferedReader read = new BufferedReader(new FileReader(oFile));
			String line;
			while ((line = read.readLine()) != null) {
				String[] oConstruction = line.split(" ");
				String oCommand = oConstruction[0];
				String oName = oConstruction[1];

				switch (oCommand) {
				case "add": {
					String oItem = oConstruction[2];
					add(carts, oName, oItem, items);
					break;
				}
				case "delete": {
					String oItem = oConstruction[2];
					delete(carts, oName, oItem, items);
					break;
				}
				case "cart": {
					cart(carts, oName, items);
					break;
				}
				case "checkout": {
					checkout(carts, oName, items);
					break;
				}
				}
			}
			read.close();
			System.out.println("");
			System.out.println("**Thank you for using Online Shopping Cart**");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Take specific customer(if already exist), add item to the list and update the cart. 
	private static void add(MyTreeMap<String, ArrayList<String>> carts, String oName, String oItem, MyTreeMap<String, Integer> items) {
		if (carts.containsKey(oName)) {	
			ArrayList<String> reUse = carts.get(oName);
			if(reUse.contains(oItem))	{
				System.out.println("-Item already exist in " + oName + "'s cart.");
				System.out.println("");
			} else {

				reUse.add(oItem);
				carts.put(oName, reUse);
				System.out.println("-" + oItem + " is added to " + oName
						+ "'s cart.");
				System.out.println("");
			}
		} else {
			ArrayList<String> cShopping = new ArrayList<String>();
			cShopping.add(oItem);
			carts.put(oName, cShopping);
			System.out.println("-" + oItem + " is added to " + oName + "'s cart.");
			System.out.println("");
		}
	}

	// Take specific customer, remove item from their list and update the cart.
	private static void delete(MyTreeMap<String, ArrayList<String>> carts, String oName, String oItem, MyTreeMap<String, Integer> items) {
		if (carts.containsKey(oName)) {
			ArrayList<String> reUse = carts.get(oName);
			int index = reUse.indexOf(oItem);
			reUse.remove(index);
			carts.put(oName, reUse);
			System.out.println("-" + oItem + " is deleted from " + oName + "'s cart.");
			System.out.println("");
		} else {
			System.out.println("-" + "Customer name " + oName + " does not exist.");
			System.out.println("");
		}
	}

	// Retrieve the view of specific customer and print it out. 
	private static void cart(MyTreeMap<String, ArrayList<String>> carts, String oName, MyTreeMap<String, Integer> items) {
		if (carts.containsKey(oName)) {
			ArrayList<String> reUse = carts.get(oName);
			System.out.println("-" + oName + " shopping cart include -: ");
			System.out.println("  ITEMS            PRICE");
			System.out.println("-------------------------");
			for(int i = 0; i < reUse.size(); i++)	{
				
				System.out.printf("  %-15s  $%d\n", reUse.get(i), items.get(reUse.get(i)));
			}
			System.out.println("");
		} else {
			System.out.println("-" + "Customer name " + oName + " does not exist.");
			System.out.println("");
		}
	}

	// Take the name of specific customer, print all the item in the cart and display total.
	private static void checkout(MyTreeMap<String, ArrayList<String>> carts, String oName, MyTreeMap<String, Integer> items) {
		int total = 0;
		if(carts.containsKey(oName))	{
			System.out.println("");
			cart(carts,oName,items);
			ArrayList<String> reUse = carts.get(oName);
			for(int i = 0; i < reUse.size(); i++)	{
				total += items.get(reUse.get(i));
			}
			System.out.print("-" + oName + " is checking out,");
			System.out.println(" checkout total is-: $" + total);
		} else	{
			System.out.println("-" + "Customer name " + oName + " does not exist.");
		}

	}
}
