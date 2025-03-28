
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class InputReader {
    public String readText(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            String returnString = "";

            while (scanner.hasNextLine()) {
                returnString += (scanner.nextLine() + (scanner.hasNextLine() ? "\n" : ""));
            }

            scanner.close();

            return returnString;
        } catch (FileNotFoundException e) {
            System.out.print("Error with reading the file!");
            return "Error!";
        }
    }
}
