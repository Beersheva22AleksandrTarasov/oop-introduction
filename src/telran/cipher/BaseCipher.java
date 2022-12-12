package telran.cipher;

public class BaseCipher {

	private String cipherKey = "";
	private int length;
	private int minCodeASCII = 33;
	private int maxCodeASCII = 126;
	boolean helperForUniqueRandomChar[] = new boolean[maxCodeASCII - minCodeASCII + 1];

	public BaseCipher(int length) {
		this.setLengthForCipherKey(length);
		this.cipherKey = generateCipherKey(this.length);
	}

	private void setLengthForCipherKey(int length) {
		if (length < 2) {
			this.length = 2;
		} else if (length > 94) {
			this.length = 94;
		} else {
			this.length = length;
		}
	}

	public String getCipherKey() {
		return cipherKey;
	}

	private String generateCipherKey(int length) {
		String tempStr = "";

		for (int i = 0; i < length; i++) {
			tempStr += getUniqueRandomChar();
		}

		return tempStr;
	}

	private char getUniqueRandomChar() {
		int randomInteger;
		do {
			randomInteger = (int) (minCodeASCII + Math.random() * (maxCodeASCII - minCodeASCII + 1));
		} while (helperForUniqueRandomChar[randomInteger - minCodeASCII]);

		helperForUniqueRandomChar[randomInteger - minCodeASCII] = true;

		return (char) randomInteger;
	}

	public String cipher(int number) {

		String result = "";
		while (number != 0) {
			result = cipherKey.charAt(number % length) + result;
			number /= length;
		}

		return result;
	}

	public int decipher(String strCipher) {
		int result = 0;
		for (int i = 0; i < strCipher.length(); i++) {
			result += cipherKey.indexOf(strCipher.charAt(i)) * Math.pow(length, strCipher.length() - i - 1);
		}

		return result;

	}

}
