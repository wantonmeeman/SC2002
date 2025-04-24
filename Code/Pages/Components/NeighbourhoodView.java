package Pages.Components;

import Exceptions.ModelNotFoundException;
import Logic.NeighbourhoodLogicActions;

import java.util.HashMap;

/**
 * The interface Neighbourhood view.
 */
public interface NeighbourhoodView {
    /**
     * Simple view string.
     *
     * @param neighbourhoodID the neighbourhood id
     * @return the string
     */
    static String simpleView(String neighbourhoodID){
        try{
            HashMap<String,String> nhm = NeighbourhoodLogicActions.getInstance().get(neighbourhoodID);
            return nhm.get("Name");
        }catch(ModelNotFoundException e){
            return "Neighbourhood not found!";
        }
    }
}
