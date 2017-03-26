package graphs_algorithms_src;

import graph_util.InterfaceUnweightedGraph;

/**
 * A minimum spanning tree is a spanning tree of a connected, undirected graph.
 * it connects all the vertices together with the minimal total weighting for
 * its
 * edges. A single graph can have many different spanning trees.
 * 
 * @author adina
 *
 */
public class MinimumSpanningTree {

	public static String Kurskal(InterfaceUnweightedGraph graph) {
		int numVertices = graph.getNumVertices();
		// if there are no vertices in the graph
		if (numVertices == 0)
			return "[]";

		StringBuilder sb = new StringBuilder();
		return "[ " + sb + "]";
	}
	// public void minSpanTree() {
	// vertices[0].wasVisited = true;//visited first node
	// Stack<Integer> stack = new Stack<>();
	// stack.push(0);
	//
	// while (!stack.isEmpty()) {
	// // get an unvisited vertex adjacent to stack top
	// int v0 = stack.peek();
	// int v = getAdjUnvisited(v0);
	// if (v == -1) {//if no more unvisited
	// stack.pop();
	// } else {//v is not visited
	// vertices[v].wasVisited = true;//visited
	// stack.push(v);
	// printVert(v0);
	// printVert(v);
	// System.out.print(" ");
	// }
	// }
	// //reset flags
	// }
}
