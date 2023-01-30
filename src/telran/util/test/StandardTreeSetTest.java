package telran.util.test;

import org.junit.jupiter.api.BeforeEach;

import telran.util.StandardTreeSet;
import telran.util.Sorted;

public class StandardTreeSetTest extends SortedTest {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new StandardTreeSet<Integer>();
		super.setUp();
	}

	@Override
	protected Sorted<Integer> getSortedCollection() {

		return new StandardTreeSet<Integer>();

	}
}
