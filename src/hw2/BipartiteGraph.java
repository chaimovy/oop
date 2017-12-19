package hw2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BipartiteGraph<T> {
	private Map<Node<T>, Map<T,Node<T>>> parents;
	private Map<Node<T>, Map<T,Node<T>>> children;
    /**
     * @modifies this
     * @effects Creates a new graph named graphName. The graph is initially
     * 			empty.
     */
    public BipartiteGraph() {
        parents=new HashMap<Node<T>,Map<T,Node<T>>>();
        children=new HashMap<Node<T>,Map<T,Node<T>>>();
        
    	
    }

    /**
     * @requires nodeLabel != null
     * @throws exception
     * @modifies this.parents
     * @effects Adds a black node represented by the T nodeName to this.
     */
    public void addBlackNode(T nodeLabel) {
    	if (parents.containsKey(nodeLabel))
    	{
    		return; //todo: throw exception
    	}
    	parents.put(new Node<T>(nodeLabel,false), new HashMap<T,Node<T>>());
    	
    	
    }

    
    /**
     * @requires nodeLabel != null
     * @throws exception
     * @modifies this.parents
     * @effects Adds a white node represented by the T nodeLable to this.
     */
    public void addWhiteNode(T nodeLabel) {
    	if (parents.containsKey(nodeLabel))
    	{
    		return; //todo: throw exception
    	}
    	parents.put(new Node<T>(nodeLabel,true), new HashMap<T,Node<T>>());
    	
    	
    }

    
    /**
     * @requires parentLabel && childLabel && edgeLabel !=null
     * @modifies this.parents and this.children
     * @effects Adds an edge from the node parentName to the node childName
     * 			in the graph graphName. The new edge's label is the T
     * 			edgeLabel.
     */
    public void addEdge(T parentLabel, T childLabel, T edgeLabel) {
    	if ()
    	
    	
    }

    
    /**
     * @return a space-separated list of all the black nodes
     */
    public List<T> listBlackNodes() {
    	//TODO: Implement this method
    	return null;
    	
    }

    
    /**
     * @return a space-separated list of all the white nodes
     */
    public List<T> listWhiteNodes() {
    	//TODO: Implement this method
    	return null;
    	
    }

    
    /**
     * @requires parentLabel !=null
     * @throws exception 
     * @return a space-separated list of the children of parentLabel
     */
    public List<T> listChildren(T parentLabel) {
    	//TODO: Implement this method
    	return null;
    }

    
    /**
     * @requires childLabel !=null
     * @throws exeption
     * @return a space-separated list  of the parents of
     * 		   childLabel in the graph.
     */
    public List<T> listParents(T childLabel) {
    	//TODO: Implement this method
    	return null;
    	
    }

    
    /**
     * @requires parentLabel && edgeLabel !=null
     * @throws exeption
     * @return the Label of the child of parentLabel that is connected by the
     * 		   edge labeled edgeLabel
     */
    public T getChildByEdgeLabel(T parentName,T edgeLabel) {
    	//TODO: Implement this method
    	return null;
    	
    }

    
    /**
     * @requires childLabel && edgeLabel !=null
     * @throws exeption
     * @return the Label of the parent of childLabel that is connected by the
     * 		   edge labeled edgeLabel
     */
    public T getParentByEdgeLabel(T childLabel,T edgeLabel) {
    	//TODO: Implement this method
    	return null;
    	
    }
}