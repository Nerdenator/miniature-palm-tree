package graph_util;

import java.util.Objects;

/**
 * For use with adjacency lists; an adjacency list item contains a vertex and a
 * weight (which is just 0 if the graph is unweighted)
 * 
 * @author adina
 */
public class Item {
	public Integer vertex;
	public Integer weight;

	public Item(Integer vertex) {
		this.vertex = vertex;
		this.weight = 0;
	}

	public Item(Integer vertex, Integer weight) {
		this.vertex = vertex;
		this.weight = weight;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		Item it = (Item) o;
		if (it.vertex == this.vertex && it.weight == this.weight)
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.vertex, this.weight);
	}
}
