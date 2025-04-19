package Util;

public class TruncateText {
    public static String truncate(String input) {
        if(input != null)
			return input.length() > Config.MAX_SIMPLE_TEXT_LENGTH ? input.substring(0, Config.MAX_SIMPLE_TEXT_LENGTH - 3) + "..." : input;
        return null;
    }

}
