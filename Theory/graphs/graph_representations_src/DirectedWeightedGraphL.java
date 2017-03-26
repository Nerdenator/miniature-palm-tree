package graph_representations_src;

import graph_util.InterfaceWeightedGraph;
import graph_util.Item;
import graph_util.Vertex;

/**
 * Represent a directed weighted graph as an adjacency list and support
 * some basic operations
 * 
 * List adjList[x] contains all vertices y s.t. (x,y) is an edge
 * 
 * Space: O(n+m), n vertices, m edges
 * 
 * @author adina
 *
 */
// TODO: weights better
public class DirectedWeightedGraphL<MyType> extends DirectedUnweightedGraphL<MyType>
		implements InterfaceWeightedGraph<MyType> {

	public DirectedWeightedGraphL(int capacity, Vertex<MyType>[] vertices) {
		super(capacity, vertices);
	}

	/**
	 * Add an edge between nodes x,y (but not also y,x) in O(1)
	 * If no weight is given, the weight is 0
	 * 
	 * @param x first vertex
	 * @param y second vertex
	 */
	@Override
	public void addEdge(int x, int y) {
		super.addEdge(x, y);
	}

	/**
	 * Add an edge with weight w between nodes x,y (but not also y,x) in O(1)
	 * 
	 * @param x first vertex
	 * @param y second vertex
	 */
	@Override
	public void addEdge(int x, int y, int w) {
		// only add edge between two existing vertices, with positive weight
		if (x > numVertices || y > numVertices || w < 0)
			return;
		// add the edge
		adjList[x].add(new Item(y, w));
	}

	/**
	 * Get the weight of the edge between x and y in O(n)
	 * 
	 * @param x first vertex
	 * @param y second vertex
	 * @return the weight
	 */
	@Override
	public int getWeight(int x, int y) {
		// find the weight of y in the vertices of adjList[x]
		for (Item it : adjList[x])
			if (it.vertex == y)
				return it.weight;
		// if there is no edge, return a very high weight
		return Integer.MAX_VALUE;
	}
}
