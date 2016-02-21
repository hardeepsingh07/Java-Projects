public class SelectionSort implements SortAlogrithm {
	private SortTimer t;

	@Override
	public void sort(double[] a, SortTimer t) {
		this.t = t;
		int min;
		for (int i = 0; i < a.length - 1; i++) {
			min = i;
			for (int j = i + 1; j < a.length; j++) {
				t.addComparision();
				if (a[j] < a[min])
					min = j;
			}
			if (min != i) {
				swap(a, i, min);
			}
		}
	}

	public void swap(double[] a, int i, int min) {
		double temp;
		temp = a[i];
		a[i] = a[min];
		a[min] = temp;
		t.addMoves(3);
	}
}
