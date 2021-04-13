package com.hackerrank.practice.graphs.mst.prim;

public class Vertex {

	public Vertex(int vertexNumber, int key, Vertex parent) {
		this.vertexNumber = vertexNumber;
		this.key = key;
		this.parent = parent;
	}

	private Integer vertexNumber;
	private Integer key;
	private Vertex parent;

	public Integer getVertexNumber() {
		return vertexNumber;
	}

	public void setVertexNumber(Integer vertexNumber) {
		this.vertexNumber = vertexNumber;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public Vertex getParent() {
		return parent;
	}

	public void setParent(Vertex parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Vertex [vertexNumber=" + vertexNumber + ", key=" + key + "]";
	}

	@Override
	public boolean equals(Object o) {
		return this.vertexNumber.equals(((Vertex) o).getVertexNumber());
	}

}
