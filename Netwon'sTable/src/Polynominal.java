import java.util.spi.*;
import java.util.ArrayList;

public class Polynominal {
	
	public ArrayList<Double> expand(double s, ArrayList<Double> x,
			int length)	{
		ArrayList<ArrayList<Double>> n = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> t = new ArrayList<Double>();
		for(int i = 0; i < x.size() + 1; i++)	{
			t.add(0.0);
		}
		t.add(0, s);
		for(int i = 0; i < x.size(); i++)	{
			n.add(add(t));
			n.add(multiply(t, -x.get(i)));
			t = compress(n);
			n.clear();
		}
		int tLength = t.size();
		for(int i = 0; i < length - tLength; i++)	{
			t.add(0.0);
		}
		return t;
	}
	public ArrayList<Double> compress(ArrayList<ArrayList<Double>> n) 	{
		ArrayList<Double> comp = new ArrayList<Double>();
		for(int i = 0; i < n.get(0).size(); i++)	{
			double sum = 0.0;
			for(int j = 0; j < n.size(); j++)	{
				sum += n.get(j).get(i);
			}
			comp.add(sum);
		}
		return comp;
	}
	public ArrayList<Double> add(ArrayList<Double> t)	{
		ArrayList<Double> s = new ArrayList<Double>();
		s.add(0.0);
		for(int i = 0;i < t.size() - 1; i++)	{
			s.add(t.get(i));
		}
		return s;
	}
	public ArrayList<Double> multiply(ArrayList<Double> t, double x)	{
		ArrayList<Double> s = new ArrayList<Double>();
		for(double d : t)	{
			s.add(d * x);
		}
		return s;
	}
}
