<h3>Depth-first Search</h3>

<p>The strategy followed by depth-first search is, as its name implies, to search “deeper” in the graph whenever possible. Depth-first search explores edges out of the most recently discovered vertex <i>v</i> that still has unexplored edges leaving it. Once all of <i>v</i>’s edges have been explored, the search “backtracks” to explore edges leaving the vertex from which <i>v</i> was discovered. This process continues until we have discovered all the vertices that are reachable from the original source vertex. If any undiscovered vertices remain, then depth-first search selects one of them as a new source, and it repeats the search from that source. The algorithm repeats this entire process until it has discovered every vertex.</p>

<p>As in BFS, whenever DFS discovers a vertex <i>v</i> during a scan of the adjacency list of an already discovered vertex <i>u</i>, it records this event by setting <i>v</i>’s predecessor attribute <i>v</i>:<i>π</i> to <i>u</i>. Unlike BFS, whose predecessor subgraph forms a tree, the predecessor subgraph produced by a DFS may be composed of several trees, because the search may repeat from multiple sources.</p>

<p>The predecessor subgraph of a DFS forms a <b>depth-first forest</b> comprising several <b>depth-first trees</b>.</p>

<p>As in BFS, DFS colors vertices during the search to indicate their state. Each vertex is initially white, is grayed when it is <b>discovered</b> in the search, and is blackened when it is <b>finished</b>, that is, when its adjacency list has been examined completely. This technique guarantees that each vertex ends up in exactly one depth-first tree, so that these trees are disjoint.</p>
<p>

<p>
Besides creating a depth-first forest, depth-first search also <b>timestamps</b> each vertex. Each vertex <i>v</i> has two timestamps: the first timestamp <i>v:d</i> records when <i>v</i> is first discovered (and grayed), and the second timestamp <i>v:f</i> records when the search finishes examining <i>v</i>’s adjacency list (and blackens <i>v</i>). These timestamps provide important information about the structure of the graph and are generally helpful in reasoning about the behavior of DFS.
</p>

<p>
The procedure DFS below records when it discovers vertex <i>u</i> in the attribute <i>u:d</i> and when it finishes vertex <i>u</i> in the attribute <i>u:f</i>. These timestamps are integers between <i>1</i> and <i>2|V|</i>, since there is one discovery event and one finishing event for each of the <i>|V|</i> vertices. For every vertex </i>u</i>,<br>
&ensp;&ensp; &ensp;&ensp; <i>u:d < u:f</i><br>
Vertex <i>u</i> is WHITE before time <i>u:d</i>, GRAY between time <i>u:d</i> and time <i>u:f</i>, and BLACK thereafter.
</p>
u.d -> time when the vertex u is discovered.</br	>
u.f -> time when the vertex u is completed ie. its adjacency list is completely examined..</br	>
u.π -> predecessor of u in the graph.
</p>
<p>
<b>DFS(G)</b>
</br>&ensp;1.&ensp;			<b>for</b> each vertex <i>u ∈ G.V</i>
</br>&ensp;2.&ensp; &emsp;		<i>u:color</i> = WHITE
</br>&ensp;3.&ensp; &emsp; 		<i>u:π</i> = NIL
</br>&ensp;4.&ensp; 			<i>time</i> = 0
</br>&ensp;5.&ensp; 			<b>for</b> each vertex <i>u ∈ G.V</i>
</br>&ensp;6.&ensp; &emsp;		if <i>u:color</i> == WHITE
</br>&ensp;7.&ensp; &emsp;&emsp;			DFS-VISIT<i>(G, u)</i>
</br></br>
<b>DFS-VISIT<i>(G,u)</b>
</br>&ensp;1.&ensp; 			time = time + 1
</br>&ensp;2.&ensp;			u.d = time
</br>&ensp;3.&ensp;			u.color = GRAY
</br>&ensp;4.&ensp; 			<b>for</b> each vertex <i>v ∈ G.Adj[u]</i>
</br>&ensp;5.&ensp;&emsp;		if v.color == WHITE	
</br>&ensp;6.&ensp;&emsp;&emsp;		v.π = u
</br>&ensp;7.&ensp;&emsp;&emsp; 		DFS-VISIT<i>(G, u)</i>
</br>&ensp;8.&ensp; 			u.color = BLACK
</br>&ensp;9.&ensp; 			time = time + 1
</br>10.&ensp; 				u.f = time

</i>
</p>



