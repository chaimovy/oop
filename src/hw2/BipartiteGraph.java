package hw2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BipartiteGraph<T,Z> {
    /*Abstraction Function:
     * represents a black-white bipartite graph, where black nodes are connected 
     * to white nodes by directed edges 
     */


    /* Representation Invariant
     * no black nodes connected to each other &&
     * no white nodes connected to each other &&
     * no more than one edge in each direction between any pair of nodes 
     */

	private Map<T, Map<T, T>> blackParents; // key = node label
	private Map<T, Map<T, T>> whiteParents;
	private Map<T, Map<T, T>> children;
	private Map<T, Z> nodeMap;
	/**
     * @modifies none
     * @return nodes mapping
     */
	public Map<T, Z> getNodeMap()
	{
		return nodeMap;
	}
    /**
     * @modifies this
     * @effects Creates a new graph named graphName. The graph is initially
     * 			empty.
     */
    public BipartiteGraph() {
    	blackParents=new HashMap<T,Map<T, T>>();
    	whiteParents=new HashMap<T,Map<T, T>>();
        children=new HashMap<T,Map<T,T>>();
        nodeMap=new HashMap<T,Z>();
        CheckRep();
    }

    /**
     * @throws Exception 
     * @requires nodeLabel != null
     * @modifies this.parents
     * @effects Adds a black node represented by the T nodeLabel to this.
     */
    public void addBlackNode(T node) throws Exception {
    	CheckRep();
    	if (blackParents.containsKey(node))
    	{
    		throw new Exception("Node " + node.toString() + "Already Exists.");
    	}
    	blackParents.put(node, new HashMap<T, T>());
    	CheckRep();
    }

    
    /**
     * @throws Exception 
     * @requires nodeLabel != null
     * @modifies this.parents
     * @effects Adds a white node represented by the T nodeLable to this.
     */
    public void addWhiteNode(T node) throws Exception {
    	if (whiteParents.containsKey(node))
    	{
    		throw new Exception("Node " + node.toString() + "Already Exists.");
    	}
    	whiteParents.put(node, new HashMap<T, T>());
    	CheckRep();
    }

    
    /**
     * @throws Exception 
     * @requires parentLabel && childLabel && edgeLabel !=null
     * @modifies this.parents and this.children
     * @effects Adds an edge from the node parentLabel to the node childLabel
     * 			in the graph. The new edge's label is the T
     * 			edgeLabel.
     */
    public void addEdge(T parentLabel, T childLabel, T edgeLabel) throws Exception {
    	CheckRep();
    	if (whiteParents.containsKey(parentLabel) && whiteParents.containsKey(childLabel) || blackParents.containsKey(parentLabel) && blackParents.containsKey(childLabel)) {
    		throw new Exception("Cannot connect nodes of same color");
    	}
    	if (whiteParents.containsKey(parentLabel) && blackParents.containsKey(childLabel)) {
    		if (whiteParents.get(parentLabel).containsKey(edgeLabel))
    			throw new Exception("Nodes are already connected.");
    		if (whiteParents.get(parentLabel).containsValue(childLabel))
    			throw new Exception("Node " + parentLabel.toString() + " has already edge " + edgeLabel.toString());
    		whiteParents.get(parentLabel).put(edgeLabel, childLabel);
    	}
    	else if (whiteParents.containsKey(childLabel) && blackParents.containsKey(parentLabel)) {
    		if (blackParents.get(parentLabel).containsKey(edgeLabel))
    			throw new Exception("Nodes are already connected.");
    		if (blackParents.get(parentLabel).containsValue(childLabel))
    			throw new Exception("Node " + parentLabel.toString() + " has already edge " + edgeLabel.toString());
    		blackParents.get(parentLabel).put(edgeLabel, childLabel);
    	}
    	else
    		throw new Exception("One of the nodes doesn't exist.");
   		if (!children.containsKey(childLabel))
		{
			children.put(childLabel, new HashMap<T, T>());
		}
		children.get(childLabel).put(edgeLabel, parentLabel);
		CheckRep();
    }

    
    /**
     * @return a space-separated list of all the black nodes
     */
    public List<T> listBlackNodes() {
    	return new ArrayList<T>(blackParents.keySet());
    }

    
    /**
     * @return a space-separated list of all the white nodes
     */
    public List<T> listWhiteNodes() {
    	return new ArrayList<T>(whiteParents.keySet());
    }

    
    /**
     * @requires parentLabel !=null
     * @throws Exception 
     * @return list of the children of parentLabel
     */
    public List<T> listChildren(T parentLabel) throws Exception {
    	if (blackParents.containsKey(parentLabel))
    	{
    		List<T> children = new ArrayList<T>(blackParents.get(parentLabel).values());// TODO: check if collection can be casted to list or change return type
    		return children;
    	}
    	else if (whiteParents.containsKey(parentLabel))
    	{
    		List<T> children = new ArrayList<T>(whiteParents.get(parentLabel).values());// TODO: check if collection can be casted to list or change return type
    		return children;
    	}
    	else
    		throw new Exception("Parent Node doesn't exist.");
    }

    
    /**
     * @requires childLabel !=null
     * @return  list  of the parents of
     * 		   childLabel in the graph.
     * @throws Exception 
     */
    public List<T> listParents(T childLabel) throws Exception {
    	List<T> parents = new ArrayList<T>();
    	if (whiteParents.containsKey(childLabel) || blackParents.containsKey(childLabel))
    	{
    		if(children.containsKey(childLabel))
    			parents.addAll(children.get(childLabel).values());// TODO: check if collection can be casted to list or change return type
    		return parents;
    	}
    	throw new Exception("Node " + childLabel.toString() + "doesn't exist.");
    }

    
    /**
     * @requires parentLabel && edgeLabel !=null
     * @return the Label of the child of parentLabel that is connected by the
     * 		   edge labeled edgeLabel
     * @throws Exception 
     */
    public T getChildByEdgeLabel(T parentLabel,T edgeLabel) throws Exception {
    	T child = null;
    	if ( !whiteParents.containsKey(parentLabel) && !blackParents.containsKey(parentLabel)){
    		throw new Exception("Parent Node doesn't exist.");
    	}
    	if ( whiteParents.containsKey(parentLabel)){
    		if (!whiteParents.get(parentLabel).containsKey(edgeLabel)) {
    			throw new Exception("Parent Node doesn't have edge "+edgeLabel.toString());
    		}
    		else {
    			child= whiteParents.get(parentLabel).get(edgeLabel); 
    		}
    			
    	}
    	if ( blackParents.containsKey(parentLabel)){
    		if (!blackParents.get(parentLabel).containsKey(edgeLabel)) {
    			throw new Exception("Parent Node doesn't have edge "+edgeLabel.toString());
    		}
    		else {
    			child = blackParents.get(parentLabel).get(edgeLabel); 
    		}
    			
    	}
    	return child;
    }

    
    /**
     * @requires childLabel && edgeLabel !=null
     * @throws exeption
     * @return the Label of the parent of childLabel that is connected by the
     * 		   edge labeled edgeLabel
     * @throws Exception 
     */
    public T getParentByEdgeLabel(T childLabel,T edgeLabel) throws Exception {
    	if(!children.containsKey(childLabel))
    		throw new Exception("Node " + childLabel.toString() + "Doesn't exist.");
    	if(!children.get(childLabel).containsKey(edgeLabel))
    		throw new Exception("Edge" + edgeLabel.toString() + "Doesn't exist.");
    	return children.get(childLabel).get(edgeLabel);
    	
    }
    
    private void CheckRep() {
        Iterator<Map.Entry<T, Map<T,T>>> itrParent=whiteParents.entrySet().iterator();
        Iterator<Map.Entry<T,T>>itrChild;
        Set<T> set ;
      //for all white nodes
        while (itrParent.hasNext()) {     
        	Map.Entry<T, Map<T,T>> curEntryParent=itrParent.next();
        	//check that they are not black
        	assert !blackParents.containsKey(curEntryParent.getKey());  
        	itrChild = curEntryParent.getValue().entrySet().iterator();
        	//for every child of some white node
        	while (itrChild.hasNext()) {  
        		Map.Entry<T,T> curEntryChild=itrChild.next();
        		//check that child is black
        		assert blackParents.containsKey(curEntryChild.getValue()) && !whiteParents.containsKey(curEntryChild.getValue()); 
        		set= new HashSet<T>(curEntryParent.getValue().values());
        		assert set.size()==curEntryParent.getValue().values().size(); 
        	}
        	
        }
        itrParent=blackParents.entrySet().iterator();
      //for all black nodes
        while (itrParent.hasNext()) {    
        	Map.Entry<T, Map<T,T>> curEntryParent=itrParent.next();
        	 //check that they are not white
        	assert !whiteParents.containsKey(curEntryParent.getKey());
        	itrChild = curEntryParent.getValue().entrySet().iterator();
        	 //for every child of some black node
        	while (itrChild.hasNext()) { 
        		Map.Entry<T,T> curEntryChild=itrChild.next();
        		 //check that child is white
        		assert whiteParents.containsKey(curEntryChild.getValue()) && !blackParents.containsKey(curEntryChild.getValue()); 
        		set= new HashSet<T>(curEntryParent.getValue().values());
        		//check that there is no more than 1 edge between each per of nodes
        		assert set.size()==curEntryParent.getValue().values().size();
        	}
        }
    }



    
}