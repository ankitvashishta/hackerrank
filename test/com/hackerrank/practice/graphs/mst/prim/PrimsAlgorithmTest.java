package com.hackerrank.practice.graphs.mst.prim;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrimsAlgorithmTest {

	private PrimsAlgorithm primsAlgorithm;

	private static final String TEST_CASE1 = "test/com/hackerrank/practice/graphs/mst/prim/Test_Case1.txt";
	private static final String TEST_CASE2 = "test/com/hackerrank/practice/graphs/mst/prim/Test_Case2.txt";
	private static final String TEST_CASE3 = "test/com/hackerrank/practice/graphs/mst/prim/Test_Case3.txt";
	private static final String NUMBER_OF_VERTICES = "numberOfVertices";
	private static final String START_OF_GRAPH = "startOfGraph";

	@BeforeEach
	public void setUp() {
		primsAlgorithm = new PrimsAlgorithm();
	}

	@Test
	void testCase1() throws FileNotFoundException {
		Map<String, Integer> graphDetails = new HashMap<String, Integer>();
		int[][] edges = getGraphDetails(TEST_CASE1, graphDetails);
		int result = primsAlgorithm.prims(graphDetails.get(NUMBER_OF_VERTICES), edges,
				graphDetails.get(START_OF_GRAPH));
		assertEquals(15, result);
	}
	
	@Test
	void testCase2() throws FileNotFoundException {
		Map<String, Integer> graphDetails = new HashMap<String, Integer>();
		int[][] edges = getGraphDetails(TEST_CASE2, graphDetails);
		int result = primsAlgorithm.prims(graphDetails.get(NUMBER_OF_VERTICES), edges,
				graphDetails.get(START_OF_GRAPH));
		assertEquals(49950000, result);
	}
	
	@Test
	void testCase3() throws FileNotFoundException {
		Map<String, Integer> graphDetails = new HashMap<String, Integer>();
		int[][] edges = getGraphDetails(TEST_CASE3, graphDetails);
		int result = primsAlgorithm.prims(graphDetails.get(NUMBER_OF_VERTICES), edges,
				graphDetails.get(START_OF_GRAPH));
		assertEquals(6359060, result);
	}

	private static int[][] getGraphDetails(String fileName, Map<String, Integer> graphDetails)
			throws FileNotFoundException {

		File file = new File(fileName);

		Scanner scanner = new Scanner(file);
		String[] nm = scanner.nextLine().split(" ");

		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);
		int[][] edges = new int[m][3];

		for (int i = 0; i < m; i++) {
			String[] edgesRowItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int j = 0; j < 3; j++) {
				int edgesItem = Integer.parseInt(edgesRowItems[j]);
				edges[i][j] = edgesItem;
			}
		}

		int start = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		graphDetails.put(NUMBER_OF_VERTICES, n);
		graphDetails.put(START_OF_GRAPH, start);

		scanner.close();

		return edges;
	}

}
