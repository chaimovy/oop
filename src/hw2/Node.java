package hw2;

import java.util.HashMap;
import java.util.Map;

public class Node<T> {
	private T label;
	private Map<T, Node<T>> parents;
	private Map<T, Node<T>> children;
	
	public Node(T label)
	{
		this.label = label;
		parents = new HashMap<T, Node<T>>(); // key = edge label. value = node. nodes contains label inside.
		children = new HashMap<T, Node<T>>();
	}
	
	public T getLabel()
	{
		return label;
	}
	
	public Map<T, Node<T>> getParents()
	{
		return parents;
	}
	
	public Map<T, Node<T>> getChildren()
	{
		return children;
	}
	
	public void addChild(T edgeLabel, T nodeLabel)
	{
		children.put(edgeLabel, new Node<T>(nodeLabel));
	}
	
	public void addParent(T edgeLabel, T nodeLabel)
	{
		parents.put(edgeLabel, new Node<T>(nodeLabel));
	}
	


}
