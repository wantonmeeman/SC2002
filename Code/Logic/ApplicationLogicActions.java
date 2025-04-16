package Logic;

import Data.Models.Flat;
import Data.Models.Project;
import Data.Models.Application;
import Data.Models.Applicant;

import Data.Repository.ProjectRepository;
import Data.Repository.ApplicationRepository;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;
import Logic.UserLogicActions;
import Util.GenerateID;

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

    public String apply(HashMap<String,String> hm) throws ModelAlreadyExistsException, ModelNotFoundException{
        String appID = create(hm);
        Applicant applicant = (Applicant) UserLogicActions.getInstance().getObject(hm.get("UserID"));
        applicant.setApplicationID(appID);

        return appID;
    }

    public void book(String ID, String OfficerID) throws ModelNotFoundException{
        Application application = getObject(ID);
        application.setStatus("Booked");
        application.setOfficerID(OfficerID);
        ApplicationRepository.getInstance().update(ID,application);
    }

    public void withdraw(String ID) throws ModelNotFoundException{
        Application application = getObject(ID);
        application.setStatus("Withdrawn");
        ApplicationRepository.getInstance().update(ID,application);
    }

    public static ApplicationLogicActions getInstance() {
        if (instance == null)
            instance = new ApplicationLogicActions();
        return instance;
    }
}
