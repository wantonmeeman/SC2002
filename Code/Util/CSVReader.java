package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CSVReader {
    public static ArrayList<ArrayList<String>> readCSV(String filename) {

        ArrayList<ArrayList<String>> returnArr = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                returnArr.add(new ArrayList<>(Arrays.asList(((scanner.nextLine()).split(",")))));
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.print("Error with reading the file!");
        }
        return returnArr;
    }
}
