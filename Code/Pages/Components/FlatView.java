package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.FlatLogicActions;

import java.util.HashMap;

public class FlatView {
    public static String detailedView(String flatID) throws ModelNotFoundException {
        String returnStr = "";

        HashMap<String,String> fhm = FlatLogicActions.getInstance().get(flatID);
        String type = fhm.get("Type");
        String modifiedType = type.charAt(0) + " " + type.substring(1);

        returnStr += "Flat Type: " + modifiedType +"\n";
        returnStr += "Flats Available: " + fhm.get("TotalUnits") + "\n";
        returnStr += "Flats Price: $" + fhm.get("Price");

        return returnStr;
    }

    public static String simpleView(String flatID) throws ModelNotFoundException {
        HashMap<String,String> fhm = FlatLogicActions.getInstance().get(flatID);
        String type = fhm.get("Type");
        String modifiedType = type.charAt(0) + " " + type.substring(1);
        return modifiedType+" - " + fhm.get("TotalUnits") + " Units left - $"+fhm.get("Price");
    }

    public static String applicantView(String flatID) throws ModelNotFoundException {
        String returnStr = "";

        HashMap<String,String> fhm = FlatLogicActions.getInstance().get(flatID);
        String type = fhm.get("Type");
        String modifiedType = type.charAt(0) + " " + type.substring(1);

        returnStr += "Flat Type: " + modifiedType +"\n";
        returnStr += "Flats Price: $" + fhm.get("Price");

        return returnStr;
    }
}

