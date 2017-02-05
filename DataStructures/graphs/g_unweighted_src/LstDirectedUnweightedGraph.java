package g_unweighted_src;

import java.util.ArrayList;
import java.util.List;

import graphs.InterfaceUnweightedGraph;
import graphs.Vertex;

/**
 * Represent a directed unweighted graph as an adjacency list and support some
 * basic
 * operations
 * 
 * An adjacency list is an array of lists (or sometimes a list of lists). Each
 * individual list shows what vertices a given vertex is adjacent to.
 * List adj[x] contains all vertices y s.t. (x,y) is an edge
 * 
 * Space: O(n+m), n vertices, m edges
 * 
 * @author adina
 *
 */
public class LstDirectedUnweightedGraph<MyType> implements InterfaceUnweightedGraph<MyType> {
	// a list of vertices with associated labels
	protected Vertex<MyType>[] vertices;
	// number of vertices
	protected int numVertices;

	// an array of lists of vertices
	// adjList[x] contains all vertices y s.t. (x,y) is an edge
	protected List<Integer>[] adjList;

	// maximum number of vertices before resize
	protected int capacity;

	@SuppressWarnings("unchecked")
	public LstDirectedUnweightedGraph(int capacity, Vertex<MyType>[] vertices) {
		this.capacity = capacity;
		this.numVertices = vertices.length;

		this.vertices = new Vertex[capacity];
		for (int i = 0; i < numVertices; i++)
			this.vertices[i] = vertices[i];

		adjList = new List[capacity];
		for (int i = 0; i < numVertices; i++)
			adjList[i] = new ArrayList<Integer>();
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public int getNumVertices() {
		return numVertices;
	}

	@Override
	public MyType getVertexLabel(int index) {
		// null if out of bounds
		if (index >= numVertices || index < 0)
			return null;
		//  find label
		return vertices[index].label;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void addVertex(MyType label) {
		if (numVertices == capacity) {
			// increase capacity
			int capacity2 = capacity * 3 / 2;
			List<Integer>[] adjList2 = new List[capacity2];
			Vertex<MyType>[] vertices2 = new Vertex[capacity2];

			// copy vertices array over
			for (int i = 0; i < numVertices; i++)
				vertices2[i] = vertices[i];

			// copy adjacency list over
			for (int i = 0; i < numVertices; i++)
				adjList2[i] = adjList[i];

			// save the new structures
			adjList = adjList2;
			vertices = vertices2;
			capacity = capacity2;
		}
		// add the new vertex at last position
		vertices[numVertices] = new Vertex<MyType>(label);
		// add a new list at last position
		adjList[numVertices] = new ArrayList<Integer>();
		// increase the number of vertices
		numVertices++;
	}

	/**
	 * Get the index of the vertex with label
	 * O(n)
	 * 
	 * @param label
	 */
	@Override
	public int getVertexIndex(MyType label) {
		for (int i = 0; i < numVertices; i++)
			if (vertices[i].label == label)
				return i;
		return -1;
	}

	/**
	 * Remove the vertex at index
	 * O(n^2) since it causes recreation of vertices array and the adjacency
	 * matrix
	 * 
	 * @param index
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void removeVertex(int index) {
		if (index >= numVertices || index < 0)
			return;

		Vertex<MyType>[] vertices2 = new Vertex[capacity];
		List<Integer>[] adjList2 = new List[capacity];

		// copy vertices array and adjacency list over
		int i2 = 0;
		for (int i = 0; i < numVertices; i++) {
			// skip over index
			if (i == index)
				continue;
			vertices2[i2] = vertices[i];
			adjList2[i2] = adjList[i];
			i2++;
		}

		numVertices--;
		vertices = vertices2;
		adjList = adjList2;
	}

	/**
	 * Add an edge between nodes x,y and y,x
	 * O(1)
	 * 
	 * @param x
	 * @param y
	 */
	@Override
	public void addEdge(int x, int y) {
		// only add edge between two existing vertices
		if (x > numVertices || y > numVertices)
			return;
		// add the edge
		adjList[x].add(y);
	}

	/**
	 * Remove the edge between the vertices x and y
	 * O(deg(x))
	 * 
	 * @param x
	 * @param y
	 */
	@Override
	public void removeEdge(int x, int y) {
		// only have edge between two existing vertices
		if (x > numVertices || y > numVertices)
			return;

		for (int i = 0; i < adjList[x].size(); i++)
			if (adjList[x].get(i) == y) {
				adjList[x].remove(i);
				break;
			}

	}

	/**
	 * Is (x,y) an edge
	 * O(deg(x))
	 * 
	 * @param x
	 * @param y
	 * @return true if edge, false otherwise
	 */
	@Override
	public boolean isEdge(int x, int y) {
		// no edge between out of bounds vertices
		if (x > numVertices || y > numVertices)
			return false;
		// enough to check one
		return adjList[x].contains(y);
	}

	/**
	 * Find all vertices adjacent to x: all y s.t. (x,y) or (y,x) is an edge
	 * O(n+m), m = max size of adj[i]
	 * 
	 * @param x
	 * @return a list of all vertices y that are adjacent to x
	 */
	@Override
	public List<Integer> getNeighborVertices(int x) {
		// out vertices
		List<Integer> adjVert = adjList[x];
		//in vertices (only if not already in)
		for (int i = 0; i < numVertices; i++) {
			if (!adjVert.contains(i) && adjList[i].contains(x))
				adjVert.add(i);
		}
		return adjVert;
	}

	/**
	 * Show the adjacency lists
	 */
	public void displayLists() {
		// print the matrix and the label row
		for (int i = 0; i < numVertices; i++) {
			System.out.print(i + ": ");
			for (int j = 0; j < adjList[i].size(); j++)
				System.out.print(adjList[i].get(j) + " ");
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Find all in-edges for x: all y s.t. (y,x) is an edge
	 * O(n+m), m = max size of adj[i]
	 * 
	 * @param x
	 * @return a list of all vertices y that are adjacent to x
	 */
	@Override
	public List<Integer> getInEdges(int x) {
		List<Integer> inEdges = new ArrayList<Integer>();
		for (int i = 0; i < numVertices; i++) {
			if (adjList[i].contains(x))
				inEdges.add(i);
		}
		return inEdges;
	}

	/**
	 * Find all out-edges for x: all y s.t. (x,y) is an edge
	 * O(1)
	 * 
	 * @param x
	 * @return a list of all vertices y that are adjacent to x
	 */
	@Override
	public List<Integer> getOutEdges(int x) {
		return adjList[x];
	}

	@Override
	public void setVisited(int idx, boolean visited) {
		if (idx < 0 || idx > numVertices)
			return;
		vertices[idx].wasVisited = visited;
	}

	@Override
	public boolean isVisited(int idx) {
		if (idx < 0 || idx > numVertices)
			return true;
		return vertices[idx].wasVisited;
	}
}
