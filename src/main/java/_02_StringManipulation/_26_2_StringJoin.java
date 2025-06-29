package _02_StringManipulation;


// This code is a solution to the problem of joining an array of strings with a specified separator.
// It includes a method to join the strings and a set of tests to validate the implementation.

public class _26_2_StringJoin {

	public static void main(String[] args) {
		RunTests runTests = new RunTests();
		runTests.runTests();
	}

	public static String solve(String[] arr, String s) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				res.append(s);
			}
			res.append(arr[i]);
		}

		return res.toString();
	}

	static class RunTests {
		public void runTests() {
			Object[][] tests = {
					// Example 1 from the book
					{ new String[] { "join", "by", "space" }, " ", "join by space" },
					// Example 2 from the book
					{ new String[] { "b", "", "k", "", "p", "r n", "", "d", "d!!" }, "ee",
							"beeeekeeeepeer neeeedeed!!" },
					// Edge case - empty arrays
					{ new String[] {}, "x", "" },
					{ new String[] {}, "", "" },
					{ new String[] {}, "long separator", "" },
					// Edge case - single element arrays
					{ new String[] { "a" }, "x", "a" },
					{ new String[] { "" }, "x", "" },
					{ new String[] { "multiple words" }, "x", "multiple words" },
					// two element arrays
					{ new String[] { "a", "b" }, "", "ab" },
					{ new String[] { "a", "b" }, " ", "a b" },
					{ new String[] { "", "" }, ",", "," },
					// Edge case - empty strings in array
					{ new String[] { "", "", "" }, ",", ",," },
					{ new String[] { "hello", "", "world" }, " ", "hello  world" },
					// special characters
					{ new String[] { "\n", "\t" }, ",", "\n,\t" },
					{ new String[] { "tab", "separated" }, "\t", "tab\tseparated" },
					// long separators
					{ new String[] { "short", "strings" }, "very long separator",
							"shortvery long separatorstrings" },
					// mixed content
					{ new String[] { "123", "abc", "!@#", "   " }, "|", "123|abc|!@#|   " },
					// whitespace handling
					{ new String[] { "  leading", "trailing  ", "  both  " }, "|",
							"  leading|trailing  |  both  " },
					// numbers and special chars
					{ new String[] { "123", "456" }, "-", "123-456" },
					{ new String[] { "!@#", "$%^" }, "&", "!@#&$%^" },
			};

			for (Object[] test : tests) {
				String[] arr = (String[]) test[0];
				String s = (String) test[1];
				String want = (String) test[2];
				String got = _26_2_StringJoin.solve(arr, s);
				if (!got.equals(want)) {
					throw new RuntimeException(String.format(
							"\nsolve(%s, %s): got: %s, want: %s\n",
							java.util.Arrays.toString(arr), s, got, want));
				}
			}
		}
	}
}