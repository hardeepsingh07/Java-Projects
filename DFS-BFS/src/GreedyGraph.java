import java.io.IOException;

import graph.Edge;
import graph.Graph;
import graph.GreedyPriorityQueue;
import graph.GreedyVertex;
import java.lang.Package;

public class GreedyGraph extends Graph {
	private GreedyPriorityQueue p;

	public GreedyGraph(String name) throws IOException {
		process_header(name);
		add_vertices();
		add_edges();
	}

	public double weightOf(Edge e) {
		return getEdge(e).getWeight();
	}

	public void add_vertices() {
		vertices = new GreedyVertex[order];
		p = new GreedyPriorityQueue();
		for (int i = 0; i < order; i++) {
			vertices[i] = new GreedyVertex(i);
		}
	}

	public GreedyVertex getVertex(int i) {
		return (GreedyVertex)vertices[i];
	}

	public void greedy(int u) {
		setCost(u, 0.0);
		p.add(getVertex(u));
		while (p.size() > 0) {
			int v = p.poll().getIndex();
			markVertex(v);
			for (int w : getNeighbors(v)) {
				if (!vertexMarked(w)) {
					if (isFringe(w)) {
						if (newCost(v, w) < costOf(w))
							modifyFringe(v, w);
					} else
						addFrindge(v, w);
				}
			}
		}
	}

	private void addFrindge(int v, int w) {
		// TODO Auto-generated method stub
		getEdge(v, w).setSelected(true);
		double cost = newCost(v, w);
		GreedyVertex vertex = getVertex(w);
		vertex.setFringe(true);
		vertex.setParent(v);
		vertex.setCost(cost);
		p.add(vertex);
	}

	private void modifyFringe(int v, int w) {
		// TODO Auto-generated method stub
		getEdge(v, w).setSelected(true);
		getEdge(getVertex(w).getParent(), w).setSelected(false);
		double cost = newCost(v, w);
		GreedyVertex vertex = getVertex(w);
		vertex.setParent(v);
		vertex.setCost(cost);
		p.promote(vertex);
	}

	private double costOf(int w) {
		// TODO Auto-generated method stub
		return getVertex(w).getCost();
	}

	private double newCost(int v, int w) {
		// TODO Auto-generated method stub
		return 0.0;
	}

	private boolean isFringe(int w) {
		// TODO Auto-generated method stub
		return getVertex(w).isFringe();
	}

	private void setCost(int u, double d) {
		// TODO Auto-generated method stub
		getVertex(u).setCost(d);
	}

}
