package hw2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BipartiteGraph<T> {
    /*Abstraction Function:
     * represents a black-white bipartite graph, where black nodes are connected 
     * to white nodes by directed edges 
     */


    /* Representation Invariant
     * no black nodes connected to each other &&
     * no white nodes connected to each other &&
     * no more than one edge in each direction between any pair of nodes 
     */

	private Map<T, Node<T>> blackParents; // key = node label
	private Map<T, Node<T>> whiteParents;
	private Map<T, Node<T>> children;
    /**
     * @modifies this
     * @effects Creates a new graph named graphName. The graph is initially
     * 			empty.
     */
    public BipartiteGraph() {
    	blackParents=new HashMap<T,Node<T>>();
    	whiteParents=new HashMap<T,Node<T>>();
        children=new HashMap<T,Node<T>>();
        CheckRep();
    }

    /**
     * @throws Exception 
     * @requires nodeLabel != null
     * @modifies this.parents
     * @effects Adds a black node represented by the T nodeLabel to this.
     */
    public void addBlackNode(Node<T> node) throws Exception {
    	CheckRep();
    	if (blackParents.containsKey(node.getLabel()))
    	{
    		throw new Exception("Node " + node.getLabel().toString() + "Already Exists.");
    	}
    	blackParents.put(node.getLabel(), node);
    	CheckRep();
    }

    
    /**
     * @throws Exception 
     * @requires nodeLabel != null
     * @modifies this.parents
     * @effects Adds a white node represented by the T nodeLable to this.
     */
    public void addWhiteNode(Node<T> node) throws Exception {
    	CheckRep();
    	if (whiteParents.containsKey(node.getLabel()))
    	{
    		throw new Exception("Node " + node.getLabel().toString() + "Already Exists.");
    	}
    	whiteParents.put(node.getLabel(), node);
    	CheckRep();
    }

    
    /**
     * @throws Exception 
     * @requires parentLabel && childLabel && edgeLabel !=null
     * @modifies this.parents and this.children
     * @effects Adds an edge from the node parentName to the node childName
     * 			in the graph graphName. The new edge's label is the T
     * 			edgeLabel.
     */
    public void addEdge(T parentLabel, T childLabel, T edgeLabel) throws Exception {
    	CheckRep();
    	if (whiteParents.containsKey(parentLabel) && whiteParents.containsKey(childLabel) || blackParents.containsKey(parentLabel) && blackParents.containsKey(childLabel)) {
    		throw new Exception("Cannot connect nodes of same color");
    	}
    	if (whiteParents.containsKey(parentLabel) && blackParents.containsKey(childLabel)) {
    		if (whiteParents.get(parentLabel).getParents().containsKey(edgeLabel))
    			throw new Exception("Nodes are already connected.");
    		if (whiteParents.get(parentLabel).getChildren().containsValue(childLabel))
    			throw new Exception("Node " + parentLabel.toString() + " has already edge " + edgeLabel.toString());
    		whiteParents.get(parentLabel).addChild(edgeLabel, childLabel);
    	}
    	else if (whiteParents.containsKey(childLabel) && blackParents.containsKey(parentLabel)) {
    		if (blackParents.get(parentLabel).getParents().containsKey(edgeLabel))
    			throw new Exception("Nodes are already connected.");
    		if (blackParents.get(parentLabel).getChildren().containsValue(childLabel))
    			throw new Exception("Node " + parentLabel.toString() + " has already edge " + edgeLabel.toString());
    		blackParents.get(parentLabel).addChild(edgeLabel, childLabel);
    	}
    	else
    		throw new Exception("One of the nodes doesn't exist.");
   		if (!children.containsKey(childLabel))
		{
			children.put(childLabel, new Node<T>(childLabel));
		}
		children.get(childLabel).addParent(edgeLabel, parentLabel);
		CheckRep();
    }

    
    /**
     * @return a space-separated list of all the black nodes
     */
    public List<Node<T>> listBlackNodes() {
    	return new ArrayList<Node<T>>(blackParents.values());
    }

    
    /**
     * @return a space-separated list of all the white nodes
     */
    public List<Node<T>> listWhiteNodes() {
    	return new ArrayList<Node<T>>(whiteParents.values());
    }

    
    /**
     * @requires parentLabel !=null
     * @throws Exception 
     * @return list of the children of parentLabel
     */
    public List<Node<T>> listChildren(T parentLabel) throws Exception {
    	if (blackParents.containsKey(parentLabel))
    	{
    		List<Node<T>> children = new ArrayList<Node<T>>(blackParents.get(parentLabel).getChildren().values());// TODO: check if collection can be casted to list or change return type
    		return children;
    	}
    	else if (whiteParents.containsKey(parentLabel))
    	{
    		List<Node<T>> children = new ArrayList<Node<T>>(whiteParents.get(parentLabel).getChildren().values());// TODO: check if collection can be casted to list or change return type
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
    public List<Node<T>> listParents(T childLabel) throws Exception {
    	List<Node<T>> parents = new ArrayList<Node<T>>();
    	if (whiteParents.containsKey(childLabel) || blackParents.containsKey(childLabel))
    	{
    		if(children.containsKey(childLabel))
    			parents.addAll(children.get(childLabel).getParents().values());// TODO: check if collection can be casted to list or change return type
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
    public Node<T> getChildByEdgeLabel(T parentLabel,T edgeLabel) throws Exception {
    	Node<T> child = null;
    	if ( !whiteParents.containsKey(parentLabel) && !blackParents.containsKey(parentLabel)){
    		throw new Exception("Parent Node doesn't exist.");
    	}
    	if ( whiteParents.containsKey(parentLabel)){
    		if (!whiteParents.get(parentLabel).getChildren().containsKey(edgeLabel)) {
    			throw new Exception("Parent Node doesn't have edge "+edgeLabel.toString());
    		}
    		else {
    			child= whiteParents.get(parentLabel).getChildren().get(edgeLabel); 
    		}
    			
    	}
    	if ( blackParents.containsKey(parentLabel)){
    		if (!blackParents.get(parentLabel).getChildren().containsKey(edgeLabel)) {
    			throw new Exception("Parent Node doesn't have edge "+edgeLabel.toString());
    		}
    		else {
    			child = blackParents.get(parentLabel).getChildren().get(edgeLabel); 
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
    public Node<T> getParentByEdgeLabel(T childLabel,T edgeLabel) throws Exception {
    	if(!children.containsKey(childLabel))
    		throw new Exception("Node " + childLabel.toString() + "Doesn't exist.");
    	if(!children.get(childLabel).getParents().containsKey(edgeLabel))
    		throw new Exception("Edge" + edgeLabel.toString() + "Doesn't exist.");
    	return children.get(childLabel).getParents().get(edgeLabel);
    	
    }
    
    private void CheckRep() {
        Iterator<Map.Entry<T, Node<T>>> itrParent=whiteParents.entrySet().iterator();
        Iterator<Map.Entry<T,Node<T>>> itrChild;
        Set<Node<T>> set ;
        while (itrParent.hasNext()) {
        	Map.Entry<T, Node<T>> curEntryParent=itrParent.next();
        	assert !blackParents.containsKey(curEntryParent.getKey());
        	itrChild = curEntryParent.getValue().getChildren().entrySet().iterator();
        	while (itrChild.hasNext()) {
        		Map.Entry<T,Node<T>> curEntryChild=itrChild.next();
        		assert blackParents.containsKey(curEntryChild.getValue().getLabel()) && !whiteParents.containsKey(curEntryChild.getValue().getLabel());
        		set= new HashSet<Node<T>>(curEntryParent.getValue().getChildren().values());
        		assert set.size()==curEntryParent.getValue().getChildren().values().size();
        	}
        	
        }
        itrParent=blackParents.entrySet().iterator();
        while (itrParent.hasNext()) {
        	Map.Entry<T, Node<T>> curEntryParent=itrParent.next();
        	assert !whiteParents.containsKey(curEntryParent.getKey());
        	itrChild = curEntryParent.getValue().getChildren().entrySet().iterator();  
        	while (itrChild.hasNext()) {
        		Map.Entry<T,Node<T>> curEntryChild=itrChild.next();
        		assert whiteParents.containsKey(curEntryChild.getValue().getLabel()) && !blackParents.containsKey(curEntryChild.getValue().getLabel());
        		set= new HashSet<Node<T>>(curEntryParent.getValue().getChildren().values());
        		assert set.size()==curEntryParent.getValue().getChildren().values().size();
        	}
        }
    }
    
    public Node<T> findNode(T nodeLabel) {
    	if (whiteParents.containsKey(nodeLabel)) 
    		return whiteParents.get(nodeLabel);
    	if (blackParents.containsKey(nodeLabel)) 
    		return blackParents.get(nodeLabel);
    	return null;
    }
    
}