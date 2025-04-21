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
                    strArr.get(0),
                    strArr.get(1),
                    Boolean.parseBoolean(strArr.get(2)),
                    strArr.get(3),
                    Boolean.parseBoolean(strArr.get(4)),
                    Boolean.parseBoolean(strArr.get(5)),
                    strArr.get(6)
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
            row.add(searchSetting.getProjectName());
            row.add(String.valueOf(searchSetting.getProjectAscending()));
            row.add(searchSetting.getProjectNeighbourhoodID());
            row.add(String.valueOf(searchSetting.getProjectThreeRoomFlat()));
            row.add(String.valueOf(searchSetting.getProjectTwoRoomFlat()));
            row.add(searchSetting.getProjectManagerID());           // sortBy

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
