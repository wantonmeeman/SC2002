package Data.Repository;

import Data.Models.Model;
import static Util.Config.*;

import java.util.ArrayList;
import Data.Models.Registration;

public class RegistrationRepository extends DataRepository {
    private static RegistrationRepository instance;

    public RegistrationRepository() {
        setFilepath(DATA_PATH + REGISTRATION_CSV);
        fetch();
    }

    @Override
    public ArrayList<Model> toModelList(ArrayList<ArrayList<String>> csv) {
        ArrayList<Model> registrationArr = new ArrayList<>();

        for (ArrayList<String> strArr : csv) {
            registrationArr.add(new Registration(
                strArr.get(0),
                strArr.get(1),
                strArr.get(2),
                strArr.get(3)
            ));
        }

        return registrationArr;
    }

    @Override
    public ArrayList<ArrayList<String>> toCSV(ArrayList<Model> alm) {
        ArrayList<ArrayList<String>> csvData = new ArrayList<>();

        // Loop through each Model in alm
        alm.forEach(model -> {
            Registration registration = (Registration) model;

            ArrayList<String> row = new ArrayList<>();
            row.add(registration.getID());
            row.add(registration.getOfficerID());       // Add userID
            row.add(registration.getProjectID());         // Add name
            row.add(registration.getStatus());

            csvData.add(row);
        });

        return csvData;
    }

    public static RegistrationRepository getInstance() {
        if (instance == null)
            instance = new RegistrationRepository();
        return instance;
    }

}
