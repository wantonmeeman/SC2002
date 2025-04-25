package Logic;

import Data.Models.Officer;
import Data.Models.Project;
import Data.Models.Flat;
import Data.Models.User;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;
import Exceptions.UnauthorizedActionException;
import Exceptions.WrongInputException;
import Logic.UserLogicActions;
import Logic.SearchSettingLogicActions;
import Logic.FlatLogicActions;
import Data.Repository.ProjectRepository;
import Util.DefaultGenerateID;
import Util.Interfaces.IDGenerator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Project logic actions.
 */
public class ProjectLogicActions extends DataLogicActions<Project>{
    private static ProjectLogicActions instance;

    /**
     * Instantiates a new Project logic actions.
     *
     * @param idGenerator the id generator
     */
    public ProjectLogicActions(IDGenerator idGenerator) {
        super(idGenerator);
    }

    @Override
    protected HashMap<String, String> toMap(Project project) {
        HashMap<String, String> projectMap = new HashMap<>();

        projectMap.put("ID", project.getID());
        projectMap.put("Name", project.getName());
        projectMap.put("NeighbourhoodID", project.getNeighbourhoodID());
        projectMap.put("OpeningDate", String.valueOf(project.getOpeningDate()));
        projectMap.put("ClosingDate", String.valueOf(project.getClosingDate()));
        projectMap.put("Visibility", String.valueOf(project.isVisible()));
        projectMap.put("OfficerSlots", String.valueOf(project.getOfficerSlots()));
        projectMap.put("ManagerID", project.getManagerID());
        projectMap.put("TwoRoomFlatID", project.getTwoRoomFlatID());
        projectMap.put("ThreeRoomFlatID", project.getThreeRoomFlatID());

        return projectMap;
    }

    public String create(HashMap<String,String> hm){
        String projectID = generateID();

        String name = hm.get("Name");
        String neighbourhoodID = hm.get("NeighbourhoodID");
        long openingDate = Long.parseLong(hm.get("OpeningDate"));
        long closingDate = Long.parseLong(hm.get("ClosingDate"));
        boolean visibility = Boolean.parseBoolean(hm.get("Visibility"));
        int officerSlots = Integer.parseInt(hm.get("OfficerSlots"));
        String managerID = hm.get("ManagerID");
        String twoRoomFlatID = hm.get("TwoRoomFlatID");
        String threeRoomFlatID = hm.get("ThreeRoomFlatID");

        try {
            ProjectRepository.getInstance().create(
                    new Project(
                            projectID, name, neighbourhoodID, openingDate,
                    closingDate, visibility ,officerSlots,  managerID, twoRoomFlatID, threeRoomFlatID));
        }catch(ModelAlreadyExistsException e){
            create(hm);//Try again
        }

        return projectID;
    }

    /**
     * Create project string.
     *
     * @param hm the hm
     * @return the string
     * @throws UnauthorizedActionException the unauthorized action exception
     */
    public String createProject(HashMap<String,String> hm) throws UnauthorizedActionException{
        boolean canCreate = true;

        ArrayList<HashMap<String,String>> pal = ProjectLogicActions.getInstance().getAll();
        for(HashMap<String,String> phm:pal){
            boolean timeCheck = Long.parseLong(phm.get("OpeningDate")) <= Long.parseLong(hm.get("ClosingDate")) && Long.parseLong(hm.get("OpeningDate")) <= Long.parseLong(phm.get("ClosingDate"));
            boolean managerCheck = phm.get("ManagerID").equals(hm.get("ManagerID"));
            if(timeCheck && managerCheck){
                canCreate = false;
            }
        }

        if(canCreate){
            return create(hm);
        }else{
            throw new UnauthorizedActionException();
        }
    }

    @Override
    protected Stream<Project> getAllObject(){
        return ProjectRepository.getInstance().getAll()
                .stream()
                .map(model -> (Project) model);
    }

