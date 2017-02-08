package graph_list_src;

import graph_util.InterfaceWeightedGraph;
import graph_util.Item;
import graph_util.Vertex;

/**
 * Represent an unweighted undirected graph as an adjacency list and support
 * some basic operations
 * 
 * List adjList[x] contains all vertices y s.t. (x,y) is an edge
 * 
 * Space: O(n+m), n vertices, m edges
 * 
 * @author adina
 */
public class UndirectedWeightedGraphL<MyType> extends UndirectedUnweightedGraphL<MyType>
		implements InterfaceWeightedGraph<MyType> {

	public UndirectedWeightedGraphL(int capacity, Vertex<MyType>[] vertices) {
		super(capacity, vertices);
	}

	/**
	 * Add an edge between nodes x,y and y,x in O(1)
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
	 * Add an edge with weight w between nodes x,y and y,x in O(1)
	 * 
	 * @param x first vertex
	 * @param y second vertex
	 * @param w the weight of the edge
	 */
	@Override
	public void addEdge(int x, int y, int w) {
		// only add edge between two existing vertices, with positive weight
		if (x < 0 || y < 0 || x > numVertices || y > numVertices)
			return;
		// add the edge
		adjList[x].add(new Item(y, w));
		adjList[y].add(new Item(x, w));
	}

	/**
	 * Get the weight of the edge between x, y in O(n)
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
