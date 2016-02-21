import java.util.Scanner;

public class Matrix {

	public static double[][] m1;
	public static double[][] m2;

	public Matrix(double[][] m1, double[][] m2) {
		this.m1 = m1;
		this.m2 = m2;

		System.out.println("Matrix 1: ");
		printMatrix(m1);
		System.out.println("Matrix 2: ");
		printMatrix(m2);
	}

	public static double[][] add() {
		int m = m1.length;
		int n = m1[0].length;
		int s = m2.length;
		int q = m2[0].length;
		if (m == s && n == q) {
			double[][] result = new double[m][n];
			for (int i = 0; i < m; i++)
				for (int j = 0; j < n; j++)
					result[i][j] = m1[i][j] + m2[i][j];
			return result;
		} else {
			System.out.println("Matrices are not the same size");
		}
		return null;
	}

	public static double[][] subtract() {
		int m = m1.length;
		int n = m1[0].length;
		int s = m2.length;
		int q = m2[0].length;
		if (m == s && n == q) {
			double[][] result = new double[m][n];
			for (int i = 0; i < m; i++)
				for (int j = 0; j < n; j++)
					result[i][j] = m1[i][j] - m2[i][j];
			return result;
		} else {
			System.out.println("Matrices are not the same size");
		}
		return null;
	}

	public static double[][] multiply() {
		int mM1 = m1.length;
		int nM1 = m1[0].length;
		int mM2 = m2.length;
		int nM2 = m2[0].length;
		if (nM1 != mM2) {
			System.out.println("Matrix dimensions are not approriate");
		} else {
			double[][] result = new double[mM1][nM2];
			for (int i = 0; i < mM1; i++)
				for (int j = 0; j < nM2; j++)
					for (int k = 0; k < nM1; k++)
						result[i][j] += m1[i][k] * m2[k][j];
			return result;
		}
		return null;
	}

	public static double[][] fill() {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the n-Deminsion of the Matrix: ");
		int n = in.nextInt();
		double[][] data = new double[n][n];
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[row].length; col++) {
				System.out.print("Enter " + row + ":" + col + "th: ");
				data[row][col] = in.nextDouble();
			}
			System.out.println();
		}
		return data;
	}

	public void printMatrix(double[][] result) {
		int mResult = result.length;
		int nResult = result[0].length;
		for (int i = 0; i < mResult; i++) {
			for (int j = 0; j < nResult; j++) {
				System.out.printf("%.2f ", result[i][j]);
			}
			System.out.print("\n");
		}
		System.out.println();
	}
}
