package graph_matrix_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import graph_matrix_src.DirectedUnweightedGraphM;
import graph_util.Vertex;

public class DirectedWeightedGraphMTest {
	// number of vertices
	int numVert = 5;
	// maximum number of vertices before resize
	int capacity = 6;

	// vertices in graph
	Vertex<Character>[] vert;

	DirectedUnweightedGraphM<Character> G;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		// vertices for the matrix graphs
		vert = new Vertex[numVert];
		// add a character label A-E
		for (int v = 0; v < numVert; v++)
			vert[v] = new Vertex<Character>((char) (v + 'A'));

		// undirected unweighted graph represented as an adjacency matrix
		G = new DirectedUnweightedGraphM<Character>(capacity, vert);
		G.addEdge(0, 1);
		G.addEdge(1, 0);
		G.addEdge(1, 2);
		G.addEdge(0, 3);
		G.addEdge(3, 2);
	}

	@Test
	public void testSetup() {
		assertEquals(capacity, G.getCapacity());
		assertEquals(numVert, G.getNumVertices());

		// vertex labels
		assertEquals('A', (char) G.getVertexLabel(0));
		assertEquals('B', (char) G.getVertexLabel(1));
		assertEquals('C', (char) G.getVertexLabel(2));
		assertEquals('D', (char) G.getVertexLabel(3));
		assertEquals('E', (char) G.getVertexLabel(4));

		assertNull(G.getVertexLabel(-1));
		assertNull(G.getVertexLabel(5));

		// test set up correctly
		assertTrue(G.isEdge(0, 1));
		assertTrue(G.isEdge(1, 0));

		assertFalse(G.isEdge(0, 2));

		assertTrue(G.isEdge(3, 2));
		assertFalse(G.isEdge(2, 3));

		assertFalse(G.isEdge(1, 3));

		// add edge
		G.addEdge(0, 2);
		assertTrue(G.isEdge(0, 2));
		assertFalse(G.isEdge(2, 0));

		// remove edge
		G.removeEdge(1, 0);
		assertTrue(G.isEdge(0, 1));
		assertFalse(G.isEdge(1, 0));

		// out of bounds vertices for isEdge
		assertFalse(G.isEdge(-1, 3));
		assertFalse(G.isEdge(1, 8));

		// remove edge 0,1
		G.removeEdge(0, 1);
		assertFalse(G.isEdge(0, 1));
	}

	@Test
	public void testNeighborVertices() {
		assertEquals("[1, 3]", G.getNeighborVertices(0).toString());
		G.removeEdge(0, 1);
		assertFalse(G.isEdge(0, 1));
		G.removeEdge(1, 0);
		assertFalse(G.isEdge(1, 0));
		assertEquals("[3]", G.getNeighborVertices(0).toString());
	}

	@Test
	public void testInOutEdges() {
		assertEquals("[1]", G.getInEdges(0).toString());
		assertEquals("[1, 3]", G.getInEdges(2).toString());
		assertEquals("[0]", G.getInEdges(1).toString());
		assertEquals("[]", G.getInEdges(4).toString());

		assertEquals("[1, 3]", G.getOutEdges(0).toString());
		assertEquals("[]", G.getOutEdges(2).toString());
		assertEquals("[0, 2]", G.getOutEdges(1).toString());
		assertEquals("[]", G.getOutEdges(4).toString());
	}
}
