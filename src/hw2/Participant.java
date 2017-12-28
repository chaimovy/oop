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
	public void simulate(BipartiteGraph<String,Node<String,Transaction>> graph) 
	{
		List<String> children;
		try {
			children = graph.listChildren(getLabel());
			while(!graph.getNodeMap().get(getLabel()).getOutBuffer().isEmpty())
			{
				Transaction tx=graph.getNodeMap().get(getLabel()).getOutBuffer().poll();
				for (String pipe : children) {
					if (tx != null)
						graph.getNodeMap().get(pipe).addTransaction(tx);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
