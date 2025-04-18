package Data.Repository;

import Data.Models.Model;
import Data.Models.SearchSetting;

import static Util.Config.*;

import java.util.ArrayList;

public class SearchSettingRepository extends DataRepository {
    private static SearchSettingRepository instance;

    public SearchSettingRepository() {
        setFilepath(DATA_PATH + SEARCH_SETTING_CSV);
        fetch();
    }

    @Override
    public ArrayList<Model> toModelList(ArrayList<ArrayList<String>> csv) {
        ArrayList<Model> searchSettingArr = new ArrayList<>();

        for (ArrayList<String> strArr : csv) {
            searchSettingArr.add(new SearchSetting(
                    strArr.get(0), // userID (String)
                    strArr.get(1), // location (String)
                    new Boolean[] {
                            Boolean.parseBoolean(strArr.get(2)), // flatTypes[0]
                            Boolean.parseBoolean(strArr.get(3))  // flatTypes[1]
                    },
                    strArr.get(4), // neighbourhood (String)
                    Long.parseLong(strArr.get(5)), // openingDate (long)
                    Long.parseLong(strArr.get(6)), // closingDate (long)
                    Boolean.parseBoolean(strArr.get(7)), // ascending (Boolean)
                    Integer.parseInt(strArr.get(8)) // sortBy (int)
            ));
        }

        return searchSettingArr;
    }

    @Override
    public ArrayList<ArrayList<String>> toCSV(ArrayList<Model> alm) {
        ArrayList<ArrayList<String>> csvData = new ArrayList<>();

        // Loop through each Model in alm
        alm.forEach(model -> {
            SearchSetting searchSetting = (SearchSetting) model;

            ArrayList<String> row = new ArrayList<>();
            row.add(searchSetting.getID());                                // userID
            row.add(searchSetting.getLocation());                          // location
            row.add(String.valueOf(searchSetting.getFlatTypes()[0]));      // flatType 0
            row.add(String.valueOf(searchSetting.getFlatTypes()[1]));      // flatType 1
            row.add(searchSetting.getNeighbourhood());                     // neighbourhood
            row.add(String.valueOf(searchSetting.getOpeningDate()));       // openingDate
            row.add(String.valueOf(searchSetting.getClosingDate()));       // closingDate
            row.add(String.valueOf(searchSetting.getAscending()));         // ascending
            row.add(String.valueOf(searchSetting.getSortBy()));            // sortBy

            csvData.add(row);
        });

        return csvData;
    }

    public static SearchSettingRepository getInstance() {
        if (instance == null)
            instance = new SearchSettingRepository();
        return instance;
    }

}
