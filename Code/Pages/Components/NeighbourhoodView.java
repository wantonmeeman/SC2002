package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.NeighbourhoodLogicActions;

import java.util.HashMap;

public class NeighbourhoodView {
    public static String simpleView(String neighbourhoodID){
        try{
            HashMap<String,String> nhm = NeighbourhoodLogicActions.getInstance().get(neighbourhoodID);
            return nhm.get("Name");
        }catch(ModelNotFoundException e){
            return "Neighbourhood not found!";
        }
    }
}
