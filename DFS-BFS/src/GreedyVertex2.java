import graph.Vertex;

public class GreedyVertex2 extends Vertex implements Comparable<GreedyVertex2> {

	private boolean fringe;
	private double c;

	public GreedyVertex2(int i) {
		this(i, 0.0);
	}

	public GreedyVertex2(int i, double c) {
		super(i);
		parent = 0;
		fringe = false;
		this.c = c;
	}

	@Override
	public int compareTo(GreedyVertex2 that) {
		if (this.c < that.c)
			return -1;
		if (this.c == that.c) {
			if (this.index < that.index)
				return -1;
			else
				return 1;
		}
		return 1;
	}

	public void setFringe(boolean fringe) {
		this.fringe = fringe;
	}

	public void setCost(double c) {
		this.c = c;
	}

	public boolean isFringe() {
		return fringe;
	}

	public double getCost() {
		return c;
	}

}
