//by Hardeep Singh
import java.util.Random;
import java.util.Scanner;

public class Sorter {
	public static void main(String[] args) {
		boolean check = true;
		SortAlogrithm s = null;
		SortTimer t = new SortTimer();
		System.out.println("*****Welcome to Sorter.*****");
		System.out.println("1. Selection");
		System.out.println("2. Bubble");
		System.out.println("3. Insertion");
		System.out.println("4. Merge");
		System.out.println("5. Heap");
		System.out.println("6. Quick");
		System.out.println("7. Exit");

		while (check) {
			System.out
					.print("Which method would you like to use (Example -: Bubble): ");
			Scanner kb = new Scanner(System.in);
			String option = kb.nextLine();
			System.out.print("Raise 10 to what power for array size: ");
			int rN = kb.nextInt();
			System.out.println();

			// switch according to the input
			switch (option) {
			case "Selection": {
				s = new SelectionSort();
				System.out.println("Selection Sort");
				break;
			}
			case "Bubble": {
				s = new BubbleSort();
				System.out.println("Bubble Sort");
				break;
			}
			case "Insertion": {
				s = new InsertionSort();
				System.out.println("Insertion Sort");
				break;
			}
			case "Merge": {
				s = new MergeSort();
				System.out.println("Merge Sort");
				break;
			}
			case "Heap": {
				s = new HeapSort();
				System.out.println("Heap Sort");
				break;
			}
			case "Quick": {
				s = new QuickSort();
				System.out.println("Quick Sort");
				break;
			}
			case "Exit": {
				check = false;
				System.exit(0);
			}
			default: {
				System.out
						.println("String do not match any of the choices, terminating the program now.");
				System.exit(0);
			}
			}

			// send in the randomize array to the sort method
			long comparison;
			long moves;
			long time;
			long avgComp = 0;
			long avgMoves = 0;
			long avgTime = 0;
			System.out.println("\tn    Time(ms)   Comparison       Moves");
			for (int i = 1; i <= rN; i++) {
				int p = (int) Math.pow(10, i);
				double [] n = randomize(i, p);
				t.reset();
				s.sort(n, t);
				if (!verify(n)) {
					System.out
							.println("Array could not be sorting. Exiting...");
					System.exit(1);
				}
				comparison = t.getComparison();
				moves = t.getMoves();
				time = t.getElapsedTime() / 1000;
				avgComp += comparison;
				avgMoves += moves;
				avgTime += time;
				System.out.printf("%9d%12d%13d%12d\n", p, time, comparison,
						moves);
			}
			avgComp = avgComp / rN;
			avgMoves = avgMoves / rN;
			avgTime = avgTime / rN;
			System.out
					.println("-----------------------------------------------");
			System.out.printf("  Average  %10d%13d%12d", avgTime, avgComp,
					avgMoves);
			System.out.println("\n");
		}
	}

	// ----------------------------------------------------------------------------
	// i = exponent and n = 10 raise to i
	public static double[] randomize(int i, int n) {
		Random rG = new Random();
		double[] a = new double[n];
		for (int j = 0; j < n; j++) {
			a[j] = rG.nextInt(n);
		}
		return a;
	}

	// ----------------------------------------------------------------------------
	private static void print(double[] n) {
		for (int i = 0; i < n.length; i++) {
			System.out.print(n[i] + " ");
		}
		System.out.println();
	}

	// ------------------------------------------------------------------------------
	private static boolean verify(double[] n) {
		for (int i = 0; i < n.length - 1; i++) {
			if (n[i] < n[i + 1])
				return true;
		}
		return false;
	}
	// -------------------------------------------------------------------------------
}
