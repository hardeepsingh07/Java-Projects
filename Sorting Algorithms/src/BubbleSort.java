public class BubbleSort implements SortAlogrithm {
	private SortTimer t;

	@Override
	public void sort(double[] a, SortTimer t) {
		this.t = t;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length - 1; j++) {
				t.addComparision();
				if (a[j] > a[j + 1]) {
					swap(a, j, j + 1);
				}
			}
		}
	}

	public void swap(double[] a, int j, int jn) {
		double temp;
		temp = a[j];
		a[j] = a[jn];
		a[jn] = temp;
		t.addMoves(3);
	}
}
