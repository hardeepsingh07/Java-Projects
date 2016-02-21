public class HeapSort implements SortAlogrithm {
	private SortTimer t;
	private double[] a;
	int length;

	@Override
	public void sort(double[] a, SortTimer t) {
		this.a = a;
		this.t = t;
		length = a.length;
		heapify(a);
		heap(a);

	}

	public void heap(double[] a) {
		double[] heap = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			heap[i] = poll();
		}
		for (int i = 0; i < a.length; i++) {
			a[i] = heap[i];
		}
	}

	public void siftDown(int i) {
		int b = i;
		int c = smallestChild(b);
		while (hasChild(b) && a[b] > a[c]) {
			t.addComparision();
			swap(b, c);
			b = c;
		}
	}

	public boolean hasChild(int i) {
		t.addComparision();
		if (i <= (length / 2 - 1)) {
			return true;
		}
		return false;
	}

	public int smallestChild(int i) {
		if (length - 1 >= (2 * i + 2)) {
			if (a[2 * i + 1] > a[2 * i + 2]) {
				t.addComparision();
				return (2 * i + 2);
			}
		}
		return (2 * i + 1);
	}

	public void heapify(double[] a) {
		int k = a.length / 2 - 1;
		for (int i = k; i >= 0; i--) {
			siftDown(i);
		}
	}

	public double poll() {
		double e = a[0];
		a[0] = a[length - 1];
		length--;
		t.addMoves(3);
		siftDown(0);
		return e;
	}

	public void swap(int i, int k) {
		double temp = a[i];
		a[i] = a[k];
		a[k] = temp;
		t.addMoves(3);
	}
}
