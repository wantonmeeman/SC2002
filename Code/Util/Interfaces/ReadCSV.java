package Util.Interfaces;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public interface ReadCSV {
    static ArrayList<ArrayList<String>> readCSV(final String filepath) {

        //System.out.println("Reading file: " + filepath);
        ArrayList<ArrayList<String>> returnArr = new ArrayList<>();

        try {
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String[] lineList = scanner.nextLine().split(",");
                if(lineList.length > 0) {
                    for (int x = 0; lineList.length > x; x++) {
                        if (lineList[x].equals("null")) {
                            lineList[x] = null;
                        }else{
                            lineList[x] = lineList[x].replace('␟',',').replace('§','\n');
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
