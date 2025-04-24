package Util.Interfaces;

import Util.WrapText;

/**
 * The interface Print to cmd.
 */
public interface PrintToCMD {
    /**
     * Print.
     *
     * @param message the message
     */
    static void print(String message){
        System.out.println(WrapText.wrap(message));
    }
}
