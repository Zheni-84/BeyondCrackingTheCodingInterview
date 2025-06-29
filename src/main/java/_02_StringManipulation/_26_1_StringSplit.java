package _02_StringManipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _26_1_StringSplit {

	public static void main(String[] args) {
		RunTests runTests = new RunTests();
		runTests.runTests();

		//tests();
	}

	private static void tests() {
		String str1 = "split by space";
		char delimiter1 = ' ';
		String str2 = "beekeeper needed";
		char delimiter2 = 'e';
		String str3 = "/home/./..//Documents/";
		char delimiter3 = '/';
		String str4 = "";
		char delimiter4 = '?';

		String[] result = split(str1, delimiter1);
		for (String s : result) {
			System.out.println(s);
		}

		String[] result2 = split(str2, delimiter2);
		for (String s : result2) {
			System.out.println(s);
		}

		String[] result3 = split(str3, delimiter3);
		for (String s : result3) {
			System.out.println(s);
		}
		String[] result4 = split(str4, delimiter4);
		for (String s : result4) {
			System.out.println(s);
		}
	}

	public static String[] split(String str, char delimiter) {
		if (str == null || str.isEmpty()) {
			return new String[0];
		}

		List<String> res = new ArrayList<>();
		StringBuilder current = new StringBuilder();

		for (char c : str.toCharArray()) {
			if (c == delimiter) {
				res.add(current.toString());
				// Reset current StringBuilder for the next segment
				// Clear the StringBuilder
				current.setLength(0);
			} else {
				current.append(c);
			}
		}

		// Add the last segment if it exists
		res.add(current.toString());

		return res.toArray(new String[0]);
	}

	static class RunTests {
		public void runTests() {
			Object[][] tests = {
					// Example 1 from the book
					{ "split by space", ' ', new String[] { "split", "by", "space" } },
					// Example 2 from the book
					{ "beekeeper needed", 'e',
							new String[] { "b", "", "k", "", "p", "r n", "", "d", "d" } },
					// Example 3 from the book
					{ "/home/./..//Documents/", '/',
							new String[] { "", "home", ".", "..", "", "Documents", "" } },
					// Example 4 from the book
					{ "", '?', new String[] {} },
					// Edge case - empty string with various delimiters
					{ "", ' ', new String[] {} },
					{ "", '\n', new String[] {} },
					{ "", '\0', new String[] {} },
					// Edge case - single character string
					{ "a", 'a', new String[] { "", "" } },
					{ "a", 'b', new String[] { "a" } },
					// Edge case - no splits
					{ "hello", 'x', new String[] { "hello" } },
					{ "hello", '?', new String[] { "hello" } },
					// Edge case - all splits
					{ "aaa", 'a', new String[] { "", "", "", "" } },
					// Edge case - special characters
					{ "\n\n\n", '\n', new String[] { "", "", "", "" } },
					{ "tab\tseparated\ttext", '\t',
							new String[] { "tab", "separated", "text" } },
					// Edge case - consecutive delimiters
					{ "one,,two,,,three", ',',
							new String[] { "one", "", "two", "", "", "three" } },
					// Edge case - delimiter at start/end
					{ ",start,middle,end,", ',',
							new String[] { "", "start", "middle", "end", "" } },
					// Edge case - mixed length strings
					{ "short,medium string,very very long string", ',',
							new String[] { "short", "medium string",
									"very very long string" } },
					// Edge case - whitespace handling
					{ "  leading space", ' ', new String[] { "", "", "leading", "space" } },
					{ "trailing space  ", ' ',
							new String[] { "trailing", "space", "", "" } },
					// Edge case - numbers and special chars
					{ "123,456,789", ',', new String[] { "123", "456", "789" } },
					{ "!@#$%", '@', new String[] { "!", "#$%" } }
			};

			for (Object[] test : tests) {
				String s = (String) test[0];
				char c = (char) test[1];
				String[] want = (String[]) test[2];
				String[] got = _26_1_StringSplit.split(s, c);
				if (!Arrays.equals(got, want)) {
					throw new RuntimeException(String.format(
							"\nNOT solve(\"%s\", '%c'): got: %s, want: %s\n",
							s, c, Arrays.toString(got), Arrays.toString(want)));
				}else {
					System.out.println(String.format("solve(\"%s\", '%c'): got: %s, want: %s",
							s, c, Arrays.toString(got), Arrays.toString(want)));
				}
			}
		}
	}
}