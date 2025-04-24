package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.ProjectLogicActions;
import Logic.RegistrationLogicActions;
import Logic.UserLogicActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * The interface Officer formatter.
 */
public interface OfficerFormatter {
    /**
     * Format officers string.
     *
     * @param projectID the project id
     * @return the string
     * @throws ModelNotFoundException the model not found exception
     */
    static String formatOfficers(String projectID) throws ModelNotFoundException {
        String returnStr = "";
        ArrayList<HashMap<String,String>> ral = RegistrationLogicActions.getInstance().getApprovedOfficers(projectID);

        int x = 0;
        for(HashMap<String,String> rhm : ral) {
                HashMap<String,String> uhm = UserLogicActions.getInstance().get(rhm.get("OfficerID"));
                returnStr += uhm.get("Name");

                if (x < ral.size() - 1) {
                    returnStr += ", ";
                }
                x++;
        }
        return returnStr;
    }
}
