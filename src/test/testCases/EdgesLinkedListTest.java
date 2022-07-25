package nz.ac.auckland.softeng281.a4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EdgesLinkedListTest {

	EdgesLinkedList list;

	@Before
	public void setUp() {
		list = new EdgesLinkedList();
	}

	// EDGE GET IS TESTED WHEN TESTING OTHER METHOD LIKE PREPEND AND APPEND
	@Test
	public void testPrependEmptyList() {
		list.prepend(new Edge(new Node("1"), new Node("2"), 1));
		assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
	}

	@Test
	public void testPrependNonEmptyList() {
		list.prepend(new Edge(new Node("1"), new Node("2"), 1));
		list.prepend(new Edge(new Node("2"), new Node("3"), 4));
		assertEquals(new Edge(new Node("2"), new Node("3"), 4), list.get(0));
		assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(1));
	}

	@Test
	public void testPrependLargerList() {
		list.prepend(new Edge(new Node("5"), new Node("2"), 9));
		list.prepend(new Edge(new Node("1"), new Node("2"), 1));
		list.prepend(new Edge(new Node("2"), new Node("1"), 3));
		list.prepend(new Edge(new Node("2"), new Node("3"), 4));
		assertEquals(new Edge(new Node("2"), new Node("3"), 4), list.get(0));
		assertEquals(new Edge(new Node("2"), new Node("1"), 3), list.get(1));
		assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(2));
		assertEquals(new Edge(new Node("5"), new Node("2"), 9), list.get(3));
	}

	@Test
	public void testSizeOneEdge() {
		list.prepend(new Edge(new Node("1"), new Node("2"), 1));
		assertEquals(1, list.size());
	}

	@Test
	public void testSizeTwoEdges() {
		list.prepend(new Edge(new Node("1"), new Node("2"), 1));
		list.prepend(new Edge(new Node("2"), new Node("3"), 4));
		assertEquals(2, list.size());
	}

	@Test
	public void testSizeThreeEdges() {
		list.prepend(new Edge(new Node("1"), new Node("2"), 1));
		list.prepend(new Edge(new Node("2"), new Node("3"), 4));
		list.prepend(new Edge(new Node("3"), new Node("4"), 5));
		assertEquals(3, list.size());
	}

	@Test
	public void testSizeIncorrectAmount() {
		list.prepend(new Edge(new Node("1"), new Node("2"), 1));
		list.prepend(new Edge(new Node("2"), new Node("3"), 4));
		list.prepend(new Edge(new Node("3"), new Node("4"), 5));
		assertNotEquals(2, list.size());
	}
	// note get method gets tested with prepend and other methods

	@Test
	public void testAppendEmptyList() {
		list.append(new Edge(new Node("1"), new Node("2"), 1));
		assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
	}

	@Test
	public void testAppendNonEmptyList() {
		list.append(new Edge(new Node("1"), new Node("2"), 1));
		list.append(new Edge(new Node("2"), new Node("3"), 1));
		list.append(new Edge(new Node("3"), new Node("4"), 4));
		assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
		assertEquals(new Edge(new Node("2"), new Node("3"), 1), list.get(1));
		assertEquals(new Edge(new Node("3"), new Node("4"), 4), list.get(2));
	}

	@Test
	public void testAppendAndPrepend1() {
		list.append(new Edge(new Node("1"), new Node("2"), 1));
		list.prepend(new Edge(new Node("2"), new Node("3"), 1));
		list.append(new Edge(new Node("3"), new Node("4"), 4));
		assertEquals(new Edge(new Node("2"), new Node("3"), 1), list.get(0));
		assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(1));
		assertEquals(new Edge(new Node("3"), new Node("4"), 4), list.get(2));
	}

	@Test
	public void testInsert1() {
		list.append(new Edge(new Node("1"), new Node("2"), 1));
		list.prepend(new Edge(new Node("2"), new Node("3"), 1));
		list.insert(1, (new Edge(new Node("3"), new Node("4"), 4)));
		assertEquals(new Edge(new Node("2"), new Node("3"), 1), list.get(0));
		assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(2));
		assertEquals(new Edge(new Node("3"), new Node("4"), 4), list.get(1));

	}

	// test inserting to front of non empty list
	@Test
	public void testInsertFrontNonEmpty() {
		list.append(new Edge(new Node("1"), new Node("2"), 1));
		list.prepend(new Edge(new Node("2"), new Node("3"), 1));
		list.insert(0, (new Edge(new Node("3"), new Node("4"), 4)));
		assertEquals(new Edge(new Node("2"), new Node("3"), 1), list.get(1));
		assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(2));
		assertEquals(new Edge(new Node("3"), new Node("4"), 4), list.get(0));

	}

	// test inserting at front and end
	@Test(expected = InvalidPositionException.class)
	public void testInsertFrontEmpty() {
		list.insert(0, (new Edge(new Node("3"), new Node("4"), 4)));
	}

	@Test(expected = InvalidPositionException.class)
	public void testInsertEnd() {
		list.append(new Edge(new Node("1"), new Node("2"), 1));
		list.insert(1, (new Edge(new Node("3"), new Node("4"), 4)));

	}

	@Test
	public void testRemoveFront() {
		list.append(new Edge(new Node("1"), new Node("2"), 1));
		list.prepend(new Edge(new Node("2"), new Node("3"), 1));
		list.insert(1, (new Edge(new Node("3"), new Node("4"), 4)));
		list.remove(0);
		assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(1));
		assertEquals(new Edge(new Node("3"), new Node("4"), 4), list.get(0));

	}

	@Test
	public void testRemoveBack() {
		list.append(new Edge(new Node("1"), new Node("2"), 1));
		list.prepend(new Edge(new Node("2"), new Node("3"), 1));
		list.insert(1, (new Edge(new Node("3"), new Node("4"), 4)));
		list.remove(2);
		assertEquals(new Edge(new Node("2"), new Node("3"), 1), list.get(0));
		assertEquals(new Edge(new Node("3"), new Node("4"), 4), list.get(1));

	}

	@Test
	public void testRemoveMid() {
		list.append(new Edge(new Node("1"), new Node("2"), 1));
		list.prepend(new Edge(new Node("2"), new Node("3"), 1));
		list.insert(1, (new Edge(new Node("3"), new Node("4"), 4)));
		list.remove(1);
		assertEquals(new Edge(new Node("2"), new Node("3"), 1), list.get(0));
		assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(1));
	}

	@Test(expected = InvalidPositionException.class)
	public void EmptyRemove() {
		list.remove(0);
	}

}