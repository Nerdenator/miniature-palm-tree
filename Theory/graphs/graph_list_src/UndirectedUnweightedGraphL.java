package graph_list_src;

import java.util.ArrayList;
import java.util.List;

import graph_util.InterfaceUnweightedGraph;
import graph_util.Item;
import graph_util.Vertex;

/**
 * Represent an undirected unweighted graph as an adjacency list and support
 * some basic operations
 * 
 * List adjList[x] contains all vertices y s.t. (x,y) is an edge
 * 
 * Space: O(n+m), n vertices, m edges
 * 
 * @author adina
 */
public class UndirectedUnweightedGraphL<MyType> implements InterfaceUnweightedGraph<MyType> {

	// a list of vertices with associated labels
	protected Vertex<MyType>[] vertices;

	// number of vertices
	protected int numVertices;

	// an array of lists of vertices
	// adjList[x] contains all vertex indices s y s.t. (x,y) is an edge
	protected List<Item>[] adjList;

	// maximum number of vertices before resize
	protected int capacity;

	/**
	 * Constructor given matrix capacity and a list of vertex labels
	 * 
	 * @param capacity
	 * @param vertices
	 */
	@SuppressWarnings("unchecked")
	public UndirectedUnweightedGraphL(int capacity, Vertex<MyType>[] vertices) {
		this.capacity = capacity;
		this.numVertices = vertices.length;

		// copy the vertex list into an array of size capacity
		this.vertices = new Vertex[capacity];
		for (int i = 0; i < numVertices; i++)
			this.vertices[i] = vertices[i];

		// create the array of adjacency lists
		adjList = new List[capacity];
		for (int i = 0; i < numVertices; i++)
			adjList[i] = new ArrayList<Item>();
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
		// find label
		return vertices[index].label;
	}

	/**
	 * Add a vertex with a given label to the graph
	 * 
	 * @param label the label for the current vertex
	 */
	@Override
	public void addVertex(MyType label) {
		// if we've run out of space, increase the capacity
		if (numVertices == capacity) {
			// increase capacity
			int newCapacity = capacity * 3 / 2;

			@SuppressWarnings("unchecked")
			List<Item>[] newAdjList = new List[newCapacity];
			@SuppressWarnings("unchecked")
			Vertex<MyType>[] newVertices = new Vertex[newCapacity];

			// copy vertices array over
			for (int i = 0; i < numVertices; i++)
				newVertices[i] = vertices[i];

			// copy adjacency list over
			for (int i = 0; i < numVertices; i++)
				newAdjList[i] = adjList[i];

			// save the new structures
			adjList = newAdjList;
			vertices = newVertices;
			capacity = newCapacity;
		}

		// add the new vertex at last position
		vertices[numVertices] = new Vertex<MyType>(label);
		// add a new list at last position
		adjList[numVertices] = new ArrayList<Item>();
		// increase the number of vertices
		numVertices++;
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
	 * Remove the vertex at index in O(n)
	 * 
	 * @param index
	 */
	@Override
	public void removeVertex(int index) {
		if (index >= numVertices || index < 0)
			return;

		// remove the vertex and the adjacency list at index
		for (int i = 0; i < numVertices; i++) {
			// skip over index
			if (i >= index) {
				vertices[i] = vertices[i + 1];
				adjList[i] = adjList[i + 1];
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
		adjList[x].add(new Item(y));
		adjList[y].add(new Item(x));
	}

	/**
	 * Remove the edge between the vertices x and y in O(max(deg(x), deg(y)))
	 * 
	 * @param x first vertex
	 * @param y second vertex
	 */
	@Override
	public void removeEdge(int x, int y) {
		// only remove edge between two existing vertices
		if (x < 0 || y < 0 || x > numVertices || y > numVertices)
			return;

		// go through the adjacency list for x and remove y if it exists
		for (int i = 0; i < adjList[x].size(); i++) {
			if (adjList[x].get(i).vertex == y) {
				adjList[x].remove(i);
				break;
			}
		}
		// go through the adjacency list for y and remove x if it exists
		for (int i = 0; i < adjList[y].size(); i++) {
			if (adjList[y].get(i).vertex == x) {
				adjList[y].remove(i);
				break;
			}
		}
	}

	/**
	 * Is there an edge between x & y (and y & x) in O(deg(x))
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

		// enough to check if adjacency list for one vertex contains the other
		for (Item it : adjList[x])
			if (it.vertex == y)
				return true;
		return false;
	}

	/**
	 * Find all vertices adjacent to x: all y s.t.
	 * (x,y) is an edge (and so is (y,x)) in O(n)
	 * (O(1) if adjList held only vertices)
	 * 
	 * @param x the index of the vertex we're looking at
	 * @return a list of all vertices y that are adjacent to x
	 */
	@Override
	public List<Integer> getNeighborVertices(int x) {
		List<Integer> neighbors = new ArrayList<>();
		for (Item it : adjList[x])
			neighbors.add(it.vertex);
		return neighbors;
	}

	/**
	 * Show the adjacency lists
	 */
	public void displayLists() {
		// print the matrix and the label row
		for (int i = 0; i < numVertices; i++) {
			System.out.print(i + ": ");
			for (Item it : adjList[i])
				System.out.print("(" + it.vertex + "," + it.weight + ") ");
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
