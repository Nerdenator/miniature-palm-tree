package graphs_algorithms_src;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import graph_util.InterfaceUnweightedGraph;

/**
 * Perform BFS and DFS on an Unweighted Graph
 * 
 * @author adina
 *
 */
public class GraphTraversals {
	/**
	 * Breadth-first search explores the neighbor nodes first, before moving to
	 * the next level neighbors.
	 * Use a queue (FIFO) to keep track of vertices on each level.
	 * 
	 * @param G the graph we're traversing
	 * @return a string with all the vertices (labels) visited
	 */
	public static String breadthFirstSearch(InterfaceUnweightedGraph<?> G) {
		// get the number of vertices in the graph
		int numVertices = G.getNumVertices();

		// if there are no vertices in the graph
		if (numVertices == 0)
			return "[]";

		// use a StringBuilder to create the string efficiently
		StringBuilder sb = new StringBuilder();

		// use a queue to hold the vertices for BFS
		Queue<Integer> queue = new LinkedList<>();

		// add root to queue, mark it as visited and append it to result
		queue.add(0);
		G.setVisited(0, true);
		sb.append(G.getVertexLabel(0) + " ");

		// while the queue is not empty
		while (!queue.isEmpty()) {
			// dequeue a vertex
			int curVert = queue.remove();

			// get the out-edges vertices from the current vertex
			List<Integer> outVert = G.getOutEdges(curVert);

			// for all unvisited out-edges vertices, add them to the queue,
			// visit and append them to result
			for (int i = 0; i < outVert.size(); i++) {
				// get current out-edges vertex
				curVert = outVert.get(i);

				// check that it wasn't previously visited
				if (!G.isVisited(curVert)) {
					// add to queue
					queue.add(curVert);
					// mark it as visited
					G.setVisited(curVert, true);
					// add label to result string
					sb.append(G.getVertexLabel(curVert) + " ");
				}
			}
		}

		// reset the visited flags for future traversals
		for (int i = 0; i < numVertices; i++)
			G.setVisited(i, false);

		return "[ " + sb + "]";
	}

	/**
	 * Call depthFirstSearchRecursive to traverse the graph
	 * 
	 * @param G the graph we're traversing
	 * @return a string with all the vertices (labels) visited
	 */
	public static String depthFirstSearchRecursive(InterfaceUnweightedGraph<?> G) {
		// get the number of vertices in the graph
		int numVertices = G.getNumVertices();

		// if there are no vertices in the graph
		if (numVertices == 0)
			return "[]";

		// use a StringBuilder to create the string efficiently
		StringBuilder sb = new StringBuilder();

		// set the root as visited and add the its label to the result
		G.setVisited(0, true);
		sb.append(G.getVertexLabel(0) + " ");

		// recursive call, sb gets updated (reference)
		depthFirstSearchRecursive(G, 0, sb);

		// reset the visited flags for future traversals
		for (int i = 0; i < numVertices; i++)
			G.setVisited(i, false);

		return "[ " + sb + "]";
	}

	/**
	 * Recursively traverse the graph depth-first. Use the call stack to keep
	 * visiting the first unvisited out-edge vertex and keep track of the rest
	 * 
	 * @param G the graph we're traversing
	 * @param curVert the current vertex we're looking at
	 * @param sb a string with all the vertices (labels) visited
	 */
	private static void depthFirstSearchRecursive(InterfaceUnweightedGraph<?> G, int curVert, StringBuilder sb) {
		// get the out-edges vertices from the current vertex
		List<Integer> outVert = G.getOutEdges(curVert);

		// for all unvisited out-edges vertices, visit, append them to result
		// and recursively call depthFirstSearchRecursive on them
		for (int i = 0; i < outVert.size(); i++) {
			// current out vertex
			curVert = outVert.get(i);
			// check that it wasn't previously visited
			if (!G.isVisited(curVert)) {
				// mark it as visited
				G.setVisited(curVert, true);
				// add label to result string
				sb.append(G.getVertexLabel(curVert) + " ");
				// recurse from current vertex
				depthFirstSearchRecursive(G, curVert, sb);
			}
		}
	}

	/**
	 * Recursively traverse the graph depth-first. Use a stack to keep
	 * visiting the first unvisited out-edge vertex and keep track of the rest
	 * 
	 * @param G the graph we're traversing
	 * @return a string with all the vertices (labels) visited
	 */
	public static String depthFirstSearchIterative(InterfaceUnweightedGraph<?> G) {
		// get the number of vertices in the graph
		int numVertices = G.getNumVertices();

		// if there are no vertices in the graph
		if (numVertices == 0)
			return "[]";

		// use a StringBuilder to create the string efficiently
		StringBuilder sb = new StringBuilder();

		// use a stack to hold the vertices for DFS
		Stack<Integer> stack = new Stack<>();

		// add root to stack, mark it as visited and append it to result
		stack.push(0);
		G.setVisited(0, true);
		sb.append(G.getVertexLabel(0) + " ");

		// while the stack isn't empty
		while (!stack.isEmpty()) {
			// get the current vertex at the top of the stack
			int curVert = stack.peek();

			// get the out-edges from the current vertex
			List<Integer> outVert = G.getOutEdges(curVert);

			// keep track on whether we're done with unvisited out-edges
			boolean allVisited = true;

			// for the first unvisited out-edges vertex, push it to the stack,
			// visit and append it to result
			for (int i = 0; i < outVert.size(); i++) {
				// get current out-edges vertex
				curVert = outVert.get(i);

				// check that it wasn't previously visited
				if (!G.isVisited(curVert)) {
					// found unvisited vertex
					allVisited = false;
					// add to stack
					stack.push(curVert);
					// mark it as visited
					G.setVisited(curVert, true);
					// add to result string
					sb.append(G.getVertexLabel(curVert) + " ");
					// done with this list
					break;
				}
			}

			// if there is no unvisited out-edges vertex, pop vertex from stack
			if (allVisited)
				stack.pop();
		}

		// reset the visited flags for future traversals
		for (int i = 0; i < numVertices; i++)
			G.setVisited(i, false);

		return "[ " + sb + "]";
	}
}
