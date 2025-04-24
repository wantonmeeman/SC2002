package Pages.Components;

import Util.Config;

/**
 * The interface Seperator.
 */
public interface Seperator {
    /**
     * Seperate string.
     *
     * @return the string
     */
    static String seperate(){
        String returnStr = "";
        for(int x = 0;x < Config.MAX_LINE_WIDTH;x++){
            returnStr += "=";
        }
        return returnStr;
    }
}
