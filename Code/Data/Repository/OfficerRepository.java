package Data.Repository;

import Data.Models.HDBOfficer;
import Data.Models.Model;

import java.util.ArrayList;

import static Util.Config.*;

public class OfficerRepository extends DataRepository {
    private static OfficerRepository instance;

    public OfficerRepository() {
        setFilepath(DATA_PATH + OFFICER_CSV);
        fetch();
    }

    @Override
    public ArrayList<Model> toModelList(ArrayList<ArrayList<String>> csv) {
        ArrayList<Model> officerArr = new ArrayList<>();

        for (ArrayList<String> strArr : csv) {
            officerArr.add(new HDBOfficer(
                    strArr.get(1),
                    strArr.get(0),
                    Integer.parseInt(strArr.get(2)),
                    strArr.get(3).charAt(0),
                    strArr.get(4)));
        }

        return officerArr;
    }

    @Override
    public ArrayList<ArrayList<String>> toCSV(ArrayList<Model> alm) {
        ArrayList<ArrayList<String>> csvData = new ArrayList<>();

        // Loop through each Model in alm
        alm.forEach(model -> {
            HDBOfficer officer = (HDBOfficer) model;

            ArrayList<String> row = new ArrayList<>();
            row.add(officer.getID());       // Add userID
            row.add(officer.getName());         // Add name
            row.add(officer.getPassword());
            row.add(String.valueOf(officer.getAge())); // Convert age to String
            row.add(String.valueOf(officer.getMaritalStatus())); // Convert maritalStatus to String

            csvData.add(row);
        });

        return csvData;
    }

//    Should we do it like this?
//    public static HDBOfficerRepository getInstance() {
//        return new HDBOfficerRepository();
//    }
    //cREATING NEW instance everytime or checking first

    public static OfficerRepository getInstance() {
        if (instance == null)
            instance = new OfficerRepository();
        return instance;
    }

}
