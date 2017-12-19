package hw2;

public class Node<T> {
	private T Label;
	private Boolean isWhite;
	public Node(T Label, Boolean isWhite) {
		this.setLabel(Label);
		this.setIsWhite(isWhite);
		
		
	}
	public Boolean getIsWhite() {
		return isWhite;
	}
	public void setIsWhite(Boolean isWhite) {
		this.isWhite = isWhite;
	}
	public T getLabel() {
		return Label;
	}
	public void setLabel(T label) {
		Label = label;
	}
	
	

}
