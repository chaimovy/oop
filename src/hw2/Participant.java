package hw2;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Participant extends Node<String,Transaction>{
	private double fee;
	private Queue<Transaction> outBuffer;
	public Participant(String label, double fee) {
		super(label);
		this.fee = fee;
		outBuffer = new LinkedBlockingQueue<Transaction>();
	}

	public Boolean addTransaction(Transaction tx)
	{
		if(tx.getDest().equals(this.getLabel()))
		{
			buffer.add(tx);
		}
		else
		{
			buffer.add(new Transaction(this.getLabel(), fee));
			outBuffer.add(new Transaction(tx.getDest(), tx.getValue() - fee));
		}
		return true;
	}
	
	
	@Override
	public List<String> simulate(BipartiteGraph<String> graph) throws Exception 
	{
		return graph.listChildren(getLabel());
			
	}



}
