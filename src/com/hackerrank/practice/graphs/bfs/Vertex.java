package com.hackerrank.practice.graphs.bfs;

public class Vertex {

	public Vertex(int vertexNumber) {
		this.vertexNumber = vertexNumber;
		this.distance = -1;
		this.parent = null;
		this.vertexColor = VertexColor.WHITE;
	}

	private int vertexNumber;
	private int distance;
	private Vertex parent;
	private VertexColor vertexColor;

	public int getVertexNumber() {
		return vertexNumber;
	}

	public void setVertexNumber(int vertexNumber) {
		this.vertexNumber = vertexNumber;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Vertex getParent() {
		return parent;
	}

	public void setParent(Vertex parent) {
		this.parent = parent;
	}

	public VertexColor getVertexColor() {
		return vertexColor;
	}

	public void setVertexColor(VertexColor vertexColor) {
		this.vertexColor = vertexColor;
	}

	@Override
	public String toString() {
		return "Vertex [vertexNumber=" + vertexNumber + ", distance=" + distance + ", parent="
				+ parent + ", vertexColor=" + vertexColor + "]";
	}
	
	

}
