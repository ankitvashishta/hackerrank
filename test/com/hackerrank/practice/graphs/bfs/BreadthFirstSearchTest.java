package com.hackerrank.practice.graphs.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BreadthFirstSearchTest {

	private BreadthFirstSearch breadthFirstSearch;

	private static final String TEST_CASE1 = "test/com/hackerrank/practice/graphs/bfs/Test_Case1.txt";
	private static final String TEST_CASE1_SOLUTION = "test/com/hackerrank/practice/graphs/bfs/Test_Case1_Solution.txt";
	private static final String TEST_CASE2 = "test/com/hackerrank/practice/graphs/bfs/Test_Case2.txt";
	private static final String TEST_CASE2_SOLUTION = "test/com/hackerrank/practice/graphs/bfs/Test_Case2_Solution.txt";
	private static final String TEST_CASE3 = "test/com/hackerrank/practice/graphs/bfs/Test_Case3.txt";
	private static final String TEST_CASE3_SOLUTION = "test/com/hackerrank/practice/graphs/bfs/Test_Case3_Solution.txt";
	private static final String NUMBER_OF_NODES = "noOfNodes";
	private static final String NUMBER_OF_EDGES = "noOfEdges";
	private static final String STARTING_NODE = "startingNode";

	@BeforeEach
	public void setUp() {
		breadthFirstSearch = new BreadthFirstSearch();

	}

	@Test
	void testCase1() throws IOException {
		Map<String, Integer> graphDetails = new HashMap<>();
		int[][] edges = getGraphDetails(TEST_CASE1, graphDetails);
		int[] res = breadthFirstSearch.bfs(graphDetails.get(NUMBER_OF_NODES), graphDetails.get(NUMBER_OF_EDGES), edges,
				graphDetails.get(STARTING_NODE));
		StringBuffer strBuffer = new StringBuffer();
		for (int i : res) {
			strBuffer.append(i).append(" ");
		}
		assertEquals(getSolutionString(TEST_CASE1_SOLUTION), strBuffer.toString().strip());
	}

	@Test
	void testCase2() throws IOException {
		Map<String, Integer> graphDetails = new HashMap<>();
		int[][] edges = getGraphDetails(TEST_CASE2, graphDetails);
		int[] res = breadthFirstSearch.bfs(graphDetails.get(NUMBER_OF_NODES), graphDetails.get(NUMBER_OF_EDGES), edges,
				graphDetails.get(STARTING_NODE));
		StringBuffer strBuffer = new StringBuffer();
		for (int i : res) {
			strBuffer.append(i).append(" ");
		}
		assertEquals(getSolutionString(TEST_CASE2_SOLUTION), strBuffer.toString().strip());
	}

	@Test
	void testCase3() throws IOException {
		Map<String, Integer> graphDetails = new HashMap<>();
		int[][] edges = getGraphDetails(TEST_CASE3, graphDetails);
		int[] res = breadthFirstSearch.bfs(graphDetails.get(NUMBER_OF_NODES), graphDetails.get(NUMBER_OF_EDGES), edges,
				graphDetails.get(STARTING_NODE));
		StringBuffer strBuffer = new StringBuffer();
		for (int i : res) {
			strBuffer.append(i).append(" ");
		}
		assertEquals(getSolutionString(TEST_CASE3_SOLUTION), strBuffer.toString().strip());
	}

	private int[][] getGraphDetails(String fileName, Map<String, Integer> graphDetails) throws FileNotFoundException {

		File myObj = new File(fileName);
		Scanner scanner = new Scanner(myObj);
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		String[] nm = scanner.nextLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);

		int[][] edges = new int[m][2];

		for (int i = 0; i < m; i++) {
			String[] edgesRowItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int j = 0; j < 2; j++) {
				int edgesItem = Integer.parseInt(edgesRowItems[j]);
				edges[i][j] = edgesItem;
			}
		}

		int s = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		graphDetails.put(NUMBER_OF_NODES, n);
		graphDetails.put(NUMBER_OF_EDGES, m);
		graphDetails.put(STARTING_NODE, s);

		scanner.close();
		return edges;
	}

	private String getSolutionString(String fileName) throws IOException {
		return Files.readString(Paths.get(fileName));
	}

}
