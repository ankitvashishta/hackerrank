Given a graph which consists of several edges connecting its nodes, find a subgraph of the given graph with the following properties:

1. The subgraph contains all the nodes present in the original graph.
2. The subgraph is of minimum overall weight (sum of all edges) among all such subgraphs.
3. It is also required that there is exactly one, exclusive path between any two nodes of the subgraph.

The minimum spanning tree is built gradually by adding edges one at a time. At first the spanning tree consists only of a single vertex (root). Then the minimum weight edge outgoing from this vertex is selected and added to the spanning tree. Now select and add the edge with the minimum weight that has one end in an already selected vertex (i.e. a vertex that is already part of the spanning tree), and the other end in an unselected vertex. And so on, i.e. every time we select and add the edge with minimal weight that connects one selected vertex with one unselected vertex. The process is repeated until the spanning tree contains all vertices (or equivalently until we have n−1 edges).

