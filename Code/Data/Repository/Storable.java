package Data.Repository;

import Util.CSVUtils;

import java.util.ArrayList;

public class Storable extends CSVUtils{
    private String filepath;

    protected void setFilepath(String filepath) {
    this.filepath = filepath;
}

    protected void save(ArrayList<ArrayList<String>> csvArrList){
        saveCSV(filepath,csvArrList);
    };

    protected ArrayList<ArrayList<String>> load(){
        return readCSV(filepath);
    };
}
