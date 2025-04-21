package Data.Repository;

import Data.Models.Model;
import Data.Models.Neighbourhood;

import java.util.ArrayList;

import static Util.Config.*;

public class NeighbourhoodRepository extends DataRepository {
    private static NeighbourhoodRepository instance;

    public NeighbourhoodRepository() {
        setFilepath(DATA_PATH + NEIGHBOURHOOD_CSV);
        fetch();
    }

    @Override
    public ArrayList<Model> toModelList(ArrayList<ArrayList<String>> csv) {
        ArrayList<Model> neighbourhoodArr = new ArrayList<>();

        for (ArrayList<String> strArr : csv) {
            neighbourhoodArr.add(new Neighbourhood(
                    strArr.get(0),
                    strArr.get(1)
            ));
        }

        return neighbourhoodArr;
    }

    @Override
    public ArrayList<ArrayList<String>> toCSV(ArrayList<Model> alm) {
        ArrayList<ArrayList<String>> csvData = new ArrayList<>();

        // Loop through each Model in alm
        alm.forEach(model -> {
            Neighbourhood neighbourhood = (Neighbourhood) model;

            ArrayList<String> row = new ArrayList<>();
            row.add(neighbourhood.getID());                                // userID
            row.add(neighbourhood.getName());                          // location

            csvData.add(row);
        });

        return csvData;
    }

    public static NeighbourhoodRepository getInstance() {
        if (instance == null)
            instance = new NeighbourhoodRepository();
        return instance;
    }

}
