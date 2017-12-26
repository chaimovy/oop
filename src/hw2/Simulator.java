package hw2;

public class Simulator<T, V> implements Simulatable<T> {
	private BipartiteGraph<T> graph;
	public Simulator(){
		graph = new BipartiteGraph<T>();
	}
	
	
	/**
	 * @throws Exception 
	 * @requires 
     *           && channelName != null && channelName has
	 *           not been used in a previous addChannel()  or
	 *           addParticipant() call on this object
	 *           limit > 0
	 * @modifies simulator named simName
	 * @effects Creates a new Channel named by the String channelName, with a limit, and add it to
	 *          the simulator named simName.
	 */
	public void addPipe(T pipeLabel) throws Exception {
	    graph.addBlackNode(pipeLabel);
	}
	
	
	public void addFilter(T filterLabel) throws Exception {
	    graph.addWhiteNode(filterLabel);
	}
	
	/**
	 * @throws Exception 
	 * @requires ((addPipe(parentName) &&
	 *           addFilter(childName)) || (addFilter(parentName) &&
	 *           addPipe(childName))) && edgeLabel != null && node named
	 *           parentName has no other outgoing edge labeled edgeLabel 
	 *           && node named childName has no other incoming edge labeled edgeLabel
	 * @modifies simulator named simName
	 * @effects Adds an edge from the node named parentName to the node named
	 *          childName in the simulator named simName. The new edge's label
	 *          is the String edgeLabel.
	 */
	public void addEdge(T parentName, T childName, T edgeLabel) throws Exception {
        graph.addEdge(parentName, childName, childName);
	}

	
	/**
	 * @requires  addChannel(channelName)
	 *           A transaction Transaction != null
	 * @modifies channel named channelName
	 * @effects pushes the Transaction into the channel named channelName in the
	 *          simulator named simName.
	 */
	@SuppressWarnings("unchecked")
	public void sendTransaction(T pipeLabel, V tx) {
		Node<T> pipe = graph.findNode(pipeLabel);
		if (pipe != null)
			((Pipe<T, V>)pipe).addTransaction(tx);
    }

	@SuppressWarnings("unchecked")
	@Override
	public void simulate(BipartiteGraph<T> graph) {
		for(Node<T> pipe : graph.listBlackNodes())
			((Pipe<T, V>)pipe).simulate(graph);
		for(Node<T> filter : graph.listWhiteNodes())
			((Filter<T,V>)filter).simulate(graph);
	}
}
