package graphs_src;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent a directed weighted graph as an adjacency list and support some
 * basic operations
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
//TODO: weights better
public class Graph_Lst_DirWei<MyType> extends Graph_Lst_DirUnw<MyType> implements InterfaceWeightedGraph<MyType> {

	private List<Integer>[] adjListWeight;

	@SuppressWarnings("unchecked")
	public Graph_Lst_DirWei(int capacity, Vertex<MyType>[] vertices) {
		super(capacity, vertices);

		// create the list of weights
		adjListWeight = new List[capacity];
		for (int i = 0; i < numVertices; i++)
			adjListWeight[i] = new ArrayList<Integer>();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void addVertex(MyType label) {
		if (numVertices == capacity) {
			// increase capacity
			int capacity2 = capacity * 3 / 2;
			List<Integer>[] adjList2 = new List[capacity2];
			List<Integer>[] adjListWeight2 = new List[capacity2];
			Vertex<MyType>[] vertices2 = new Vertex[capacity2];

			// copy vertices array over
			for (int i = 0; i < numVertices; i++)
				vertices2[i] = vertices[i];

			// copy adjacency list over
			for (int i = 0; i < numVertices; i++)
				adjList2[i] = adjList[i];

			// copy adjacency weight list over
			for (int i = 0; i < numVertices; i++)
				adjListWeight2[i] = adjListWeight[i];

			// save the new structures
			adjList = adjList2;
			vertices = vertices2;
			capacity = capacity2;
			adjListWeight = adjListWeight2;
		}
		// add the new vertex at last position
		vertices[numVertices] = new Vertex<MyType>(label);
		// add a new list at last position
		adjList[numVertices] = new ArrayList<Integer>();
		// add a new weight list at last position
		adjListWeight[numVertices] = new ArrayList<Integer>();
		// increase the number of vertices
		numVertices++;
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
		List<Integer>[] adjListWeight2 = new List[capacity];

		// copy vertices array and adjacency list over
		int i2 = 0;
		for (int i = 0; i < numVertices; i++) {
			// skip over index
			if (i == index)
				continue;
			vertices2[i2] = vertices[i];
			adjList2[i2] = adjList[i];
			adjListWeight2[i2] = adjListWeight[i];
			i2++;
		}

		numVertices--;
		vertices = vertices2;
		adjList = adjList2;
		adjListWeight = adjListWeight2;
	}

	/**
	 * Add an edge with weight 1 between nodes x,y and y,x if nothing was
	 * specified
	 * O(1)
	 * 
	 * @param x
	 * @param y
	 * @param w
	 */
	@Override
	public void addEdge(int x, int y) {
		addEdge(x, y, 1);
	}

	/**
	 * Add an edge with weight w between nodes x,y
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
		adjList[x].add(y);
		// add the edge
		adjListWeight[x].add(w);
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
		int index = adjList[x].indexOf(y);
		if (index == -1)
			return -1;
		return adjListWeight[x].get(index);
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
		// only add edge between two existing vertices
		if (x > numVertices || y > numVertices)
			return;

		for (int i = 0; i < adjList[x].size(); i++)
			if (adjList[x].get(i) == y) {
				adjList[x].remove(i);
				adjListWeight[x].remove(i);
				break;
			}
	}

	/**
	 * Show the adjacency lists
	 */
	@Override
	public void displayLists() {
		// print the matrix and the label row
		for (int i = 0; i < numVertices; i++) {
			System.out.print(i + ": ");
			for (int j = 0; j < adjList[i].size(); j++)
				System.out.print(adjList[i].get(j) + "(" + adjListWeight[i].get(j) + ")" + " ");
			System.out.println();
		}
		System.out.println();
	}

}
