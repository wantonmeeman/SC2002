package Screen;

import java.util.Scanner;

public class ExitScreen extends Screen<Void,Void> {
    StringBuilder sb;

    public ExitScreen() {
    }

    @Override
    public Void start(Void v, Scanner sc) {
        sb = new StringBuilder();

        displayExit();
        return null;
    };

    private void displayExit(){
        sb.append("Exiting...");

        System.out.print(sb.toString());
        sb.setLength(0);
    }
;
}
