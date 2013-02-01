import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestQueue {
	public static int A_NUMBER_OF_TIMES = 8;
	public static int AN_INTEGER = 5;
	public static int A_SECOND_INTEGER = 7;
	private Queue queue;

	@Before
	public void instanciateQueue() {
		queue = new Queue();
	}

	@Test
	public void initialQueueIsEmpty() {
		assertTrue(queue.isEmpty());
	}

	@Test
	public void queueIsNotEmptyAfterInsertingAnElement() {
		queue.insert(AN_INTEGER);
		assertFalse(queue.isEmpty());
	}

	@Test
	public void initialQueueHasLengthZero() {
		assertTrue(queue.length() == 0);
	}

	@Test
	public void queueLengthIncreasesWithInserts() {
		for (int i = 0; i < A_NUMBER_OF_TIMES; i++) {
			queue.insert(AN_INTEGER);
		}
		assert (queue.length() == A_NUMBER_OF_TIMES);
	}

	@Test
	public void queueLengthDecreasesWhenDequeueing() throws Exception {
		for (int i = 0; i < A_NUMBER_OF_TIMES; i++) {
			queue.insert(AN_INTEGER);
			queue.pop();
		}
		assertTrue(queue.length() == 0);
	}

	@Test
	public void initialQueueCannotDequeueElements() {
		if (queue.isEmpty()) {
			try {
				queue.pop();
				fail("Succeded dequeueing an empty list");
			} catch (Exception e) {

			}
		}
	}

	@Test
	public void dequeueingReturnsFirstInsertedElement() throws Exception {
		queue.insert(AN_INTEGER);
		queue.insert(A_SECOND_INTEGER);
		int actualElement = queue.pop();
		assertTrue(actualElement == AN_INTEGER);
	}

	@Test
	public void dequeueingTwiceReturnsSecondAddedElement() throws Exception {
		queue.insert(AN_INTEGER);
		queue.insert(A_SECOND_INTEGER);
		int firstPoppedElement = queue.pop();
		int secondPoppedElement = queue.pop();
		assertTrue(secondPoppedElement == A_SECOND_INTEGER);
	}

}
