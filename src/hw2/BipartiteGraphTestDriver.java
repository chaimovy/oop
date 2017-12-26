package hw2;

import java.util.*;

/**
 * This class implements a testing driver for BipartiteGraph. The driver
 * manages BipartiteGraphs whose nodes and edges are Strings.
 */
public class BipartiteGraphTestDriver {

    private Map<String, BipartiteGraph<String>> graphs;

    /**
     * @modifies this
     * @effects Constructs a new test driver.
     */
    public BipartiteGraphTestDriver () {
    	graphs = new HashMap<String, BipartiteGraph<String>>();
    }

    
    /**
     * @requires graphName != null
     * @modifies this
     * @effects Creates a new graph named graphName. The graph is initially
     * 			empty.
     */
    public void createGraph(String graphName) {
        graphs.put(graphName, new BipartiteGraph<String>());
    }

    
    /**
     * @throws Exception 
     * @requires createGraph(graphName)
     *           && nodeName != null
     *           && neither addBlackNode(graphName,nodeName) 
     *                  nor addWhiteNode(graphName,nodeName)
     *                      has already been called on this
     * @modifies graph named graphName
     * @effects Adds a black node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addBlackNode(String graphName, String nodeName) throws Exception {
    	graphs.get(graphName).addBlackNode(nodeName);
    }

    
    /**
     * @throws Exception 
     * @requires createGraph(graphName)
     *           && nodeName != null
     *           && neither addBlackNode(graphName,nodeName) 
     *                  nor addWhiteNode(graphName,nodeName)
     *                      has already been called on this
     * @modifies graph named graphName
     * @effects Adds a white node represented by the String nodeName to the
     * 			graph named graphName.
     */
    public void addWhiteNode(String graphName, String nodeName) throws Exception {
    	graphs.get(graphName).addWhiteNode(nodeName);
    }

    
    /**
     * @throws Exception 
     * @requires createGraph(graphName)
     *           && ((addBlackNode(parentName) && addWhiteNode(childName))
     *              || (addWhiteNode(parentName) && addBlackNode(childName)))
     *           && edgeLabel != null
     *           && node named parentName has no other outgoing edge labeled
     * 				edgeLabel
     *           && node named childName has no other incoming edge labeled
     * 				edgeLabel
     * @modifies graph named graphName
     * @effects Adds an edge from the node parentName to the node childName
     * 			in the graph graphName. The new edge's label is the String
     * 			edgeLabel.
     */
    public void addEdge(String graphName,
    					String parentName, String childName, 
                        String edgeLabel) throws Exception {
    	graphs.get(graphName).addEdge(parentName,childName,edgeLabel);
    	
    }

    
    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the black nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listBlackNodes(String graphName) {
    	ArrayList<String> rawList = new ArrayList<String>();
    	for(Node<String> child : graphs.get(graphName).listBlackNodes())
    		rawList.add(child.getLabel());
    	Collections.sort(rawList.subList(0, rawList.size()));
    	String NodesList = "";
    	for (int i=0; i<rawList.size(); i++) {
    		if (i==rawList.size()-1)
    			NodesList += rawList.get(i);
    		else
    			NodesList += rawList.get(i) + " ";
		}
    	return NodesList;
    }

    
    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the white nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listWhiteNodes(String graphName) {
    	ArrayList<String> rawList = new ArrayList<String>();
    	for(Node<String> child : graphs.get(graphName).listWhiteNodes())
    		rawList.add(child.getLabel());
       	Collections.sort(rawList.subList(0, rawList.size()));
       	String NodesList = "";
    	for (int i=0; i<rawList.size(); i++) {
    		if (i==rawList.size()-1)
    			NodesList += rawList.get(i);
    		else
    			NodesList += rawList.get(i) + " ";
		}
    	return NodesList;
    }

    
    /**
     * @requires createGraph(graphName) && createNode(parentName)
     * @return a space-separated list of the names of the children of
     * 		   parentName in the graph graphName, in alphabetical order.
     * @throws Exception 
     */
    public String listChildren(String graphName, String parentName) throws Exception {
    	ArrayList<String> rawList = new ArrayList<String>();
    	for(Node<String> child : graphs.get(graphName).listChildren(parentName))
    		rawList.add(child.getLabel());
       	Collections.sort(rawList.subList(0, rawList.size()));
       	String NodesList = "";
    	for (int i=0; i<rawList.size(); i++) {
    		if (i==rawList.size()-1)
    			NodesList += rawList.get(i);
    		else
    			NodesList += rawList.get(i) + " ";
		}
    	return NodesList;
    }

    
    /**
     * @requires createGraph(graphName) && createNode(childName)
     * @return a space-separated list of the names of the parents of
     * 		   childName in the graph graphName, in alphabetical order.
     * @throws Exception 
     */
    public String listParents(String graphName, String childName) throws Exception {
    	ArrayList<String> rawList = new ArrayList<String>();
    	for(Node<String> parent : graphs.get(graphName).listParents(childName))
    		rawList.add(parent.getLabel());
    	Collections.sort(rawList.subList(0, rawList.size()));
    	String NodesList = "";
    	for (int i=0; i<rawList.size(); i++) {
    		if (i==rawList.size()-1)
    			NodesList += rawList.get(i);
    		else
    			NodesList += rawList.get(i) + " ";
		}
    	return NodesList;
    }

    
    /**
     * @requires addEdge(graphName, parentName, str, edgeLabel) for some
     * 			 string str
     * @return the name of the child of parentName that is connected by the
     * 		   edge labeled edgeLabel, in the graph graphName.
     * @throws Exception 
     */
    public String getChildByEdgeLabel(String graphName, String parentName,
    								   String edgeLabel) throws Exception {
    	return graphs.get(graphName).getChildByEdgeLabel(parentName, edgeLabel).getLabel();
    }

    
    /**
     * @requires addEdge(graphName, str, childName, edgeLabel) for some
     * 			 string str
     * @return the name of the parent of childName that is connected by the 
     * 		   edge labeled edgeLabel, in the graph graphName.
     * @throws Exception 
     */
    public String getParentByEdgeLabel(String graphName, String childName,
    									String edgeLabel) throws Exception {
    	return graphs.get(graphName).getParentByEdgeLabel(childName, edgeLabel).getLabel();
    }
}
