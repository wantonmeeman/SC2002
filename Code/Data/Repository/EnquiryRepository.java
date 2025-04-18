package Data.Repository;

import Data.Models.Model;

import java.util.ArrayList;

import static Util.Config.*;
import Data.Models.Enquiry;

public class EnquiryRepository extends DataRepository {
    private static EnquiryRepository instance;

    public EnquiryRepository() {
        setFilepath(DATA_PATH + ENQUIRY_CSV);
        fetch();
    }

    @Override
public ArrayList<Model> toModelList(ArrayList<ArrayList<String>> csv) {
        ArrayList<Model> officerArr = new ArrayList<>();

        for (ArrayList<String> strArr : csv) {
            officerArr.add(new Enquiry(
                    strArr.get(0),
                    strArr.get(1),
                    strArr.get(2),
                    strArr.get(3),
                    strArr.get(4),
                    Long.parseLong(strArr.get(5))
            ));
        }

        return officerArr;
    }

    @Override
    public ArrayList<ArrayList<String>> toCSV(ArrayList<Model> alm) {
        ArrayList<ArrayList<String>> csvData = new ArrayList<>();

        // Loop through each Model in alm
        alm.forEach(model -> {
            Enquiry enquiry = (Enquiry) model;

            ArrayList<String> row = new ArrayList<>();
            row.add(enquiry.getID());       // Add userID
            row.add(enquiry.getProjectID());         // Add name
            row.add(enquiry.getUserID());
            row.add(enquiry.getMessage()); // Convert age to String
            row.add(enquiry.getReply()); // Convert maritalStatus to String
            row.add(String.valueOf(enquiry.getTimestamp()));

            csvData.add(row);
        });

        return csvData;
    }

    public static EnquiryRepository getInstance() {
        if (instance == null)
            instance = new EnquiryRepository();
        return instance;
    }

}
