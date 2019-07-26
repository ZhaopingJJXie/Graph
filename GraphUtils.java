
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * SD2x Homework #6
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class GraphUtils {

	public static int minDistance(Graph graph, String src, String dest) {

		/**
		 * Given a Graph, this method returns the shortest distance (in terms of number
		 * of edges) from the node labeled src to the node labeled dest. The method
		 * should return -1 for any invalid inputs, including: null values for the
		 * Graph, src, or dest; there is no node labeled src or dest in the graph; there
		 * is no path from src to dest.
		 * 
		 * Keep in mind that this method does not just return the distance of any path
		 * from src to dest, it must be the shortest path.
		 */

		if (graph == null || src == null || dest == null)
			return -1;
		// if input is null, return -1
		else {
			if (!graph.containsElement(src) || !graph.containsElement(dest)) {
				// if graph does not contain elements, return -1
				return -1;
			} else {
				BreadthFirstSearch myBfs = new BreadthFirstSearch(graph);
				Node start = graph.getNode(src);
				if (src.equals(dest) && !graph.getNodeNeighbors(start).contains(graph.getNode(dest))) {
					// if source equals to the dest and there is no path to itself, return 0;
					return 0;
				}
				if (myBfs.bfs(start, dest)) {
//					if there is a path
					int edgeCount = 1;
					Set<Node> marked = new HashSet<Node>();
					Node srcNode = graph.getNode(src);
					Node destNode = graph.getNode(dest);
					marked.add(srcNode);
//					add source node to the marked set
					Set<Node> neighbours = graph.getNodeNeighbors(srcNode);
					while (!neighbours.contains(destNode)) {
						// if the neighbours set doesn't contain the dest node
//						add the neighbours to the marked set 
						for (Node neighbour : neighbours) {
							marked.add(neighbour);
						}
//						update the next level neighbours as neighbours
						Set<Node> nei = new HashSet<Node>();
						for (Node neighbour : neighbours) {
							nei.addAll(graph.getNodeNeighbors(neighbour));
							nei.removeAll(marked);
						}
						neighbours.addAll(nei);
						neighbours.removeAll(marked);
						edgeCount++;

					}
					return edgeCount;
				} else
					return -1;
			}
		}

	}

	public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {

		/**
		 * Given a Graph, this method returns a Set of the values of all nodes within
		 * the specified distance (in terms of number of edges) of the node labeled src,
		 * i.e. for which the minimum number of edges from src to that node is less than
		 * or equal to the specified distance. The value of the node itself should not
		 * be in the Set, even if there is an edge from the node to itself. The method
		 * should return null for any invalid inputs, including: null values for the
		 * Graph or src; there is no node labeled src in the graph; distance less than
		 * 1. However, if distance is greater than or equal to 1 and there are no nodes
		 * within that distance (meaning: src is the only node in the graph), the method
		 * should return an empty Set.
		 */
		if (graph == null || src == null || !graph.containsElement(src) || distance < 1) {
			// for any invalid input, the method returns null
			return null;
		} else {
			Set<String> setString = new HashSet<String>();
			if (distance == 1 && graph.numNodes == 1) {
				// if the source is the only node, return an empty set
				return setString;
			} else {
				int dis = 1;
				Node srcNode = graph.getNode(src);
				Set<Node> neighbours = graph.getNodeNeighbors(srcNode);
				neighbours.add(srcNode);
				Set<Node> neighboursWithin = graph.getNodeNeighbors(srcNode);
				while (dis < distance) {
					Set<Node> nei = new HashSet<Node>();
					for (Node neighbour : neighbours) {
						nei.addAll(graph.getNodeNeighbors(neighbour));
					}
					neighbours.removeAll(neighbours);
					neighbours.addAll(nei);
					neighbours.remove(srcNode);
					nei.remove(srcNode);
					neighboursWithin.addAll(nei);
					dis++;
				}
				neighboursWithin.remove(srcNode);
				for (Node node : neighboursWithin) {
					setString.add(node.getElement());
				}
				return setString;
			}
		}

	}

	public static boolean isHamiltonianPath(Graph g, List<String> values) {

		/**
		 * Given a Graph, this method indicates whether the List of node values
		 * represents a Hamiltonian Path. A Hamiltonian Path is a valid path through the
		 * graph in which every node in the graph is visited exactly once, except for
		 * the start and end nodes, which are the same, so that it is a cycle. If the
		 * values in the input List represent a Hamiltonian Path, the method should
		 * return true,but the method should return false otherwise, e.g. if the path is
		 * not a cycle, if some nodes are not visited, if some nodes are visited more
		 * than once, if some values do not have corresponding nodes in the graph, if
		 * the input is not a valid path (i.e., there is a sequence of nodes in the List
		 * that are not connected by an edge), etc. The method should also return false
		 * if the input Graph or List is null.
		 */
		if (g == null || values == null) {
			return false;
		}
		if (values.size() == 0) {
			return false;
		}
		String head = values.get(0);
		String tail = values.get(values.size() - 1);

		if (!head.equals(tail)) {
			// if the path is not a cycle, return false
			return false;
		}

		Set<Node> allNodes = g.getAllNodes();
		// check if all nodes in the graph are in the list
		for (Node node : allNodes) {
			if (!values.contains(node.element)) {
				return false;
			}
		}
		Set<Node> visited = new HashSet<Node>();
		// check the path is hamiltonian or not
		for (int i = 0; i < values.size() - 1; i++) {
			//check every element in the list
			if (g.containsElement(values.get(i)) && g.containsElement(values.get(i + 1))) {
				Node start = g.getNode(values.get(i));
				Node next = g.getNode(values.get(i + 1));
				if (i != values.size() - 2) {
					//if the node already existed in the visited set, return false;
					if (visited.contains(start) || visited.contains(next)) {
						return false;
					}
				} else {
					if (visited.contains(start)) {
						return false;
					}
				}
				// check if there is a edge between the two nodes
				if (!g.getNodeNeighbors(start).contains(next)) {
					return false;
				} else {
					// add the nodes to the visited set
					visited.add(start);
//					visited.add(next);
				}
			}
			else {
				return false;
			}

			// this line is here only so this code will compile if you don't modify it
		}
		return true;
	}
}
