package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.ProjectLogicActions;
import Logic.UserLogicActions;
import Util.Config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class RegistrationView {
    public static String simpleView(String projectID, String status) throws ModelNotFoundException {
        HashMap<String, String> project = ProjectLogicActions.getInstance().get(projectID);

        return project.get("Name") + " - " + "Registration Status: " + status;
    }
}
