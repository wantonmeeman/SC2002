package Pages.Components;

import Util.Config;

public interface Seperator {
    static String seperate(){
        String returnStr = "";
        for(int x = 0;x < Config.MAX_LINE_WIDTH;x++){
            returnStr += "=";
        }
        return returnStr;
    }
}
