package hw2;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public  class Node<T,V>  {
	protected Queue<V> buffer;
	protected Queue<V> outBuffer;
	private T label;
	public Node(T label) {
		this.label=label;
		buffer = new LinkedBlockingQueue<V>();
		outBuffer = new LinkedBlockingQueue<V>();
	}
	


	public List<T> simulate(BipartiteGraph<T> graph) throws Exception {
		return null;
	}

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
