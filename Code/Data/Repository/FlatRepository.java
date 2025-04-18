package Data.Repository;

import Data.Models.Model;
import Data.Models.Flat;

import static Util.Config.*;

import java.util.ArrayList;

public class FlatRepository extends DataRepository {
    private static FlatRepository instance;

    public FlatRepository() {
        setFilepath(DATA_PATH + FLAT_CSV);
        fetch();
    }

    @Override
    public ArrayList<Model> toModelList(ArrayList<ArrayList<String>> csv) {
        ArrayList<Model> flatArr = new ArrayList<>();

        for (ArrayList<String> strArr : csv) {
            flatArr.add(new Flat(
                    strArr.get(0),                            // ID (String)
                    strArr.get(1),                            // type (String)
                    Integer.parseInt(strArr.get(2)),          // totalUnits (int)
                    Float.parseFloat(strArr.get(3))           // price (float)
            ));
        }

        return flatArr;
    }

    @Override
    public ArrayList<ArrayList<String>> toCSV(ArrayList<Model> alm) {
        ArrayList<ArrayList<String>> csvData = new ArrayList<>();

        alm.forEach(model -> {
            Flat flat = (Flat) model;

            ArrayList<String> row = new ArrayList<>();
            row.add(flat.getID());                        // ID
            row.add(flat.getType());                      // type
            row.add(String.valueOf(flat.getTotalUnits())); // totalUnits
            row.add(String.valueOf(flat.getPrice()));      // price

            csvData.add(row);
        });

        return csvData;
    }

    public static FlatRepository getInstance() {
        if (instance == null)
            instance = new FlatRepository();
        return instance;
    }

}
