package graph_representations_src;

import java.util.ArrayList;
import java.util.List;

import graph_util.InterfaceUnweightedGraph;
import graph_util.Vertex;

/**
 * Represent a directed unweighted graph as an adjacency matrix and support
 * some basic operations
 * 
 * Array adjMat[x][y] = 1 if there is an edge between x & y
 * 
 * Space: O(n^2)
 * 
 * @author adina
 */
public class DirectedUnweightedGraphM<MyType> extends UndirectedUnweightedGraphM<MyType>
		implements InterfaceUnweightedGraph<MyType> {

	public DirectedUnweightedGraphM(int capacity, Vertex<MyType>[] vertices) {
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

		// add the edge only between x & y
		adjMat[x][y] = 1;
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
		// remove the edge
		adjMat[x][y] = null;
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
			if (isEdge(x, y) || isEdge(y, x))
				adjacentVert.add(y);
		return adjacentVert;
	}

	/**
	 * Find all in-edges for x <=> all y s.t. (y,x) is an edge in O(n)
	 * 
	 * @param x the index of the vertex we're looking at
	 * @return a list of all vertices y s.t. (y,x) is an edge
	 */
	@Override
	public List<Integer> getInEdges(int x) {
		// return null for index out of bounds
		if (x < 0 || x > numVertices)
			return null;

		// add all vertices y s.t. (y,x) is an edge
		List<Integer> inEdges = new ArrayList<Integer>();
		for (int y = 0; y < numVertices; y++)
			if (isEdge(y, x))
				inEdges.add(y);
		return inEdges;
	}

	/**
	 * Find all out-edges for x <=> all y s.t. (x,y) is an edge in O(n)
	 * 
	 * @param x the index of the vertex we're looking at
	 * @return a list of all vertices y s.t. (x,y) is an edge
	 */
	@Override
	public List<Integer> getOutEdges(int x) {
		// return null for index out of bounds
		if (x < 0 || x > numVertices)
			return null;

		// add all vertices y s.t. (x,y) is an edge
		List<Integer> outEdges = new ArrayList<Integer>();
		for (int y = 0; y < numVertices; y++)
			if (isEdge(x, y))
				outEdges.add(y);
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
