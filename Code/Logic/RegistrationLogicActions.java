package Logic;

import Data.Models.*;
import Data.Repository.RegistrationRepository;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;
import Exceptions.UnauthorizedActionException;
import Util.DefaultGenerateID;
import Util.Interfaces.IDGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * The type Registration logic actions.
 */
public class RegistrationLogicActions extends DataLogicActions<Registration>{
    private static RegistrationLogicActions instance;

    /**
     * Instantiates a new Registration logic actions.
     *
     * @param idGenerator the id generator
     */
    public RegistrationLogicActions(IDGenerator idGenerator) {
        super(idGenerator);
    }

    @Override
    protected HashMap<String, String> toMap(Registration registration) {
        HashMap<String, String> registrationMap = new HashMap<>();

        registrationMap.put("ID", registration.getID());
        registrationMap.put("OfficerID", registration.getOfficerID());
        registrationMap.put("ProjectID", registration.getProjectID());
        registrationMap.put("Status", registration.getStatus());

        return registrationMap;
    }

    public String create(HashMap<String, String> hm){
        String registrationID = generateID(); // assuming this exists
        String officerID = hm.get("OfficerID");
        String projectID = hm.get("ProjectID");
        String status = "Pending"; // default status

        Registration reg = new Registration(registrationID, officerID, projectID, status);

        try {
            RegistrationRepository.getInstance().create(reg); // assuming RegistrationRepository is set up
        } catch (ModelAlreadyExistsException e) {
            create(hm);
        }

        return registrationID;
    }

    @Override
    protected Stream<Registration> getAllObject(){
        return RegistrationRepository.getInstance().getAll()
                .stream()
                .map(model -> (Registration) model);
    }

    @Override
    public void delete(String ID) throws ModelNotFoundException{
        RegistrationRepository.getInstance().delete(ID);
    }

    /**
     * Get by officer id array list.
     *
     * @param userID the user id
     * @return the array list
     */
    public ArrayList<HashMap<String,String>> getByOfficerID(String userID){
        return getAllObject()
                .filter(registration -> {
                    try{
                        return registration.getOfficerID().equals(userID);
                    }catch(NullPointerException e){
                        return false;
                    }
                })
                .map(this::toMap)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Get by project id array list.
     *
     * @param projectID the project id
     * @return the array list
     */
    public ArrayList<HashMap<String,String>> getByProjectID(String projectID){
        return getAllObject()
                .filter(registration -> {
                    try{
                        return registration.getProjectID().equals(projectID);
                    }catch(NullPointerException e){
                        return false;
                    }
                })
                .map(this::toMap)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private boolean registerEligibility(String userID, String projectID) {
        try {
            String userApplicationID = UserLogicActions.getInstance().get(userID).get("ApplicationID");
            ArrayList<HashMap<String,String>> ral = RegistrationLogicActions.getInstance().getByOfficerID(userID);

            if (userApplicationID != null) {//Check if user has an active application to same project
                HashMap<String,String> ahm = ApplicationLogicActions.getInstance().get(userApplicationID);
                boolean isActive = ahm.get("Status").equals("Successful") || ahm.get("Status").equals("Pending") || ahm.get("Status").equals("Booked");
                String applicationProjectID = ProjectLogicActions.getInstance().getProjectByFlatID(ahm.get("FlatID")).get("ID");

                if(isActive && applicationProjectID.equals(projectID)){
                    return false;
                }
            }

            ArrayList<HashMap<String,String>> registrationArrayList = getApprovedOfficers(projectID);
            HashMap<String,String> phm = ProjectLogicActions.getInstance().get(projectID);

            //Check if the registration is full
            int totalValidOfficer = registrationArrayList.size();
            int officerSlots = Integer.parseInt(phm.get("OfficerSlots"));

            if((officerSlots-totalValidOfficer) <= 0){
                return false;
            }

            long openingApply = Long.parseLong(phm.get("OpeningDate"));
            long closingApply = Long.parseLong(phm.get("ClosingDate"));

            for(HashMap<String,String> rhm:ral){
                boolean isActive = rhm.get("Status").equals("Successful") || rhm.get("Status").equals("Pending") || rhm.get("Status").equals("Booked");
               if(isActive) {
                   String registrationProjectID = rhm.get("ProjectID");

                   //Check if user has an active registeration to same project
                   if (registrationProjectID.equals(projectID)) {
                       return false;
                   }

                   HashMap<String, String> newPhm = ProjectLogicActions.getInstance().get(registrationProjectID);

                   long openingNew = Long.parseLong(newPhm.get("OpeningDate"));
                   long closingNew = Long.parseLong(newPhm.get("ClosingDate"));

                   if (!(closingApply < openingNew || openingApply > closingNew)) {//Check if there is any time conflict with a active registration
                        return false;
                   }
               }
            }

            return true;
        }catch (ModelNotFoundException e){
            return false;
        }
    }

    /**
     * Register string.
     *
     * @param hm the hm
     * @return the string
     * @throws UnauthorizedActionException the unauthorized action exception
     * @throws ModelNotFoundException      the model not found exception
     */
    public String register(HashMap<String,String> hm) throws UnauthorizedActionException, ModelNotFoundException {
        String officerID = hm.get("OfficerID");
        String projectID = hm.get("ProjectID");

        if(registerEligibility(officerID,projectID)) {
            return create(hm);
        }else{
            throw new UnauthorizedActionException();
        }
    }

    /**
     * Delete by project id.
     *
     * @param projectID the project id
     * @throws ModelNotFoundException the model not found exception
     */
    public void deleteByProjectID(String projectID) throws ModelNotFoundException{
        List<String> toDeleteIDs = getAllObject()
                .filter(registration -> registration.getProjectID().equals(projectID))
                .map(Model::getID)
                .toList(); // or .collect(Collectors.toList()) for older Java versions

        for (String id : toDeleteIDs) {
            delete(id);
        }
    }

    /**
     * Approve.
     *
     * @param registrationID the registration id
     * @throws ModelNotFoundException      the model not found exception
     * @throws UnauthorizedActionException the unauthorized action exception
     */
    public void approve(String registrationID) throws ModelNotFoundException, UnauthorizedActionException {
        Registration registration = getObject(registrationID);
        String projectID = registration.getProjectID();

        HashMap<String,String> phm = ProjectLogicActions.getInstance().get(projectID);

        int officerSlots = Integer.parseInt(phm.get("OfficerSlots"));
        int totalValidOfficer = getApprovedOfficers(projectID).size();

        if((officerSlots-totalValidOfficer) > 0){
            registration.setStatus("Successful");
            RegistrationRepository.getInstance().update();
        }else{
            throw new UnauthorizedActionException();
        }
    }

    /**
     * Reject.
     *
     * @param registrationID the registration id
     * @throws ModelNotFoundException the model not found exception
     */
    public void reject(String registrationID) throws ModelNotFoundException {
        Registration registration = getObject(registrationID);
        registration.setStatus("Unsuccessful");
        RegistrationRepository.getInstance().update();
    }

    /**
     * Gets approved officers.
     *
     * @param projectID the project id
     * @return the approved officers
     * @throws ModelNotFoundException the model not found exception
     */
    public ArrayList<HashMap<String,String>> getApprovedOfficers(String projectID) throws ModelNotFoundException{
        return getAll().stream()
                .filter(registration->registration.get("ProjectID").equals(projectID))
                .filter(registration->registration.get("Status").equals("Successful"))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static RegistrationLogicActions getInstance() {
        if (instance == null)
            instance = new RegistrationLogicActions(new DefaultGenerateID());
        return instance;
    }
}
