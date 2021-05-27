package com.hackerrank.practice.graphs.dijkstra;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

import com.hackerrank.practice.graphs.mst.kruskal.Edge;
import com.hackerrank.practice.graphs.mst.prim.Vertex;

public class Dijkstra {

	// Complete the shortestReach function below.
	static int[] shortestReach(int n, int[][] edges, int s) {
		Map<Integer, LinkedList<Edge>> edgesMap = new HashMap<Integer, LinkedList<Edge>>();
		populateAdjacencyMatrix(n, edges, edgesMap);
		Map<Integer, Vertex> verticesInGraph = initializeSingleSource(n, s);

		Set<Vertex> visitedVertices = new HashSet<Vertex>();
		PriorityQueue<Vertex> vertexPriorityQueue = new PriorityQueue<Vertex>((x, y) -> {
			return x.getKey().compareTo(y.getKey());
		});

		Vertex startingVertex = verticesInGraph.get(s - 1);
		vertexPriorityQueue.add(startingVertex);

		while (!vertexPriorityQueue.isEmpty()) {
			Vertex parentVertex = vertexPriorityQueue.peek();

			for (Edge edge : edgesMap.get(parentVertex.getVertexNumber())) {
				Vertex childVertex = verticesInGraph.get(edge.getEndVertex());
				if (!visitedVertices.contains(childVertex)) {
					relax(parentVertex, childVertex, edge.getWeight());
					vertexPriorityQueue.add(childVertex);
				}
			}
			visitedVertices.add(parentVertex);
			vertexPriorityQueue.removeIf(v -> v.equals(parentVertex));
		}

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			Vertex vertex = verticesInGraph.get(i);
			if (vertex.getKey().equals(Integer.MAX_VALUE))
				arr[i] = -1;
			else
				arr[i] = vertex.getKey();
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

	private static void populateAdjacencyMatrix(int n, int[][] edges, Map<Integer, LinkedList<Edge>> edgesMap) {
		for (int[] edgeDetails : edges) {
			int sourceVertex = edgeDetails[0] - 1;
			int destinationVertex = edgeDetails[1] - 1;
			int weight = edgeDetails[2];
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
	}

	public static void main(String[] args) throws IOException {

		String fileName = "src/com/hackerrank/practice/graphs/dijkstra/Test_Case4.txt";
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
				//scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

				for (int j = 0; j < 3; j++) {
					int edgesItem = Integer.parseInt(edgesRowItems[j]);
					edges[i][j] = edgesItem;
				}
			}

			int s = scanner.nextInt();
			//scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			int[] result = shortestReach(n, edges, s);

			for (int i = 0; i < result.length; i++) {
				if (i != s - 1) {
					System.out.print(result[i]);

					if (i != result.length - 1) {
						System.out.print(" ");
					}
				}

			}

			System.out.println();
		}

		scanner.close();
	}

}
