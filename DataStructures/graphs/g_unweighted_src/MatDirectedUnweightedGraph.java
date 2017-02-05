package g_unweighted_src;

import java.util.ArrayList;
import java.util.List;

import graphs.InterfaceUnweightedGraph;
import graphs.Vertex;

/**
 * Represent an unweighted directed graph as an adjacency matrix and support
 * some basic operations
 * Space: O(n^2)
 * 
 * @author adina
 *
 */
public class MatDirectedUnweightedGraph<MyType> implements InterfaceUnweightedGraph<MyType> {
	// maximum number of vertices before resize
	protected int capacity;
	// the adjacency matrix
	protected Integer[][] adjMat;

	// a list of vertices with associated labels
	protected Vertex<MyType>[] vertices;
	// number of vertices
	protected int numVertices;

	@SuppressWarnings("unchecked")
	public MatDirectedUnweightedGraph(int capacity, Vertex<MyType>[] vertices) {
		this.capacity = capacity;
		adjMat = new Integer[capacity][capacity];
		this.numVertices = vertices.length;

		this.vertices = new Vertex[capacity];
		for (int i = 0; i < numVertices; i++) {
			this.vertices[i] = vertices[i];
		}
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
	public void addVertex(MyType label) {
		if (numVertices == capacity) {
			// increase capacity
			int capacity2 = capacity * 3 / 2;
			Integer[][] adjMat2 = new Integer[capacity2][capacity2];
			@SuppressWarnings("unchecked")
			Vertex<MyType>[] vertices2 = new Vertex[capacity2];

			// copy vertices array over
			for (int i = 0; i < numVertices; i++)
				vertices2[i] = vertices[i];
			// copy adjacency matrix over
			for (int i = 0; i < numVertices; i++)
				for (int j = 0; j < numVertices; j++)
					adjMat2[i][j] = adjMat[i][j];

			// save the new structures
			adjMat = adjMat2;
			vertices = vertices2;
			capacity = capacity2;
		}
		// add the new vertex and increase the number of vertices
		vertices[numVertices++] = new Vertex<MyType>(label);
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
	public void removeVertex(int index) {
		if (index >= numVertices || index < 0)
			return;

		@SuppressWarnings("unchecked")
		Vertex<MyType>[] vertices2 = new Vertex[capacity];

		// copy vertices array over
		int i2 = 0;
		for (int i = 0; i < numVertices; i++) {
			// skip over index
			if (i == index)
				continue;
			vertices2[i2++] = vertices[i];
		}

		// copy the adjacency matrix over
		Integer[][] adjMat2 = new Integer[capacity][capacity];
		i2 = 0;
		int j2 = 0;
		for (int i = 0; i < numVertices; i++) {
			if (i == index)
				continue;
			for (int j = 0; j < numVertices; j++) {
				if (j == index)
					continue;
				adjMat2[i2][j2++] = adjMat[i][j];
			}
			i2++;
			j2 = 0;
		}

		numVertices--;
		vertices = vertices2;
		adjMat = adjMat2;
	}

	/**
	 * Add an edge between nodes x,y
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
		adjMat[x][y] = 1;
	}

	/**
	 * Remove the edge between the vertices x and y
	 * O(1)
	 * 
	 * @param x
	 * @param y
	 */
	@Override
	public void removeEdge(int x, int y) {
		// only add edge between two existing vertices
		if (x > numVertices || y > numVertices)
			return;
		// remove the edge
		adjMat[x][y] = null;
	}

	@Override
	public boolean isEdge(int x, int y) {
		// no edge between out of bounds vertices
		if (x > numVertices || y > numVertices)
			return false;
		// enough to check one
		if (adjMat[x][y] != null)
			return true;
		return false;
	}

	/**
	 * Find all vertices adjacent to x: all y s.t. (x,y) or (y,x) is an edge
	 * O(n)
	 * 
	 * @param x
	 * @return a list of all vertices y that are adjacent to x
	 */
	@Override
	public List<Integer> getNeighborVertices(int x) {
		List<Integer> adjacentVert = new ArrayList<Integer>();
		for (int i = 0; i < numVertices; i++)
			if (adjMat[i][x] != null || adjMat[x][i] != null)
				adjacentVert.add(i);
		return adjacentVert;
	}

	/**
	 * Show the adjacency matrix
	 * (works best for numbers 0-9 or single character letters
	 */
	public void displayMatrix() {
		// pad label row to allow label column
		System.out.print("  | ");
		// top row is labels
		for (int i = 0; i < numVertices; i++)
			System.out.print(vertices[i].label + " ");
		for (int i = numVertices; i < capacity; i++)
			System.out.print("- ");
		System.out.println();
		// print a line to separate
		for (int i = 0; i < capacity; i++)
			System.out.print("==");
		System.out.println("===");

		// print the matrix and the label row
		for (int i = 0; i < capacity; i++) {
			for (int j = 0; j < capacity; j++) {
				// first element is the label + separator
				if (j == 0)
					if (i < numVertices)
						System.out.print(vertices[i].label + " | ");
					else
						System.out.print("- | ");
				if (adjMat[i][j] == null)
					System.out.print("- ");
				else
					System.out.print(adjMat[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Find all in-edges for x <=> all y s.t. (y,x) is an edge
	 * O(n)
	 * 
	 * @param x
	 * @return a list of all vertices y that are adjacent to x
	 */
	@Override
	public List<Integer> getInEdges(int x) {
		List<Integer> inEdges = new ArrayList<Integer>();
		for (int i = 0; i < numVertices; i++)
			if (adjMat[i][x] != null)
				inEdges.add(i);
		return inEdges;
	}

	/**
	 * Find all out-edges for x <=> all y s.t. (x,y) is an edge
	 * O(n)
	 * 
	 * @param x
	 * @return a list of all vertices y that are adjacent to x
	 */
	@Override
	public List<Integer> getOutEdges(int x) {
		List<Integer> outEdges = new ArrayList<Integer>();
		for (int i = 0; i < numVertices; i++)
			if (adjMat[x][i] != null)
				outEdges.add(i);
		return outEdges;
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
