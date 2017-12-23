package hw2;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


public class Simulator<T, Transaction> implements Simulatable<T> {
	private Map<T, Queue<Transaction>> jobs;
	
	public Simulator() {
		jobs = new HashMap<T, Queue<Transaction>>();
	}
	
	@Override
	public void simulate(BipartiteGraph<T> graph) {
		// TODO Auto-generated method stub
		for(T pipe : graph.listBlackNodes())
		{
			Transaction tx = jobs.get(pipe).poll();
		}
		for(T filter : graph.listWhiteNodes())
		{
			
		}
	}
	
	public void sendTransaction(T node, Transaction tx) {
		if(!jobs.containsKey(node))
		{
			jobs.put(node, new LinkedBlockingQueue<Transaction>());
		}
		jobs.get(node).add(tx);
	}
}
