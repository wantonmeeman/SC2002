package Pages.Components;

import Data.Models.Model;
import Data.Models.Project;
import Exceptions.ModelNotFoundException;
import Logic.NeighbourhoodLogicActions;
import Logic.ProjectLogicActions;
import Logic.UserLogicActions;
import Util.Config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static Pages.Components.OfficerFormatter.formatOfficers;

/**
 * The interface Project view.
 */
public interface ProjectView {
    /**
     * Detailed view string.
     *
     * @param projectID the project id
     * @return the string
     * @throws ModelNotFoundException the model not found exception
     */
    static String detailedView(String projectID) throws ModelNotFoundException {
        String returnStr = "";
        SimpleDateFormat formatter = Config.DATE_FORMAT;
        HashMap<String,String> phm = ProjectLogicActions.getInstance().get(projectID);
        HashMap<String,String> mhm = UserLogicActions.getInstance().get(phm.get("ManagerID"));

        String formattedOpening = formatter.format(new Date(Integer.parseInt(phm.get("OpeningDate")) * 1000L));
        String formattedClosing = formatter.format(new Date(Integer.parseInt(phm.get("ClosingDate")) * 1000L));

        returnStr += "Project Name: "+phm.get("Name");
        returnStr += "\nProject Neighbourhood: " + NeighbourhoodView.simpleView(phm.get("NeighbourhoodID"));
        returnStr += "\nProject Officers: "+formatOfficers(projectID);
        returnStr += "\nOfficer Slots: "+ phm.get("OfficerSlots");
        returnStr += "\nProject Manager: "+ mhm.get("Name");
        returnStr += "\nProject Application Opening to Ending: " + formattedOpening + " to " + formattedClosing;

        return returnStr;
    }

    /**
     * Simple view string.
     *
     * @param projectID the project id
     * @return the string
     * @throws ModelNotFoundException the model not found exception
     */
    static String simpleView(String projectID) throws ModelNotFoundException{
        HashMap<String,String> phm = ProjectLogicActions.getInstance().get(projectID);
        SimpleDateFormat formatter = Config.DATE_FORMAT;

        String formattedOpening = formatter.format(new Date(Integer.parseInt(phm.get("OpeningDate")) * 1000L));
        String formattedClosing = formatter.format(new Date(Integer.parseInt(phm.get("ClosingDate")) * 1000L));

        return phm.get("Name") +" - " + formattedOpening + " - " + formattedClosing;
    }
}
