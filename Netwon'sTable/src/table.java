// By Hardeep Singh
// Interpolating and Simplied Polynominal
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class table {
	public static void main(String[] args) throws IOException {
		String filename;
		Scanner kb =  new Scanner(System.in);
		System.out.print("Enter name of the file: ");
		filename = kb.nextLine();
		dTable dTable = new dTable(filename);
		dTable.solve();
		System.out.println("Table");
		dTable.formattedTable();
		System.out.println("Interpolating Polynomial");
		dTable.interPoly();
		System.out.println("Simplified Polynomial");
		dTable.simpfiedPoly();
	}

}

