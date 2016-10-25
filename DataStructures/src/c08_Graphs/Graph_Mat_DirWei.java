package c08_Graphs;

/**
 * Represent a directed unweighted graph as an adjacency matrix and support some
 * basic operations
 * Space: O(n^2)
 * 
 * @author adina
 *
 */
public class Graph_Mat_DirWei<MyType> extends Graph_Mat_DirUnw<MyType> implements InterfaceWeightedGraph<MyType> {

	public Graph_Mat_DirWei(int capacity, Vertex<MyType>[] vertices) {
		super(capacity, vertices);
	}

	/**
	 * Add an edge with weight w between nodes x,y
	 * O(1)
	 * 
	 * @param x
	 * @param y
	 */
	@Override
	public void addEdge(int x, int y, int w) {
		// only add edge between two existing vertices, with positive weight
		if (x > numVertices || y > numVertices || w < 0)
			return;
		// add the edge
		adjMat[x][y] = w;
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
