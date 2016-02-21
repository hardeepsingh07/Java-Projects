import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class dTable {
	private double[][]method;
	private String filename;
	private ArrayList<Double> coefficient;
	private String [][] formatTable;
	
	public dTable(String filename) throws IOException	{
		this.filename = filename;
		this.readFromFile();
		this.coefficient = new ArrayList<Double>();
		
	}
	public void readFromFile() throws IOException	{
		String[] x = null;
		String [] fx = null;
		FileInputStream fstream = new FileInputStream(this.filename);
		DataInputStream inputStream = new DataInputStream(fstream);
		BufferedReader reader = new BufferedReader
				(new InputStreamReader(inputStream));
		x = reader.readLine().split("\\s+");
		fx = reader.readLine().split("\\s+");
		this.method = new double[x.length][x.length + 1];
		if(x.length != fx.length)
			System.out.println("Numbers are one to one fucntions");
		this.method = new double[x.length][x.length + 1];
		for(int i = 0; i < x.length; i++)
			method[i][0] = Double.parseDouble(x[i]);
		for(int j = 0; j < fx.length; j++)
			method[j][1] = Double.parseDouble(fx[j]);
	}
	public void solve() {
		int xlength = this.method[0].length;
		for(int i = 2; i < xlength; i++)	{
			for(int j = 0; j < xlength - i; j++ )	{
				this.method[j][i] = (this.method[j + 1][i - 1] - this.method[j][i - 1])
									/
									(this.method[j + (i - 1)][0] - this.method[j][0]);
			}			
		}
		for(int i = 1; i < method[0].length; i++)	{
			this.coefficient.add(method[0][i]);
		}
	}
	public void alignTable()
	{
		int width = this.method[0].length;
		int length = 2 * this.method.length;
		this.formatTable = new String[length][width];
		for(int i = 0; i < this.formatTable.length; i++)  	{
			for(int j = 0; j < this.formatTable[i].length; j++)	{
				this.formatTable[i][j] = String.format("%7s", "  ");
			}
		}
		int s = 0;
		for(int i = 0; i < this.method.length; i++){
			this.formatTable[s][0] = String.format("%7.2f", this.method[i][0]);
			s += 2;
		}
		s = 0;
		for(int i = 0; i < this.method.length; i++){
			this.formatTable[s][1] = String.format("%7.2f", this.method[i][1]);
			s += 2;
		}
		int n = this.method[0].length;
		for(int j = 2; j < n; j++)	{
			s = j -1;
			for(int i = 0; i < n - j; i++)	{
				formatTable[s][j] = String.format("%7.2f", this.method[i][j]);
				s += 2;
			}
		}
	}
	
	public void interPoly()	{
		ArrayList<String> x = new ArrayList<String>();
		String s = "";
		for(int i = 0; i < this.method.length - 1; i++)	{
			double n = this.method[i][0];
			if(n < 0)	{
				s = "+";
			}
			else if(n > 0)	{
				s = "-";
			}
			if(round(n) == 0)	{
				x.add("(x)");
			}
			else	{
				x.add(String.format("(x%s%.2f)", s, n));
			}
		}
		String polynominal = String.format("%.2f", this.coefficient.get(0));
		for(int i = 1; i < x.size() + 1; i++)	{
			double h = this.coefficient.get(i);
			if(h != 0)	{
				if(h > 0)	{
					s = "+";
				}
				else	{
					s = "-";
				}
				String p = "";
				for(int j = 0; j < i; j++)	{
					p += x.get(j);
				}
				polynominal += String.format(" %s %.3f%s", p, Math.abs(h), p);
			}
		}
		System.out.println(polynominal);		
	}
	
	
	public void simpfiedPoly()	{
		Polynominal p = new Polynominal();
		ArrayList<Double> x = new ArrayList<Double>();
		ArrayList <ArrayList <Double>> n = new ArrayList <ArrayList <Double>>(); 
		
		for(int i = 0; i < this.method[0].length - 1; i++)	{
			x.add(0.0);
		}
		x.add(0, this.coefficient.get(0));
		n.add(x);
		
		for(int i = 1; i < this.coefficient.size(); i++)	{
			x = new ArrayList<Double>();
			double s = this.coefficient.get(i);
			for(int j = 0; j < i; j++)	{
				x.add(this.method[j][0]);
			}
			n.add(p.expand(s, x, this.method[0].length));
		}
		x = p.compress(n);
		System.out.println(simplifiedString(x));
	}
	public double round(double x)	{
		return (double)Math.round(x * 1000)/1000;
	}
	public String simplifiedString(ArrayList<Double> x)	{
		String polynominal = "";
		String exponent = "";
		for(int i = 0; i < x.size(); i++)	{
			Double m = x.get(i);
			exponent = String.format("x^%d", i);
			if(m != 0)	{
				if(i == 0)	{
					polynominal += String.format(" %+.2f", m);
				}
				else {
					polynominal += String.format(" %+.2f%s", m, exponent);
				}
			}
		}
		return polynominal;
	}
	public double[][] getMethod(){
		return this.method;
	}
	public ArrayList<Double> getCoefficient()	{
		return this.coefficient;
	}
	public void formattedTable()	{
		alignTable();
		for(String[] i: formatTable)	{
			for(String j : i)	{
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}
	public void printMethod()	{
		for(int i = 0; i < this.method.length; i++)	{
			for(int j = 0; j < this.method[i].length; j++)	{
				System.out.printf("%8.2f", this.method[i][j]);
			}
			System.out.println("\n");
		}
	}
}

