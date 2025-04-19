package Logic;

import Data.Models.Flat;
import Data.Models.Project;
import Data.Models.Application;
import Data.Models.User;
import Data.Models.Applicant;

import Data.Repository.ProjectRepository;
import Data.Repository.ApplicationRepository;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;
import Exceptions.RepositoryNotFoundException;
import Exceptions.UnauthorizedActionException;
import Logic.UserLogicActions;
import Util.GenerateID;
import Logic.DataLogicActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApplicationLogicActions extends DataLogicActions<Application>{
    private static ApplicationLogicActions instance;

    @Override
    protected HashMap<String, String> toMap(Application application) {
        HashMap<String, String> applicationMap = new HashMap<>();

        applicationMap.put("ID", application.getID());
        applicationMap.put("UserID", application.getUserID());
        applicationMap.put("ProjectID", application.getProjectID());
        applicationMap.put("Status", application.getStatus());
        applicationMap.put("OfficerID", application.getOfficerID());
        applicationMap.put("Type", String.valueOf(application.getType()));

        return applicationMap;
    }

    public String create(HashMap<String, String> hm) throws ModelAlreadyExistsException {
        String applicationID = GenerateID.generateID(8); // assuming this exists
        String userID = hm.get("UserID");
        String projectID = hm.get("ProjectID");
        int type = Integer.parseInt(hm.get("Type"));

        Application app = new Application(applicationID, userID, projectID, "Pending", null, type);

        ApplicationRepository.getInstance().create(app); // assuming ApplicationRepository is set up

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

    private Boolean checkApplicationValidity(int type, String userID, String projectID) throws ModelNotFoundException{
        HashMap<String,String> uhm = UserLogicActions.getInstance().get(userID);
        String applicationID = uhm.get("ApplicationID");
        int age = Integer.parseInt(uhm.get("Age"));
        char maritalStatus = uhm.get("MaritalStatus").charAt(0);

        //Check if Single Married criteria
        if(type == 0){
            if((maritalStatus == 'M' && age < 21) || (maritalStatus == 'S' && age < 35)){
                return false;
            }
        } else if(type == 1){
            if(maritalStatus == 'S' || (maritalStatus == 'M' && age < 21)){
                return false;
            }
        }

        //Could be NULL! Handle this TODO
        if(applicationID != null) {
            HashMap<String, String> ahm = ApplicationLogicActions.getInstance().get(applicationID);
            String status = ahm.get("Status");

            //Check if the User has already applied
            if (status.equals("Pending") || status.equals("Successful") || status.equals("Booked")) {
                return false;
            }
        }

        String role = uhm.get("Role");
        if(role.equals("Officer")){
            ArrayList<HashMap<String,String>> rArr = RegistrationLogicActions.getInstance().getByOfficerID(userID);
            if(!rArr.isEmpty()) {
                HashMap<String, String> phm = ProjectLogicActions.getInstance().get(projectID);

                long applicationOpening = Long.parseLong(phm.get("OpeningDate"));
                long applicationClosing = Long.parseLong(phm.get("ClosingDate"));

                for (HashMap<String, String> rhm : rArr) {

                    //Check if the officer has another project within application period
                    HashMap<String, String> rphm = ProjectLogicActions.getInstance().get(projectID);
                    long registerOpening = Long.parseLong(rphm.get("OpeningDate"));
                    long registerClosing = Long.parseLong(rphm.get("ClosingDate"));

                    String registerProjectID = rhm.get("ProjectID");
                    if (
                            (rhm.get("Status").equals("Pending") ||
                                    rhm.get("Status").equals("Successful") ||
                                    rhm.get("Status").equals("Booked")) && (
                                    registerProjectID.equals(projectID)//Cannot apply if registered
                                            ||
                                            !(applicationClosing < registerOpening || applicationOpening > registerClosing)//Cannot apply if already have a application date conflicting
                            )) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //Double check this
    public String apply(HashMap<String,String> hm) throws UnauthorizedActionException, ModelAlreadyExistsException, ModelNotFoundException,RepositoryNotFoundException{

        int type = Integer.parseInt(hm.get("Type"));
        String userID = hm.get("UserID");
        String projectID = hm.get("ProjectID");

        if(checkApplicationValidity(type,userID,projectID)) {
            String appID = create(hm);
            UserLogicActions.getInstance().apply(hm.get("UserID"), appID);
            return appID;
        }else{
            throw new UnauthorizedActionException();
        }

    }

    public void book(String ID, String OfficerID) throws ModelNotFoundException{
        Application application = getObject(ID);
        if(application.getStatus().equals("Successful")) {
            application.setStatus("Booked");
            application.setOfficerID(OfficerID);

            //ApplicationRepository.getInstance().update(ID, application);
        }
    }

    public void withdraw(String ID) throws ModelNotFoundException{
        Application application = getObject(ID);
        application.setStatus("Withdrawn");

        //ApplicationRepository.getInstance().update(ID,application);
    }

    public static ApplicationLogicActions getInstance() {
        if (instance == null)
            instance = new ApplicationLogicActions();
        return instance;
    }
}
