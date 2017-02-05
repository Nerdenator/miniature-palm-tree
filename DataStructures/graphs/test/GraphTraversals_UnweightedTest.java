package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import g_unweighted_src.LstUndirectedUnweightedGraph;
import g_unweighted_src.MatUndirectedUnweightedGraph;
import graphs.Vertex;
import graphs_algorithms_src.GraphTraversals;

public class GraphTraversals_UnweightedTest {

	int numVert = 8;
	int capacity = 10;
	MatUndirectedUnweightedGraph<Integer> graphUndirectedAdjMat1;
	MatUndirectedUnweightedGraph<Integer> graphUndirectedAdjMat2;

	LstUndirectedUnweightedGraph<Integer> graphUndirectedAdjList1;
	LstUndirectedUnweightedGraph<Integer> graphUndirectedAdjList2;
	Vertex<Integer>[] vertices;

	String DFS1, DFS2, BFS1, BFS2;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		// integer graph
		vertices = new Vertex[numVert];
		for (int v = 0; v < numVert; v++)
			vertices[v] = new Vertex<Integer>(v);

		// GRAPH 1, represented using matrix
		graphUndirectedAdjMat1 = new MatUndirectedUnweightedGraph<Integer>(capacity, vertices);
		graphUndirectedAdjMat1.addEdge(0, 1);
		graphUndirectedAdjMat1.addEdge(0, 2);
		graphUndirectedAdjMat1.addEdge(0, 3);
		graphUndirectedAdjMat1.addEdge(1, 4);
		graphUndirectedAdjMat1.addEdge(2, 5);
		graphUndirectedAdjMat1.addEdge(3, 6);
		graphUndirectedAdjMat1.addEdge(4, 7);

		// GRAPH 1, represented using list
		graphUndirectedAdjList1 = new LstUndirectedUnweightedGraph<Integer>(capacity, vertices);
		graphUndirectedAdjList1.addEdge(0, 1);
		graphUndirectedAdjList1.addEdge(0, 2);
		graphUndirectedAdjList1.addEdge(0, 3);
		graphUndirectedAdjList1.addEdge(1, 4);
		graphUndirectedAdjList1.addEdge(2, 5);
		graphUndirectedAdjList1.addEdge(3, 6);
		graphUndirectedAdjList1.addEdge(4, 7);

		// GRAPH 1, results
		DFS1 = "[ 0 1 4 7 2 5 3 6 ]";
		BFS1 = "[ 0 1 2 3 4 5 6 7 ]";

		////////////////////////////////////////////

		// GRAPH 2, represented using matrix
		graphUndirectedAdjMat2 = new MatUndirectedUnweightedGraph<Integer>(capacity, vertices);
		graphUndirectedAdjMat2.addEdge(0, 1);
		graphUndirectedAdjMat2.addEdge(0, 2);
		graphUndirectedAdjMat2.addEdge(0, 4);
		graphUndirectedAdjMat2.addEdge(0, 6);
		graphUndirectedAdjMat2.addEdge(3, 4);
		graphUndirectedAdjMat2.addEdge(3, 5);
		graphUndirectedAdjMat2.addEdge(4, 5);
		graphUndirectedAdjMat2.addEdge(5, 6);
		graphUndirectedAdjMat2.addEdge(6, 7);

		// GRAPH 2, represented using list
		graphUndirectedAdjList2 = new LstUndirectedUnweightedGraph<Integer>(capacity, vertices);
		graphUndirectedAdjList2.addEdge(0, 1);
		graphUndirectedAdjList2.addEdge(0, 2);
		graphUndirectedAdjList2.addEdge(0, 4);
		graphUndirectedAdjList2.addEdge(0, 6);
		graphUndirectedAdjList2.addEdge(3, 4);
		graphUndirectedAdjList2.addEdge(3, 5);
		graphUndirectedAdjList2.addEdge(4, 5);
		graphUndirectedAdjList2.addEdge(5, 6);
		graphUndirectedAdjList2.addEdge(6, 7);

		// GRAPH 2, results
		DFS2 = "[ 0 1 2 4 3 5 6 7 ]";
		BFS2 = "[ 0 1 2 4 6 3 5 7 ]";
	}

	@Test
	public void testBFS() {
		assertEquals(BFS1, GraphTraversals.breadthFirstSearch(graphUndirectedAdjMat1));
		assertEquals(BFS1, GraphTraversals.breadthFirstSearch(graphUndirectedAdjList1));

		assertEquals(BFS2, GraphTraversals.breadthFirstSearch(graphUndirectedAdjMat2));
		assertEquals(BFS2, GraphTraversals.breadthFirstSearch(graphUndirectedAdjList2));
	}

	@Test
	public void testDFS() {
		assertEquals(DFS1, GraphTraversals.depthFirstSearch_Iterative(graphUndirectedAdjMat1));
		assertEquals(DFS1, GraphTraversals.depthFirstSearch_Iterative(graphUndirectedAdjList1));
		assertEquals(DFS2, GraphTraversals.depthFirstSearch_Iterative(graphUndirectedAdjMat2));
		assertEquals(DFS2, GraphTraversals.depthFirstSearch_Iterative(graphUndirectedAdjList2));
		assertEquals(DFS1, GraphTraversals.depthFirstSearch_Recursive(graphUndirectedAdjMat1));
		assertEquals(DFS1, GraphTraversals.depthFirstSearch_Recursive(graphUndirectedAdjList1));
		assertEquals(DFS2, GraphTraversals.depthFirstSearch_Recursive(graphUndirectedAdjMat2));
		assertEquals(DFS2, GraphTraversals.depthFirstSearch_Recursive(graphUndirectedAdjList2));
	}

}