    /**
     * Gets all manager filtered.
     *
     * @param userID the user id
     * @return the all manager filtered
     * @throws ModelNotFoundException the model not found exception
     */
    public ArrayList<HashMap<String,String>> getAllManagerFiltered(String userID) throws ModelNotFoundException{
        ArrayList<HashMap<String, String>> projList = new ArrayList<>();
        HashMap<String, String> ss = new HashMap<>();
        try {
            ss = SearchSettingLogicActions.getInstance().get(userID);
        }catch(ModelNotFoundException e){
            //Create a new ss

            ss = new HashMap<String,String>();

            ss.put("ID", userID);
            ss.put("ProjectName", null);
            ss.put("ProjectAscending", "true");
            ss.put("ProjectNeighbourhoodID", null);
            ss.put("ProjectThreeRoomFlat", "true");
            ss.put("ProjectTwoRoomFlat","true");
            ss.put("ProjectManagerID",null);

            SearchSettingLogicActions.getInstance().create(ss);
        }

        String projectManagerID = ss.get("ProjectManagerID");
        String projectNeighbourhood = ss.get("ProjectNeighbourhoodID");
        String projectName = ss.get("ProjectName");


        boolean ascending = Boolean.parseBoolean(ss.get("ProjectAscending"));

        Comparator<HashMap<String,String>> naturalOrder = Comparator.comparing(s -> s.get("Name"));

        naturalOrder = ascending ? naturalOrder : naturalOrder.reversed();

        projList = getAllObject()
                .filter(proj -> projectManagerID == null || projectManagerID.equals(proj.getManagerID()))
                .filter(proj->projectNeighbourhood == null || projectNeighbourhood.equals(proj.getNeighbourhoodID()))
                .filter(proj-> projectName == null || Pattern.compile(projectName).matcher(proj.getName()).find())
                .map(this::toMap)
                .sorted(naturalOrder)
                .collect(Collectors.toCollection(ArrayList::new));

        return projList;
    }


    /**
     * Gets all filtered.
     *
     * @param userID the user id
     * @return the all filtered
     * @throws ModelNotFoundException the model not found exception
     */
    public ArrayList<HashMap<String,String>> getAllFiltered(String userID) throws ModelNotFoundException{
        ArrayList<HashMap<String, String>> projList = new ArrayList<>();

        HashMap<String,String> user = UserLogicActions.getInstance().get(userID);
            HashMap<String, String> ss;
        try {
            ss = SearchSettingLogicActions.getInstance().get(userID);
        }catch(ModelNotFoundException e){
            //Create a new ss

            ss = new HashMap<String,String>();

            ss.put("ID", userID);
            ss.put("ProjectName", null);
            ss.put("ProjectAscending", "true");
            ss.put("ProjectNeighbourhoodID", null);
            ss.put("ProjectThreeRoomFlat", "true");
            ss.put("ProjectTwoRoomFlat","true");
            ss.put("ProjectManagerID",null);

            SearchSettingLogicActions.getInstance().create(ss);
        }

        int age = Integer.parseInt(user.get("Age"));
        char status = user.get("MaritalStatus").charAt(0);

        boolean ascending = Boolean.parseBoolean(ss.get("ProjectAscending"));
        boolean threeRoomFlatFilter = Boolean.parseBoolean(ss.get("ProjectThreeRoomFlat"));
        boolean twoRoomFlatFilter = Boolean.parseBoolean(ss.get("ProjectTwoRoomFlat"));

            String projectManagerID = ss.get("ProjectManagerID");
            String projectNeighbourhood = ss.get("ProjectNeighbourhoodID");
            String projectName = ss.get("ProjectName");

        Comparator<HashMap<String,String>> naturalOrder = Comparator.comparing(s -> s.get("Name"));

        naturalOrder = ascending ? naturalOrder : naturalOrder.reversed();

        projList = getAllObject()
                    .filter(proj -> projectManagerID == null || projectManagerID.equals(proj.getManagerID()))
                    .filter(proj->projectNeighbourhood == null || projectNeighbourhood.equals(proj.getNeighbourhoodID()))
                    .filter(proj-> projectName == null || Pattern.compile(projectName).matcher(proj.getName()).find())


                    .map(this::toMap)
                .filter(proj -> {
                    //Check Marital Status and age
                    int totalTwoRoomFlats;
                    int totalThreeRoomFlats;
                    String twoRoomFlatID = proj.get("TwoRoomFlatID");
                    String threeRoomFlatID =  proj.get("ThreeRoomFlatID");

                    try {
                        totalTwoRoomFlats = Integer.parseInt(FlatLogicActions.getInstance().get(twoRoomFlatID).get("TotalUnits"));
                    } catch (ModelNotFoundException e) {
                        totalTwoRoomFlats = 0;
                    }

                    try {
                        totalThreeRoomFlats = Integer.parseInt(FlatLogicActions.getInstance().get(threeRoomFlatID).get("TotalUnits"));
                    } catch (ModelNotFoundException e) {
                        totalThreeRoomFlats = 0;
                    }

                    boolean twoRoomMaritalAgeCheck = (status == 'M' && age < 21) || (status == 'S' && age < 35);
                    boolean threeRoomMaritalAgeCheck = status == 'S' || (status == 'M' && age < 21);

                    if(totalTwoRoomFlats <= 0
                    || twoRoomMaritalAgeCheck
                    || !twoRoomFlatFilter
                    ){
                        proj.put("TwoRoomFlatID",null);
                    }

                    if(totalThreeRoomFlats <= 0
                    || threeRoomMaritalAgeCheck
                    || !threeRoomFlatFilter
                    ){
                        proj.put("ThreeRoomFlatID",null);
                    }

                    boolean maritalStatusAgeCheck = proj.get("ThreeRoomFlatID") != null || proj.get("TwoRoomFlatID") != null;
                    boolean timeCheck = Long.parseLong(proj.get("OpeningDate")) <= System.currentTimeMillis() / 1000L && System.currentTimeMillis() / 1000L <= Long.parseLong(proj.get("ClosingDate"));
                    boolean visible = Boolean.parseBoolean(proj.get("Visibility"));

                    return maritalStatusAgeCheck && visible && timeCheck;
                })
                .sorted(naturalOrder)
                .collect(Collectors.toCollection(ArrayList::new));

        return projList;
    }

