package Logic;

import Data.Models.SearchSetting;
import Data.Repository.SearchSettingRepository;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;
import Logic.DataLogicActions;
import Util.GenerateID;

import java.util.HashMap;
import java.util.stream.Stream;

public class SearchSettingLogicActions extends DataLogicActions<SearchSetting>{
    private static SearchSettingLogicActions instance;

    @Override
    protected HashMap<String, String> toMap(SearchSetting searchSetting) {
        HashMap<String, String> searchSettingMap = new HashMap<>();

        searchSettingMap.put("ID", searchSetting.getID());
        searchSettingMap.put("Location", searchSetting.getLocation());

        // Convert Boolean[] to a comma-separated string
        Boolean[] flatTypes = searchSetting.getFlatTypes();
        searchSettingMap.put("FlatTypes", flatTypes[0] + "," + flatTypes[1]);

        searchSettingMap.put("Neighbourhood", searchSetting.getNeighbourhood());
        searchSettingMap.put("OpeningDate", String.valueOf(searchSetting.getOpeningDate()));
        searchSettingMap.put("ClosingDate", String.valueOf(searchSetting.getClosingDate()));
        searchSettingMap.put("Ascending", String.valueOf(searchSetting.getAscending()));
        searchSettingMap.put("SortBy", String.valueOf(searchSetting.getSortBy()));

        return searchSettingMap;
    }

    public String create(HashMap<String, String> hm) throws ModelAlreadyExistsException {
        String searchSettingID = GenerateID.generateID(8);
        String location = hm.get("Location");

        // Parse FlatTypes from string to Boolean[]
        String[] flatTypeStr = hm.get("FlatTypes").split(",");
        Boolean[] flatTypes = new Boolean[] {
                Boolean.parseBoolean(flatTypeStr[0]),
                Boolean.parseBoolean(flatTypeStr[1])
        };

        String neighbourhood = hm.get("Neighbourhood");
        long openingDate = Long.parseLong(hm.get("OpeningDate"));
        long closingDate = Long.parseLong(hm.get("ClosingDate"));
        Boolean ascending = Boolean.parseBoolean(hm.get("Ascending"));
        int sortBy = Integer.parseInt(hm.get("SortBy"));

        SearchSetting searchSetting = new SearchSetting(
                searchSettingID, location, flatTypes, neighbourhood, openingDate, closingDate, ascending, sortBy
        );

        SearchSettingRepository.getInstance().create(searchSetting);

        return searchSettingID;
    }

    @Override
    protected Stream<SearchSetting> getAllObject(){
        return SearchSettingRepository.getInstance().getAll()
                .stream()
                .map(model -> (SearchSetting) model);
    }

    @Override
    public void delete(String ID) throws ModelNotFoundException{
        SearchSettingRepository.getInstance().delete(ID);
    }

    public static SearchSettingLogicActions getInstance() {
        if (instance == null)
            instance = new SearchSettingLogicActions();
        return instance;
    }
}
