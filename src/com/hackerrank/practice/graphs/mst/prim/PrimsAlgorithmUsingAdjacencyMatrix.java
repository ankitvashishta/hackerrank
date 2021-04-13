package com.hackerrank.practice.graphs.mst.prim;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @param	G -> Connected Graph
 * @param	w -> collection of weight of edges
 * @param	r -> root of the graph
 * @Line0 MST-PRIM (G, w, r)
 * @Line1	for each u ∈ G.V
 * @Line2		u.key = ∞
 * @Line3		u.π = NIL
 * @Line4	r.key = 0
 * @Line5	Q = G.V
 * @Line6	While Q != ∅
 * @Line7		u = EXTRACT_MIN(Q)
 * @Line8		for each v ∈ Adj[u]
 * @Line9		if v ∈ Q and w(u, v) < v.key
 * @Line10			v.π = u
 * @Line11			v.key = w(u, v)
 * 
 * @author ankitvashishta
 *
 */
public class PrimsAlgorithmUsingAdjacencyMatrix {

	/**
	 * 
	 * @param n - number of vertices
	 * @param edges - 2-d array containing the edges
	 * @param start - root of the graph
	 * @return
	 */
	public int prims(int n, int[][] edges, int start) {
		int[][] adjacencyMatrix = populateAdjacencyMatrix(n, edges);

		Map<Integer, Vertex> verticesInGraph = new HashMap<Integer, Vertex>();
		for (int i = 0; i < n; i++) {
			verticesInGraph.put(i, new Vertex(i, Integer.MAX_VALUE, null));
		}
		Vertex startingVertex = verticesInGraph.get(start - 1);
		startingVertex.setKey(0);

		Set<Vertex> visitedVertices = new HashSet<Vertex>();
		PriorityQueue<Vertex> vertexPriorityQueue = new PriorityQueue<Vertex>((x, y) -> {
			return x.getKey().compareTo(y.getKey());
		});

		vertexPriorityQueue.add(startingVertex);
		while (!vertexPriorityQueue.isEmpty()) {
			Vertex parentVertex = vertexPriorityQueue.peek();
			int i = parentVertex.getVertexNumber();
			for (int j = 0; j < n; j++) {
				if (adjacencyMatrix[i][j] != 0) {
					Vertex childVertex = verticesInGraph.get(j);
					if (!visitedVertices.contains(childVertex)) {
						if (adjacencyMatrix[i][j] < childVertex.getKey()) {
							childVertex.setKey(adjacencyMatrix[i][j]);
							childVertex.setParent(parentVertex);
						}
						vertexPriorityQueue.add(childVertex);
					}
				}
			}
			visitedVertices.add(parentVertex);
			vertexPriorityQueue.removeIf(v -> v.equals(parentVertex));
		}
		return verticesInGraph.values().stream().collect(Collectors.summingInt(v -> v.getKey()));
	}

	private int[][] populateAdjacencyMatrix(int n, int[][] edges) {
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

}
