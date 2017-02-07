package graph_matrix_src;

import graph_util.InterfaceWeightedGraph;
import graph_util.Vertex;

/**
 * Represent a directed weighted graph as an adjacency matrix and support some
 * basic operations
 * 
 * Array adjMat[x][y] = w, the weight of the edge between x & y, or null
 * 
 * Space: O(n^2)
 * 
 * @author adina
 */
public class DirectedWeightedGraphM<MyType> extends DirectedUnweightedGraphM<MyType>
		implements InterfaceWeightedGraph<MyType> {

	public DirectedWeightedGraphM(int capacity, Vertex<MyType>[] vertices) {
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
		// only add edge between two existing vertices
		if (x < 0 || y < 0 || x > numVertices || y > numVertices)
			return;
		// add the edge
		adjMat[x][y] = 0;
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
		if (x < 0 || y < 0 || x > numVertices || y > numVertices || w < 0)
			return;
		// add the edge
		adjMat[x][y] = w;
	}

	/**
	 * Get the weight of the edge between x and y in O(1)
	 * 
	 * @param x first vertex
	 * @param y second vertex
	 * @return the weight
	 */
	@Override
	public int getWeight(int x, int y) {
		// if there is no edge, return a very high weight
		if (!isEdge(x, y))
			return Integer.MAX_VALUE;
		return adjMat[x][y];
	}
}
