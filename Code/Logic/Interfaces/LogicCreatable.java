package Logic.Interfaces;

import Exceptions.ModelAlreadyExistsException;

import java.util.HashMap;

/**
 * The interface Logic creatable.
 */
public interface LogicCreatable {
    /**
     * Create string.
     *
     * @param hm the hm
     * @return the string
     * @throws ModelAlreadyExistsException the model already exists exception
     */
    String create(HashMap<String,String> hm) throws ModelAlreadyExistsException;
}
