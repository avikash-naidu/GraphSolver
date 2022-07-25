package nz.ac.auckland.softeng281.a4;

import java.util.ArrayList;

public class NodesStackAndQueue {

	private ArrayList<Node> stack = new ArrayList<Node>();
	private int front;
	private int rear;

	public NodesStackAndQueue() {
		// initialises variables
		front = 0;
		rear = 0;

	}

	public boolean isEmpty() {
		// checks if stack is empty
		if (stack.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Push operation refers to inserting an element on the Top of the stack.
	 *
	 * @param node
	 */
	public void push(Node node) {
		// pushes node and adjusts front accordingly
		stack.add(node);
		front = stack.size() - 1;
	}

	/**
	 * pop an element from the top of the stack (removes and returns the top
	 * element)
	 *
	 * @return
	 */
	public Node pop() {
		// pop topmost node and adjusts front accordingly

		// throws error if stack empty
		if (this.isEmpty()) {
			throw new EmptyStackException("You cannot pop a NodeStackAndQueue of size 0");
		}
		Node toPop = stack.get(front);
		stack.remove(front);
		front--;
		return toPop;
	}

	/**
	 * get the element from the top of the stack without removing it
	 *
	 * @return
	 */
	public Node peek() {
		// peeks at topmost node and returns node

		// throws error if stack empty
		if (this.isEmpty()) {
			throw new EmptyStackException("You cannot peek a NodeStackAndQueue of size 0");
		}
		return stack.get(front);
	}

	/**
	 * append an element at the end of the stack
	 *
	 * @param node
	 */
	public void append(Node node) {
		// adds node to bottom of stack
		int i;
		// add the node to end so that set method has the right number of index
		// positions to use
		stack.add(node);
		// loop through to increase index position of each node in list by 1 so index 0
		// is empty to append
		if (!this.isEmpty()) {
			for (i = (stack.size() - 1); i >= 1; i--) {
				stack.set(i, stack.get((i - 1)));
			}
		}
		stack.set(0, node);
		front = stack.size() - 1;
	}

	/**
	 * return the value of front of the stack
	 **/

	// methods to get values for front
	public int getFront() {
		return this.front;
	}

	// method to get rear most value for error checking
	public Node peekBack() {
		if (this.isEmpty()) {
			throw new EmptyStackException("You cannot peekBack a NodeStackAndQueue of size 0");
		}
		return stack.get(rear);
	}

	public Node dequeue() {
		// pop but for the bottom of stack/front of queue
		if (this.isEmpty()) {
			throw new EmptyStackException("You cannot dequeue from a NodeStackAndQueue of size 0");
		}
		Node toDequeue = stack.get(rear);
		stack.remove(rear);
		return toDequeue;
	}

	public int size() {
		return stack.size();
	}
}
