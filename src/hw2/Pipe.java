package hw2;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Pipe<T,V> extends Node<T> implements Simulatable<T>{
	protected Queue<V> buffer;
	
	public Pipe(T label) {
		super(label);
		buffer = new LinkedBlockingQueue<V>();
	}
	public Pipe(Node<T> node) {
		super(node.getLabel());
		buffer = new LinkedBlockingQueue<V>();
	}
	
	@Override
	public abstract void simulate(BipartiteGraph<T> graph);

	public abstract Boolean addTransaction(V tx);

	public abstract Queue<V> getBuffer();
}
