package graph_matrix_src;

import graph_util.InterfaceWeightedGraph;
import graph_util.Vertex;

/**
 * Represent an undirected weighted graph as an adjacency matrix and support
 * some basic operations
 * 
 * Space: O(n^2)
 * 
 * @author adina
 */
public class UndirectedWeightedGraphM<MyType> extends UndirectedUnweightedGraphM<MyType>
		implements InterfaceWeightedGraph<MyType> {

	public UndirectedWeightedGraphM(int capacity, Vertex<MyType>[] vertices) {
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
		// only add edge between two existing vertices, with positive weight
		if (x < 0 || y < 0 || x > numVertices || y > numVertices)
			return;
		// add the edge
		adjMat[x][y] = 0;
		adjMat[y][x] = 0;
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
		if (x < 0 || y < 0 || x > numVertices || y > numVertices || w < 0)
			return;
		// add the edge
		adjMat[x][y] = w;
		adjMat[y][x] = w;
	}

	/**
	 * Get the weight of the edge between x, y in O(1)
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
