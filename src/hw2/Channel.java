package hw2;

import java.util.List;
import java.util.Queue;

public class Channel extends Node<String,Transaction>{
	private double limit;
	private double balance;
	
	public Channel(String label, double limit) {
		super(label);
		this.limit = limit;
		this.balance = 0;	
		
	}
	
	@Override
	public Boolean addTransaction(Transaction tx)
	{
		if(balance + tx.getValue() <= limit)
		{
			buffer.add(tx);
			balance += tx.getValue();
			return true;
		}
		return false;
	}
	
	@Override
	public Queue<Transaction> getBuffer() {
		return buffer;
	}
	
	@Override
	public List<String> simulate(BipartiteGraph<String> graph) throws Exception 
	{
		return graph.listChildren(getLabel());
			
	}
}
