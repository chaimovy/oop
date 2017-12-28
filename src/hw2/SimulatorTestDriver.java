package hw2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

/**
 * This class implements a testing driver for Simulator. The driver manages
 * Simulators for payment channels
 */
public class SimulatorTestDriver {

	private Map<String, Simulator<String, Transaction>> simulators;

	/**
	 * @modifies this
	 * @effects Constructs a new test driver.
	 */
	public SimulatorTestDriver() {
		simulators = new HashMap<String, Simulator<String, Transaction>>();
	}

	/**
	 * @throws Exception 
	 * @requires simName != null
	 * @modifies this
	 * @effects Creates a new simulator named simName. The simulator's graph is
	 *          initially empty.
	 */
	public void createSimulator(String simName) throws Exception {
		if(simulators.containsKey(simName))
			throw new Exception("Simulator with same name already exists");
		simulators.put(simName, new Simulator<String,Transaction>());
	}

	/**
	 * @throws Exception 
	 * @requires createSimulator(simName) 
     *           && channelName != null && channelName has
	 *           not been used in a previous addChannel()  or
	 *           addParticipant() call on this object
	 *           limit > 0
	 * @modifies simulator named simName
	 * @effects Creates a new Channel named by the String channelName, with a limit, and add it to
	 *          the simulator named simName.
	 */
	public void addChannel(String simName, String channelName, double limit) throws Exception {
	    simulators.get(simName).addPipe(channelName,new Channel(channelName,limit));
	}

	/**
	 * @throws Exception 
	 * @requires createSimulator(simName) && participantName != null 
	 *           && participantName has not been used in a previous addParticipant(), addChannel()
	 *           call on this object
	 *           fee > 0
	 * @modifies simulator named simName
	 * @effects Creates a new Participant named by the String participantName and add
	 *          it to the simulator named simName.
	 */
	public void addParticipant(String simName, String participantName, double fee) throws Exception {
		simulators.get(simName).addFilter(participantName,new Participant(participantName, fee));
	}

	/**
	 * @throws Exception 
	 * @requires createSimulator(simName) && ((addPipe(parentName) &&
	 *           addFilter(childName)) || (addFilter(parentName) &&
	 *           addPipe(childName))) && edgeLabel != null && node named
	 *           parentName has no other outgoing edge labeled edgeLabel 
	 *           && node named childName has no other incoming edge labeled edgeLabel
	 * @modifies simulator named simName
	 * @effects Adds an edge from the node named parentName to the node named
	 *          childName in the simulator named simName. The new edge's label
	 *          is the String edgeLabel.
	 */
	public void addEdge(String simName, String parentName, String childName, String edgeLabel) throws Exception {
		simulators.get(simName).addEdge(parentName, childName, edgeLabel);
	}

	/**
	 * @requires createSimulator(simName) && addChannel(channelName)
	 *           A transaction Transaction != null
	 * @modifies channel named channelName
	 * @effects pushes the Transaction into the channel named channelName in the
	 *          simulator named simName.
	 */
	public void sendTransaction(String simName, String channelName, Transaction tx) {
		simulators.get(simName).sendTransaction(channelName, tx);
    }
	
	
	/**
	 * @requires addChannel(channelName)
	 * @return a space-separated list of the Transaction values currently in the
	 *         channel named channelName in the simulator named simName.
	 */
	public String listContents(String simName, String channelName) {
		Queue<Transaction> contents = simulators.get(simName).listContents(channelName);
		String str = "";
		Iterator<Transaction> itr=contents.iterator();
    	while (itr.hasNext()) {
    		Transaction curTransaction=itr.next();
    		str += String.valueOf(curTransaction.getValue());
    		if(itr.hasNext())
    			str += " ";
		}
		return str;
	}

	/**
	 * @requires addParticipant(participantName)
	 * @return The sum of all  Transaction values stored in the storage of the participant participantName in the simulator simName
	 */
	public double getParticipantBalace(String simName, String participantName) {
        
		Queue<Transaction> buff = simulators.get(simName).listContents(participantName);
		double balance = 0;
		for(Transaction t : buff)
			balance += t.getValue();
		return balance;
	}
	
	/**
	 * @throws Exception 
	 * @requires createSimulator(simName)
	 * @modifies simulator named simName
	 * @effects runs simulator named simName for a single time slice.
	 */
	public void simulate(String simName){
		try {
			simulators.get(simName).simulate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prints the all edges.
	 *
	 * @requires simName the sim name
	 * @effects Prints the all edges.
	 */
	public void printAllEdges(String simName) {
		simulators.get(simName).getAllEdges();
	}

}
