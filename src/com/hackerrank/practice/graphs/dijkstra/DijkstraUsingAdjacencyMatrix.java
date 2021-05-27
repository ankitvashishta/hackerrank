package com.hackerrank.practice.graphs.dijkstra;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

import com.hackerrank.practice.graphs.mst.prim.Vertex;

public class DijkstraUsingAdjacencyMatrix {

	// Complete the shortestReach function below.
	static int[] shortestReach(int n, int[][] edges, int s) {
		int[][] adjacencyMatrix = populateAdjacencyMatrix(n, edges);
		Map<Integer, Vertex> verticesInGraph = initializeSingleSource(n, s);

		Set<Vertex> visitedVertices = new HashSet<Vertex>();
		PriorityQueue<Vertex> vertexPriorityQueue = new PriorityQueue<Vertex>((x, y) -> {
			return x.getKey().compareTo(y.getKey());
		});

		Vertex startingVertex = verticesInGraph.get(s - 1);
		vertexPriorityQueue.add(startingVertex);

		vertexPriorityQueue.add(startingVertex);
		while (!vertexPriorityQueue.isEmpty()) {
			Vertex parentVertex = vertexPriorityQueue.peek();
			int i = parentVertex.getVertexNumber();
			for (int j = 0; j < n; j++) {
				if (adjacencyMatrix[i][j] != 0) {
					Vertex childVertex = verticesInGraph.get(j);
					if (!visitedVertices.contains(childVertex)) {
						relax(parentVertex, childVertex, adjacencyMatrix[i][j]);
						vertexPriorityQueue.add(childVertex);
					}
				}
			}
			visitedVertices.add(parentVertex);
			vertexPriorityQueue.removeIf(v -> v.equals(parentVertex));
		}
		Collection<Vertex> filteredVertices = verticesInGraph.values();
		int[] arr = new int[verticesInGraph.values().size()];
		int i = 0;
		for (Vertex vertex : filteredVertices) {
			arr[i] = vertex.getKey();
			i++;
		}
		return arr;
	}

	private static void relax(Vertex u, Vertex v, int weight) {
		if (v.getKey() > u.getKey() + weight) {
			v.setKey(u.getKey() + weight);
			v.setParent(u);
		}
	}

	private static Map<Integer, Vertex> initializeSingleSource(int n, int startingNode) {
		Map<Integer, Vertex> verticesInGraph = new HashMap<Integer, Vertex>();
		for (int i = 0; i < n; i++) {
			verticesInGraph.put(i, new Vertex(i, Integer.MAX_VALUE, null));
		}
		Vertex startingVertex = verticesInGraph.get(startingNode - 1);
		startingVertex.setKey(0);
		return verticesInGraph;
	}

	private static int[][] populateAdjacencyMatrix(int n, int[][] edges) {
		int[][] adjacencyMatrix = new int[n][n];
		for (int[] edgeDetails : edges) {
			if (adjacencyMatrix[edgeDetails[0] - 1][edgeDetails[1] - 1] == 0
					|| adjacencyMatrix[edgeDetails[0] - 1][edgeDetails[1] - 1] > edgeDetails[2]) {
				int sourceVertex = edgeDetails[0] - 1;
				int destinationVertex = edgeDetails[1] - 1;
				int weight = edgeDetails[2];
				adjacencyMatrix[sourceVertex][destinationVertex] = weight;
				adjacencyMatrix[destinationVertex][sourceVertex] = weight;
			}
		}
		return adjacencyMatrix;
	}

	public static void main(String[] args) throws IOException {

		String fileName = "src/com/hackerrank/practice/graphs/dijkstra/Test_Case2.txt";
		File file = new File(fileName);

		Scanner scanner = new Scanner(file);
		int t = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int tItr = 0; tItr < t; tItr++) {

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

			int s = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			int[] result = shortestReach(n, edges, s);

			for (int i = 0; i < result.length; i++) {
				System.out.print(result[i]);
				System.out.print(" ");
			}

			System.out.println();
		}

		scanner.close();
	}

}
