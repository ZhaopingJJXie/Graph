import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BreadthFirstSearchTest {
	
	BreadthFirstSearch dgBfs;
	BreadthFirstSearch ugBfs;
	DirectedGraph dg;
	UndirectedGraph ug;
	
//	@SuppressWarnings("unused")
	@Before
	public void setUp() throws Exception {
		dg =  GraphBuilder.buildDirectedGraph("graph_builder_test.txt");
		ug = GraphBuilder.buildUndirectedGraph("graph_builder_test.txt");
		dgBfs = new BreadthFirstSearch(dg);
		ugBfs = new BreadthFirstSearch(ug);	
	}

//	@Test
//	public void testBfs() {
//		Node start = dg.getNode("0");
//		dgBfs.bfs(start, "6");
//		assertEquals(dgBfs.getEdgeCount(), 3);
//	}

}
