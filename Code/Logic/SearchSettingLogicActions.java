package Logic;

import Data.Models.SearchSetting;
import Data.Repository.SearchSettingRepository;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;
import Util.DefaultGenerateID;
import Util.Interfaces.IDGenerator;

import java.util.HashMap;
import java.util.stream.Stream;

public class SearchSettingLogicActions extends DataLogicActions<SearchSetting>{
    private static SearchSettingLogicActions instance;
    public SearchSettingLogicActions(IDGenerator idGenerator) {
        super(idGenerator);
    }
    @Override
    protected HashMap<String, String> toMap(SearchSetting searchSetting) {
        HashMap<String, String> searchSettingMap = new HashMap<>();

        searchSettingMap.put("ID", searchSetting.getID());
        searchSettingMap.put("ProjectName", searchSetting.getProjectName());
        searchSettingMap.put("ProjectAscending", String.valueOf(searchSetting.getProjectAscending()));
        searchSettingMap.put("ProjectNeighbourhoodID", searchSetting.getProjectNeighbourhoodID());
        searchSettingMap.put("ProjectThreeRoomFlat", String.valueOf(searchSetting.getProjectThreeRoomFlat()));
        searchSettingMap.put("ProjectTwoRoomFlat",String.valueOf(searchSetting.getProjectTwoRoomFlat()));
        searchSettingMap.put("ProjectManagerID",searchSetting.getProjectManagerID());

        return searchSettingMap;
    }

    public String create(HashMap<String, String> hm){
        String searchSettingID = hm.get("ID");//GenerateID.generateID(8);
        String projectName = hm.get("ProjectName");
        boolean projectAscending = Boolean.parseBoolean(hm.get("ProjectAscending"));
        String projectNeighbourhoodID = hm.get("ProjectNeighbourhoodID");
        boolean projectThreeRoomFlat = Boolean.parseBoolean(hm.get("ProjectThreeRoomFlat"));
        boolean projectTwoRoomFlat = Boolean.parseBoolean(hm.get("ProjectTwoRoomFlat"));
        String projectManagerID = hm.get("ProjectManagerID");

        SearchSetting searchSetting = new SearchSetting(
                searchSettingID,
                projectName,
                projectAscending,
                projectNeighbourhoodID,
                projectThreeRoomFlat,
                projectTwoRoomFlat,
                projectManagerID
        );

        try {
            SearchSettingRepository.getInstance().create(searchSetting);
        } catch (ModelAlreadyExistsException e) {
            create(hm);
        }

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

    public void toggleSort(String userID) throws ModelNotFoundException{
        SearchSetting ss = getObject(userID);
        ss.setProjectAscending(!ss.getProjectAscending());
        SearchSettingRepository.getInstance().update();
    }

    public void editNeighbourhood(String userID, String neighbourhoodID) throws ModelNotFoundException{
        SearchSetting ss = getObject(userID);
        ss.setProjectNeighbourhoodID(neighbourhoodID);

        SearchSettingRepository.getInstance().update();
    }

    public void editName(String userID, String name) throws ModelNotFoundException{
        SearchSetting ss = getObject(userID);
        ss.setProjectName(name);

        SearchSettingRepository.getInstance().update();
    }

    public void editManager(String userID, String managerID) throws ModelNotFoundException{
        SearchSetting ss = getObject(userID);
        ss.setProjectManagerID(managerID);

        SearchSettingRepository.getInstance().update();
    }

    public void toggleTwoRoomFilter(String userID) throws ModelNotFoundException{
        SearchSetting ss = getObject(userID);
        ss.setProjectTwoRoomFlat(!ss.getProjectTwoRoomFlat());
        SearchSettingRepository.getInstance().update();
    }

    public void toggleThreeRoomFilter(String userID) throws ModelNotFoundException{
        SearchSetting ss = getObject(userID);
        ss.setProjectThreeRoomFlat(!ss.getProjectThreeRoomFlat());
        SearchSettingRepository.getInstance().update();
    }

    public static SearchSettingLogicActions getInstance() {
        if (instance == null)
            instance = new SearchSettingLogicActions(new DefaultGenerateID());
        return instance;
    }
}
