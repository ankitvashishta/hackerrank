package com.hackerrank.practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class ShortestPath_DIJKSTRA {

	public void dijkstra(Integer[][] matrix) {
		Integer[] vertices = matrix[0];
		List<Vertex> vertexList = initializeSingleSource(vertices);
		Set<Vertex> traversedVertices = new HashSet<Vertex>();
		for(Vertex vertex : vertexList) {
			
		}		

	}

	public List<Vertex> initializeSingleSource(Integer[] vertices) {
		List<Vertex> vertexList = new ArrayList<>();
		Stream.of(vertices).forEach(v -> vertexList.add(new Vertex(v)));
		vertexList.get(0).shortestPathEstimate = 0;
		return vertexList;
	}

	public void relax(Vertex sourceVertex, Vertex destinationVertex, int weight) {
		if (destinationVertex.getShortestPathEstimate() > (sourceVertex.getShortestPathEstimate() + weight)) {
			destinationVertex.setShortestPathEstimate(sourceVertex.getShortestPathEstimate() + weight);
			destinationVertex.setParent(sourceVertex);
		}
	}

	private class Vertex {

		public Vertex(int vertexNumber) {
			this.vertexNumber = vertexNumber;
			this.shortestPathEstimate = Integer.MAX_VALUE;
			this.parent = null;
		}

		private int vertexNumber;
		private int shortestPathEstimate;
		private Vertex parent;

		public int getVertexNumber() {
			return vertexNumber;
		}

		public void setVertexNumber(int vertexNumber) {
			this.vertexNumber = vertexNumber;
		}

		public int getShortestPathEstimate() {
			return shortestPathEstimate;
		}

		public void setShortestPathEstimate(int shortestPathEstimate) {
			this.shortestPathEstimate = shortestPathEstimate;
		}

		public Vertex getParent() {
			return parent;
		}

		public void setParent(Vertex parent) {
			this.parent = parent;
		}

		public String toString() {
			return "Vertex : " + this.getVertexNumber() + "Shortest Path Estimate : " + this.getShortestPathEstimate()
					+ "Parent : " + this.getParent().vertexNumber;
		}

	}

}
