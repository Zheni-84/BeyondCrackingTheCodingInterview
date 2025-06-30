package _02_StringManipulation;

public class _26_3_StringIndexOf {

	public static void main(String[] args) {
		RunTests runTests = new RunTests();
		runTests.runTests();
	}

	public int solveNaive(String str, String subStr) {
		if (str == null || subStr == null || str.isEmpty()) {
			return -1;
		}
		if(subStr.isEmpty()){
			return 0;
		}

		int strLen = str.length();
		int subStrLen = subStr.length();

		for (int i = 0; i <= strLen - subStrLen; i++) {
			int j;
			for (j = 0; j < subStrLen; j++) {
				if (str.charAt(i + j) != subStr.charAt(j)) {
					break;
				}
			}
			if (j == subStrLen) {
				return i; // Found the substring
			}
		}

		return -1; // Substring not found
	}

	static class IndexOfUsingRollingHash {
		private static final long LARGE_PRIME = 1000000007;

		public int solve(String s, String t) {
			int sn = s.length(), tn = t.length();
			if (tn > sn) {
				return -1;
			}
			long hashT = strToNum(t);
			long currentHash = strToNum(s.substring(0, tn));
			if (hashT == currentHash && t.equals(s.substring(0, tn))) {
				return 0; // Found t at the beginning.
			}
			long firstPower = power(tn - 1);
			for (int i = 0; i < sn - tn; i++) {
				currentHash = nextHash(s, tn, i, currentHash, firstPower);
				if (hashT == currentHash && t.equals(s.substring(i + 1, i + tn + 1))) {
					return i + 1;
				}
			}
			return -1;
		}

		// Computes 128^x % LARGE_PRIME.
		// This is used to compute the contribution of the first character in the
		// substring to the hash.
		private long power(int x) {
			long res = 1;
			for (int i = 0; i < x; i++) {
				res = (res * 128) % LARGE_PRIME;
			}
			return res;
		}

		// Converts a string to a number using a polynomial hash function.
		// The hash is computed as: (c[0] * 128^(n-1) + c[1] * 128^(n-2) + ... + c[n-1]) % LARGE_PRIME
		// where c[i] is the ASCII value of the i-th character in the string.
		// This is a simple hash function that works well for small strings.
		// It is not cryptographically secure, but it is sufficient for this problem.
		// The choice of 128 is arbitrary, but it is a common choice for character encoding.
		// The choice of LARGE_PRIME is also arbitrary, but it should be large enough to avoid collisions.
		// The hash function is not perfect, but it is fast and works well for the problem at hand.
		private long strToNum(String str) {
			long res = 0;
			for (char c : str.toCharArray()) {
				res = (res * 128 + (int) c) % LARGE_PRIME;
			}
			return res;
		}

		private long nextHash(String s, int tn, int i, long currentHash,
				long firstPower) {
			// Assumes that currentHash is the hash of s[i:i+tn].
			// Assumes that firstPower is 128^(tn-1) % LARGE_PRIME.
			// Returns the hash of the next substring of s of length tn: s[i+1:i+tn+1].

			long res = currentHash;
			// 1. Remove the contribution from the first character (s[i]).
			final long numerator = res - (int) s.charAt(i) * firstPower;
			// We need to add LARGE_PRIME to handle the case where numerator is
			// negative.
			res = (numerator % LARGE_PRIME + LARGE_PRIME) % LARGE_PRIME;
			// 2. Increase every exponent.
			res = (res * 128) % LARGE_PRIME;
			// 3. Add the contribution from the new character (s[i+tn]).
			return (res + (int) s.charAt(i + tn)) % LARGE_PRIME;
		}
	}

	static class RunTests {
		public void runTests() {
			Object[][] tests = {
					// Basic test cases from book
					{ "hello world", "world", 6 },
					{ "hello world", "hello", 0 },
					{ "needle in a haystack", "needle", 0 },
					{ "needle in a haystack", "haystack", 12 },
					{ "needle in a haystack", "not", -1 },
					// Edge case - empty strings
					{ "", "", 0 },
					{ "", "x", -1 },
					{ "x", "", 0 },
					{ "abc", "", 0 },
					// Edge case - single character
					{ "x", "x", 0 },
					{ "abc", "a", 0 },
					{ "abc", "b", 1 },
					{ "abc", "c", 2 },
					{ "abc", "d", -1 },
					// Edge case - pattern longer than string
					{ "x", "xx", -1 },
					{ "abc", "abcd", -1 },
					// multiple occurrences
					{ "banana", "ana", 1 }, // Should return first occurrence
					{ "banana", "an", 1 }, // Should return first occurrence
					{ "banana", "a", 1 }, // Should return first occurrence
					// overlapping patterns
					{ "aaaaa", "aa", 0 },
					{ "aaaaa", "aaa", 0 },
					{ "aabaabaa", "aaba", 0 },
					// pattern at start/end
					{ "startend", "start", 0 },
					{ "startend", "end", 5 },
					// special characters
					{ "\n\n\n", "\n", 0 },
					{ "\n\n\n", "\n\n", 0 },
					{ "tab\tseparated", "\t", 3 },
					// repeated characters
					{ "mississippi", "issi", 1 },
					{ "mississippi", "ssi", 2 },
					{ "mississippi", "sip", 6 },
					// case sensitivity
					{ "Hello World", "hello", -1 },
					{ "Hello World", "Hello", 0 },
					// whitespace
					{ "   spaces   ", " ", 0 },
					{ "   spaces   ", "   ", 0 },
					{ "   spaces   ", "spaces", 3 },
					// numbers and special chars
					{ "123123", "123", 0 },
					{ "!@#$%", "@#", 1 },
					// long strings and patterns
					{ "very very very long string to search in", "very long string", 10 },
					{ "aaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaa", 0 }, // Many matches
			};

			_26_3_StringIndexOf naiveSolution = new _26_3_StringIndexOf();
			IndexOfUsingRollingHash rollingHash = new IndexOfUsingRollingHash();

			for (Object[] test : tests) {
				String s = (String) test[0];
				String t = (String) test[1];
				int want = (int) test[2];

				int got = naiveSolution.solveNaive(s, t);
				if (got != want) {
					throw new RuntimeException(String.format(
							"\nindexOfNaiveSolution(\"%s\", \"%s\"): got: %d, want: %d\n",
							s, t, got, want));
				}

				got = rollingHash.solve(s, t);
				if (got != want) {
					throw new RuntimeException(String.format(
							"\nindexOfUsingRollingHash(\"%s\", \"%s\"): got: %d, want: %d\n",
							s, t, got, want));
				}
			}
		}
	}
}