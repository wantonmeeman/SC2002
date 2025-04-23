package Util;

public interface PrintToCMD {
    static void print(String message){
        System.out.println(WrapText.wrap(message));
    }
}
