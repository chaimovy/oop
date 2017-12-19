package hw2;

public class BipartiteGraph {
	
    /**
     * @modifies this
     * @effects Creates a new graph named graphName. The graph is initially
     * 			empty.
     */
    public void createGraph() {
        // TODO: Implement this method
        
    	
    }

    /**
     * @requires nodeLabel != null
     * @modifies this
     * @effects Adds a black node represented by the object nodeName to this.
     */
    public void addBlackNode(Object nodeLabel) {
    	// TODO: Implement this method
    	
    	
    }

    
    /**
     * @requires nodeLabel != null
     * @modifies this
     * @effects Adds a white node represented by the object nodeLable to this.
     */
    public void addWhiteNode(Object nodeLabel) {
    	// TODO: Implement this method
    	
    	
    }

    
    /**
     * @requires parentLabel && childLabel && edgeLabel !=null
     * @modifies this
     * @effects Adds an edge from the node parentName to the node childName
     * 			in the graph graphName. The new edge's label is the String
     * 			edgeLabel.
     */
    public void addEdge(Object parentLabel, Object childLabel, Object edgeLabel) {
    	//TODO: Implement this method
    	
    	
    }

    
    /**
     * @return a space-separated list of all the black nodes
     */
    public Object listBlackNodes() {
    	//TODO: Implement this method
    	
    	
    }

    
    /**
     * @return a space-separated list of all the white nodes
     */
    public Object listWhiteNodes() {
    	//TODO: Implement this method
    	
    	
    }

    
    /**
     * @requires parentLabel !=null
     * @throws exception 
     * @return a space-separated list of the children of parentLabel
     */
    public Object listChildren(Object parentLabel) {
    	//TODO: Implement this method
    	
    	
    }

    
    /**
     * @requires childLabel
     * @return a space-separated list of the names of the parents of
     * 		   childName in the graph graphName, in alphabetical order.
     */
    public String listParents(Object childLabel) {
    	//TODO: Implement this method
    	
    	
    }

    
    /**
     * @requires addEdge(graphName, parentName, str, edgeLabel) for some
     * 			 string str
     * @return the name of the child of parentName that is connected by the
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public String getChildByEdgeLabel(String graphName, String parentName,
    								   String edgeLabel) {
    	//TODO: Implement this method
    	
    	
    }

    
    /**
     * @requires addEdge(graphName, str, childName, edgeLabel) for some
     * 			 string str
     * @return the name of the parent of childName that is connected by the 
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public String getParentByEdgeLabel(String graphName, String childName,
    									String edgeLabel) {
    	//TODO: Implement this method
    	
    	
    }
}

}
