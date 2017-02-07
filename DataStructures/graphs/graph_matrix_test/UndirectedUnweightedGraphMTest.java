package graph_matrix_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import graph_matrix_src.UndirectedUnweightedGraphM;
import graph_util.Vertex;

public class UndirectedUnweightedGraphMTest {
	// number of vertices
	int numVert = 5;
	// maximum number of vertices before resize
	int capacity = 6;

	// vertices in graph
	Vertex<Character>[] vert;

	UndirectedUnweightedGraphM<Character> G;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		// vertices for the matrix graphs
		vert = new Vertex[numVert];
		// add a character label A-E
		for (int v = 0; v < numVert; v++)
			vert[v] = new Vertex<Character>((char) (v + 'A'));

		// undirected unweighted graph represented as an adjacency matrix
		G = new UndirectedUnweightedGraphM<Character>(capacity, vert);
		G.addEdge(0, 1);
		G.addEdge(1, 2);
		G.addEdge(0, 3);
		G.addEdge(3, 4);
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

		assertTrue(G.isEdge(3, 4));
		assertTrue(G.isEdge(4, 3));

		assertFalse(G.isEdge(1, 3));

		// add edge
		G.addEdge(0, 2);
		assertTrue(G.isEdge(0, 2));
		assertTrue(G.isEdge(2, 0));

		// remove edge
		G.removeEdge(2, 0);
		assertFalse(G.isEdge(0, 2));
		assertFalse(G.isEdge(2, 0));

		// out of bounds vertices for isEdge
		assertFalse(G.isEdge(-1, 3));
		assertFalse(G.isEdge(1, 8));
	}

	@Test
	public void testAddVertexAndVertexIndex() {
		assertEquals(0, G.getVertexIndex('A'));

		// before resize
		G.addVertex('X');
		numVert++;
		assertEquals(capacity, G.getCapacity());
		assertEquals(numVert, G.getNumVertices());

		assertEquals(numVert - 1, G.getVertexIndex('X'));

		// after resize
		G.addVertex('Y');
		numVert++;
		assertEquals(capacity * 3 / 2, G.getCapacity());
		assertEquals(numVert, G.getNumVertices());

		assertEquals(numVert - 1, G.getVertexIndex('Y'));
	}

	@Test
	public void testRemoveVertex() {
		// remove the vertex from beginning
		G.removeVertex(0);
		numVert--;
		assertEquals(numVert, G.getNumVertices());
		assertEquals(-1, G.getVertexIndex('A'));
		assertEquals(0, G.getVertexIndex('B'));
		assertEquals(numVert - 1, G.getVertexIndex('E'));

		// remove the vertex from end
		assertEquals('E', (char) G.getVertexLabel(numVert - 1));
		G.removeVertex(numVert - 1);
		numVert--;
		assertEquals(numVert, G.getNumVertices());
		assertEquals(-1, G.getVertexIndex('E'));
		assertEquals(numVert - 1, G.getVertexIndex('D'));

		// remove a vertex inside
		G.removeVertex(G.getVertexIndex('C'));
		numVert--;
		assertEquals(numVert, G.getNumVertices());
		assertEquals(-1, G.getVertexIndex('C'));
		assertEquals(0, G.getVertexIndex('B'));
		assertEquals(1, G.getVertexIndex('D'));
	}

	@Test
	public void testNeighborVertices() {
		assertEquals("[1, 3]", G.getNeighborVertices(0).toString());
		G.removeEdge(0, 1);
		assertFalse(G.isEdge(0, 1));
		assertEquals("[3]", G.getNeighborVertices(0).toString());
	}
}
