package hw2;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Participant extends Filter<String,Transaction>{
	private double fee;
	
	public Participant(String label, double fee) {
		super(label);
		this.fee = fee;
	}

	public void addTransaction(Transaction tx)
	{
		if(tx.getDest().equals(this.getLabel()))
		{
			storageBuffer.add(tx);
		}
		else
		{
			storageBuffer.add(new Transaction(this.getLabel(), fee));
			outBuffer.add(new Transaction(tx.getDest(), tx.getValue() - fee));
		}
	}
	
	@Override
	public void simulate(BipartiteGraph<String> graph) {
		for(Node<String> p : getChildren().values()) // assume only 1
			while(outBuffer.size() != 0)
				((Channel)p).addTransaction(outBuffer.poll());
	}

	@Override
	public Queue<Transaction> getBuffer() {
		return storageBuffer;
	}
}
