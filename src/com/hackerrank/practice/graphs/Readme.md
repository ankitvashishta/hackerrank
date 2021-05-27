<h2>Graphs</h2>

<p><b>Graphs</b> : In computer science, a graph is an abstract data type that is meant to implement the undirected graph and directed graph concepts from the field of graph theory within a math.</p>

<p><b>Abstract Data Type</b> : In computer science, an abstract data type (ADT) is a mathematical model for data types.</p>

 <h3>Conventions Followed </h3>
 <p>
 When we characterize the running time of a graph algorithm on a given graph G = (V, E), we usually measure the size of the input in terms of the number of vertices |V| and the number of edges |E| of the graph. That is, we describe the size of the input with two parameters, not just one. We adopt a common notational convention for these parameters. Inside asymptotic notation (such as O-notation), and only inside such notation, the symbol V denotes |V| and the symbol E denotes |E|. For example, we might say, “the algorithm runs in time O(VE),” meaning that the algorithm runs in time O(|V| |E|).
 <p>Another convention we adopt appears in pseudocode. We denote the vertex set of a graph G by G:V and its edge set by G:E. That is, the pseudocode views vertex and edge sets as attributes of a graph.</p>
 
 <h3>Representation of Graphs</h3>
 <p><b>Adjacency List</b> : The adjacency list of a graph G = (V, E) consists of an array Adj of |V| lists, one for each vertex in V. For each u ∈ V, the adjacency list Adj[u] contains all the vertices v such that there is an edge (u, v) ∈ E.</br>
Vertices are stored as records or objects, and every vertex stores a list of adjacent vertices. This data structure allows the storage of additional data on the vertices. Additional data can be stored if edges are also stored as objects, in which case each vertex stores its incident edges and each edge stores its incident vertices.</br>
A potential disadvantage of the adjacency-list representation is that it provides no quicker way to determine whether a given edge (u, v) is present in the graph than to search for v in the adjacency list Adj(u). An adjacency-matrix representation of the graph remedies this disadvantage, but at the cost of using asymptotically more memory.</p>
 <p><b>Adjacency Matrix</b> : A two-dimensional matrix, in which the rows represent source vertices and columns represent destination vertices. Data on edges and vertices must be stored externally. Only the cost for one edge can be stored between each pair of vertices.</p>
 <style>
 table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
td {
  text-align: center;
}
th {
  background-color: gray;
}
</style>
<table style="width:100%"> 
<tr>
<th>Operation</th>
<th>Adjacency List</th>
<th>Adjacency Matrix</th>
</tr>
<tr>
<td>Store Graph</td>
<td>O(|V| + |E|)</td>
<td>O(|V|<sup>2</sup>)</td>
</tr>
<tr>
<td>Add Vertex</td>
<td>O(1)</td>
<td>O(|V|<sup>2</sup>)</td>
</tr>
<tr>
<td>Add Edge</td>
<td>O(1)</td>
<td>O(1)</td>
</tr>
<tr>
<td>Remove Vertex</td>
<td>O(|E|)</td>
<td>O(|V|<sup>2</sup>)</td>
</tr>
<tr>
<td>Remove Vertex</td>
<td>O(|V|)</td>
<td>O(1)</td>
</tr>
<tr>
<td>Check if vertices x & y are adjacent</td>
<td>O(|V|)</td>
<td>O(1)</td>
</tr>
<tr>
<td></td>
<td></td>
<td></td>
</tr>

</table>
