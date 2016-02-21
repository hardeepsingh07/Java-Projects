//By Hardeep Singh
import graph.Edge;
import graph.GreedyGraph;
import graph.GreedyVertex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShortestPath {

	public static void main(String[] args) throws IOException {
		Scanner kb = new Scanner(System.in);
		System.out.print("Enter a file name: ");
		String fileName = kb.nextLine();

		ShortestPathGraph sph = new ShortestPathGraph(fileName);
		System.out.print(sph);
		int s = sph.getOrder() - 1;
		System.out.println("\n~~~Find the Shortest Path~~~");
		System.out.print("Enter a start index [0-" + s + "]:");
		int start = kb.nextInt();
		System.out.print("Enter an end index [0-" + s + "]:");
		int end = kb.nextInt();
		sph.shortestPathMethod(start, end);
	}
}

// end of ShortestPath
// ----------------------------------------------------------------
class ShortestPathGraph extends GreedyGraph {

	public ShortestPathGraph(String name) throws IOException {
		super(name);
	}

	public double newCost(int v, int w) {
		return /* costOf(w) + */weightOf(new Edge(v, w, directed));
	}

	public void shortestPathMethod(int start, int end) {
		int index = 0;
		double pathLength = 0;
		ArrayList<Integer> a = new ArrayList<Integer>();
		greedy(start);
		a.add(end);
		while (getVertex(end) != getVertex(start)) {
			a.add(getVertex(end).getParent());
			index++;
			pathLength += newCost(a.get(index - 1), a.get(index));
			end = a.get(index);
		}
		System.out.printf("Path length = %1$.2f\n", pathLength);
		System.out.println("Shortest Path = " + a);
	}

}