package com.hackerrank.practice.graphs.mst.kruskal;

public class Edge implements Comparable<Edge> {

	public Edge(Integer startVertex, Integer endVertex, Integer weight) {
		super();
		this.startVertex = startVertex;
		this.endVertex = endVertex;
		this.weight = weight;
	}

	private Integer startVertex;
	private Integer endVertex;
	private Integer weight;

	public Integer getStartVertex() {
		return startVertex;
	}

	public void setStartVertex(Integer startVertex) {
		this.startVertex = startVertex;
	}

	public Integer getEndVertex() {
		return endVertex;
	}

	public void setEndVertex(Integer endVertex) {
		this.endVertex = endVertex;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Edge [startVertex=" + startVertex + ", endVertex=" + endVertex + ", weight=" + weight + "]";
	}

	@Override
	public int compareTo(Edge o) {
		return this.weight.compareTo(o.getWeight());
	}

}
