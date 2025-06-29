package _02_StringManipulation;

public class Problems {

	public static void main(String[] args) {
		String str = "Hello123";
		System.out.println("Is alpha-numeric: " + isAlphaNumeric(str));

		char c1 = 'a';
		char c2 = 'Z';
		char c3 = '5';
		System.out.println("Is lower case: " + isLowerCase(c1));
		System.out.println("Is upper case: " + isUpperCase(c2));
		System.out.println("Is digit: " + isDigit(c3));
	}

	private static boolean isAlphaNumeric(String str) {
		if (str == null || str.isEmpty()) {
			return false;
		}
		for (char c : str.toCharArray()) {
			if (!isLowerCase(c) && !isUpperCase(c) && !isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	private static char toUpperCase(char c) {
		if (isLowerCase(c)) {
			return (char) (c - 'a' + 'A');
		}

		return c;
	}

	private static boolean isLowerCase(char c) {
		return c >= 'a' && c <= 'z';
	}

	private static boolean isUpperCase(char c) {
		return c >= 'A' && c <= 'Z';
	}

	private static boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}
}
