package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.ProjectLogicActions;
import Logic.RegistrationLogicActions;
import Logic.UserLogicActions;
import Util.Config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * The interface Registration view.
 */
public interface RegistrationView {
    /**
     * Simple view string.
     *
     * @param projectID the project id
     * @param status    the status
     * @return the string
     * @throws ModelNotFoundException the model not found exception
     */
    static String simpleView(String projectID, String status) throws ModelNotFoundException {
        HashMap<String, String> project = ProjectLogicActions.getInstance().get(projectID);

        return project.get("Name") + " - " + "Registration Status: " + status;
    }

    /**
     * Detailed view string.
     *
     * @param registrationID the registration id
     * @return the string
     * @throws ModelNotFoundException the model not found exception
     */
    static String detailedView(String registrationID) throws ModelNotFoundException {
        HashMap<String, String> rhm = RegistrationLogicActions.getInstance().get(registrationID);

        return "Registration Status: " + rhm.get("Status");
    }
}
