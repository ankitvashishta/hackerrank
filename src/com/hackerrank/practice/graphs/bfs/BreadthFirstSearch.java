package com.hackerrank.practice.graphs.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BreadthFirstSearch {

	public int[] bfs(int n, int m, int[][] edges, int s) {
		int[][] adjacencyMatrix = new int[n][n];
		for (int[] edge : edges) {
			adjacencyMatrix[edge[0] - 1][edge[1] - 1] = 1;
			adjacencyMatrix[edge[1] - 1][edge[0] - 1] = 1;
		}
		return new BreadthFirstSearch().breadthFirstSearch(adjacencyMatrix, n, s - 1);

	}

	public int[] breadthFirstSearch(int[][] matrix, int n, int s) {
		List<Integer> distances = new ArrayList<>();
		int[] vertices = new int[n];
		for (int i = 0; i < n; i++) {
			vertices[i] = i;
		}
		int[] distancesArray = new int[vertices.length - 1];
		Map<Integer, Vertex> vertexMap = initializeSingleSource(vertices, s);
		Queue<Vertex> vertexQueue = new LinkedList<Vertex>();
		vertexQueue.add(vertexMap.get(s));
		while (!vertexQueue.isEmpty()) {
			Vertex parentVertex = vertexQueue.poll();
			int[] verticesArray = matrix[parentVertex.getVertexNumber()];
			for (int i = 0; i < verticesArray.length; i++) {
				int vertexNumber = verticesArray[i];
				if (vertexNumber != 0) {
					Vertex currentVertex = vertexMap.get(i);
					if (currentVertex.getVertexColor().equals(VertexColor.WHITE)) {
						currentVertex.setVertexColor(VertexColor.GRAY);
						currentVertex.setDistance(parentVertex.getDistance() + 6);
						currentVertex.setParent(parentVertex);
						vertexQueue.add(currentVertex);
					}
				}
			}
			parentVertex.setVertexColor(VertexColor.BLACK);
		}
		vertexMap.values().stream().forEach(v -> distances.add(v.getDistance()));
		for (int i = 0, j = 0; i < distances.size(); i++) {
			if (i != s) {
				distancesArray[j] = distances.get(i);
				j++;
			}
		}
		return distancesArray;
	}

	public Map<Integer, Vertex> initializeSingleSource(int[] vertices, int s) {
		Map<Integer, Vertex> vertexMap = new HashMap<Integer, Vertex>();
		List<Integer> verticesList = new ArrayList<>();
		for (int vertex : vertices) {
			verticesList.add(vertex);
		}
		verticesList.stream().forEach(v -> vertexMap.put(v, new Vertex(v)));
		Vertex source = vertexMap.get(vertices[s]);
		source.setVertexColor(VertexColor.GRAY);
		source.setDistance(0);
		source.setParent(null);
		return vertexMap;
	}

}
