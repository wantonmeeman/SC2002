package Data.Repository;

import java.util.ArrayList;

import Data.Models.Applicant;
import Data.Models.Model;

import static Util.Config.APPLICANT_CSV;
import static Util.Config.DATA_PATH;

public class ApplicantRepository extends DataRepository {
    private static ApplicantRepository instance;

    public ApplicantRepository() {
        setFilepath(DATA_PATH + APPLICANT_CSV);
        fetch();
    }

    @Override
    public ArrayList<Model> toModelList(ArrayList<ArrayList<String>> csv) {
        ArrayList<Model> applicantArr = new ArrayList<>();

        for (ArrayList<String> strArr : csv) {
            applicantArr.add(new Applicant(
                    strArr.get(1),
                    strArr.get(0),
                    Integer.parseInt(strArr.get(2)),
                    strArr.get(3).charAt(0),
                    strArr.get(4)));
        }

        return applicantArr;
    }

    @Override
    public ArrayList<ArrayList<String>> toCSV(ArrayList<Model> alm) {
        ArrayList<ArrayList<String>> csvData = new ArrayList<>();

        // Loop through each Model in alm
        alm.forEach(model -> {
            Applicant applicant = (Applicant) model;

            ArrayList<String> row = new ArrayList<>();
            row.add(applicant.getID());       // Add userID
            row.add(applicant.getName());         // Add name
            row.add(applicant.getPassword());
            row.add(String.valueOf(applicant.getAge())); // Convert age to String
            row.add(String.valueOf(applicant.getMaritalStatus())); // Convert maritalStatus to String

            csvData.add(row);
        });

        return csvData;
    }

//    Should we do it like this?
//    public static ApplicantRepository getInstance() {
//        return new ApplicantRepository();
//    }
    //cREATING NEW instance everytime or checking first

    public static ApplicantRepository getInstance() {
        if (instance == null)
            instance = new ApplicantRepository();
        return instance;
    }

}
