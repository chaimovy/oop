package hw2;

import static org.junit.Assert.*;
import org.junit.Test;


/**
 * BipartiteGraphTest contains JUnit block-box unit tests for BipartiteGraph.
 */
public class BipartiteGraphTest {

	@Test
    public void testExample() throws Exception {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        
        //create a graph
        driver.createGraph("graph1");
        
        //add a pair of nodes
        driver.addBlackNode("graph1", "n1");
        driver.addWhiteNode("graph1", "n2");
        
        //add an edge
        driver.addEdge("graph1", "n1", "n2", "edge");
        
        //check neighbors
        assertEquals("wrong black nodes", "n1", driver.listBlackNodes("graph1"));
        assertEquals("wrong white nodes", "n2", driver.listWhiteNodes("graph1"));
        assertEquals("wrong children", "n2", driver.listChildren ("graph1", "n1"));
        assertEquals("wrong children", "", driver.listChildren ("graph1", "n2"));
        assertEquals("wrong parents", "", driver.listParents ("graph1", "n1"));
        assertEquals("wrong parents", "n1", driver.listParents ("graph1", "n2"));
    }
    
    //  TODO: Add black-box tests
    
	@Test
	public void test1() throws Exception {
		BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        
        //create a graph
        driver.createGraph("graph2");
        
        //add a pair of nodes
        driver.addBlackNode("graph2", "A");
        driver.addWhiteNode("graph2", "B");
        driver.addWhiteNode("graph2", "C");
        driver.addBlackNode("graph2", "D");
        
        //add an edge
        driver.addEdge("graph2", "A", "B", "x");
        driver.addEdge("graph2", "B", "A", "y");
        driver.addEdge("graph2", "B", "D", "x");
        driver.addEdge("graph2", "C", "D", "z");
        
        //add problems
        driver.addEdge("graph2", "A", "C", "y");
        
        //check neighbors
        assertEquals("wrong black nodes", "A D", driver.listBlackNodes("graph2"));
        assertEquals("wrong white nodes", "B C", driver.listWhiteNodes("graph2"));
        assertEquals("wrong children", "A D", driver.listChildren ("graph2", "B"));
        assertEquals("wrong children", "B", driver.listChildren ("graph2", "A"));
        assertEquals("wrong children", "D", driver.listChildren ("graph2", "C"));
        assertEquals("wrong children", "", driver.listChildren ("graph2", "D"));
        assertEquals("wrong parents", "B", driver.listParents ("graph2", "A"));
        assertEquals("wrong parents", "A", driver.listParents ("graph2", "B"));
        assertEquals("wrong parents", "", driver.listParents ("graph2", "C"));
        assertEquals("wrong parents", "B C", driver.listParents ("graph2", "D"));
	}
}
