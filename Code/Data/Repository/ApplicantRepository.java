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
                    strArr.get(0),
                    strArr.get(1),
                    Integer.parseInt(strArr.get(3)),
                    strArr.get(4).charAt(0),
                    strArr.get(2),
                    strArr.get(5)
            ));
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
            row.add(String.valueOf(applicant.getAge()));
            row.add(String.valueOf(applicant.getMaritalStatus()));
            row.add(String.valueOf(applicant.getApplicationID()));

            csvData.add(row);
        });

        return csvData;
    }

    public static ApplicantRepository getInstance() {
        if (instance == null)
            instance = new ApplicantRepository();
        return instance;
    }

}
