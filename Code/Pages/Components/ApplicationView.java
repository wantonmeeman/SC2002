package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.ApplicationLogicActions;
import Logic.UserLogicActions;

import java.util.HashMap;

public class ApplicationView {
    public static String detailedView(String ID) throws ModelNotFoundException {
        String returnStr = "";
        HashMap<String,String> ahm = ApplicationLogicActions.getInstance().get(ID);

        returnStr += "Application Status: " + ahm.get("Status") + "\n";
        returnStr += "Application Flat Type: " + (Integer.parseInt(ahm.get("Type"))+2)+"-Room Flat";

        return returnStr;
    }
}
