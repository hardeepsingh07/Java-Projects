public class QuickSort implements SortAlogrithm {
	private SortTimer t;
	private double[] a;

	@Override
	public void sort(double[] a, SortTimer t) {
		this.t = t;
		this.a = a;
		quickSort(0, a.length - 1);

	}

	private void quickSort(int left, int right) {
		if (left < right) {
			int pivot = left + (int) (Math.random() * ((right - left) - 1));
			int pos = partition(left, right, pivot);
			quickSort(left, pos - 1);
			quickSort(pos + 1, right);
		}
	}

	private int partition(int left, int right, int iPivot) {
		double pivot = a[iPivot];
		swap(iPivot, right);
		int pos = left;
		for(int i = left; i < right; i++)	{
			if(a[i] <= pivot)	{
				swap(i,pos);
				pos++;
				t.addComparision();
			}
		}
		swap(pos,right);
		return pos;
	}

	private void swap(int i, int j) {
		double temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		t.addMoves(3);	
	}

}
