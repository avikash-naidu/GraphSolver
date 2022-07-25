package nz.ac.auckland.softeng281.a4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class GraphTest {

	public static class GraphUnitTest {
		Graph graph;
		Graph testGraph;

		@Before
		public void setUp() throws Exception {
			List<String> edges = Arrays.asList("1,2", "2,3", "2,4", "4,2");
			List<String> weights = Arrays.asList("10", "20", "30", "20");
			graph = new Graph(edges, weights);

			List<String> testEdges = Arrays.asList("1,2", "2,3", "2,4", "4,2", "4,5");
			List<String> testWeights = Arrays.asList("10", "20", "30", "20", "30");
			testGraph = new Graph(testEdges, testWeights);
		}

		@Test
		public void testFindNode() {
			assertTrue(graph.isNodeInGraph(new Node("1")));
			assertTrue(graph.isNodeInGraph(new Node("2")));
			assertTrue(graph.isNodeInGraph(new Node("3")));
			assertTrue(graph.isNodeInGraph(new Node("4")));
			assertFalse(graph.isNodeInGraph(new Node("5")));
		}

		@Test
		public void testFindNode2() {
			List<String> testEdges = Arrays.asList("6,7", "7,8", "7,9", "9,7", "9,10");
			List<String> testWeights = Arrays.asList("10", "20", "30", "20", "30");
			testGraph = new Graph(testEdges, testWeights);
			assertTrue(testGraph.isNodeInGraph(new Node("6")));
			assertTrue(testGraph.isNodeInGraph(new Node("7")));
			assertTrue(testGraph.isNodeInGraph(new Node("8")));
			assertTrue(testGraph.isNodeInGraph(new Node("9")));
			assertTrue(testGraph.isNodeInGraph(new Node("10")));
			assertFalse(testGraph.isNodeInGraph(new Node("1")));
		}

		@Test
		public void testFindNode3() {
			List<String> testEdges = Arrays.asList("16,23", "20,11");
			List<String> testWeights = Arrays.asList("10", "20");
			testGraph = new Graph(testEdges, testWeights);
			assertTrue(testGraph.isNodeInGraph(new Node("16")));
			assertTrue(testGraph.isNodeInGraph(new Node("23")));
			assertTrue(testGraph.isNodeInGraph(new Node("20")));
			assertTrue(testGraph.isNodeInGraph(new Node("11")));
			// test a few boundary cases
			assertFalse(testGraph.isNodeInGraph(new Node("21")));
			assertFalse(testGraph.isNodeInGraph(new Node("10")));
			assertFalse(testGraph.isNodeInGraph(new Node("12")));
		}

		@Test
		public void testfindEdgeByWeight1() {
			Edge testEdge = testGraph.searchEdgeByWeight(10);
			Edge checkEdge = new Edge(new Node("1"), new Node("2"), 10);
			assertEquals(checkEdge, testEdge);

		}

		@Test
		public void testfindEdgeByWeight2() {
			Edge testEdge = testGraph.searchEdgeByWeight(25);
			Edge checkEdge = null;
			assertEquals(checkEdge, testEdge);

		}

		@Test
		public void testfindEdgeByWeight3() {
			// checks another node
			Edge testEdge = testGraph.searchEdgeByWeight(30);
			assertEquals(new Edge(new Node("2"), new Node("4"), 30), testEdge);

		}

		@Test
		public void testfindEdgeByWeight4() {
			// checks another node
			Edge testEdge = testGraph.searchEdgeByWeight(30);
			assertNotEquals(new Edge(new Node("4"), new Node("5"), 30), testEdge);
			// if 30 but 4,5 is not first found
		}

		@Test
		public void testfindWeight1() {
			Edge testEdge = testGraph.searchEdgeByWeight(20);
			// get edge we know has weight 20
			int checkWeight = testGraph.searchWeightByEdge(new Node("2"), new Node("3"));
			// check if output is right but getting the edge and comparing searched weight
			// with returned
			// weight
			assertEquals(checkWeight, testEdge.getWeight());

		}

		@Test
		public void testfindWeight2() {
			Edge testEdge = testGraph.searchEdgeByWeight(10);
			// get edge we know has weight 10
			int checkWeight = testGraph.searchWeightByEdge(new Node("1"), new Node("2"));
			// check if output is right but getting the edge and comparing searched weight
			// with returned
			// weight
			assertEquals(checkWeight, testEdge.getWeight());

		}

		@Test
		public void testfindWeight3() {
			Edge testEdge = testGraph.searchEdgeByWeight(30);
			// get edge we know has weight 30
			int checkWeight = testGraph.searchWeightByEdge(new Node("2"), new Node("4"));
			// check if output is right but getting the edge and comparing searched weight
			// with returned
			// weight
			assertEquals(checkWeight, testEdge.getWeight());

		}

		@Test
		// simple test
		public void testShortestPath() {
			List<String> edges = Arrays.asList("1,2", "2,3", "3,4", "4,5");
			List<String> weights = Arrays.asList("10", "20", "30", "20");
			graph = new Graph(edges, weights);
			Path path = new Path(80, new Node("1"), new Node("2"), new Node("3"), new Node("4"), new Node("5"));
			assertEquals(path, graph.computeShortestPath(new Node("1"), new Node("5")));
		}

		@Test
		public void testShortestPath2() {
			// testing 1 to 5
			// making an alternate route to 5
			List<String> edges = Arrays.asList("1,2", "2,3", "3,4", "4,5", "1,5");
			List<String> weights = Arrays.asList("1", "2", "3", "4", "20");// 1-5 has very high weight cant be 1-5
			graph = new Graph(edges, weights);
			Path path = new Path(10, new Node("1"), new Node("2"), new Node("3"), new Node("4"), new Node("5"));
			assertEquals(path, graph.computeShortestPath(new Node("1"), new Node("5")));
		}

		@Test
		public void testShortestPath3() {
			// testing 1 to 5
			// making an alternate route to 5
			// using example
			List<String> edges = Arrays.asList("1,2", "1,4", "2,4", "2,5", "3,1", "3,6", "4,3", "4,6", "4,7", "4,5",
					"5,7", "7,6");
			List<String> weights = Arrays.asList("2", "1", "3", "10", "4", "5", "2", "8", "4", "7", "6", "5");
			graph = new Graph(edges, weights);
			Path path = new Path(5, new Node("1"), new Node("4"), new Node("7"));
			assertEquals(path, graph.computeShortestPath(new Node("1"), new Node("7")));
		}

	}

	public static class GraphSystemTest {

		ByteArrayOutputStream myOut;
		PrintStream origOut;

		@Before
		public void setUp() {
			origOut = System.out;
			myOut = new ByteArrayOutputStream();
			System.setOut(new PrintStream(myOut));
		}

		@After
		public void tearDown() {
			System.setOut(origOut);
			if (myOut.toString().length() > 1) {
				System.out.println(System.lineSeparator() + "the System.out.print was :" + System.lineSeparator()
						+ myOut.toString());
			}
		}

		private void runTest(String fileName, String command) {
			GraphUI.scanner = new Scanner("open " + fileName + System.getProperty("line.separator") + command
					+ System.getProperty("line.separator") + "exit" + System.getProperty("line.separator"));
			GraphControl controller = new GraphControl();
			controller.execute();
		}

		@Test
		public void testSearchWeightA() {
			runTest("a.txt", "search 1 3");
			assertTrue(myOut.toString().contains("Given the edge from source 1 target 3 has weight: 5"));
		}

		@Test
		public void testSearchEdgeByWeightA() {
			runTest("a.txt", "search 5");
			assertTrue(myOut.toString().contains("The edge searched having weight 5 is: 1-->3"));
		}

		@Test
		public void testShortestPathA() {
			runTest("a.txt", "path 5 1");
			assertTrue(myOut.toString().contains("The shortest path is: 5 -> 4 -> 1 cost: 4"));
		}

	}

}