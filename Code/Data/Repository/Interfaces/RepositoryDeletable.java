package Data.Repository.Interfaces;

import Exceptions.ModelNotFoundException;

/**
 * The interface Repository deletable.
 */
public interface RepositoryDeletable {
    /**
     * Delete.
     *
     * @param ID the id
     * @throws ModelNotFoundException the model not found exception
     */
    void delete(String ID) throws ModelNotFoundException;

    /**
     * Delete all.
     */
    void deleteAll();
}
