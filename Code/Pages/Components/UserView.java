package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.ApplicationLogicActions;
import Logic.UserLogicActions;

import java.util.HashMap;

public class UserView {
    public static String detailedView(String ID) throws ModelNotFoundException {
        String returnStr = "";
        HashMap<String,String> uhm = UserLogicActions.getInstance().get(ID);

        returnStr += "User NRIC: " + uhm.get("ID") + "\n";
        returnStr += "User Name: " + uhm.get("Name") + "\n";
        returnStr += "User Marital Status: " + uhm.get("MaritalStatus") + "\n";
        returnStr += "User Age: " + uhm.get("Age");

        return returnStr;
    }
}
