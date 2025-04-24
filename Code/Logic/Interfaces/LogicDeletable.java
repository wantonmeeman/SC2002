package Logic.Interfaces;

import Exceptions.ModelNotFoundException;

/**
 * The interface Logic deletable.
 */
public interface LogicDeletable {
    /**
     * Delete.
     *
     * @param ID the id
     * @throws ModelNotFoundException the model not found exception
     */
    void delete(String ID) throws ModelNotFoundException;
}
