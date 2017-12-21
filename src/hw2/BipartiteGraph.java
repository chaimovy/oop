package hw2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import HW1.Shape;

public class BipartiteGraph<T> {
	private Map<T, Map<T,T>> blackParents;
	private Map<T, Map<T,T>> whiteParents;
	private Map<T, Map<T,T>> children;
    /**
     * @modifies this
     * @effects Creates a new graph named graphName. The graph is initially
     * 			empty.
     */
    public BipartiteGraph() {
    	blackParents=new HashMap<T,Map<T,T>>();
    	whiteParents=new HashMap<T,Map<T,T>>();
        children=new HashMap<T,Map<T,T>>();
        
    	
    }

    /**
     * @requires nodeLabel != null
     * @throws exception
     * @modifies this.parents
     * @effects Adds a black node represented by the T nodeLabel to this.
     */
    public void addBlackNode(T nodeLabel) {
    	if (blackParents.containsKey(nodeLabel))
    	{
    		return; //TODO: throw exception
    	}
    	blackParents.put(nodeLabel, new HashMap<T,T>());
    	
    	
    }

    
    /**
     * @requires nodeLabel != null
     * @throws exception
     * @modifies this.parents
     * @effects Adds a white node represented by the T nodeLable to this.
     */
    public void addWhiteNode(T nodeLabel) {
    	if (whiteParents.containsKey(nodeLabel))
    	{
    		return; //TODO: throw exception
    	}
    	whiteParents.put(nodeLabel, new HashMap<T,T>());
    	
    	
    }

    
    /**
     * @requires parentLabel && childLabel && edgeLabel !=null
     * @modifies this.parents and this.children
     * @effects Adds an edge from the node parentName to the node childName
     * 			in the graph graphName. The new edge's label is the T
     * 			edgeLabel.
     */
    public void addEdge(T parentLabel, T childLabel, T edgeLabel) {
    	if (whiteParents.containsKey(parentLabel) && whiteParents.containsKey(childLabel) || blackParents.containsKey(parentLabel) && blackParents.containsKey(childLabel)) {
    		return; //TODO: throw exception same color
    	}
    	if (whiteParents.containsKey(parentLabel) && blackParents.containsKey(childLabel)) {
    		if (whiteParents.get(parentLabel).containsKey(edgeLabel)) {
    			return; //TODO: throw exception edge exists
    		}
    		whiteParents.get(parentLabel).put(edgeLabel, childLabel);
    		
    	}
    	else if (whiteParents.containsKey(childLabel) && blackParents.containsKey(parentLabel)) {
    		if (blackParents.get(parentLabel).containsKey(edgeLabel)) {
    			return; //TODO: throw exception edge exists
    		}
    		blackParents.get(parentLabel).put(edgeLabel, childLabel);
    		
    		
    	}
    	else
    		return; //TODO: throw exception can't connect
   		if (!children.containsKey(childLabel))
		{
			children.put(childLabel, new HashMap<T,T>());
		}
		children.get(childLabel).put(edgeLabel, parentLabel);
		return;
    	
    	
    	
    	
    }

    
    /**
     * @return a space-separated list of all the black nodes
     */
    public List<T> listBlackNodes() {
    	 List<T> blackNodes = new ArrayList<T>(blackParents.keySet());
    	 
    	return blackNodes;
    	
    }

    
    /**
     * @return a space-separated list of all the white nodes
     */
    public List<T> listWhiteNodes() {
    	 List<T> whiteNodes = new ArrayList<T>(whiteParents.keySet());
    	 
     	return whiteNodes;
    	
    }

    
    /**
     * @requires parentLabel !=null
     * @throws exception 
     * @return list of the children of parentLabel
     */
    public List<T> listChildren(T parentLabel) {
    	if (blackParents.containsKey(parentLabel))
    	{
    		List<T> children = new ArrayList<T>(blackParents.get(parentLabel).values());// TODO: check if collection can be casted to list or change return type
    		return children;
    	}
    	else 	if (whiteParents.containsKey(parentLabel))
    	{
    		List<T> children = new ArrayList<T>(whiteParents.get(parentLabel).values());// TODO: check if collection can be casted to list or change return type
    		return children;
    	}
    	else
    		return null;

    }

    
    /**
     * @requires childLabel !=null
     * @throws exeption
     * @return  list  of the parents of
     * 		   childLabel in the graph.
     */
    public List<T> listParents(T childLabel) {
    	if (children.containsKey(childLabel))
    	{
    		List<T> parents = new ArrayList<T>(children.get(childLabel).values());// TODO: check if collection can be casted to list or change return type
    		return parents;
    	}
    	return null;
    	
    }

    
    /**
     * @requires parentLabel && edgeLabel !=null
     * @throws exeption
     * @return the Label of the child of parentLabel that is connected by the
     * 		   edge labeled edgeLabel
     */
    public T getChildByEdgeLabel(T parentLabel,T edgeLabel) {
    	T child=whiteParents.get(parentLabel).get(edgeLabel);
    	if (child != null) {
    		return child;
    		
    	}
    	else {
    		child=blackParents.get(parentLabel).get(edgeLabel);
    		if (child != null) {
    			return child;
    		}
    	} 
    	
    	return null;/// TODO: throw exception not found
    	
    }

    
    /**
     * @requires childLabel && edgeLabel !=null
     * @throws exeption
     * @return the Label of the parent of childLabel that is connected by the
     * 		   edge labeled edgeLabel
     */
    public T getParentByEdgeLabel(T childLabel,T edgeLabel) {
    	//TODO: throw exception
    	return children.get(childLabel).get(edgeLabel);
    	
    }
}