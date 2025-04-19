package Util;

public class WrapText {
    public static String wrap(String input) {
		if(input != null) {
			StringBuilder result = new StringBuilder();
			int index = 0;

			while (index < input.length()) {
				int endIndex = Math.min(index + Config.MAX_LINE_WIDTH, input.length());
				result.append(input, index, endIndex).append("\n");
				index = endIndex;
			}

			return result.toString();
		}
		return null;
    }

}
