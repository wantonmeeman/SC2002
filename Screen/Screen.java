package Screen;

import java.util.Scanner;

abstract public class Screen<A,B> {
    public abstract B start(A param, Scanner sc);
}
