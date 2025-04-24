package Logic;

import Data.Models.Application;

import Data.Models.Model;
import Data.Repository.ApplicationRepository;
import Data.Repository.WithdrawalRepository;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;
import Exceptions.RepositoryNotFoundException;
import Exceptions.UnauthorizedActionException;
import Util.DefaultGenerateID;
import Util.Interfaces.IDGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Application logic actions.
 */
public class ApplicationLogicActions extends DataLogicActions<Application>{
    private static ApplicationLogicActions instance;

    /**
     * Instantiates a new Application logic actions.
     *
     * @param idGenerator the id generator
     */
    public ApplicationLogicActions(IDGenerator idGenerator) {
       super(idGenerator);
    }

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
        String applicationID = generateID(); // assuming this exists
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

    /**
     * Delete by flat id.
     *
     * @param flatID the flat id
     * @throws ModelNotFoundException the model not found exception
     */
    public void deleteByFlatID(String flatID) throws ModelNotFoundException{
        List<String> toDeleteIDs = getAllObject()
                .filter(application -> application.getFlatID().equals(flatID))
                .map(Model::getID)
                .toList();

        for (String id : toDeleteIDs) {
                WithdrawalLogicActions.getInstance().deleteByApplicationID(id);
                UserLogicActions.getInstance().removeApplications(id);
                delete(id);
        }
    }

//    public ArrayList<HashMap<String, String>> getApplicationsByProjectID(String projectID) throws ModelNotFoundException {
//        ArrayList<HashMap<String, String>> applicationList = new ArrayList<>();
//        String threeRoomID = ProjectLogicActions.getInstance().get(projectID).get("ThreeRoomFlatID");
//        String twoRoomID = ProjectLogicActions.getInstance().get(projectID).get("TwoRoomFlatID");
//
//        getAllObject()
//                .filter(application ->
//                    threeRoomID.equals(application.getFlatID()) || twoRoomID.equals(application.getFlatID())
//                ).forEach(application -> {
//                    applicationList.add(toMap(application));
//                });
//
//        return applicationList;
//    }

    /**
     * Gets filtered applications by project id.
     *
     * @param projectID the project id
     * @param ashm      the ashm
     * @return the filtered applications by project id
     * @throws ModelNotFoundException the model not found exception
     */
    public ArrayList<HashMap<String, String>> getFilteredApplicationsByProjectID(String projectID, HashMap<String,String> ashm) throws ModelNotFoundException {
        String threeRoomID = ProjectLogicActions.getInstance().get(projectID).get("ThreeRoomFlatID");
        String twoRoomID = ProjectLogicActions.getInstance().get(projectID).get("TwoRoomFlatID");

        boolean hasSearchSetting = (ashm == null);

        return getAllObject()
                .filter(application ->
                        threeRoomID.equals(application.getFlatID()) || twoRoomID.equals(application.getFlatID())
                )
                .filter(application -> {
                    HashMap<String,String> uhm;
                    boolean maritalType;

                    try {
                        uhm = UserLogicActions.getInstance().get(application.getUserID());
                    } catch (ModelNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                    if(hasSearchSetting || ashm.get("MaritalStatus") == null || ashm.get("MaritalStatus").equals(uhm.get("MaritalStatus"))){
                        maritalType = true;
                    }else{
                        maritalType = false;
                    }

                    return maritalType;
                })
                .filter(application ->{
                    HashMap<String,String> fhm;
                    boolean flatType;

                    try {
                        fhm = FlatLogicActions.getInstance().get(application.getFlatID());
                    } catch (ModelNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                    if(hasSearchSetting || ashm.get("FlatType") == null || ashm.get("FlatType").equals(fhm.get("Type"))){
                        flatType = true;
                    }else{
                        flatType = false;
                    }

                    return flatType;
                })
                .map(this::toMap)
                .collect(Collectors.toCollection(ArrayList::new));
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

    /**
     * Apply string.
     *
     * @param userID the user id
     * @param flatID the flat id
     * @return the string
     * @throws UnauthorizedActionException the unauthorized action exception
     * @throws ModelAlreadyExistsException the model already exists exception
     * @throws ModelNotFoundException      the model not found exception
     * @throws RepositoryNotFoundException the repository not found exception
     */
//Double check this
    //Returns string in case we need it.
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

    /**
     * Approve.
     *
     * @param applicationID the application id
     * @throws ModelNotFoundException the model not found exception
     */
    public void approve(String applicationID) throws ModelNotFoundException{
        Application application = getObject(applicationID);
        application.setStatus("Successful");
        ApplicationRepository.getInstance().update();
    }

    /**
     * Reject.
     *
     * @param applicationID the application id
     * @throws ModelNotFoundException the model not found exception
     */
    public void reject(String applicationID) throws ModelNotFoundException{
        Application application = getObject(applicationID);
        application.setStatus("Unsuccessful");
        ApplicationRepository.getInstance().update();
    }

    /**
     * Book.
     *
     * @param applicationID the application id
     * @param OfficerID     the officer id
     * @throws ModelNotFoundException      the model not found exception
     * @throws UnauthorizedActionException the unauthorized action exception
     */
    public void book(String applicationID, String OfficerID) throws ModelNotFoundException, UnauthorizedActionException{
        Application application = getObject(applicationID);
        if(application.getStatus().equals("Successful")) {
            application.setStatus("Booked");
            application.setOfficerID(OfficerID);

            String flatID = application.getFlatID();
            FlatLogicActions.getInstance().book(flatID);

            ApplicationRepository.getInstance().update();
        }
    }

    /**
     * Withdraw.
     *
     * @param ID the id
     * @throws ModelNotFoundException the model not found exception
     */
    public void withdraw(String ID) throws ModelNotFoundException{
        Application application = getObject(ID);
        application.setStatus("Withdrawn");

        ApplicationRepository.getInstance().update();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ApplicationLogicActions getInstance() {
        if (instance == null)
            instance = new ApplicationLogicActions(new DefaultGenerateID());
        return instance;
    }
}
