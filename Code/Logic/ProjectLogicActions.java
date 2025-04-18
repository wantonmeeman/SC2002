package Logic;

import Data.Models.Project;
import Data.Models.Flat;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;
import Logic.UserLogicActions;
import Logic.SearchSettingLogicActions;
import Logic.FlatLogicActions;
import Data.Repository.ProjectRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProjectLogicActions extends DataLogicActions<Project>{
    private static ProjectLogicActions instance;

    @Override
    protected HashMap<String, String> toMap(Project project) {
        HashMap<String, String> projectMap = new HashMap<>();

        projectMap.put("ID", project.getID());
        projectMap.put("Name", project.getName());
        projectMap.put("Neighbourhood", project.getNeighbourhood());
        projectMap.put("OpeningDate", String.valueOf(project.getOpeningDate()));
        projectMap.put("ClosingDate", String.valueOf(project.getClosingDate()));
        projectMap.put("Visibility", String.valueOf(project.isVisible()));
        projectMap.put("OfficerSlots", String.valueOf(project.getOfficerSlots()));
        projectMap.put("OfficerIDs", String.join(",", project.getOfficersIDs()));
        projectMap.put("ManagerID", project.getManagerID());
        projectMap.put("TwoRoomFlatID", project.getTwoRoomFlatID());
        projectMap.put("ThreeRoomFlatID", project.getThreeRoomFlatID());

        return projectMap;
    }

    public String create(HashMap<String,String> hm) throws ModelAlreadyExistsException{
        String projectID = generateID();
        String name = hm.get("Name");
        String neighbourhood = hm.get("Neighbourhood");
        long openingDate = Long.parseLong(hm.get("OpeningDate"));
        long closingDate = Long.parseLong(hm.get("ClosingDate"));
        boolean visibility = Boolean.parseBoolean(hm.get("Visibility"));
        int officerSlots = Integer.parseInt(hm.get("OfficerSlots"));
        String[] officerIDs = hm.get("OfficerIDs").split(",");
        String managerID = hm.get("ManagerID");
        String twoRoomFlatID = hm.get("TwoRoomFlatID");
        String threeRoomFlatID = hm.get("ThreeRoomFlatID");

        try {
            ProjectRepository.getInstance().create(
                    new Project(
                            projectID, name, neighbourhood, openingDate,
                    closingDate, visibility ,officerSlots, officerIDs, managerID, twoRoomFlatID, threeRoomFlatID));
        }catch(ModelAlreadyExistsException e){
            create(hm);//Try again
        }

        return projectID;
    }

    @Override
    protected Stream<Project> getAllObject(){
        return ProjectRepository.getInstance().getAll()
                .stream()
                .map(model -> (Project) model);
    }

    public ArrayList<HashMap<String,String>> getAllFiltered(String userID) throws ModelNotFoundException{
        ArrayList<HashMap<String, String>> projList = new ArrayList<>();

        HashMap<String,String> user = UserLogicActions.getInstance().get(userID);
        HashMap<String,String> ss = SearchSettingLogicActions.getInstance().get(userID);

        int age = Integer.parseInt(user.get("Age"));
        char status = user.get("MaritalStatus").charAt(0);

        projList = getAllObject()
                .filter(proj -> {
                    int totalFlats;
                    try {
                        totalFlats = Integer.parseInt(FlatLogicActions.getInstance().get(proj.getTwoRoomFlatID()).get("TotalUnits"));
                    } catch (ModelNotFoundException e) {
                        totalFlats = 0;
                    }

                    return (age >= 21 && status == 'M') ||
                            (age >= 35 && status == 'S' && totalFlats > 0);
                })
                .map(this::toMap) // <-- Replace with actual method to convert Project to HashMap
                .collect(Collectors.toCollection(ArrayList::new));

        return projList;
    }

    @Override
    public void delete(String ID) throws ModelNotFoundException{
        ProjectRepository.getInstance().delete(ID);
    }

    public static ProjectLogicActions getInstance() {
        if (instance == null)
            instance = new ProjectLogicActions();
        return instance;
    }
}
