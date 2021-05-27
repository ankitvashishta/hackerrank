package com.hackerrank.practice.graphs.travelling.salesman.problem;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import com.hackerrank.practice.graphs.mst.kruskal.Edge;
import com.hackerrank.practice.graphs.mst.prim.Vertex;

public class TravellingSalesmanProblem {

	/*
	 * Complete the tspGrid function below.
	 */
	static int tspGrid(int[][] horizontal, int[][] vertical) {
		/*
		 * Write your code here.
		 */
		Map<Integer, LinkedList<Edge>> edgesMap = new HashMap<>();

		populateAdjacencyMatrix(horizontal, vertical, edgesMap);

		int noOfRows = horizontal.length;
		int noOfColumns = vertical[0].length;
		int n = noOfRows * noOfColumns;
		Map<Integer, Vertex> verticesInGraph = new HashMap<Integer, Vertex>();
		for (int i = 0; i < n; i++) {
			verticesInGraph.put(i, new Vertex(i, Integer.MAX_VALUE, null));
		}
		Vertex startingVertex = verticesInGraph.get(0);
		startingVertex.setKey(0);

		Set<Vertex> visitedVertices = new HashSet<Vertex>();
		PriorityQueue<Vertex> vertexPriorityQueue = new PriorityQueue<Vertex>((x, y) -> {
			return x.getKey().compareTo(y.getKey());
		});

		vertexPriorityQueue.add(startingVertex);
		Vertex lastVertex = null;
		while (!vertexPriorityQueue.isEmpty()) {
			Vertex parentVertex = vertexPriorityQueue.peek();
			int i = parentVertex.getVertexNumber();
			Collections.sort(edgesMap.get(i));
			for (Edge edge : edgesMap.get(i)) {
				Vertex childVertex = verticesInGraph.get(edge.getEndVertex());
				if (!visitedVertices.contains(childVertex)) {
					if (edge.getWeight() < childVertex.getKey()) {
						childVertex.setKey(edge.getWeight());
						childVertex.setParent(parentVertex);
					}
					vertexPriorityQueue.add(childVertex);
					break;
				}
			}
			visitedVertices.add(parentVertex);
			lastVertex = parentVertex;
			vertexPriorityQueue.removeIf(v -> v.equals(parentVertex));
		}
		int lastWeight = edgesMap.get(lastVertex.getVertexNumber()).stream().filter(v -> v.getEndVertex().equals(0))
				.collect(Collectors.summingInt(v -> v.getWeight()));
		return lastWeight + verticesInGraph.values().stream().collect(Collectors.summingInt(v -> v.getKey()));
	}

	private static void populateAdjacencyMatrix(int[][] horizontal, int[][] vertical,
			Map<Integer, LinkedList<Edge>> edgesMap) {
		int noOfRows = horizontal.length;
		int noOfColumns = horizontal[0].length;
		int i = 0;
		for (int[] rows : horizontal) {
			for (int j = 0; j < rows.length; j++) {
				int n = i * noOfRows;
				int sourceVertex = n + j;
				int destinationVertex = n + j + 1;
				int weight = horizontal[i][j];
				addEdgeToMap(sourceVertex, destinationVertex, weight, edgesMap);
			}
			i++;
		}
		i = 0;
		for (int[] rows : vertical) {
			for (int j = 0; j < rows.length; j++) {
				int n = i * noOfColumns;
				int sourceVertex = n + j;
				int destinationVertex = n + noOfRows + j;
				int weight = vertical[i][j];
				addEdgeToMap(sourceVertex, destinationVertex, weight, edgesMap);
			}
			i++;
		}
	}

	private static void addEdgeToMap(int sourceVertex, int destinationVertex, int weight,
			Map<Integer, LinkedList<Edge>> edgesMap) {
		Edge edge = new Edge(sourceVertex, destinationVertex, weight);
		if (edgesMap.containsKey(sourceVertex)) {
			edgesMap.get(sourceVertex).add(edge);
		} else {
			LinkedList<Edge> list = new LinkedList<Edge>();
			list.add(edge);
			edgesMap.put(sourceVertex, list);
		}
		edge = new Edge(destinationVertex, sourceVertex, weight);
		if (edgesMap.containsKey(destinationVertex)) {
			edgesMap.get(destinationVertex).add(edge);
		} else {
			LinkedList<Edge> list = new LinkedList<Edge>();
			list.add(edge);
			edgesMap.put(destinationVertex, list);
		}
	}

	public static void main(String[] args) throws IOException {
		String fileName = "src/com/hackerrank/practice/graphs/travelling/salesman/problem/Test_Case1.txt";
		File file = new File(fileName);

		Scanner scanner = new Scanner(file);

		String[] mn = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

		int m = Integer.parseInt(mn[0]);

		int n = Integer.parseInt(mn[1]);

		int[][] horizontal = new int[m][n - 1];

		for (int horizontalRowItr = 0; horizontalRowItr < m; horizontalRowItr++) {
			String[] horizontalRowItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

			for (int horizontalColumnItr = 0; horizontalColumnItr < n - 1; horizontalColumnItr++) {
				int horizontalItem = Integer.parseInt(horizontalRowItems[horizontalColumnItr]);
				horizontal[horizontalRowItr][horizontalColumnItr] = horizontalItem;
			}
		}

		int[][] vertical = new int[m - 1][n];

		for (int verticalRowItr = 0; verticalRowItr < m - 1; verticalRowItr++) {
			String[] verticalRowItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

			for (int verticalColumnItr = 0; verticalColumnItr < n; verticalColumnItr++) {
				int verticalItem = Integer.parseInt(verticalRowItems[verticalColumnItr]);
				vertical[verticalRowItr][verticalColumnItr] = verticalItem;
			}
		}

		int result = tspGrid(horizontal, vertical);

		System.out.println(result);

		scanner.close();
	}
}