    /**
     * Gets active project by manager id.
     *
     * @param managerID the manager id
     * @return the active project by manager id
     * @throws ModelNotFoundException the model not found exception
     */
    public HashMap<String,String> getActiveProjectByManagerID(String managerID) throws ModelNotFoundException{

        Optional<Project> projOpt = getAllObject().filter(
                project -> project.getManagerID().equals(managerID)
                            && System.currentTimeMillis() / 1000L > project.getOpeningDate()
                            && System.currentTimeMillis() / 1000L < project.getClosingDate()
        ).findFirst();

        if (projOpt.isPresent()) {
            return toMap(projOpt.get());
        }else{
            throw new ModelNotFoundException();
        }
    }

    /**
     * Gets project by flat id.
     *
     * @param flatID the flat id
     * @return the project by flat id
     * @throws ModelNotFoundException the model not found exception
     */
    public HashMap<String,String> getProjectByFlatID(String flatID) throws ModelNotFoundException {
        Optional<Project> projOpt = getAllObject().filter(
                        project -> project.getThreeRoomFlatID().equals(flatID) || project.getTwoRoomFlatID().equals(flatID)
        ).findFirst();

        if (projOpt.isPresent()) {
            return toMap(projOpt.get());
        }else{
            throw new ModelNotFoundException();
        }
    }

    /**
     * Toggle.
     *
     * @param projectID the project id
     * @throws ModelNotFoundException the model not found exception
     */
    public void toggle(String projectID) throws ModelNotFoundException{
        getObject(projectID).toggleVisibility();
        ProjectRepository.getInstance().update();
    }

    /**
     * Edit name.
     *
     * @param projectID the project id
     * @param name      the name
     * @throws ModelNotFoundException the model not found exception
     */
    public void editName(String projectID,String name) throws ModelNotFoundException {
        getObject(projectID).setName(name);

        ProjectRepository.getInstance().update();
    }

    /**
     * Edit neighbourhood.
     *
     * @param projectID       the project id
     * @param neighbourhoodID the neighbourhood id
     * @throws ModelNotFoundException the model not found exception
     */
    public void editNeighbourhood(String projectID,String neighbourhoodID) throws ModelNotFoundException{
        getObject(projectID).setNeighbourhoodID(neighbourhoodID);
        ProjectRepository.getInstance().update();
    }

    /**
     * Edit opening closing.
     *
     * @param projectID the project id
     * @param opening   the opening
     * @param closing   the closing
     * @throws ModelNotFoundException the model not found exception
     */
    public void editOpeningClosing(String projectID, String opening, String closing) throws ModelNotFoundException{
        //Processing
        long openingLong = LocalDate.parse(opening).atStartOfDay(ZoneId.of("UTC")).toInstant().getEpochSecond();
        long closingLong = LocalDate.parse(closing).atStartOfDay(ZoneId.of("UTC")).toInstant().getEpochSecond();

        getObject(projectID).setOpeningDate(openingLong);
        getObject(projectID).setClosingDate(closingLong);
        ProjectRepository.getInstance().update();
    }

    @Override
    public void delete(String ID) throws ModelNotFoundException{
        EnquiryLogicActions.getInstance().deleteByProjectID(ID);
        RegistrationLogicActions.getInstance().deleteByProjectID(ID);

        Optional<Project> matchedProject = getAllObject()
                .filter(project -> project.getID().equals(ID))
                .findFirst();

        if (matchedProject.isPresent()) {
            Project project = matchedProject.get();

            FlatLogicActions.getInstance().deleteAllLinked(project.getThreeRoomFlatID());
            FlatLogicActions.getInstance().deleteAllLinked(project.getTwoRoomFlatID());
        }

        ProjectRepository.getInstance().delete(ID);
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ProjectLogicActions getInstance() {
        if (instance == null)
            instance = new ProjectLogicActions(new DefaultGenerateID());
        return instance;
    }
}
