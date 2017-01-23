package graphs_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import graphs_src.Graph_Lst_DirWei;
import graphs_src.Graph_Lst_UndWei;
import graphs_src.Graph_Mat_DirWei;
import graphs_src.Graph_Mat_UndWei;
import graphs_src.Vertex;

public class Graph_AllTest {

	int numVert = 5;
	int capacity = 6;

	// vertices in graph
	Vertex<Integer>[] vert;

	// undirected weighted graph represented as a matrix (undirected unweighted is identical except for the 2 extra methods)
	Graph_Mat_UndWei<Integer> graph_MUW;

	// directed weighted graph represented as a matrix (directed unweighted is identical except for the 2 extra methods)
	Graph_Mat_DirWei<Integer> graph_MDW;

	// undirected weighted graph represented as a list (undirected unweighted is identical except for the 2 extra methods)
	Graph_Lst_UndWei<Integer> graph_LUW;

	// directed weighted graph represented as a list (directed unweighted is identical except for the 2 extra methods)
	Graph_Lst_DirWei<Integer> graph_LDW;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		// vertices for the matrix graphs
		vert = new Vertex[numVert];
		for (int v = 0; v < numVert; v++)
			vert[v] = new Vertex<Integer>(v);

		// undirected weighted graph represented as a matrix
		graph_MUW = new Graph_Mat_UndWei<Integer>(capacity, vert);
		graph_MUW.addEdge(0, 1);
		graph_MUW.addEdge(1, 2);
		graph_MUW.addEdge(0, 3);
		graph_MUW.addEdge(3, 4);

		// undirected weighted graph represented as a list
		graph_LUW = new Graph_Lst_UndWei<Integer>(capacity, vert);
		graph_LUW.addEdge(0, 1);
		graph_LUW.addEdge(1, 2);
		graph_LUW.addEdge(0, 3);
		graph_LUW.addEdge(3, 4);

		//directed weighted graph represented as a matrix
		graph_MDW = new Graph_Mat_DirWei<Integer>(capacity, vert);
		graph_MDW.addEdge(0, 1);
		graph_MDW.addEdge(1, 2);
		graph_MDW.addEdge(0, 3);
		graph_MDW.addEdge(3, 4);

