package nz.ac.auckland.softeng281.a4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class NodesStackAndQueueTest {

	NodesStackAndQueue stack;

	@Before
	public void setUp() {
		stack = new NodesStackAndQueue();
	}

	@Test
	public void isEmptyEmptyStack() {
		assertTrue(stack.isEmpty());
	}

	@Test
	public void isEmptyNotEmpty() {
		stack.append(new Node("4"));
		assertFalse(stack.isEmpty());
	}

	@Test
	public void testPush1() {
		stack.push(new Node("2"));
		assertFalse(stack.isEmpty());
		assertEquals(0, stack.getFront());
	}

	@Test
	public void testPush2() {
		stack.push(new Node("2"));
		stack.push(new Node("3"));
		stack.push(new Node("4"));
		assertFalse(stack.isEmpty());
		assertEquals(2, stack.getFront());
	}

	@Test
	public void testPeek1() {
		stack.push(new Node("2"));
		assertFalse(stack.isEmpty());
		assertEquals(new Node("2"), stack.peek());
	}

	@Test
	public void testPeek2() {
		stack.push(new Node("2"));
		stack.push(new Node("3"));
		stack.push(new Node("4"));
		assertFalse(stack.isEmpty());
		assertEquals(new Node("4"), stack.peek());
	}

	@Test
	public void testAppend1() {
		stack.push(new Node("2"));
		stack.push(new Node("3"));
		stack.append(new Node("4"));
		assertFalse(stack.isEmpty());
		assertEquals(new Node("3"), stack.peek());
		assertEquals(new Node("4"), stack.peekBack());
	}

	@Test
	public void testAppend2() {
		stack.push(new Node("2"));
		stack.push(new Node("3"));
		stack.append(new Node("4"));
		stack.push(new Node("5"));
		stack.append(new Node("7"));
		assertFalse(stack.isEmpty());
		assertEquals(new Node("5"), stack.peek());
		assertEquals(new Node("7"), stack.peekBack());
	}

	@Test
	public void testPop1() {
		stack.push(new Node("2"));
		assertFalse(stack.isEmpty());
		assertEquals(new Node("2"), stack.pop());
		assertTrue(stack.isEmpty());
	}

	@Test
	public void testPop2() {
		stack.push(new Node("2"));
		stack.push(new Node("3"));
		stack.push(new Node("4"));
		assertFalse(stack.isEmpty());
		assertEquals(new Node("4"), stack.pop());
		assertEquals(new Node("3"), stack.peek());
		assertEquals(new Node("3"), stack.pop());
		assertEquals(new Node("2"), stack.peek());

	}

	@Test(expected = EmptyStackException.class)

	public void testPopEmpty() {
		stack.push(new Node("2"));
		assertFalse(stack.isEmpty());
		assertEquals(new Node("2"), stack.pop());
		assertTrue(stack.isEmpty());
		stack.pop();
	}

	@Test(expected = EmptyStackException.class)

	public void testPeekEmpty() {
		stack.push(new Node("2"));
		assertFalse(stack.isEmpty());
		assertEquals(new Node("2"), stack.pop());
		assertTrue(stack.isEmpty());
		stack.peek();
	}

	@Test

	public void testPeekBack1() {
		stack.push(new Node("2"));
		assertFalse(stack.isEmpty());
		assertEquals(new Node("2"), stack.peekBack());
	}

	@Test
	public void testPeekBack2() {
		stack.push(new Node("2"));
		stack.push(new Node("3"));
		stack.push(new Node("4"));
		stack.append(new Node("7"));
		assertFalse(stack.isEmpty());
		assertEquals(new Node("7"), stack.peekBack());
	}

	@Test(expected = EmptyStackException.class)

	public void testPeekBackEmpty() {
		stack.push(new Node("2"));
		assertFalse(stack.isEmpty());
		assertEquals(new Node("2"), stack.pop());
		assertTrue(stack.isEmpty());
		stack.peek();
	}

	@Test
	public void testdequeue1() {
		stack.push(new Node("2"));
		assertFalse(stack.isEmpty());
		assertEquals(new Node("2"), stack.dequeue());
		assertTrue(stack.isEmpty());
	}

	@Test
	public void testDequeue2() {
		stack.push(new Node("2"));
		stack.push(new Node("3"));
		stack.push(new Node("4"));
		assertFalse(stack.isEmpty());
		assertEquals(new Node("2"), stack.dequeue());
		assertEquals(new Node("3"), stack.peekBack());
		assertEquals(new Node("3"), stack.dequeue());
		assertEquals(new Node("4"), stack.peekBack());

	}
}
