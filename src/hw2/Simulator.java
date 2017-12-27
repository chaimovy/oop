package hw2;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Simulator<T, V> implements Simulatable<T> {
	private BipartiteGraph<T> graph;
	private Map<T, Node<T,V>> nodeMap;
	public Simulator(){
		graph = new BipartiteGraph<T>();
		nodeMap = new HashMap<T, Node<T,V>>();
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
	public void addPipe(T pipe) throws Exception {
	    graph.addBlackNode(pipe);
	    nodeMap.put(pipe,new Node<T,V>());
	}
	
	
	public void addFilter(T filter) throws Exception {
	    graph.addWhiteNode(filter);
	    nodeMap.put(filter,new Node<T,V>());
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

	public void sendTransaction(T pipeLabel, V tx) {
		nodeMap.get(pipeLabel).addTransaction(tx);
    }

	@Override
	public void simulate(BipartiteGraph<T> graph) 
	{
		for(T pipe : graph.listBlackNodes())
			nodeMap.get(pipe).simulate(graph);
		for(T filter : graph.listWhiteNodes())
			nodeMap.get(filter).simulate(graph);
	}

	public Queue<V> listContents(T nodeLabel)
	{
		Node<T,V> node = nodeMap.get(nodeLabel);
			return node.getBuffer();

	}


}
