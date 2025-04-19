package Logic;

import Data.Models.Applicant;
import Data.Models.Application;
import Data.Models.Registration;
import Data.Models.Officer;
import Data.Repository.ApplicationRepository;
import Data.Repository.RegistrationRepository;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.RepositoryNotFoundException;
import Exceptions.ModelNotFoundException;
import Exceptions.UnauthorizedActionException;
import Logic.UserLogicActions;
import Logic.*;
import Util.GenerateID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class RegistrationLogicActions extends DataLogicActions<Registration>{
    private static RegistrationLogicActions instance;

    @Override
    protected HashMap<String, String> toMap(Registration registration) {
        HashMap<String, String> registrationMap = new HashMap<>();

        registrationMap.put("ID", registration.getID());
        registrationMap.put("OfficerID", registration.getOfficerID());
        registrationMap.put("ProjectID", registration.getProjectID());
        registrationMap.put("Status", registration.getStatus());

        return registrationMap;
    }

    public String create(HashMap<String, String> hm) throws ModelAlreadyExistsException {
        String registrationID = GenerateID.generateID(8); // assuming this exists
        String officerID = hm.get("OfficerID");
        String projectID = hm.get("ProjectID");
        String status = "Pending"; // default status

        Registration reg = new Registration(registrationID, officerID, projectID, status);

        RegistrationRepository.getInstance().create(reg); // assuming RegistrationRepository is set up

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
        //First check for applications to the projectID
        try {
            String userApplicationID = UserLogicActions.getInstance().get(userID).get("ApplicationID");
            HashMap<String,String> projHm = ProjectLogicActions.getInstance().get(projectID);

        if (userApplicationID != null){
            System.out.println("UserApplicationID: "+userApplicationID);
            HashMap<String,String> appHm = ApplicationLogicActions.getInstance().get(userApplicationID);

            if(appHm.get("ProjectID").equals(projectID)){
                if(
                        appHm.get("Status").equals("Successful")
                ||      appHm.get("Status").equals("Pending")
                ||      appHm.get("Status").equals("Booked")
                ){
                    System.out.println("You have already applied for this Project");
                    return false;
                }
            }
        }

        ArrayList<HashMap<String,String>> al = getByOfficerID(userID);
        long openingApply = Long.parseLong(projHm.get("OpeningDate"));
        long closingApply = Long.parseLong(projHm.get("ClosingDate"));

        for(HashMap<String,String> hm:al){

            String ProjectID = hm.get("ProjectID");
            HashMap<String,String> projjhm = ProjectLogicActions.getInstance().get(ProjectID);

            long openingNew = Long.parseLong(projjhm.get("OpeningDate"));
            long closingNew = Long.parseLong(projjhm.get("ClosingDate"));

            if(
                    (hm.get("Status").equals("Pending") ||
                            hm.get("Status").equals("Successful") ||
                            hm.get("Status").equals("Booked")) &&
                    !(closingApply < openingNew || openingApply > closingNew)){
                System.out.println("Date Conflict");
                return false;
            }
        }

            return true;
        }catch(ModelNotFoundException e){
            return false;
        }
}

    public String register(HashMap<String,String> hm) throws ModelAlreadyExistsException, ModelNotFoundException, RepositoryNotFoundException, UnauthorizedActionException {

        if(registerEligibility(hm.get("OfficerID"),hm.get("ProjectID"))) {
            return create(hm);
        }else{
            throw new UnauthorizedActionException();
        }
    }

    public static RegistrationLogicActions getInstance() {
        if (instance == null)
            instance = new RegistrationLogicActions();
        return instance;
    }
}
