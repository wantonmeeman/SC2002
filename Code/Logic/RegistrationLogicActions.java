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


public class RegistrationLogicActions extends DataLogicActions<Registration>{
    private static RegistrationLogicActions instance;

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

            HashMap<String,String> phm = ProjectLogicActions.getInstance().get(projectID);
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

    public String register(HashMap<String,String> hm) throws ModelAlreadyExistsException,UnauthorizedActionException {

        if(registerEligibility(hm.get("OfficerID"),hm.get("ProjectID"))) {
            return create(hm);
        }else{
            throw new UnauthorizedActionException();
        }
    }

    public void deleteByProjectID(String projectID) throws ModelNotFoundException{
        List<String> toDeleteIDs = getAllObject()
                .filter(registration -> registration.getProjectID().equals(projectID))
                .map(Model::getID)
                .toList(); // or .collect(Collectors.toList()) for older Java versions

        for (String id : toDeleteIDs) {
            delete(id);
        }
    }

    public void approve(String registrationID) throws ModelNotFoundException {
        Registration registration = getObject(registrationID);
        registration.setStatus("Successful");
        RegistrationRepository.getInstance().update();
    }

    public void reject(String registrationID) throws ModelNotFoundException {
        Registration registration = getObject(registrationID);
        registration.setStatus("Unsuccessful");
        RegistrationRepository.getInstance().update();
    }

    public static RegistrationLogicActions getInstance() {
        if (instance == null)
            instance = new RegistrationLogicActions(new DefaultGenerateID());
        return instance;
    }
}
