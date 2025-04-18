package Data.Repository;

import Data.Models.Manager;
import Data.Models.Model;

import java.util.ArrayList;

import static Util.Config.*;

public class ManagerRepository extends DataRepository {
    private static ManagerRepository instance;

    public ManagerRepository() {
        setFilepath(DATA_PATH + MANAGER_CSV);
        fetch();
    }

    @Override
    public ArrayList<Model> toModelList(ArrayList<ArrayList<String>> csv) {
        ArrayList<Model> managerArr = new ArrayList<>();

        for (ArrayList<String> strArr : csv) {
            managerArr.add(new Manager(
                    strArr.get(0),
                    strArr.get(1),
                    Integer.parseInt(strArr.get(3)),
                    strArr.get(4).charAt(0),
                    strArr.get(2),
                    strArr.get(5)
                    ));
        }

        return managerArr;
    }

    @Override
    public ArrayList<ArrayList<String>> toCSV(ArrayList<Model> alm) {
        ArrayList<ArrayList<String>> csvData = new ArrayList<>();

        // Loop through each Model in alm
        alm.forEach(model -> {
            Manager manager = (Manager) model;

            ArrayList<String> row = new ArrayList<>();
            row.add(manager.getID());       // Add userID
            row.add(manager.getName());         // Add name
            row.add(manager.getPassword());
            row.add(String.valueOf(manager.getAge())); // Convert age to String
            row.add(String.valueOf(manager.getMaritalStatus())); // Convert maritalStatus to String
            row.add(String.valueOf(manager.getProjectID())); // Convert maritalStatus to String

            csvData.add(row);
        });

        return csvData;
    }

    public static ManagerRepository getInstance() {
        if (instance == null)
            instance = new ManagerRepository();
        return instance;
    }

}
