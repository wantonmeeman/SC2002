package Logic;

import Data.Models.Project;
import Data.Models.Flat;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;
import Logic.UserLogicActions;
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

        projectMap.put("2RoomUnits", String.valueOf(project.getFlats()[0].getTotalUnits()));
        projectMap.put("2RoomPrice", String.valueOf(project.getFlats()[0].getPrice()));
        projectMap.put("3RoomUnits", String.valueOf(project.getFlats()[1].getTotalUnits()));
        projectMap.put("3RoomPrice", String.valueOf(project.getFlats()[1].getPrice()));

        return projectMap;
    }

    public String create(HashMap<String,String> hm) throws ModelAlreadyExistsException{
        String projectID = generateID();
        String name = hm.get("Name");
        String neighbourhood = hm.get("Neighbourhood");
        long openingDate = Long.parseLong(hm.get("OpeningDate"));
        long closingDate = Long.parseLong(hm.get("ClosingDate"));
        int officerSlots = Integer.parseInt(hm.get("OfficerSlots"));
        String[] officerIDs = hm.get("OfficerIDs").split(",");
        String managerID = hm.get("ManagerID");

        // Parse Flats manually since you've split them
        int twoRoomUnits = Integer.parseInt(hm.get("2RoomUnits"));
        float twoRoomPrice = Float.parseFloat(hm.get("2RoomPrice"));
        int threeRoomUnits = Integer.parseInt(hm.get("3RoomUnits"));
        float threeRoomPrice = Float.parseFloat(hm.get("3RoomPrice"));

        Flat[] flats = new Flat[2];
        flats[0] = new Flat("2-Room", twoRoomUnits, twoRoomPrice);
        flats[1] = new Flat("3-Room", threeRoomUnits, threeRoomPrice);

        try {
            ProjectRepository.getInstance().create(
                    new Project(projectID, name, neighbourhood, openingDate,
                    closingDate, officerSlots, officerIDs, managerID, flats));
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
        HashMap<String,String> ss = UserLogicActions.getInstance().getSearchSetting(userID);

        int age = Integer.parseInt(user.get("Age"));
        char status = user.get("MaritalStatus").charAt(0);

        projList = getAllObject()
                .filter(proj ->
                        (age >= 21 && status == 'M') ||
                                (age >= 35 && status == 'S' && proj.getFlats()[0].getTotalUnits() > 0)
                )
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
