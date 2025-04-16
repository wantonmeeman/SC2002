package Data.Repository;

import Data.Models.Model;
import Data.Models.Project;
import Data.Models.Flat;

import Util.Config.*;

import java.util.ArrayList;
import java.util.Date;

public class ProjectRepository extends DataRepository {
    private static ProjectRepository instance;

    public ProjectRepository() {
        //setFilepath(DATA_PATH + OFFICER_CSV);
        //fetch();
        this.listOfModels.add(new Project(
                "TestPID",
                "Test Project23",
                "Test Neighbourhood",
                System.currentTimeMillis() / 1000L,                     // openingDate (Unix time)
                (System.currentTimeMillis() / 1000L) + (86400 * 30),    // closingDate: 30 days later
                5,                                                      // officerSlots
                new String[] {"T2109876H", "S6543210I"},//TODO maybe use an arrayList?
                "T8765432F",
                new Flat[] {
                new Flat("2-Room", 50, 200000.0f),
                new Flat("3-Room", 30, 300000.0f)}
        ));
        this.listOfModels.add(new Project(
                "TestPID",
                "Test Project2",
                "Test Neighbourhood",
                System.currentTimeMillis() / 1000L,                     // openingDate (Unix time)
                (System.currentTimeMillis() / 1000L) + (86400 * 30),    // closingDate: 30 days later
                5,                                                      // officerSlots
                new String[] {"T2109876H", "S6543210I"},//TODO maybe use an arrayList?
                "T8765432F",
                new Flat[] {
                        new Flat("2-Room", 50, 200000.0f),
                        new Flat("3-Room", 0, 300000.0f)}
        ));
        this.listOfModels.add(new Project(
                "TestPID",
                "Test Project3",
                "Test Neighbourhood",
                System.currentTimeMillis() / 1000L,                     // openingDate (Unix time)
                (System.currentTimeMillis() / 1000L) + (86400 * 30),    // closingDate: 30 days later
                5,                                                      // officerSlots
                new String[] {"T2109876H", "S6543210I"},//TODO maybe use an arrayList?
                "T8765432F",
                new Flat[] {
                        new Flat("2-Room", 0, 200000.0f),
                        new Flat("3-Room", 0, 300000.0f)}
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

    public static ProjectRepository getInstance() {
        if (instance == null)
            instance = new ProjectRepository();
        return instance;
    }

}
