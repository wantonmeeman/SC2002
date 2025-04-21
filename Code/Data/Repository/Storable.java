package Data.Repository;

import Util.ReadCSV;
import Util.SaveCSV;

import java.util.ArrayList;

public class Storable{
    private String filepath;

    protected void setFilepath(String filepath) {
    this.filepath = filepath;
}

    protected void save(ArrayList<ArrayList<String>> csvArrList){
        SaveCSV.saveCSV(filepath,csvArrList);
    };

    protected ArrayList<ArrayList<String>> load(){
        return ReadCSV.readCSV(filepath);
    };
}
