package c08_Graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class GraphTraversals {
	/**
	 * 
	 * @param graph
	 * @return
	 */
	public static String breadthFirstSearch(InterfaceUnweightedGraph graph) {
		int numVertices = graph.getNumVertices();
		// if there are no vertices in the graph
		if (numVertices == 0)
			return "[]";

		StringBuilder sb = new StringBuilder();

		// use a queue to hold the vertices for BFS
		Queue<Integer> queue = new LinkedList<>();

		// Initial step: add vertex to queue, mark it as visited and append it to result
		queue.add(0);
		graph.setVisited(0, true);
		sb.append(graph.getVertexLabel(0) + " ");

		// while the queue is not empty
		while (!queue.isEmpty()) {
			// dequeue a vertex
			int vert = queue.remove();

			// get all out edges from vert
			List<Integer> outVert = graph.getOutEdges(vert);

			// for all unvisited neighbors, add them to the queue, visit and append them to result
			for (int i = 0; i < outVert.size(); i++) {
				// current vertex
				vert = outVert.get(i);
				// check that it wasn't previously visited
				if (!graph.isVisited(vert)) {
					// add to queue
					queue.add(vert);
					// mark it as visited
					graph.setVisited(vert, true);
					// add to result string
					sb.append(graph.getVertexLabel(vert) + " ");
				}
			}
		}

		// reset the flags for future searches
		for (int i = 0; i < numVertices; i++)
			graph.setVisited(i, false);

		return "[ " + sb + "]";
	}

	public static String depthFirstSearch_Recursive(InterfaceUnweightedGraph graph) {
		int numVertices = graph.getNumVertices();
		// if there are no vertices in the graph
		if (numVertices == 0)
			return "[]";

		StringBuilder sb = new StringBuilder();

		// Initial step: add vertex to queue, mark it as visited and append it to result
		graph.setVisited(0, true);
		sb.append(graph.getVertexLabel(0) + " ");

		// recursive call, sb gets updated
		depthFirstSearch_Recursive(graph, 0, sb);

		// reset the flags for future searches
		for (int i = 0; i < numVertices; i++)
			graph.setVisited(i, false);

		return "[ " + sb + "]";
	}

	private static void depthFirstSearch_Recursive(InterfaceUnweightedGraph graph, int vert, StringBuilder sb) {
		// get the adjacent vertices of vert
		List<Integer> outVert = graph.getOutEdges(vert);
		for (int i = 0; i < outVert.size(); i++) {
			// current vertex
			vert = outVert.get(i);
			if (!graph.isVisited(vert)) {
				graph.setVisited(vert, true);
				sb.append(graph.getVertexLabel(vert) + " ");
				depthFirstSearch_Recursive(graph, vert, sb);
			}
		}
	}

	public static String depthFirstSearch_Iterative(InterfaceUnweightedGraph graph) {
		int numVertices = graph.getNumVertices();
		// if there are no vertices in the graph
		if (numVertices == 0)
			return "[]";

		StringBuilder sb = new StringBuilder();

		// use a stack to hold the vertices for DFS
		Stack<Integer> stack = new Stack<>();

		// Initial step: add vertex to queue, mark it as visited and append it to result
		stack.push(0);
		graph.setVisited(0, true);
		sb.append(graph.getVertexLabel(0) + " ");

		while (!stack.isEmpty()) {
			int vert = stack.peek();
			// get the adjacent vertices of vert
			List<Integer> outVert = graph.getOutEdges(vert);
			// keep track on whether we're done with unvisited out vertices
			boolean allVisited = true;

			// find the first unvisited vertex in list
			for (int i = 0; i < outVert.size(); i++) {
				// current vertex
				vert = outVert.get(i);
				// check that it wasn't previously visited
				if (!graph.isVisited(vert)) {
					// found unvisited vertex
					allVisited = false;
					// add to stack
					stack.push(vert);
					// mark it as visited
					graph.setVisited(vert, true);
					// add to result string
					sb.append(graph.getVertexLabel(vert) + " ");
					// done with this list
					break;
				}
			}
			// if no adjacent nodes unvisited, go to next node on stack
			if (allVisited)
				stack.pop();
		}

		// reset the flags for future searches
		for (int i = 0; i < numVertices; i++)
			graph.setVisited(i, false);

		return "[ " + sb + "]";
	}

	//	public void minSpanTree() {
	//		vertices[0].wasVisited = true;//visited first node
	//		Stack<Integer> stack = new Stack<>();
	//		stack.push(0);
	//
	//		while (!stack.isEmpty()) {
	//			// get an unvisited vertex adjacent to stack top
	//			int v0 = stack.peek();
	//			int v = getAdjUnvisited(v0);
	//			if (v == -1) {//if no more unvisited
	//				stack.pop();
	//			} else {//v is not visited
	//				vertices[v].wasVisited = true;//visited
	//				stack.push(v);
	//				printVert(v0);
	//				printVert(v);
	//				System.out.print(" ");
	//			}
	//		}
	//		//reset flags
	//	}

}
