import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class GraphUtilsTest {
	BreadthFirstSearch dgBfs;
	BreadthFirstSearch ugBfs;
	DirectedGraph dg;
	UndirectedGraph ug;
	Set<String> setString;
	@Before
	public void setUp() throws Exception {
		dg =  GraphBuilder.buildDirectedGraph("graph_builder_test.txt");
		ug = GraphBuilder.buildUndirectedGraph("graph_builder_test.txt");
		dgBfs = new BreadthFirstSearch(dg);
		ugBfs = new BreadthFirstSearch(ug);	
		setString= new HashSet<String>();
	}

	@Test
	public void testMinDistance() {
//		fail("Not yet implemented");
		 int edge = GraphUtils.minDistance(ug, "0", "0");
		 assertEquals(edge, 0);
	}

	@Test
	public void testNodesWithinDistance() {
		setString = GraphUtils.nodesWithinDistance(dg, "7",3);
		for (String string : setString) {
			System.out.print(string + ',' );
		}
		
//		fail("Not yet implemented");
	}

//	@Test
//	public void testIsHamiltonianPath() {
//		fail("Not yet implemented");
//	}

}
