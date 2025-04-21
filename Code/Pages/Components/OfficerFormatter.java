package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.UserLogicActions;

import java.util.HashMap;
import java.util.Objects;

public class OfficerFormatter {
    public static String formatOfficers(String officerString) throws ModelNotFoundException {
        String[] officerIDs = officerString.split(",");
        String returnStr = "";

        for(int x = 0;x < officerIDs.length;x++) {
            if(!Objects.equals(officerIDs[x], "")) {
                HashMap<String, String> ohm = UserLogicActions.getInstance().get(officerIDs[x]);
                returnStr += ohm.get("Name");

                if (x < officerIDs.length - 1) {
                    returnStr += ", ";
                }
            }
        }
        return returnStr;
    }
}
