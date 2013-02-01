import java.util.LinkedList;

public class Queue {
	private boolean isEmpty = true;
	private int length = 0;
	private LinkedList<Integer> elements = new LinkedList<Integer>();

	public void insert(int anInteger) {
		elements.addLast(anInteger);
		length++;
		isEmpty = false;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public int length() {
		return length;
	}

	public int pop() throws Exception {
		if (isEmpty()) {
			throw new Exception("The queue is empty.");
		}
		length--;
		int queueTip = elements.getFirst();
		elements.removeFirst();
		return queueTip;
	}
}
