package c08_Graphs;

/**
 * Represent an undirected weighted graph as an adjacency matrix and support
 * some basic operations
 * When the graph G is dense, i.e., it has close to n^2 edges, a memory usage of
 * n^2 may be acceptable.
 * 
 * Fun property: A*A counts the number of nodes k s.t. G contains both (i,k) and
 * (k,j) (so how many nodes there are s.t. length of path from i to j is exactly
 * 2)
 * 
 * Space: O(n^2)
 * 
 * @author adina
 *
 */
public class Graph_Mat_UndWei<MyType> extends Graph_Mat_UndUnw<MyType> implements InterfaceWeightedGraph<MyType> {

	public Graph_Mat_UndWei(int capacity, Vertex<MyType>[] vertices) {
		super(capacity, vertices);
	}

	/**
	 * Add an edge with weight w between nodes x,y and y,x
	 * O(1)
	 * 
	 * @param x
	 * @param y
	 * @param w
	 */
	@Override
	public void addEdge(int x, int y, int w) {
		// only add edge between two existing vertices, with positive weight
		if (x > numVertices || y > numVertices || w < 0)
			return;
		// add the edge
		adjMat[x][y] = w;
		adjMat[y][x] = w;
	}

	/**
	 * Get the weight of the edge between x, y
	 * 
	 * @param x
	 * @param y
	 * @return the weight
	 */
	@Override
	public int getWeight(int x, int y) {
		if (adjMat[x][y] == null)
			return -1;
		return adjMat[x][y];
	}

}
