import java.util.Scanner;

public class Matrices {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("*****Matrix Program*****");
		Matrix m = new Matrix(fill(), fill());
		boolean done = false;
		while (!done) {
			System.out.println("\n*****Matrix Operations*****");
			System.out.println("1. Addition");
			System.out.println("2. Subtraction");
			System.out.println("3. Multiplication");
			System.out.println("4. New Data");
			System.out.println("5. Quit");
			System.out.print("Enter your choice: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println();
				double resultA[][] = m.add();
				if(resultA != null) {
					m.printMatrix(resultA);
				}
				break;
			case 2:
				System.out.println();
				double resultS[][] = m.subtract();
				if(resultS != null) {
					m.printMatrix(resultS);
				}
				break;
			case 3:
				System.out.println();
				double resultM[][] = m.multiply();
				if(resultM != null) {
					m.printMatrix(resultM);
				}
				break;
			case 4:
				m = new Matrix(fill(), fill());
				break;
			case 5:
				System.out.println("Good Bye!");
				done = true;
				System.exit(0);
				break;
			}
		}

	}

	public static double[][] fill() {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the Dimension of the Matrix(n <space> n): ");
		String n = in.nextLine();
		String[] temp = n.split(" ");
		int r = Integer.parseInt(temp[0]);
		int c = Integer.parseInt(temp[1]);
		double[][] data = new double[r][c];
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[row].length; col++) {
				System.out.print("Enter " + row + ":" + col + "th: ");
				data[row][col] = in.nextDouble();
			}
			System.out.println();
		}
		return data;
	}
}