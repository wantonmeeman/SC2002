
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class InputReader {

    private File file;
    private Scanner scanner;
    private String returnString;

    public InputReader() {
        this.file = null;
        this.scanner = null;
        this.returnString = "";
    };

    //Should we add processing here

    public String readText(String filename) {
        try {
            this.file = new File(filename);
            this.scanner = new Scanner(this.file);

            while (this.scanner.hasNextLine()) {
                this.returnString += (this.scanner.nextLine() + (this.scanner.hasNextLine() ? "\n" : ""));
            }

            return this.returnString;
        } catch (FileNotFoundException e) {
            System.out.print("Error with reading the file!");
            return "Error!";
        }
    }
}
