package hw2;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Filter<T,V> extends Node<T> implements Simulatable<T>{
	protected Queue<Transaction> storageBuffer;
	protected Queue<Transaction> outBuffer;
	
	public Filter(T label) {
		super(label);
		storageBuffer = new LinkedBlockingQueue<Transaction>();
		outBuffer = new LinkedBlockingQueue<Transaction>();	
	}
	
	public Filter(Node<T> node) {
		super(node.getLabel());
		storageBuffer = new LinkedBlockingQueue<Transaction>();
		outBuffer = new LinkedBlockingQueue<Transaction>();	
	}

	@Override
	public abstract void simulate(BipartiteGraph<T> graph);

	public abstract void addTransaction(V tx);

	public abstract Queue<V> getBuffer();
	
	

}
