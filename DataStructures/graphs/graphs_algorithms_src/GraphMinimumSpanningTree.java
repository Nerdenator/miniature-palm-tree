package graphs_algorithms_src;

import graphs.InterfaceUnweightedGraph;

/**
 * A minimum spanning tree is a spanning tree of a connected, undirected graph.
 * it connects all the vertices together with the minimal total weighting for
 * its
 * edges. A single graph can have many different spanning trees.
 * 
 * @author adina
 *
 */
public class GraphMinimumSpanningTree {

	public static String Kurskal(InterfaceUnweightedGraph graph) {
		int numVertices = graph.getNumVertices();
		// if there are no vertices in the graph
		if (numVertices == 0)
			return "[]";

		StringBuilder sb = new StringBuilder();
		return "[ " + sb + "]";
	}
}
