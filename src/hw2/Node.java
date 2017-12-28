package hw2;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Node<T,V> implements Simulatable<T,Node<T,V>> {
	protected Queue<V> buffer;
	protected Queue<V> outBuffer;
	private T label;
	public Node(T label) {
		this.label=label;
		buffer = new LinkedBlockingQueue<V>();
		outBuffer = new LinkedBlockingQueue<V>();
	}
	@Override
	public void simulate(BipartiteGraph<T, Node<T,V>> graph) {}

	public  Boolean addTransaction(V tx) {
		if (buffer.add(tx))
			return true;
		return false;
	}

	public  Queue<V> getBuffer() {
		return buffer;
	}
	public  Queue<V> getOutBuffer() {
		return outBuffer;
	}

	public T getLabel() {
		return label;
	}
}
