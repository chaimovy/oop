package hw2;

import java.util.List;
import java.util.Queue;

public class Channel extends Node<String,Transaction>{
	private double limit;
	private double balance;
	/**
     * @requires label!=null, limit>0
     * @modifies this
     * @effects Constracts new Channel. 
     */
	public Channel(String label, double limit) {
		super(label);
		this.limit = limit;
		this.balance = 0;
	}
	/**
     * @requires tx!=null
     * @modifies this.balance
     * @effects Add transaction to current channel's buffer.
     * @return true if transaction succeeded, false otherwise
     */
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
	/**
     * @modifies none
     * @return Channel's buffer
     */
	@Override
	public Queue<Transaction> getBuffer() {
		return buffer;
	}
	/**
     * @requires graph!=null
     * @modifies current channel and it's children (participants)
     * @effects Executes all transactions in channel's buffer.
     */
	@Override
	public void simulate(BipartiteGraph<String, Node<String,Transaction>> graph) 
	{
		List<String> children;
		try {
			children = graph.listChildren(getLabel());
			while(!graph.getNodeMap().get(getLabel()).getBuffer().isEmpty())
			{
				Transaction tx=graph.getNodeMap().get(getLabel()).getBuffer().poll();
				for (String filter : children) {
					if (tx != null)
						graph.getNodeMap().get(filter).addTransaction(tx);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
