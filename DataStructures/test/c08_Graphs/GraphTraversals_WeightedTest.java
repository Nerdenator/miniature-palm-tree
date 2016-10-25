package c08_Graphs;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import c08_Graphs.GraphTraversals;
import c08_Graphs.Graph_Lst_DirUnw;
import c08_Graphs.Graph_Mat_DirUnw;
import c08_Graphs.Vertex;

public class GraphTraversals_WeightedTest {

	int numVert = 8;
	int capacity = 10;
	Graph_Mat_DirUnw<Integer> graphDirectedAdjMat1;
	Graph_Mat_DirUnw<Integer> graphDirectedAdjMat2;

	Graph_Lst_DirUnw<Integer> graphDirectedAdjList1;
	Graph_Lst_DirUnw<Integer> graphDirectedAdjList2;
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
		graphDirectedAdjMat1 = new Graph_Mat_DirUnw<Integer>(capacity, vertices);
		graphDirectedAdjMat1.addEdge(0, 1);
		graphDirectedAdjMat1.addEdge(0, 2);
		graphDirectedAdjMat1.addEdge(0, 3);
		graphDirectedAdjMat1.addEdge(1, 4);
		graphDirectedAdjMat1.addEdge(2, 5);
		graphDirectedAdjMat1.addEdge(3, 6);
		graphDirectedAdjMat1.addEdge(4, 7);

		// GRAPH 1, represented using list
		graphDirectedAdjList1 = new Graph_Lst_DirUnw<Integer>(capacity, vertices);
		graphDirectedAdjList1.addEdge(0, 1);
		graphDirectedAdjList1.addEdge(0, 2);
		graphDirectedAdjList1.addEdge(0, 3);
		graphDirectedAdjList1.addEdge(1, 4);
		graphDirectedAdjList1.addEdge(2, 5);
		graphDirectedAdjList1.addEdge(3, 6);
		graphDirectedAdjList1.addEdge(4, 7);

		// GRAPH 1, results
		DFS1 = "[ 0 1 4 7 2 5 3 6 ]";
		BFS1 = "[ 0 1 2 3 4 5 6 7 ]";

		////////////////////////////////////////////

		// GRAPH 2, represented using matrix
		graphDirectedAdjMat2 = new Graph_Mat_DirUnw<Integer>(capacity, vertices);
		graphDirectedAdjMat2.addEdge(0, 1);
		graphDirectedAdjMat2.addEdge(0, 2);
		graphDirectedAdjMat2.addEdge(0, 4);
		graphDirectedAdjMat2.addEdge(0, 6);
		graphDirectedAdjMat2.addEdge(3, 4);
		graphDirectedAdjMat2.addEdge(3, 5);
		graphDirectedAdjMat2.addEdge(4, 5);
		graphDirectedAdjMat2.addEdge(5, 6);
		graphDirectedAdjMat2.addEdge(6, 7);

		// GRAPH 2, represented using list
		graphDirectedAdjList2 = new Graph_Lst_DirUnw<Integer>(capacity, vertices);
		graphDirectedAdjList2.addEdge(0, 1);
		graphDirectedAdjList2.addEdge(0, 2);
		graphDirectedAdjList2.addEdge(0, 4);
		graphDirectedAdjList2.addEdge(0, 6);
		graphDirectedAdjList2.addEdge(3, 4);
		graphDirectedAdjList2.addEdge(3, 5);
		graphDirectedAdjList2.addEdge(4, 5);
		graphDirectedAdjList2.addEdge(5, 6);
		graphDirectedAdjList2.addEdge(6, 7);

		// GRAPH 2, results
		DFS2 = "[ 0 1 2 4 5 6 7 ]";
		BFS2 = "[ 0 1 2 4 6 5 7 ]";
	}

	@Test
	public void testBFS() {
		assertEquals(BFS1, GraphTraversals.breadthFirstSearch(graphDirectedAdjMat1));
		assertEquals(BFS1, GraphTraversals.breadthFirstSearch(graphDirectedAdjList1));

		assertEquals(BFS2, GraphTraversals.breadthFirstSearch(graphDirectedAdjMat2));
		assertEquals(BFS2, GraphTraversals.breadthFirstSearch(graphDirectedAdjList2));
	}

	@Test
	public void testDFS() {
		assertEquals(DFS1, GraphTraversals.depthFirstSearch_Iterative(graphDirectedAdjMat1));
		assertEquals(DFS1, GraphTraversals.depthFirstSearch_Iterative(graphDirectedAdjList1));
		assertEquals(DFS2, GraphTraversals.depthFirstSearch_Iterative(graphDirectedAdjMat2));
		assertEquals(DFS2, GraphTraversals.depthFirstSearch_Iterative(graphDirectedAdjList2));
		assertEquals(DFS1, GraphTraversals.depthFirstSearch_Recursive(graphDirectedAdjMat1));
		assertEquals(DFS1, GraphTraversals.depthFirstSearch_Recursive(graphDirectedAdjList1));
		assertEquals(DFS2, GraphTraversals.depthFirstSearch_Recursive(graphDirectedAdjMat2));
		assertEquals(DFS2, GraphTraversals.depthFirstSearch_Recursive(graphDirectedAdjList2));
	}

}
