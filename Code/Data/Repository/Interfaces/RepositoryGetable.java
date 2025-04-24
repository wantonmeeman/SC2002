package Data.Repository.Interfaces;

import Data.Models.Model;
import Exceptions.ModelNotFoundException;

import java.util.ArrayList;

/**
 * The interface Repository getable.
 */
public interface RepositoryGetable {
    /**
     * Get model.
     *
     * @param ID the id
     * @return the model
     * @throws ModelNotFoundException the model not found exception
     */
    Model get(String ID) throws ModelNotFoundException;

    /**
     * Gets all.
     *
     * @return the all
     */
    ArrayList<Model> getAll();
}
