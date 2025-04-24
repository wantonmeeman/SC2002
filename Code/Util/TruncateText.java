package Util;

/**
 * The type Truncate text.
 */
public class TruncateText {
    /**
     * Truncate string.
     *
     * @param input the input
     * @return the string
     */
    public static String truncate(String input) {
        if(input != null)
			return input.length() > Config.MAX_SIMPLE_TEXT_LENGTH ? input.substring(0, Config.MAX_SIMPLE_TEXT_LENGTH - 3) + "..." : input;
        return null;
    }

}
