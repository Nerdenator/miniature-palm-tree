package graphs_algorithms_test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import graph_representations_src.DirectedUnweightedGraphL;
import graph_representations_src.DirectedUnweightedGraphM;
import graph_util.InterfaceUnweightedGraph;
import graph_util.Vertex;
import graphs_algorithms_src.GraphTraversals;

public class GraphTraversalsDirectedTest {

	int numVert = 8;
	int capacity = 10;
	InterfaceUnweightedGraph<Integer> GM;
	InterfaceUnweightedGraph<Integer> GL;

	String DFS1 = "[ 0 1 4 7 2 5 3 6 ]";
	String BFS1 = "[ 0 1 2 3 4 5 6 7 ]";

	String DFS2 = "[ 0 1 2 4 5 6 7 ]";
	String BFS2 = "[ 0 1 2 4 6 5 7 ]";

	public void createGraph1(InterfaceUnweightedGraph<Integer> G) {
		G.addEdge(0, 1);
		G.addEdge(0, 2);
		G.addEdge(0, 3);
		G.addEdge(1, 4);
		G.addEdge(2, 5);
		G.addEdge(3, 6);
		G.addEdge(4, 7);
	}

	public void createGraph2(InterfaceUnweightedGraph<Integer> G) {
		G.addEdge(0, 1);
		G.addEdge(0, 2);
		G.addEdge(0, 4);
		G.addEdge(0, 6);
		G.addEdge(3, 4);
		G.addEdge(3, 5);
		G.addEdge(4, 5);
		G.addEdge(5, 6);
		G.addEdge(6, 7);
	}

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		Vertex<Integer>[] vertices;
		// integer graph
		vertices = new Vertex[numVert];
		for (int v = 0; v < numVert; v++)
			vertices[v] = new Vertex<Integer>(v);

		// Graph represented using adjacency matrix
		GM = new DirectedUnweightedGraphM<Integer>(capacity, vertices);

		// Graph represented using adjacency list
		GL = new DirectedUnweightedGraphL<Integer>(capacity, vertices);
	}

	@Test
	public void testBFS1() {
		createGraph1(GM);
		assertEquals(BFS1, GraphTraversals.breadthFirstSearch(GM));
		createGraph1(GL);
		assertEquals(BFS1, GraphTraversals.breadthFirstSearch(GL));
	}

	@Test
	public void testBFS2() {
		createGraph2(GM);
		assertEquals(BFS2, GraphTraversals.breadthFirstSearch(GM));
		createGraph2(GL);
		assertEquals(BFS2, GraphTraversals.breadthFirstSearch(GL));
	}

	@Test
	public void testDFSRecursive1() {
		createGraph1(GM);
		assertEquals(DFS1, GraphTraversals.depthFirstSearchRecursive(GM));
		createGraph1(GL);
		assertEquals(DFS1, GraphTraversals.depthFirstSearchRecursive(GL));
	}

	@Test
	public void testDFSRecursive2() {
		createGraph2(GM);
		assertEquals(DFS2, GraphTraversals.depthFirstSearchRecursive(GM));
		createGraph2(GL);
		assertEquals(DFS2, GraphTraversals.depthFirstSearchRecursive(GL));
	}

	@Test
	public void testDFSIterative1() {
		createGraph1(GM);
		assertEquals(DFS1, GraphTraversals.depthFirstSearchIterative(GM));
		createGraph1(GL);
		assertEquals(DFS1, GraphTraversals.depthFirstSearchIterative(GL));
	}

	@Test
	public void testDFSIterative2() {
		createGraph2(GM);
		assertEquals(DFS2, GraphTraversals.depthFirstSearchIterative(GM));
		createGraph2(GL);
		assertEquals(DFS2, GraphTraversals.depthFirstSearchIterative(GL));
	}
}
