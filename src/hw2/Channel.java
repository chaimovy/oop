package hw2;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Channel extends Pipe<String,Transaction>{
	private double limit;
	private double balance;
	
	public Channel(String label, double limit) {
		super(label);
		this.limit = limit;
		this.balance = 0;		
	}

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
	public void simulate(BipartiteGraph<String> graph) 
	{
		for(Node<String> p : getChildren().values())
			while(buffer.size() != 0)
				((Participant)p).addTransaction(buffer.poll());
	}
}
