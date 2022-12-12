package telran.cipher.test;

import org.junit.jupiter.api.Test;

import telran.cipher.BaseCipher;

class BaseCipherTest {

	@Test
	void testBaseCipher() {
		int lengthCipherKey = 25;
		int number = 12345;
		
		BaseCipher objectBaseChiper = new BaseCipher(lengthCipherKey);
		System.out.println(objectBaseChiper.getCipherKey());
		System.out.println(objectBaseChiper.cipher(number));
		System.out.println(objectBaseChiper.decipher(objectBaseChiper.cipher(number)));
	}

}
