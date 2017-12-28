package hw2;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Node<T,V> implements Simulatable<T,Node<T,V>> {
	protected Queue<V> buffer;
	protected Queue<V> outBuffer;
	private T label;
	/**
     * @requires label!=null
     * @modifies this
     * @effects Constracts new Node. 
     */
	public Node(T label) {
		this.label=label;
		buffer = new LinkedBlockingQueue<V>();
		outBuffer = new LinkedBlockingQueue<V>();
	}
	/**
     * @requires graph!=null
     * @modifies current node and it's children
     * @effects Executes all transactions in node's buffer.
     */
	@Override
	public void simulate(BipartiteGraph<T, Node<T,V>> graph) {}

	public  Boolean addTransaction(V tx) {
		if (buffer.add(tx))
			return true;
		return false;
	}
	/**
     * @modifies none
     * @return Node's buffer
     */
	public  Queue<V> getBuffer() {
		return buffer;
	}
	/**
     * @modifies none
     * @return Node's out buffer
     */
	public  Queue<V> getOutBuffer() {
		return outBuffer;
	}
	/**
     * @modifies none
     * @return Node's label
     */
	public T getLabel() {
		return label;
	}
}
