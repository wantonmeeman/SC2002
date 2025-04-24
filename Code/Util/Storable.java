package Util;

import Util.Interfaces.ReadCSV;
import Util.Interfaces.SaveCSV;

import java.util.ArrayList;

/**
 * The type Storable.
 */
public class Storable implements SaveCSV, ReadCSV{
    private String filepath;

    /**
     * Sets filepath.
     *
     * @param filepath the filepath
     */
    protected void setFilepath(String filepath) {
    this.filepath = filepath;
}

    /**
     * Save.
     *
     * @param csvArrList the csv arr list
     */
    protected void save(ArrayList<ArrayList<String>> csvArrList){
        SaveCSV.saveCSV(filepath,csvArrList);
    };

    /**
     * Load array list.
     *
     * @return the array list
     */
    protected ArrayList<ArrayList<String>> load(){
        return ReadCSV.readCSV(filepath);
    };
}
