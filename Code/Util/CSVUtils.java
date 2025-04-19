package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVUtils {

    public static ArrayList<ArrayList<String>> readCSV(final String filepath) {

        ArrayList<ArrayList<String>> returnArr = new ArrayList<>();

        try {
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);

           // scanner.nextLine(); skips first line as it is the header

            while (scanner.hasNextLine()) {
                String[] lineList = scanner.nextLine().split(",");
                if(lineList.length > 0) {
                    for (int x = 0; lineList.length > x; x++) {
                        if (lineList[x].equals("null")) {
                            lineList[x] = null;
                        }
                    }

                    returnArr.add(
                            new ArrayList<>(
                                    Arrays.asList(lineList)
                            )
                    );
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error with reading the file!");
        }
        return returnArr;
    }

    public static void saveCSV(final String filepath, ArrayList<ArrayList<String>> csvData) {
        try {
            File file = new File(filepath);

            // Check if the file exists, if not, create it
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // create directories if they don't exist
                file.createNewFile();
            }

            PrintWriter printWriter = new PrintWriter(new FileWriter(filepath));
            int y = 0;
            for (ArrayList<String> line: csvData) {
                for(int x = 0;line.size() > x;x++) {
                    String value = line.get(x);

                    if (value != null && (value.contains(",") || value.contains("\"") || value.contains("\n"))) {
                        // Escape double quotes by doubling them
                        value = value.replace("\"", "\"\"");
                        // Wrap the value in quotes
                        value = "\"" + value + "\"";
                    }

                    printWriter.print(line.get(x)+(x==line.size()-1 ?"":","));
                }
                if (y < csvData.size() - 1) {
                    printWriter.print("\n");
                }
            }
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Data could not be saved to file: ");
        }
    }
}
