package graph_matrix_src;

import java.util.ArrayList;
import java.util.List;

import graph_util.InterfaceUnweightedGraph;
import graph_util.Vertex;

/**
 * Represent an undirected unweighted graph as an adjacency matrix and support
 * some basic operations
 * 
 * Array adjMat[x][y] = 1 if there is an edge between x & y
 * 
 * Space: O(n^2)
 * 
 * @author adina
 */
public class UndirectedUnweightedGraphM<MyType> implements InterfaceUnweightedGraph<MyType> {

	// maximum number of vertices before resize
	protected int capacity;

	// the adjacency matrix
	protected Integer[][] adjMat;

	// a list of vertices with associated labels
	protected Vertex<MyType>[] vertices;

	// number of vertices
	protected int numVertices;

	/**
	 * Constructor given matrix capacity and a list of vertex labels
	 * 
	 * @param capacity
	 * @param vertices
	 */
	@SuppressWarnings("unchecked")
	public UndirectedUnweightedGraphM(int capacity, Vertex<MyType>[] vertices) {
		this.capacity = capacity;
		adjMat = new Integer[capacity][capacity];
		this.numVertices = vertices.length;

		// we want the vertices matrix to be of size capacity, so copy it over
		this.vertices = new Vertex[capacity];
		for (int i = 0; i < numVertices; i++) {
			this.vertices[i] = vertices[i];
		}
	}

	/**
	 * @return the current size of the adjacency matrix
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @return the number of vertices in the graph
	 */
	@Override
	public int getNumVertices() {
		return numVertices;
	}

	/**
	 * @return the label of the current index (or null if out of bounds)
	 */
	@Override
	public MyType getVertexLabel(int index) {
		// null if out of bounds
		if (index >= numVertices || index < 0)
			return null;
		// return label
		return vertices[index].label;
	}

	/**
	 * Add a vertex with a given label to the graph
	 * 
	 * @param label the label for the current vertex
	 */
	@Override
	public void addVertex(MyType label) {
		// if the adjacency matrix is too small for the number of vertices
		// increase its size and copy it over
		if (numVertices == capacity) {
			// increase capacity
			int newCapacity = capacity * 3 / 2;
			// create a new adjacency matrix
			Integer[][] newAdjMat = new Integer[newCapacity][newCapacity];
			// create a new array of vertex labels
			@SuppressWarnings("unchecked")
			Vertex<MyType>[] newVertices = new Vertex[newCapacity];

			// copy vertices array over
			for (int i = 0; i < numVertices; i++)
				newVertices[i] = vertices[i];

			// copy adjacency matrix over
			for (int i = 0; i < numVertices; i++)
				for (int j = 0; j < numVertices; j++)
					newAdjMat[i][j] = adjMat[i][j];

			// save the new structures
			adjMat = newAdjMat;
			vertices = newVertices;
			capacity = newCapacity;
		}
		// add the new vertex and increase the number of vertices
		vertices[numVertices++] = new Vertex<MyType>(label);
	}

	/**
	 * Get the index of a vertex (or -1) given its label in O(n)
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
	 * Remove the vertex at index in O(n^2)
	 * 
	 * @param index
	 */
	@Override
	public void removeVertex(int index) {
		if (index >= numVertices || index < 0)
			return;

		// remove the vertex at given index from the vertices array
		for (int i = 0; i < numVertices; i++) {
			if (i >= index)
				vertices[i] = vertices[i + 1];
		}

		// remove the vertex at given index from the adjacency matrix
		for (int i = 0; i < numVertices; i++) {
			for (int j = 0; j < numVertices; j++) {
				if (i >= index) {
					if (j >= index)
						adjMat[i][j] = adjMat[i + 1][j + 1];
					else
						adjMat[i][j] = adjMat[i + 1][j];
				} else if (j >= index)
					adjMat[i][j] = adjMat[i][j + 1];
			}
		}
		// adjust the number of vertices
		numVertices--;
	}

	/**
	 * Add an edge between nodes x,y and y,x in O(1)
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
		adjMat[x][y] = 1;
		adjMat[y][x] = 1;
	}

	/**
	 * Remove the edge between the vertices x and y in O(1)
	 * 
	 * @param x first vertex
	 * @param y second vertex
	 */
	@Override
	public void removeEdge(int x, int y) {
		// only remove edge between two existing vertices
		if (x < 0 || y < 0 || x > numVertices || y > numVertices)
			return;
		// remove the edge
		adjMat[x][y] = null;
		adjMat[y][x] = null;

	}

	/**
	 * Is there an edge between x & y and y & x in O(1)
	 * 
	 * @param x first vertex
	 * @param y second vertex
	 * @return true if edge, false otherwise
	 */
	@Override
	public boolean isEdge(int x, int y) {
		// no edge between out of bounds vertices
		if (x < 0 || y < 0 || x > numVertices || y > numVertices)
			return false;

		// enough to check one in unweighted graph
		if (adjMat[x][y] != null)
			return true;

		return false;
	}

	/**
	 * Find all vertices adjacent to x: all y s.t.
	 * (x,y) is an edge (and so is (y,x)) in O(n)
	 * 
	 * @param x the index of the vertex we're looking at
	 * @return a list of all vertices y that are adjacent to x
	 */
	@Override
	public List<Integer> getNeighborVertices(int x) {
		// return null for index out of bounds
		if (x < 0 || x > numVertices)
			return null;

		// add all vertices with an edge to x
		List<Integer> adjacentVert = new ArrayList<Integer>();
		for (int y = 0; y < numVertices; y++)
			if (isEdge(x, y))
				adjacentVert.add(y);
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

	@Override
	public List<Integer> getInEdges(int x) {
		return getNeighborVertices(x);
	}

	@Override
	public List<Integer> getOutEdges(int x) {
		return getNeighborVertices(x);
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
