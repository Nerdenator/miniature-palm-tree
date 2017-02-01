package graphs_src;

public interface InterfaceWeightedGraph<MyType> extends InterfaceUnweightedGraph<MyType> {

	// add an edge with weight w between nodes x,y and y,x
	public void addEdge(int x, int y, int w);

	// get the weight of edge x,y
	public int getWeight(int x, int y);
}
