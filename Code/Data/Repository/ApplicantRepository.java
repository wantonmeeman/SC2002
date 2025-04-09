package Repository;

import java.util.ArrayList;

import Models.Applicant;

public class ApplicantRepository extends DataRepository<Applicant> {

    private static final String FILEPATH = "../InputData/ApplicantList.csv";

    public ApplicantRepository() {
        super();

        setFilepath(FILEPATH);
        load();
    }

    @Override
    public ArrayList<Applicant> toModelList(ArrayList<ArrayList<String>> csv) {
        ArrayList<Applicant> applicantArr = new ArrayList<>();

        //Maybe need to move it to another mapper class?
        for (ArrayList<String> strArr : csv) {
            applicantArr.add(new Applicant(strArr.get(1), strArr.get(0), Integer.parseInt(strArr.get(2)), strArr.get(3).charAt(0), strArr.get(4)));
        }

        return applicantArr;
    }
 
    @Override
    public ArrayList<ArrayList<String>> toCSV(ArrayList<Applicant> alm) {
        ArrayList<ArrayList<String>> csvData = new ArrayList<>();
    
        // Loop through each Model in alm
        for (Applicant applicant : alm) { 
            ArrayList<String> row = new ArrayList<>();
        
            row.add(applicant.getUserID());       // Add userID
            row.add(applicant.getName());         // Add name
            row.add(applicant.getPassword());
        
            row.add(String.valueOf(applicant.getAge())); // Convert age to String
            row.add(String.valueOf(applicant.getMaritalStatus())); // Convert maritalStatus to String
        
            csvData.add(row);
        }
    
        return csvData;
    }


    @Override
    public void save() {
        saveCSV(getFilepath(), toCSV(listOfModels));
    }

    @Override
    public void load() {
        listOfModels.clear();
        readCSV(getFilepath());
    }

    public static ApplicantRepository getInstance() {
        return new ApplicantRepository();
    }

}
