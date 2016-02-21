public class InsertionSort implements SortAlogrithm {
	private SortTimer t;
	
	@Override
	public void sort(double[] a, SortTimer t) {
		this.t = t;
		for(int i = 1; i < a.length; i++)	{
			double temp = a[i];
			int j;
			for(j = i; j > 0; j--)	{
				if(a[j - 1] < temp)	break;
				a[j] = a[j - 1];
				t.addComparision();
				t.addMove();
			}
			a[j] = temp;
			t.addMove();
		}			
	}
}
