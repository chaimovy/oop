package hw2;

import java.util.List;

public class Participant extends Node<String,Transaction>{
	private double fee;
	/**
     * @requires label!=null, fee>0
     * @modifies this
     * @effects Constracts new Participant. 
     */
	public Participant(String label, double fee) {
		super(label);
		this.fee = fee;
	}
	/**
     * @requires tx!=null
     * @modifies this.buffer, this.outBuffer
     * @effects Add transaction to current transaction's buffer. if fee is too high, desposes of it.
     * @return true if transaction succeeded, false otherwise
     */
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
	
	/**
     * @requires graph!=null
     * @modifies current participant and it's children (channels)
     * @effects Executes all transactions in participant's buffer.
     */
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
						if(!graph.getNodeMap().get(pipe).addTransaction(tx))
							graph.getNodeMap().get(getLabel()).addTransaction(tx);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
