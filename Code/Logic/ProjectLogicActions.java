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

        if(project.getTwoRoomFlatID().charAt(0) == '-'){
            projectMap.put("TwoRoomFlatID", null);
            project.setTwoRoomFlatID(project.getTwoRoomFlatID().substring(1));
        }else{
            projectMap.put("TwoRoomFlatID", project.getTwoRoomFlatID());
        }

        if(project.getThreeRoomFlatID().charAt(0) == '-'){
            projectMap.put("ThreeRoomFlatID", null);
            project.setThreeRoomFlatID(project.getThreeRoomFlatID().substring(1));
        }else{
            projectMap.put("ThreeRoomFlatID", project.getThreeRoomFlatID());
        }


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
        HashMap<String, String> ss = SearchSettingLogicActions.getInstance().get(userID);

        int age = Integer.parseInt(user.get("Age"));
        char status = user.get("MaritalStatus").charAt(0);

        projList = getAllObject()
                .filter(proj -> {
                    //Check Marital Status and age
                    int totalTwoRoomFlats;
                    int totalThreeRoomFlats;

                    try {
                        totalTwoRoomFlats = Integer.parseInt(FlatLogicActions.getInstance().get(proj.getTwoRoomFlatID()).get("TotalUnits"));                        totalThreeRoomFlats = Integer.parseInt(FlatLogicActions.getInstance().get(proj.getThreeRoomFlatID()).get("TotalUnits"));
                    } catch (ModelNotFoundException e) {
                        totalTwoRoomFlats = 0;
                    }

                    try {
                        totalThreeRoomFlats = Integer.parseInt(FlatLogicActions.getInstance().get(proj.getThreeRoomFlatID()).get("TotalUnits"));
                    } catch (ModelNotFoundException e) {
                        totalThreeRoomFlats = 0;
                    }

                    if(totalTwoRoomFlats <= 0
                    || (status == 'M' && age < 21)
                    || (status == 'S' && age < 35)
                    ){
                        proj.setTwoRoomFlatID("-"+proj.getTwoRoomFlatID());
                    }

                    if(totalThreeRoomFlats <= 0
                    || status == 'S'
                    || (status == 'M' && age < 21)){
                        proj.setThreeRoomFlatID("-"+proj.getThreeRoomFlatID());
                    }

                    boolean maritalStatusAgeCheck = proj.getThreeRoomFlatID().charAt(0) != '-' || proj.getTwoRoomFlatID().charAt(0) != '-';
                    boolean timeCheck = true;//proj.getOpeningDate() <= System.currentTimeMillis() / 1000L && System.currentTimeMillis() / 1000L <= proj.getClosingDate();

                    return maritalStatusAgeCheck && proj.isVisible() && timeCheck;
                })
                .map(this::toMap)
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
