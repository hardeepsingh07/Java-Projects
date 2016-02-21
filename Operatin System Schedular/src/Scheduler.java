import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Scheduler {

	public static String line;
	public static String line1;
	public static ArrayList<Jobs> myList = new ArrayList<Jobs>();
	public static ArrayList<Jobs> copy = new ArrayList<Jobs>();
	public static int highestTimeIndex;
	public static DecimalFormat decimalFormatter;
	public static FileReader file;
	public static String filename;

	@SuppressWarnings({ "unchecked" })
	public static void main(String[] args) throws Exception {

		// Initialize the decimal formatter
		decimalFormatter = new DecimalFormat("##.##");

		// File names
		String[] filenames = { "testdata1.txt", "testdata2.txt", "testdata3.txt" };

		for (int i = 0; i < filenames.length; i++) {
			System.out.println("------------------------------------" 
							+ filenames[i].replace(".txt", "").toUpperCase()
								+ "-----------------------------------\n\n");
			myList.clear();
			copy.clear();
			// Get the File
			try {
				file = new FileReader(filenames[i]);
			} catch (Exception e) {
				System.out.println("No file found please try again...");
				System.exit(0);
			}

			// Read from file
			BufferedReader br = new BufferedReader(file);

			// Read from file and store it in ArrayList of objects
			while ((line = br.readLine()) != null && (line1 = br.readLine()) != null) {
				myList.add(new Jobs(line, Integer.parseInt(line1)));
			}
			br.close();

			fcfs(); // first come first serve algorithm
			SJF(); // Shortest job first

			// get the highest time for while loop
			Jobs temp = Collections.max(myList);
			highestTimeIndex = myList.indexOf(temp);

			RRB3(); // Round Robin Quantum 3
			RRB4(); // Round Robin Quantum 4
			// print();
		}

	}

	public static void fcfs() {
		System.out.println("\t\t\tFirst come First Serve Algorithm");
		printHeader();

		// create variables
		String jobName;
		int startTime = 0;
		int endTime;
		int completeTime;
		int CompTime = 0;
		double averageCompTime = 0.00;

		// Process the information
		for (Jobs j : myList) {
			jobName = j.getJob();
			endTime = j.getTime();
			completeTime = startTime + endTime;
			CompTime += completeTime;
			print(jobName, startTime, endTime, completeTime, 0, false);
			startTime = completeTime;
		}

		averageCompTime = (double) CompTime / myList.size();
		System.out.println("\t\t\t    Completion Time: " + CompTime);
		System.out.println("\t\t\tAverage Completion Time: " + 
										decimalFormatter.format(averageCompTime) + "\n");

	}

	@SuppressWarnings("unchecked")
	public static void SJF() {
		// make a copy
		for (Jobs j : myList) {
			copy.add(new Jobs(j));
		}

		// sort the copy
		Collections.sort(copy);

		// Print the Sorted List first
		System.out.println("Sorted List:");
		printList(copy);

		// Print Header
		System.out.println("\n\t\t\t      Shortest Job First");
		printHeader();

		// Create Variables
		String jobName;
		int startTime = 0;
		int endTime;
		int completeTime;
		int CompTime = 0;
		double averageCompTime = 0.0;

		// Process the information
		for (Jobs j : copy) {
			jobName = j.getJob();
			endTime = j.getTime();
			completeTime = startTime + endTime;
			CompTime += completeTime;
			print(jobName, startTime, endTime, completeTime, 0, false);
			startTime = completeTime;
		}
		averageCompTime = (double) CompTime / myList.size();
		System.out.println("\t\t\t    Completion Time: " + CompTime);
		System.out.println("\t\t\tAverage Completion Time: " + 
										decimalFormatter.format(averageCompTime) + "\n");
	}

	public static void RRB3() {
		System.out.println("\t\t\t    Round-Robin Quamtum 3");
		printHeader();

		// Create Variables
		String jobName;
		int startTime = 0;
		int endTime;
		int completeTime = 0;
		int remainingTime = 0;
		int CompTime = 0;
		double averageCompTime = 0.0;

		// Process the Information
		while (myList.get(highestTimeIndex).getTimeCopy1() != 0) {
			for (Jobs j : myList) {
				jobName = j.getJob();
				endTime = j.getTimeCopy1();
				if (endTime > 3) {
					j.rrb3Decrement(3); // Quantum subtract 3
					remainingTime = j.getTimeCopy1();
					completeTime += 3;
					endTime = completeTime;
					print(jobName, startTime, endTime, completeTime, remainingTime, true);
					startTime = completeTime;
					endTime = j.getTimeCopy1();
				} else {
					if (endTime != 0) {
						j.rrb3Decrement(endTime);
						completeTime += endTime;
						endTime = completeTime;
						CompTime += completeTime;
						remainingTime = 0;
						print(jobName, startTime, endTime, completeTime, remainingTime, false);
						endTime = j.getTimeCopy1();
						startTime = completeTime;
					}
				}
			}
		}
		averageCompTime = (double) CompTime / myList.size();
		System.out.println("\t\t\t    Completion Time: " + CompTime);
		System.out.println("\t\t\tAverage Completion Time: " + 
										decimalFormatter.format(averageCompTime) + "\n");
	}

	public static void RRB4() {
		System.out.println("\t\t\t    Round-Robin Quantum 4");
		printHeader();

		// Create Variables
		String jobName;
		int startTime = 0;
		int endTime;
		int completeTime = 0;
		int remainingTime;
		int CompTime = 0;
		double averageCompTime = 0.0;

		// Process Information
		while (myList.get(highestTimeIndex).getTimeCopy2() != 0) {
			for (Jobs j : myList) {
				jobName = j.getJob();
				endTime = j.getTimeCopy2();
				if (endTime > 4) {
					j.rrb4Decrement(4); // Quantum subtract 4
					remainingTime = j.getTimeCopy2();
					completeTime += 4;
					endTime = completeTime;
					print(jobName, startTime, endTime, completeTime, remainingTime, true);
					startTime = completeTime;
					endTime = j.getTimeCopy2();
				} else {
					if (endTime != 0) {
						j.rrb4Decrement(endTime);
						completeTime += endTime;
						endTime = completeTime;
						CompTime += completeTime;
						remainingTime = 0;
						print(jobName, startTime, endTime, completeTime, remainingTime, false);
						endTime = j.getTimeCopy2();
						startTime = completeTime;
					}
				}
			}
		}
		averageCompTime = (double) CompTime / myList.size();
		System.out.println("\t\t\t    Completion Time: " + CompTime);
		System.out.println("\t\t\tAverage Completion Time: " + 
									decimalFormatter.format(averageCompTime) + "\n");
	}

	public static void printHeader() {
		System.out.println(" --------------------------------------------"
				+ "----------------------------------");
		System.out.println("|  Job#  |  Start Time  |   End Time   | "
				+ " Completion Time  |  Remaining Time   |");
		System.out.println(" ----------------------------------------"
				+ "--------------------------------------");
	}

	public static void print(String jobName, int startTime, int endTime, 
			int completeTime, int remainingTime, boolean trigger) {
		if (trigger) {
			System.out.println("|  " + jobName + "\t" + startTime 
					+ "\t\t" + endTime + "\t\t" + "X" + "\t\t     "
					+ remainingTime + "\t       |");
			System.out.println(" -----------------------------------------"
					+ "-------------------------------------");
		} else {
			System.out.println("|  " + jobName + "\t" + startTime + "\t\t" 
					+ endTime + "\t\t" + completeTime
					+ "\t\t     " + remainingTime + "\t       |");
			System.out.println(" ---------------------------------------------"
					+ "---------------------------------");
		}
	}

	// For Debugging Purposes
	public static void printList(ArrayList<Jobs> list) {
		for (Jobs j : list) {
			System.out.println("\tJob #: " + j.getJob() + " Time: " + j.getTime());
		}
	}

}
