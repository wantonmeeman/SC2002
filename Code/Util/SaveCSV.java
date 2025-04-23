package Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public interface SaveCSV {
    static void saveCSV(final String filepath, ArrayList<ArrayList<String>> csvData) {
        try {

            //System.out.println("Saving to file: " + filepath);

            //File file = new File(filepath);
            //File parent = file.getParentFile();

//            if (parent != null && !parent.exists()) {
//                parent.mkdirs(); // create directories if they don't exist
//            }
//
//            if(!file.exists()){
//                file.createNewFile(); // create the file if it doesn't exist
//            }

            // // Check if the file exists, if not, create it
            // if (!file.exists()) {
            //     file.getParentFile().mkdirs(); // create directories if they don't exist
            //     file.createNewFile();
            // }

            PrintWriter printWriter = new PrintWriter(new FileWriter(filepath));
            int y = 0;
            for (ArrayList<String> line: csvData) {
                for(int x = 0;line.size() > x;x++) {
                    String value = line.get(x);

                    if (value != null){
                        value = value.replace(',','␟').replace('\n','§');
                    }

                    printWriter.print(value+(x==line.size()-1 ?"":","));
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
