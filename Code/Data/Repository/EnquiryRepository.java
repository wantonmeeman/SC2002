package Data.Repository;

import Data.Models.Model;
import Data.Models.Officer;

import java.util.ArrayList;
import java.util.Date;

import Util.Config.*;
import Data.Models.Enquiry;

public class EnquiryRepository extends DataRepository {
    private static EnquiryRepository instance;

    public EnquiryRepository() {
        //setFilepath(DATA_PATH + OFFICER_CSV);
        //fetch();
        this.listOfModels.add(new Enquiry(
                "12345678",
                "TestPID",
                "TestUID",
                "Message",
                System.currentTimeMillis() / 1000L
        ));
    }

    @Override
public ArrayList<Model> toModelList(ArrayList<ArrayList<String>> csv) {
        ArrayList<Model> officerArr = new ArrayList<>();

//        for (ArrayList<String> strArr : csv) {
//            officerArr.add(new Officer(
//                    strArr.get(1),
//                    strArr.get(0),
//                    Integer.parseInt(strArr.get(2)),
//                    strArr.get(3).charAt(0),
//                    strArr.get(4)));
//        }

        return officerArr;
    }

    @Override
    public ArrayList<ArrayList<String>> toCSV(ArrayList<Model> alm) {
        ArrayList<ArrayList<String>> csvData = new ArrayList<>();

//        // Loop through each Model in alm
//        alm.forEach(model -> {
//            Officer officer = (Officer) model;
//
//            ArrayList<String> row = new ArrayList<>();
//            row.add(officer.getID());       // Add userID
//            row.add(officer.getName());         // Add name
//            row.add(officer.getPassword());
//            row.add(String.valueOf(officer.getAge())); // Convert age to String
//            row.add(String.valueOf(officer.getMaritalStatus())); // Convert maritalStatus to String
//
//            csvData.add(row);
//        });

        return csvData;
    }

    public static EnquiryRepository getInstance() {
        if (instance == null)
            instance = new EnquiryRepository();
        return instance;
    }

}
