public class MergeSort implements SortAlogrithm {
	private SortTimer t;
	private double[] temp;
	private double[] a;

	@Override
	public void sort(double[] a, SortTimer t) {
		this.t = t;
		this.a = a;
		temp = new double[a.length];
		mergeSort(0, a.length - 1);

	}

	private void mergeSort(int left, int right) {
		if (left < right) {
			int middle = left + (right - left) / 2;
			mergeSort(left, middle);
			mergeSort(middle + 1, right);
			merge(left, middle, right);
		}
	}

	private void merge(int left, int middle, int right) {
		for (int i = left; i <= right; i++) {
			temp[i] = a[i];
		}
		int p = left;
		int q = middle + 1;
		int k = left;
		while (p <= middle && q <= right) {
			if (temp[p] <= temp[q]) {
				t.addComparision();
				a[k] = temp[p];
				t.addMove();
				p++;
			} else {
				a[k] = temp[q];
				t.addMove();
				q++;
			}
			k++;
		}
		while (p <= middle) {
			t.addMove();
			a[k] = temp[p];
			k++;
			p++;
		}
	}
}
