package Data.Repository;

import Data.Models.Model;
import Data.Models.Application;

import java.util.ArrayList;


import static Util.Config.APPLICATION_CSV;
import static Util.Config.DATA_PATH;

public class ApplicationRepository extends DataRepository {
    private static ApplicationRepository instance;

    public ApplicationRepository() {
        setFilepath(DATA_PATH + APPLICATION_CSV);
        fetch();
    }

    @Override
    public ArrayList<Model> toModelList(ArrayList<ArrayList<String>> csv) {
        ArrayList<Model> applicationArr = new ArrayList<>();

        for (ArrayList<String> strArr : csv) {
            applicationArr.add(new Application(
                    strArr.get(0),
                    strArr.get(1),
                    strArr.get(2),
                    strArr.get(3),
                    strArr.get(4)
                    )
            );
        }

        return applicationArr;
    }

    @Override
    public ArrayList<ArrayList<String>> toCSV(ArrayList<Model> alm) {
        ArrayList<ArrayList<String>> csvData = new ArrayList<>();

        alm.forEach(model -> {
            Application application = (Application) model;

            ArrayList<String> row = new ArrayList<>();
            row.add(application.getID());
            row.add(application.getUserID());
            row.add(application.getFlatID());
            row.add(application.getStatus());
            row.add(application.getOfficerID());

            csvData.add(row);
        });

        return csvData;
    }

    public static ApplicationRepository getInstance() {
        if (instance == null)
            instance = new ApplicationRepository();
        return instance;
    }

}
