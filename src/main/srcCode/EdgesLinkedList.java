package nz.ac.auckland.softeng281.a4;

/**
 * The Linked List Class has only one head pointer to the start edge (head)
 * Edges are indexed starting from 0. The list goes from 0 to size-1. Note that
 * the List does not have a maximum size.
 *
 * @author Partha Roop
 */
public class EdgesLinkedList {
	// the head of the linked list
	private Edge head;

	public EdgesLinkedList() {
		head = null;
	}

	/**
	 * This method adds an edge as the start edge of the list
	 *
	 * @param edge to prepend
	 */
	public void prepend(Edge edge) {
		// adds a edge at the start of the list
		edge.setNext(this.head);
		head = edge;
	}

	/**
	 * This method adds an edge as the end edge of the list
	 *
	 * @param edge to append
	 */
	public void append(Edge edge) {

		Edge bottom = head;
		// list empty case
		if (bottom == null) {
			head = edge;
			return;
		}
		// if list is not empty
		boolean nullMet = false;

		while (nullMet != true) {
			// test until next value is null that is when to stop changing bottom
			if (bottom.getNext() != null) {
				bottom = bottom.getNext();
			} else {
				nullMet = true;
			}
		}
		bottom.setNext(edge);
	}

	/**
	 * This method gets the edge at a given position
	 *
	 * @param pos: an integer, which is the position
	 * @return the Edge at the position pos
	 */
	public Edge get(int pos) throws InvalidPositionException {
		if (pos < 0 || pos > size() - 1) {
			throw new InvalidPositionException("Position " + pos + " outside the list boundary");
		}
		// counts for each value in the linked list
		int count = 0;
		Edge top = head;
		while (count != pos) {
			count++;
			top = top.getNext();
		}
		return top;
	}

	/**
	 * This method adds an edge at a given position in the List
	 *
	 * @param pos:  an integer, which is the position
	 * @param edge: the edge to add
	 * @throws InvalidPositionException
	 */
	public void insert(int pos, Edge edge) throws InvalidPositionException {
		if (pos < 0 || pos > size() - 1) {
			throw new InvalidPositionException("Position " + pos + " outside the list boundary");
		}
		// finds the correct position then inserts edge accordingly
		if (pos != 0) {
			Edge before = this.get(pos - 1);
			Edge after = this.get(pos);
			edge.setNext(after);
			before.setNext(edge);
		} else if (pos == 0) {
			edge.setNext(this.head);
			head = edge;
		}
	}

	/**
	 * This method removes an edge at a given position
	 *
	 * @param pos: an integer, which is the position
	 */
	public void remove(int pos) throws InvalidPositionException {
		if (pos < 0 || pos > size() - 1) {
			throw new InvalidPositionException("Position " + pos + " outside the list boundary");
		}
		// removes edge if possible by going through positions in linked list
		if (pos == size() - 1) {
			Edge before = this.get(pos - 1);
			before.setNext(null);
		} else if (pos == 0) {
			Edge after = this.get(pos + 1);
			head = after;
		} else if (pos != 0) {
			Edge before = this.get(pos - 1);
			Edge after = this.get(pos + 1);
			before.setNext(after);
		}

	}

	/**
	 * This method returns the size of the Linked list
	 *
	 * @return the size of the list
	 */

	public int size() {
		// counts number of nodes until null is reached
		int count = 0;
		Edge top = head;
		while (top != null) {
			count++;
			top = top.getNext();
		}
		return count;
	}

	/**
	 * This method is used for printing the data in the list from head till the last
	 * node
	 */
	public void print() {
		// output list
		Edge edge = head;
		while (edge != null) {
			System.out.println(edge);
			edge = edge.getNext();
		}
	}
}
