package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.ProjectLogicActions;
import Logic.UserLogicActions;
import Util.Config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static Pages.Components.OfficerFormatter.formatOfficers;

public class ProjectView {
    public static String detailedView(String projectID) throws ModelNotFoundException {
        String returnStr = "";
        SimpleDateFormat formatter = Config.DATE_FORMAT;
        HashMap<String,String> phm = ProjectLogicActions.getInstance().get(projectID);
        HashMap<String,String> mhm = UserLogicActions.getInstance().get(phm.get("ManagerID"));

        String formattedOpening = formatter.format(new Date(Integer.parseInt(phm.get("OpeningDate")) * 1000L));
        String formattedClosing = formatter.format(new Date(Integer.parseInt(phm.get("ClosingDate")) * 1000L));

        returnStr += "Project Name: "+phm.get("Name");
        returnStr += "\nProject Neighbourhood: " +phm.get("Neighbourhood");
        returnStr += "\nProject Officers: "+formatOfficers(phm.get("OfficerIDs"));
        returnStr += "\nProject Manager: "+ mhm.get("Name");
        returnStr += "\nProject Application Opening to Ending: " + formattedOpening + " to " + formattedClosing;

        return returnStr;
    }
}
