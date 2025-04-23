package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.ApplicationLogicActions;
import Logic.UserLogicActions;

import java.util.HashMap;

public interface UserView {
    static String detailedView(String ID) throws ModelNotFoundException {
        String returnStr = "";
        HashMap<String,String> uhm = UserLogicActions.getInstance().get(ID);

        returnStr += "User NRIC: " + uhm.get("ID") + "\n";
        returnStr += "User Name: " + uhm.get("Name") + "\n";
        returnStr += "User Marital Status: " + uhm.get("MaritalStatus") + "\n";
        returnStr += "User Age: " + uhm.get("Age");

        return returnStr;
    }

    static String profileView(String ID) throws ModelNotFoundException{
        HashMap<String,String> uhm = UserLogicActions.getInstance().get(ID);

        return detailedView(ID) + "\n" +
               "User Role: "+uhm.get("Role");
    }

    static String simpleView(String ID) throws ModelNotFoundException{
        HashMap<String,String> uhm = UserLogicActions.getInstance().get(ID);

        return uhm.get("Name");
    }

    static String applicantView(String ID, String applicationID) throws ModelNotFoundException{

        HashMap<String,String> ahm = ApplicationLogicActions.getInstance().get(applicationID);

        return simpleView(ID) + " - "+ ahm.get("Status");
    }
}
