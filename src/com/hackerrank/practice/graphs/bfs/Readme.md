<h3>Breadth-first Search</h3>

<p><b>Breadth-first search</b> is one of the simplest algorithms for searching a graph and the archetype for many important graph algorithms. Prim’s minimum-spanning- tree algorithm and Dijkstra’s single-source shortest-paths algorithm use ideas similar to those in <i>BFS</i>.</p>

<p>Given a graph <i>G = (V, E)</i> and a distinguished source vertex <i>s</i>, breadth-first search systematically explores the edges of<i>G</i> to “discover” every vertex that is reachable from <i>s</i>. It computes the distance (smallest number of edges) from <i>s</i> to each reachable vertex. It also produces a “breadth-first tree” with root <i>s</i> that contains all reachable vertices. For any vertex <i>v</i> reachable from <i>s</i>, the simple path in the breadth-first tree from <i>s</i> to <i>v</i> corresponds to a “shortest path” from <i>s</i> to <i>v</i> in G, that is, a path containing the smallest number of edges. The algorithm works on both directed and undirected graphs.</p>

<p>To keep track of progress, breadth-first search colors each vertex <b>white, gray, or black</b>. All vertices start out white and may later become gray and then black. A vertex is discovered the first time it is encountered during the search, at which time it becomes nonwhite.</p>

<p>The breadth-first-search procedure BFS below assumes that the input graph <i>G = (V, E)</i> is represented using adjacency lists. It attaches several additional attributes to each vertex in the graph. We store the color of each vertex <i>u ∈ V</i> in the attribute <i>u.color</i> and the predecessor of <i>u</i> in the attribute <i>u.π</i>. If u has no predecessor (for example, if <i>u = s</i> or <i>u</i> has not been discovered), then <i>u.π = NIL</i>. The attribute <i>u:d</i> holds the distance from the source <i>s</i> to vertex <i>u</i> computed by the algorithm. The algorithm also uses a first-in, first-out queue <i>Q</i> to manage the set of gray vertices.</p>
<p>
u.d -> distance from source s to vertex u computed by the algorithm.</br	>
u.π -> predecessor of u in the graph.
</p>
<p>
<b>BFS<i>(G,s)</b>
</br>&ensp;1.&ensp; 			<b>for</b> each vertex u ∈ G.V - {s}
</br>&ensp;2.&ensp; &emsp;			u.color = WHITE
</br>&ensp;3.&ensp; &emsp;			u.d = ∞
</br>&ensp;4.&ensp; &emsp;			u.π = NIL
</br>&ensp;5.&ensp; 			s.color = GRAY
</br>&ensp;6.&ensp; 			s.d = 0
</br>&ensp;7.&ensp; 			s.π = NIL
</br>&ensp;8.&ensp; 			Q = ∅
</br>&ensp;9.&ensp; 			ENQUEUE(Q, s)
</br>10.&ensp; 			<b>while</b> Q != ∅
</br>11.&ensp; &emsp;			u = DEQUEUE(Q)
</br>12.&ensp; &emsp;			<b>for</b> each v ∈ Adj[u]
</br>13.&ensp; &emsp;&emsp;		<b>if</b> v.color == WHITE
</br>14.&ensp; &emsp;&emsp;&emsp;		v.color = GRAY
</br>15.&ensp; &emsp;&emsp;&emsp;		v.d = u.d + 1
</br>16.&ensp; &emsp;&emsp;&emsp;		v.π = u
</br>17.&ensp; &emsp;&emsp;&emsp;		ENQUEUE(Q, v)
</br>18.&ensp; &emsp;		u.color = BLACK
</i>
</p>



