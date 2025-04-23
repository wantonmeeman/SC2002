package Pages.Components;

import Data.Models.User;
import Exceptions.ModelNotFoundException;
import Logic.NeighbourhoodLogicActions;
import Logic.SearchSettingLogicActions;
import Logic.UserLogicActions;

import java.util.HashMap;

public interface SearchSettingView {
    static String detailedView(String searchSettingID) throws ModelNotFoundException {
        String returnStr = "";
        HashMap<String,String> sshm = SearchSettingLogicActions.getInstance().get(searchSettingID);


        boolean ascending = Boolean.parseBoolean(sshm.get("ProjectAscending"));
        boolean twoRoomFilter = Boolean.parseBoolean(sshm.get("ProjectTwoRoomFlat"));
        boolean threeRoomFilter = Boolean.parseBoolean(sshm.get("ProjectThreeRoomFlat"));

        returnStr += "Project - Name Filter: " + (sshm.get("ProjectName") == null || sshm.get("ProjectName").isEmpty() ? "None" : sshm.get("ProjectName")) + "\n";
        returnStr += "Project - Ascending: " + (ascending ? "Yes" : "No") + "\n";

        if(sshm.get("ProjectNeighbourhoodID") == null){
            returnStr += "Project - Neighbourhood Filter: None\n";
        }else{
            try {
                HashMap<String, String> nhm = NeighbourhoodLogicActions.getInstance().get(sshm.get("ProjectNeighbourhoodID"));
                returnStr += "Project - Neighbourhood Filter: " + (sshm.get("ProjectNeighbourhoodID") == null ? "None" : nhm.get("Name")) + "\n";
            }catch(ModelNotFoundException e){
                returnStr += "Project - Neighbourhood Filter: Neighbourhood no longer exists\n";
            }
        }

        returnStr += "Project - Get Two Room Flat: " + (twoRoomFilter ? "Yes":"No") + "\n";
        returnStr += "Project - Get Three Room Flat: " + (threeRoomFilter ? "Yes":"No") + "\n";

        if(sshm.get("ProjectManagerID") == null){
            returnStr += "Project - Manager Filter: None";
        }else{
            HashMap<String,String> uhm = UserLogicActions.getInstance().get(sshm.get("ProjectManagerID"));

            returnStr += "Project - Manager Filter: " + uhm.get("Name");
        }

        return returnStr;
    }

}
