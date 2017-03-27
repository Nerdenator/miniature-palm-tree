package graphs_algorithms_src;

import java.util.LinkedList;
import java.util.List;

import graph_util.InterfaceUnweightedGraph;

/**
 * A minimum spanning tree is a spanning tree of a connected, undirected graph.
 * It connects all the vertices together with the minimal total weighting for
 * its edges. A single graph can have many different spanning trees.
 * 
 * @author adina
 */
public class MinimumSpanningTree {

	/**
	 * 1. Sort all the edges in non-decreasing order of their weight.
	 * 
	 * 2. Pick the smallest edge. Check if it forms a cycle with the spanning
	 * tree
	 * formed so far. If cycle is not formed, include this edge. Else, discard
	 * it.
	 * 
	 * 3. Repeat step#2 until there are (V-1) edges in the spanning tree.
	 * 
	 * @param graph
	 * @return
	 */
	public static List<?> KurskalAlgorithm(InterfaceUnweightedGraph<?> graph) {
		List<?> mst = new LinkedList<>();

		// get the number of vertices in the graph
		int numVertices = graph.getNumVertices();

		return mst;
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
