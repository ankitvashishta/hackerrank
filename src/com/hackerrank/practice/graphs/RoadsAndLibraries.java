package com.hackerrank.practice.graphs;

import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.hackerrank.practice.graphs.mst.kruskal.Edge;

public class RoadsAndLibraries {

	/*
	 * Complete the 'roadsAndLibraries' function below.
	 *
	 * The function is expected to return a LONG_INTEGER. The function accepts
	 * following parameters: 1. INTEGER n 2. INTEGER c_lib 3. INTEGER c_road 4.
	 * 2D_INTEGER_ARRAY cities
	 */

	public static long roadsAndLibraries(int n, int c_lib, int c_road, List<List<Integer>> cities) {
		// Write your code here

		return 0l;

	}
	
	public int kruskals(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
		Set<Edge> treeEdges = mstKruskal(gNodes, gFrom, gTo, gWeight);

		return treeEdges.stream().collect(Collectors.summingInt(v -> v.getWeight()));
	}

	/**
	 * @Line1 MST-Kruskal(G)
	 * @Line2 A = ∅
	 * @Line3 for each vertex v ∈ G.V
	 * @Line4 	MAKE-SET(v)
	 * @Line5 sort the edges of G:E into nondecreasing order by weight w
	 * @Line5 for each edge(u, v) ∈ G.E, taken in nondecreasing order by weight
	 * @Line6 	if FIND-SET(u) ≠ FIND-SET(v) then
	 * @Line7 		A = A ∪ {(u, v)}
	 * @Line8 		UNION(FIND-SET(u), FIND-SET(v))
	 * @Line9 return A
	 */
	/*
	 * MAKE-SET(v) - It uses a disjoint-set data structure to maintain several
	 * disjoint sets of elements. Each set contains the vertices in one tree of the
	 * current forest.
	 *
	 * FIND-SET(u) - The operation FIND-SET(u) returns a representative element from
	 * the set that contains u. Thus, we can determine whether two vertices u and v
	 * belong to the same tree by testing whether FIND-SET(u) equals FIND-SET(v)
	 */

	public Set<Edge> mstKruskal(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {

		// Collection of all the vertices in the graph.
		Set<Integer> verticesSet = new HashSet<Integer>();

		// Collection of all the edges in the graph.
		List<Edge> edgeList = new ArrayList<Edge>();

		for (int i = 0; i < gFrom.size(); i++) {
			edgeList.add(new Edge(gFrom.get(i), gTo.get(i), gWeight.get(i)));
			verticesSet.add(gFrom.get(i));
			verticesSet.add(gTo.get(i));
		}

		// Collection of every vertex in a separate collection - @Line4
		List<LinkedList<Integer>> disjointSet = new ArrayList<LinkedList<Integer>>();

		verticesSet.forEach(v -> {
			LinkedList<Integer> verticesInTree = new LinkedList<>();
			verticesInTree.push(v);
			disjointSet.add(verticesInTree);

		});

		// Edges of the minimum spanning tree.
		Set<Edge> treeEdges = new HashSet<Edge>();

		// Sort the edges by weight.
		Collections.sort(edgeList);

		for (Edge edge : edgeList) {
			LinkedList<Integer> sourceTree = null;
			LinkedList<Integer> destinationTree = null;
			for (LinkedList<Integer> individualTree : disjointSet) {
				if (individualTree.contains(edge.getStartVertex())) {
					sourceTree = individualTree;
				}
				if (individualTree.contains(edge.getEndVertex())) {
					destinationTree = individualTree;
				}
			}
			if (!(sourceTree == destinationTree)) {
				sourceTree.addAll(destinationTree);
				disjointSet.remove(destinationTree);
				treeEdges.add(edge);
			}
		}
		return treeEdges;
	}

	public static void main(String[] args) throws IOException {

		File file = new File("src/com/hackerrank/practice/graphs/Test_Case1.txt");
		Scanner scanner = new Scanner(file);

		int q = Integer.parseInt(scanner.nextLine().trim());

		IntStream.range(0, q).forEach(qItr -> {
			String[] firstMultipleInput = scanner.nextLine().replaceAll("\\s+$", "").split(" ");

			int n = Integer.parseInt(firstMultipleInput[0]);

			int m = Integer.parseInt(firstMultipleInput[1]);

			int c_lib = Integer.parseInt(firstMultipleInput[2]);

			int c_road = Integer.parseInt(firstMultipleInput[3]);

			List<List<Integer>> cities = new ArrayList<>();

			IntStream.range(0, m).forEach(i -> {
				cities.add(Stream.of(scanner.nextLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt)
						.collect(toList()));
			});

			long result = roadsAndLibraries(n, c_lib, c_road, cities);

			System.out.print(String.valueOf(result));
			System.out.println();
		});

		scanner.close();
	}
}
