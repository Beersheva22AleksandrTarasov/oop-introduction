package telran.memory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MemoryOperationsTest {
	byte ar[];

	@Test
	void maxMemoryTest() {
		int maxMemory = MemoryOperations.getMaxAvaibleMemory();
		ar = new byte[maxMemory];
		ar = null;
		boolean flException = false;
		try {
			ar = new byte[maxMemory + 1];
			
		}catch(Throwable e) {
			flException = true;
		}
		assertTrue(flException);
	}
}
