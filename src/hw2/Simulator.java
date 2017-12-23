package hw2;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


public class Simulator<T, V> implements Simulatable<T> {
	private Map<T, Queue<Transaction>> jobs;
	
	public Simulator() {
		jobs = new HashMap<T, Queue<Transaction>>();
	}
	
	@Override
	public void simulate(BipartiteGraph<T> graph) {
		// TODO Auto-generated method stub
		T finalDest;
		T nextDest = null;
		
		for(T pipe : graph.listBlackNodes()) // we assume transaction is heading to proper destination.
		{
			Transaction tx = jobs.get(pipe).poll();
			finalDest = (T)tx.getDest();
			try {
				nextDest = graph.getNextDest(pipe, finalDest);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if(nextDest != null)
					sendTransaction(nextDest, tx);
			}
		}
		for(T filter : graph.listWhiteNodes())
		{
			Transaction tx = jobs.get(filter).poll();
			finalDest = (T)tx.getDest();
			try {
				nextDest = graph.getNextDest(filter, finalDest);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if(nextDest != null)
					sendTransaction(nextDest, tx);
			}
		}
	}
	
	public void sendTransaction(T node, Transaction tx) {//TODO: check illegal path
		if(!jobs.containsKey(node))
		{
			jobs.put(node, new LinkedBlockingQueue<Transaction>());
		}
		jobs.get(node).add(tx);
	}
}