		//directed weighted graph represented as a list
		graph_LDW = new Graph_Lst_DirWei<Integer>(capacity, vert);
		graph_LDW.addEdge(0, 1);
		graph_LDW.addEdge(1, 2);
		graph_LDW.addEdge(0, 3);
		graph_LDW.addEdge(3, 4);
	}

	@Test
	public void testUndirectedGraphMat() {
		assertEquals(capacity, graph_MUW.getCapacity());
		assertEquals(numVert, graph_MUW.getNumVertices());

		assertEquals((Integer) 0, graph_MUW.getVertexLabel(0));

		assertTrue(graph_MUW.isEdge(0, 1));
		assertFalse(graph_MUW.isEdge(0, 2));
		assertEquals(1, graph_MUW.getWeight(0, 1));

		assertEquals("[1, 3]", graph_MUW.getNeighborVertices(0).toString());

		graph_MUW.removeEdge(0, 1);

		assertFalse(graph_MUW.isEdge(0, 1));

		assertEquals("[3]", graph_MUW.getNeighborVertices(0).toString());

		// add vertex
		graph_MUW.addVertex(9);
		numVert++;

		// new vertex position
		int idx = graph_MUW.getVertexIndex(9);
		assertEquals(numVert - 1, idx);

		// add edge from new vertex
		graph_MUW.addEdge(idx, 2);

		assertFalse(graph_MUW.isEdge(0, idx));
		assertTrue(graph_MUW.isEdge(idx, 2));
		assertTrue(graph_MUW.isEdge(2, idx));

		assertEquals(capacity, graph_MUW.getCapacity());
		assertEquals(numVert, graph_MUW.getNumVertices());

		int w = 9;
		graph_MUW.addEdge(1, 3, w);
		assertTrue(graph_MUW.isEdge(1, 3));
		assertEquals(w, graph_MUW.getWeight(1, 3));
		assertEquals(w, graph_MUW.getWeight(3, 1));

		graph_MUW.removeEdge(1, 3);
		assertFalse(graph_MUW.isEdge(1, 3));
		assertEquals(-1, graph_MUW.getWeight(1, 3));

		// add vertex
		graph_MUW.addVertex(9);
		numVert++;

		// new vertex position
		idx = graph_MUW.getVertexIndex(9);
		// add edge from new vertex
		graph_MUW.addEdge(idx, 2);

		// add vertex, will overflow and resize
		graph_MUW.addVertex(8);
		numVert++;
		capacity = capacity * 3 / 2;
		assertEquals(capacity, graph_MUW.getCapacity());
		assertEquals(numVert, graph_MUW.getNumVertices());

		// remove a vertex at beginning
		graph_MUW.removeVertex(0);
		numVert--;
		assertEquals(numVert, graph_MUW.getNumVertices());

		// remove a vertex at end
		assertEquals((Integer) 8, graph_MUW.getVertexLabel(numVert - 1));
		graph_MUW.removeVertex(numVert - 1);
		numVert--;
		assertEquals(numVert, graph_MUW.getNumVertices());

		// remove a vertex inside
		graph_MUW.removeVertex(graph_MUW.getVertexIndex(3));
		numVert--;
		assertEquals(numVert, graph_MUW.getNumVertices());
	}

	@Test
	public void testUndirectedGraphLst() {
		assertEquals(capacity, graph_LUW.getCapacity());
		assertEquals(numVert, graph_LUW.getNumVertices());

		assertEquals((Integer) 0, graph_LUW.getVertexLabel(0));

		assertTrue(graph_LUW.isEdge(0, 1));
		assertFalse(graph_LUW.isEdge(0, 2));
		assertEquals(1, graph_LUW.getWeight(0, 1));

		assertEquals("[1, 3]", graph_LUW.getNeighborVertices(0).toString());

		graph_LUW.removeEdge(0, 1);

		assertFalse(graph_LUW.isEdge(0, 1));

		assertEquals("[3]", graph_LUW.getNeighborVertices(0).toString());

		// add vertex
		graph_LUW.addVertex(8);
		numVert++;
		assertEquals(numVert, graph_LUW.getNumVertices());

		// new vertex position
		int idx = graph_LUW.getVertexIndex(8);
		assertEquals(numVert - 1, idx);

		// add edge from new vertex
		graph_LUW.addEdge(idx, 2);

		assertFalse(graph_LUW.isEdge(0, idx));
		assertTrue(graph_LUW.isEdge(idx, 2));
		assertTrue(graph_LUW.isEdge(2, idx));

		assertEquals(capacity, graph_LUW.getCapacity());
		assertEquals(numVert, graph_LUW.getNumVertices());

		int w = 9;
		graph_LUW.addEdge(1, 3, w);
		assertTrue(graph_LUW.isEdge(1, 3));
		assertEquals(w, graph_LUW.getWeight(1, 3));
		assertEquals(w, graph_LUW.getWeight(3, 1));

		graph_LUW.removeEdge(1, 3);
		assertFalse(graph_LUW.isEdge(1, 3));
		assertEquals(-1, graph_LUW.getWeight(1, 3));

		// add vertex
		graph_LUW.addVertex(9);
		numVert++;
		assertEquals(numVert, graph_LUW.getNumVertices());

		// new vertex position
		idx = graph_LUW.getVertexIndex(9);
		// add edge from new vertex
		graph_LUW.addEdge(idx, 2);

		// add vertex, will overflow and resize
		graph_LUW.addVertex(8);
		numVert++;
		capacity = capacity * 3 / 2;
		assertEquals(capacity, graph_LUW.getCapacity());
		assertEquals(numVert, graph_LUW.getNumVertices());

		// remove a vertex at beginning
		graph_LUW.removeVertex(0);
		numVert--;
		assertEquals(numVert, graph_LUW.getNumVertices());

		// remove a vertex at end
		assertEquals((Integer) 8, graph_LUW.getVertexLabel(numVert - 1));
		graph_LUW.removeVertex(numVert - 1);
		numVert--;
		assertEquals(numVert, graph_LUW.getNumVertices());

		// remove a vertex inside
		graph_LUW.removeVertex(graph_LUW.getVertexIndex(3));
		numVert--;
		assertEquals(numVert, graph_LUW.getNumVertices());
	}

	@Test
	public void testDirectedGraphMat() {
		assertEquals(capacity, graph_MDW.getCapacity());
		assertEquals(numVert, graph_MDW.getNumVertices());

		assertEquals((Integer) 0, graph_MDW.getVertexLabel(0));

		assertTrue(graph_MDW.isEdge(0, 1));
		assertFalse(graph_MDW.isEdge(0, 2));
		assertEquals(1, graph_MDW.getWeight(0, 1));

		assertEquals("[1, 3]", graph_MDW.getNeighborVertices(0).toString());

		graph_MDW.removeEdge(0, 1);

		assertFalse(graph_MDW.isEdge(0, 1));

		assertEquals("[3]", graph_MDW.getNeighborVertices(0).toString());

		// add vertex
		graph_MDW.addVertex(9);
		numVert++;

		// new vertex position
		int idx = graph_MDW.getVertexIndex(9);
		assertEquals(numVert - 1, idx);

		// add edge from new vertex
		graph_MDW.addEdge(idx, 2);

		assertFalse(graph_MDW.isEdge(0, idx));
		assertTrue(graph_MDW.isEdge(idx, 2));
		assertFalse(graph_MDW.isEdge(2, idx));

		assertEquals(capacity, graph_MDW.getCapacity());
		assertEquals(numVert, graph_MDW.getNumVertices());

		int w = 9;
		graph_MDW.addEdge(1, 3, w);
		assertTrue(graph_MDW.isEdge(1, 3));
		assertEquals(w, graph_MDW.getWeight(1, 3));
		assertEquals(-1, graph_MDW.getWeight(3, 1));

		graph_MDW.removeEdge(1, 3);
		assertFalse(graph_MDW.isEdge(1, 3));
		assertEquals(-1, graph_MDW.getWeight(1, 3));

		// add vertex
		graph_MDW.addVertex(9);
		numVert++;

		// new vertex position
		idx = graph_MDW.getVertexIndex(9);
		// add edge from new vertex
		graph_MDW.addEdge(idx, 2);

		// add vertex, will overflow and resize
		graph_MDW.addVertex(8);
		numVert++;
		capacity = capacity * 3 / 2;
		assertEquals(capacity, graph_MDW.getCapacity());
		assertEquals(numVert, graph_MDW.getNumVertices());

		// remove a vertex at beginning
		graph_MDW.removeVertex(0);
		numVert--;
		assertEquals(numVert, graph_MDW.getNumVertices());

		// remove a vertex at end
		assertEquals((Integer) 8, graph_MDW.getVertexLabel(numVert - 1));
		graph_MDW.removeVertex(numVert - 1);
		numVert--;
		assertEquals(numVert, graph_MDW.getNumVertices());

		// remove a vertex inside
		graph_MDW.removeVertex(graph_MDW.getVertexIndex(3));
		numVert--;
		assertEquals(numVert, graph_MDW.getNumVertices());
	}

	@Test
	public void testDirectedGraphLst() {
		assertEquals(capacity, graph_LDW.getCapacity());
		assertEquals(numVert, graph_LDW.getNumVertices());

		assertEquals((Integer) 0, graph_LDW.getVertexLabel(0));

		assertTrue(graph_LDW.isEdge(0, 1));
		assertFalse(graph_LDW.isEdge(0, 2));
		assertEquals(1, graph_LDW.getWeight(0, 1));

		assertEquals("[1, 3]", graph_LDW.getNeighborVertices(0).toString());

		graph_LDW.removeEdge(0, 1);

		assertFalse(graph_LDW.isEdge(0, 1));

		assertEquals("[3]", graph_LDW.getNeighborVertices(0).toString());

		// add vertex
		graph_LDW.addVertex(9);
		numVert++;

		// new vertex position
		int idx = graph_LDW.getVertexIndex(9);
		assertEquals(numVert - 1, idx);

		// add edge from new vertex
		graph_LDW.addEdge(idx, 2);

		assertFalse(graph_LDW.isEdge(0, idx));
		assertTrue(graph_LDW.isEdge(idx, 2));
		assertFalse(graph_LDW.isEdge(2, idx));

		assertEquals(capacity, graph_LDW.getCapacity());
		assertEquals(numVert, graph_LDW.getNumVertices());

		int w = 9;
		graph_LDW.addEdge(1, 3, w);
		assertTrue(graph_LDW.isEdge(1, 3));
		assertEquals(w, graph_LDW.getWeight(1, 3));
		assertEquals(-1, graph_LDW.getWeight(3, 1));

		graph_LDW.removeEdge(1, 3);
		assertFalse(graph_LDW.isEdge(1, 3));
		assertEquals(-1, graph_LDW.getWeight(1, 3));

		// add vertex
		graph_LDW.addVertex(9);
		numVert++;

		// new vertex position
		idx = graph_LDW.getVertexIndex(9);
		// add edge from new vertex
		graph_LDW.addEdge(idx, 2);

		// add vertex, will overflow and resize
		graph_LDW.addVertex(8);
		numVert++;
		capacity = capacity * 3 / 2;
		assertEquals(capacity, graph_LDW.getCapacity());
		assertEquals(numVert, graph_LDW.getNumVertices());

		// remove a vertex at beginning
		graph_LDW.removeVertex(0);
		numVert--;
		assertEquals(numVert, graph_LDW.getNumVertices());

		// remove a vertex at end
		assertEquals((Integer) 8, graph_LDW.getVertexLabel(numVert - 1));
		graph_LDW.removeVertex(numVert - 1);
		numVert--;
		assertEquals(numVert, graph_LDW.getNumVertices());

		// remove a vertex inside
		graph_LDW.removeVertex(graph_LDW.getVertexIndex(3));
		numVert--;
		assertEquals(numVert, graph_LDW.getNumVertices());
	}
}
