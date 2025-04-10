package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CSVUtils {

    public static ArrayList<ArrayList<String>> readCSV(final String filepath) {

        ArrayList<ArrayList<String>> returnArr = new ArrayList<>();

        try {
            File file = new File(filepath);
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

    public static void saveCSV(final String filepath, ArrayList<ArrayList<String>> csvString) {
//        try {
//            //PrintWriter printWriter = new PrintWriter(new FileWriter(filepath));
//            //for (modelList mappableObject : modelList) {
//                //printWriter.println();
//            //}
//        } catch (IOException e) {
//            throw new RuntimeException("Data could not be saved to file: ");
//        }
    }
}
