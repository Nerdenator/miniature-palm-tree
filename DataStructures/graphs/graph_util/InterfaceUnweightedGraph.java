package graph_util;

import java.util.List;

public interface InterfaceUnweightedGraph<MyType> {
	// get the number of vertices
	public int getNumVertices();

	// get the label of the vertex at index
	public MyType getVertexLabel(int index);

	// add a vertex with label
	public void addVertex(MyType label);

	// remove the vertex with index
	public void removeVertex(int index);

	// get the index of the vertex with label
	public int getVertexIndex(MyType label);

	// add an edge between nodes x,y and y,x
	public void addEdge(int x, int y);

	// remove the edge between x and y
	public void removeEdge(int x, int y);

	// is there an edge between x and y
	public boolean isEdge(int x, int y);

	// find all the vertices adjacent to x: all y s.t. (x,y) & (y,x) is an edge
	public List<Integer> getNeighborVertices(int x);

	// find all the edges incident on x: all y s.t. (y,x) is an edge
	public List<Integer> getInEdges(int x);

	// find all edges coming out of x: all y s.t. (x,y) is an edge
	public List<Integer> getOutEdges(int x);

	// set whether the node was visited or not (for traversals)
	public void setVisited(int idx, boolean visited);

	// was the node visited (for traversals)
	public boolean isVisited(int idx);
}
