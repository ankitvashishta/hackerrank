package com.hackerrank.practice.graphs.mst.kruskal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KruskalAlgorithmTest {

	private KruskalAlgorithm kruskalAlgorithm;

	private static final String TEST_CASE1 = "test/com/hackerrank/practice/graphs/mst/kruskal/Test_Case1.txt";
	private static final String TEST_CASE2 = "test/com/hackerrank/practice/graphs/mst/kruskal/Test_Case2.txt";
	private static final String TEST_CASE3 = "test/com/hackerrank/practice/graphs/mst/kruskal/Test_Case3.txt";
	

	@BeforeEach
	public void setUp() {
		kruskalAlgorithm = new KruskalAlgorithm();
	}

	@Test
	public void testCase1() throws IOException {
		Map<String, List<Integer>> graphDetails = new HashMap<String, List<Integer>>();
		int gNodes = getGraphDetails(graphDetails, TEST_CASE1);
		int res = kruskalAlgorithm.kruskals(gNodes, graphDetails.get("gFrom"), graphDetails.get("gTo"),
				graphDetails.get("gWeight"));
		assertEquals(12, res);
	}
	
	@Test
	public void testCase2() throws IOException {
		Map<String, List<Integer>> graphDetails = new HashMap<String, List<Integer>>();
		int gNodes = getGraphDetails(graphDetails, TEST_CASE2);
		int res = kruskalAlgorithm.kruskals(gNodes, graphDetails.get("gFrom"), graphDetails.get("gTo"),
				graphDetails.get("gWeight"));
		assertEquals(200, res);
	}

	@Test
	public void testCase3() throws IOException {
		Map<String, List<Integer>> graphDetails = new HashMap<String, List<Integer>>();
		int gNodes = getGraphDetails(graphDetails, TEST_CASE3);
		int res = kruskalAlgorithm.kruskals(gNodes, graphDetails.get("gFrom"), graphDetails.get("gTo"),
				graphDetails.get("gWeight"));
		assertEquals(150, res);
	}
	
	
	private static int getGraphDetails(Map<String, List<Integer>> graphDetails, String file) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String[] gNodesEdges = bufferedReader.readLine().split(" ");

		int gNodes = Integer.parseInt(gNodesEdges[0]);
		int gEdges = Integer.parseInt(gNodesEdges[1]);

		List<Integer> gFrom = new ArrayList<>();
		List<Integer> gTo = new ArrayList<>();
		List<Integer> gWeight = new ArrayList<>();

		IntStream.range(0, gEdges).forEach(i -> {
			try {
				String[] gFromToWeight = bufferedReader.readLine().split(" ");
				gFrom.add(Integer.parseInt(gFromToWeight[0]));
				gTo.add(Integer.parseInt(gFromToWeight[1]));
				gWeight.add(Integer.parseInt(gFromToWeight[2]));
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});
		graphDetails.put("gFrom", gFrom);
		graphDetails.put("gTo", gTo);
		graphDetails.put("gWeight", gWeight);
		bufferedReader.close();

		return gNodes;
	}

}
