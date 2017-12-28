package hw2;

import java.util.Queue;

public class Simulator<T, V>  {
	private BipartiteGraph<T, Node<T,V>> graph;
	/**
	 * @modifies this
	 * @effects constructs new simulator.
	 */
	public Simulator(){
		graph = new BipartiteGraph<T, Node<T,V>>();
	}
	
	/**
	 * @throws Exception 
	 * @requires pipe != null && pipeLabel!=null
	 * @modifies this.graph
	 * @effects Adds new pipe to graph and adds link to node mapping
	 */
	public void addPipe(T pipeLabel,Node<T,V> pipe) throws Exception {
	    graph.addBlackNode(pipeLabel);
	    graph.getNodeMap().put(pipeLabel,pipe);
	}
	
	/**
	 * @throws Exception 
	 * @requires filter != null && filterLabel!=null
	 * @modifies this.graph
	 * @effects Adds new filter to graph and adds link to node mapping
	 */
	public void addFilter(T filterLabel,Node<T,V> filter) throws Exception {
	    graph.addWhiteNode(filterLabel);
	    graph.getNodeMap().put(filterLabel,filter);
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
	 * @requires v != null && transfer object tx!=null
	 * @modifies this.graph
	 * @effects Adds new transction to specified pipe.
	 */
	public void sendTransaction(T pipeLabel, V tx) {
		graph.getNodeMap().get(pipeLabel).addTransaction(tx);
    }

	/**
	 * @throws Exception
     * @modifies current graph's nodes
     * @effects Executes all transactions currently in pipes and filters
     */
	public void simulate() throws Exception 
	{
		for(T pipe : graph.listBlackNodes()) {
			graph.getNodeMap().get(pipe).simulate(graph);
		}
		for(T filter : graph.listWhiteNodes()) {
			graph.getNodeMap().get(filter).simulate(graph);
		}
	}
	/**
	 * @requires nodeLabel!=null
     * @modifies current graph's nodes
     * @effects none
     * @return specified node's buffer.
     */
	public Queue<V> listContents(T nodeLabel)
	{
		return graph.getNodeMap().get(nodeLabel).getBuffer();
	}
}
