package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.SearchSettingLogicActions;

import java.util.HashMap;

public interface ApplicantSearchView {
    static String detailedView(HashMap<String,String> ashm) throws ModelNotFoundException {
        String returnStr = "";

        String flatType = ashm.get("FlatType");

        returnStr += "Applicant - Marital Status: " + (ashm.get("MaritalStatus") == null ? "Not applied" : ashm.get("MaritalStatus")) + "\n";
        returnStr += "Applicant - Flat Type: "+ (flatType == null ? "Not applied" : flatType.charAt(0) + " " + flatType.substring(1)) + "\n";

        return returnStr;
    }
}
