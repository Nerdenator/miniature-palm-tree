package graph_list_src;

import java.util.ArrayList;
import java.util.List;

import graph_util.InterfaceUnweightedGraph;
import graph_util.Item;
import graph_util.Vertex;

/**
 * Represent a directed unweighted graph as an adjacency list and support
 * some basic operations
 * 
 * List adjList[x] contains all vertices y s.t. (x,y) is an edge
 * 
 * Space: O(n+m), n vertices, m edges
 * 
 * @author adina
 *
 */
public class DirectedUnweightedGraphL<MyType> extends UndirectedUnweightedGraphL<MyType>
		implements InterfaceUnweightedGraph<MyType> {

	public DirectedUnweightedGraphL(int capacity, Vertex<MyType>[] vertices) {
		super(capacity, vertices);
	}

	/**
	 * Add an edge between nodes x,y (but not also y,x) in O(1)
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
	}

	/**
	 * Remove the edge between the vertices x and y (but not also y,x) in O(1)
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
	}

	/**
	 * Find all vertices adjacent to x: all y s.t.
	 * (x,y) is an edge (and so is (y,x))
	 * in O(n*m), m = max size of adj[i]
	 * 
	 * @param x the index of the vertex we're looking at
	 * @return a list of all vertices y that are adjacent to x
	 */
	@Override
	public List<Integer> getNeighborVertices(int x) {
		List<Integer> adjVert = new ArrayList<>();
		// out vertices, copy all in adjList[x]
		for (Item it : adjList[x])
			adjVert.add(it.vertex);

		// in vertices
		for (int i = 0; i < numVertices; i++) {
			// add only if not already in the list
			if (!adjVert.contains(i)) {
				for (Item it : adjList[i])
					if (it.vertex == x)
						adjVert.add(i);
			}
		}
		return adjVert;
	}

	/**
	 * Find all in-edges for x <=> all y s.t. (y,x) is an edge
	 * O(n*m), m = max size of adj[i]
	 * 
	 * @param x the index of the vertex we're looking at
	 * @return a list of all vertices y s.t. (y,x) is an edge
	 * 
	 */
	@Override
	public List<Integer> getInEdges(int x) {
		List<Integer> inEdges = new ArrayList<Integer>();

		for (int i = 0; i < numVertices; i++) {
			for (Item it : adjList[i])
				if (it.vertex == x)
					inEdges.add(i);
		}
		return inEdges;
	}

	/**
	 * Find all out-edges for x <=> all y s.t. (x,y) is an edge in O(m)
	 * 
	 * @param x the index of the vertex we're looking at
	 * @return a list of all vertices y s.t. (x,y) is an edge
	 */
	@Override
	public List<Integer> getOutEdges(int x) {
		List<Integer> outEdges = new ArrayList<Integer>();
		for (Item it : adjList[x])
			outEdges.add(it.vertex);
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
