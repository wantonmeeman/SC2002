package Logic.Interfaces;

import Exceptions.ModelNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The interface Logic getable.
 */
public interface LogicGetable {
    /**
     * Get hash map.
     *
     * @param ID the id
     * @return the hash map
     * @throws ModelNotFoundException the model not found exception
     */
    HashMap<String,String> get(String ID) throws ModelNotFoundException;

    /**
     * Gets all.
     *
     * @return the all
     */
    ArrayList<HashMap<String,String>> getAll();
}
