package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.ApplicationLogicActions;
import Logic.UserLogicActions;

import java.util.HashMap;

/**
 * The interface User view.
 */
public interface UserView {
    /**
     * Detailed view string.
     *
     * @param ID the id
     * @return the string
     * @throws ModelNotFoundException the model not found exception
     */
    static String detailedView(String ID) throws ModelNotFoundException {
        String returnStr = "";
        HashMap<String,String> uhm = UserLogicActions.getInstance().get(ID);

        returnStr += "User NRIC: " + uhm.get("ID") + "\n";
        returnStr += "User Name: " + uhm.get("Name") + "\n";
        returnStr += "User Marital Status: " + uhm.get("MaritalStatus") + "\n";
        returnStr += "User Age: " + uhm.get("Age");

        return returnStr;
    }

    /**
     * Profile view string.
     *
     * @param ID the id
     * @return the string
     * @throws ModelNotFoundException the model not found exception
     */
    static String profileView(String ID) throws ModelNotFoundException{
        HashMap<String,String> uhm = UserLogicActions.getInstance().get(ID);

        return detailedView(ID) + "\n" +
               "User Role: "+uhm.get("Role");
    }

    /**
     * Simple view string.
     *
     * @param ID the id
     * @return the string
     * @throws ModelNotFoundException the model not found exception
     */
    static String simpleView(String ID) throws ModelNotFoundException{
        HashMap<String,String> uhm = UserLogicActions.getInstance().get(ID);

        return uhm.get("Name");
    }

    /**
     * Applicant view string.
     *
     * @param ID            the id
     * @param applicationID the application id
     * @return the string
     * @throws ModelNotFoundException the model not found exception
     */
    static String applicantView(String ID, String applicationID) throws ModelNotFoundException{

        HashMap<String,String> ahm = ApplicationLogicActions.getInstance().get(applicationID);

        return simpleView(ID) + " - "+ ahm.get("Status");
    }
}
