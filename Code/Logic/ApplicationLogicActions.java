package Logic;

import Data.Models.Flat;
import Data.Models.Project;
import Data.Models.Application;

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

        applicationMap.put("ID", application.getApplicationID());
        applicationMap.put("UserID", application.getUserID());
        applicationMap.put("ProjectID", application.getProjectID());
        applicationMap.put("Status", application.getStatus());
        applicationMap.put("OfficerID", application.getOfficerID());
        applicationMap.put("Type", String.valueOf(application.getType()));

        return applicationMap;
    }

    public String create(HashMap<String, String> hm) throws ModelAlreadyExistsException {
        String applicationID = generateID(); // assuming this exists
        String userID = hm.get("UserID");
        String projectID = hm.get("ProjectID");
        int type = Integer.parseInt(hm.get("Type"));

        Application app = new Application(applicationID, userID, projectID, "Pending", null, type);

        ApplicationRepository.getInstance().create(app); // assuming ApplicationRepository is set up

        System.out.print(userID);

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

    public static ApplicationLogicActions getInstance() {
        if (instance == null)
            instance = new ApplicationLogicActions();
        return instance;
    }
}
