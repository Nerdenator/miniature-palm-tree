package graphs_src;

/**
 * Class to represent a vertex in a graph.
 * A vertex has a label of a generic type
 * For graph searches, a vertex also has a flag for visited or not.
 * 
 * @author adina
 *
 */

public class Vertex<MyType> {

	public enum Colors {
		WHITE, GRAY, BLACK
	}

	MyType label;
	boolean wasVisited;
	Colors color;

	public Vertex(MyType label) {
		// set the label
		this.label = label;
		// current vertex was not visited
		wasVisited = false;
		// current vertex is white
		color = Colors.WHITE;
	}

}
