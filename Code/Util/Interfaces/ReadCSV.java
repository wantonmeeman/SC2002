package Util.Interfaces;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The interface Read csv.
 */
public interface ReadCSV {
    /**
     * Read csv array list.
     *
     * @param filepath the filepath
     * @return the array list
     */
    static ArrayList<ArrayList<String>> readCSV(final String filepath) {

        //System.out.println("Reading file: " + filepath);
        ArrayList<ArrayList<String>> returnArr = new ArrayList<>();

        try {
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                if(line.trim().isEmpty()) continue;//This skips empty lines

                String[] lineList = line.split(",");
                if(lineList.length > 0) {
                    for (int x = 0; lineList.length > x; x++) {
                        if (lineList[x].equals("null")) {
                            lineList[x] = null;
                        }else{
                            lineList[x] = lineList[x].replace("�??",",").replace("§","\n");
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

}
