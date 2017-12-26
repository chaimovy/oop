package hw2;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Participant extends Filter<String,Transaction>{
	private double fee;
	private ArrayList<Transaction> storageBuffer;
	private Queue<Transaction> outBuffer;
	
	public Participant(String label, double fee) {
		super(label);
		this.fee = fee;
		storageBuffer = new ArrayList<Transaction>();
		outBuffer = new LinkedBlockingQueue<Transaction>();
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
	
	public double getBalance()
	{
		double balance=0;
		for(Transaction curr : storageBuffer)
			balance += curr.getValue();
		return balance;
	}
	
	@Override
	public void simulate(BipartiteGraph<String> graph) {
		for(Node<String> p : getChildren().values()) // assume only 1
			while(outBuffer.size() != 0)
				((Channel)p).addTransaction(outBuffer.poll());
	}

}
