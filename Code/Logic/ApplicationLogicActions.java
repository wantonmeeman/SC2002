package Logic;

import Data.Models.Application;

import Data.Repository.ApplicationRepository;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;
import Exceptions.RepositoryNotFoundException;
import Exceptions.UnauthorizedActionException;
import Util.GenerateID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class ApplicationLogicActions extends DataLogicActions<Application>{
    private static ApplicationLogicActions instance;

    @Override
    protected HashMap<String, String> toMap(Application application) {
        HashMap<String, String> applicationMap = new HashMap<>();

        applicationMap.put("ID", application.getID());
        applicationMap.put("UserID", application.getUserID());
        applicationMap.put("FlatID", application.getFlatID());
        applicationMap.put("Status", application.getStatus());
        applicationMap.put("OfficerID", application.getOfficerID());

        return applicationMap;
    }

    public String create(HashMap<String, String> hm){
        String applicationID = GenerateID.generateID(8); // assuming this exists
        String userID = hm.get("UserID");
        String flatID = hm.get("FlatID");

        Application app = new Application(applicationID, userID, flatID,"Pending", null);

        try {
            ApplicationRepository.getInstance().create(app); // assuming ApplicationRepository is set up
        } catch (ModelAlreadyExistsException e) {
            create(hm);
        }
        return applicationID;
    }

    @Override
    protected Stream<Application> getAllObject(){
        return ApplicationRepository.getInstance().getAll()
                .stream()
                .map(model -> (Application) model);
    }

    @Override
    public void delete(String ID) throws ModelNotFoundException{
        ApplicationRepository.getInstance().delete(ID);
    }

    public ArrayList<HashMap<String, String>> getApplicationsByProjectID(String projectID) throws ModelNotFoundException {
        ArrayList<HashMap<String, String>> applicationList = new ArrayList<>();
        String threeRoomID = ProjectLogicActions.getInstance().get(projectID).get("ThreeRoomFlatID");
        String twoRoomID = ProjectLogicActions.getInstance().get(projectID).get("TwoRoomFlatID");

        getAllObject()
                .filter(application ->
                    threeRoomID.equals(application.getFlatID()) || twoRoomID.equals(application.getFlatID())
                ).forEach(application -> {
                    applicationList.add(toMap(application));
                });

        return applicationList;
    }

    public ArrayList<HashMap<String, String>> getFilteredApplicationsByProjectID(String projectID, HashMap<String,String> ashm) throws ModelNotFoundException {
        ArrayList<HashMap<String, String>> applicationList = new ArrayList<>();
        String threeRoomID = ProjectLogicActions.getInstance().get(projectID).get("ThreeRoomFlatID");
        String twoRoomID = ProjectLogicActions.getInstance().get(projectID).get("TwoRoomFlatID");

        getAllObject()
                .filter(application ->
                        threeRoomID.equals(application.getFlatID()) || twoRoomID.equals(application.getFlatID())
                )
                .filter(application -> {
                    HashMap<String,String> uhm;
                    HashMap<String,String> fhm;

                    try {
                        uhm = UserLogicActions.getInstance().get(application.getUserID());
                    } catch (ModelNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        fhm = FlatLogicActions.getInstance().get(application.getFlatID());
                    } catch (ModelNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                    boolean maritalType;
                    boolean flatType;

                    if(ashm.get("MaritalStatus") == null || ashm.get("MaritalStatus").equals(uhm.get("MaritalStatus"))){
                        maritalType = true;
                    }else{
                        maritalType = false;
                    }

                    if(ashm.get("FlatType") == null || ashm.get("FlatType").equals(fhm.get("Type"))){
                        flatType = true;
                    }else{
                        flatType = false;
                    }

                    return flatType && maritalType;
                })
                .forEach(application -> {
                    applicationList.add(toMap(application));
                });

        return applicationList;
    }

    private Boolean checkApplicationValidity(String userID, String flatID) throws ModelNotFoundException{
        HashMap<String,String> uhm = UserLogicActions.getInstance().get(userID);
        HashMap<String,String> fhm = FlatLogicActions.getInstance().get(flatID);

        String applicationID = uhm.get("ApplicationID");
        int age = Integer.parseInt(uhm.get("Age"));
        char maritalStatus = uhm.get("MaritalStatus").charAt(0);

        String type = fhm.get("Type");

        //Check if Single Married criteria
        if(type.equals("2Room")){
            if((maritalStatus == 'M' && age < 21) || (maritalStatus == 'S' && age < 35)){
                return false;
            }
        } else if(type.equals("3Room")){
            if(maritalStatus == 'S' || (maritalStatus == 'M' && age < 21)){
                return false;
            }
        }

        //If the user has an active application
        if(applicationID != null) {
            HashMap<String, String> ahm = ApplicationLogicActions.getInstance().get(applicationID);
            String status = ahm.get("Status");

            //Check if the User's active application is active
            if (status.equals("Pending") || status.equals("Successful") || status.equals("Booked")) {
                return false;
            }
        }

        String role = uhm.get("Role");
        if(role.equals("Officer")){
            ArrayList<HashMap<String,String>> rArr = RegistrationLogicActions.getInstance().getByOfficerID(userID);
            String projectID = ProjectLogicActions.getInstance().getProjectByFlatID(flatID).get("ID");
            if(!rArr.isEmpty()) {
                for (HashMap<String, String> rhm : rArr) {
                    String registerProjectID = rhm.get("ProjectID");

                    if (
                            (rhm.get("Status").equals("Pending") ||
                                    rhm.get("Status").equals("Successful") ||
                                    rhm.get("Status").equals("Booked")
                            ) && registerProjectID.equals(projectID)
                    ){
                            return false;
                    }
                }
            }
        }
        return true;
    }

    //Double check this
    public String apply(String userID, String flatID) throws UnauthorizedActionException, ModelAlreadyExistsException, ModelNotFoundException,RepositoryNotFoundException{

        if(checkApplicationValidity(userID,flatID)) {
            HashMap<String, String> hm = new HashMap<String,String>();

            hm.put("UserID",userID);
            hm.put("FlatID", flatID);

            String appID = create(hm);
            UserLogicActions.getInstance().apply(hm.get("UserID"), appID);

            ApplicationRepository.getInstance().update();
            return appID;
        }else{
            throw new UnauthorizedActionException();
        }
    }

    public void approve(String applicationID) throws ModelNotFoundException{
        Application application = getObject(applicationID);
        application.setStatus("Successful");
        ApplicationRepository.getInstance().update();
    }

    public void book(String applicationID, String OfficerID) throws ModelNotFoundException{
        Application application = getObject(applicationID);
        if(application.getStatus().equals("Successful")) {
            application.setStatus("Booked");
            application.setOfficerID(OfficerID);

            String flatID = application.getFlatID();
            FlatLogicActions.getInstance().book(flatID);

            ApplicationRepository.getInstance().update();
        }
    }

    public void withdraw(String ID) throws ModelNotFoundException{
        Application application = getObject(ID);
        application.setStatus("Withdrawn");

        ApplicationRepository.getInstance().update();
    }

    public static ApplicationLogicActions getInstance() {
        if (instance == null)
            instance = new ApplicationLogicActions();
        return instance;
    }
}
