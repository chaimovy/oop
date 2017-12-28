package hw2;

import java.util.List;

public class Participant extends Node<String,Transaction>{
	private double fee;
	public Participant(String label, double fee) {
		super(label);
		this.fee = fee;
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
			if(tx.getValue() - fee > 0)
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
