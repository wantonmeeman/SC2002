package Data.Repository.Interfaces;

import Data.Models.Model;
import Exceptions.ModelAlreadyExistsException;

/**
 * The interface Repository creatable.
 */
public interface RepositoryCreatable {
    /**
     * Create.
     *
     * @param model the model
     * @throws ModelAlreadyExistsException the model already exists exception
     */
    void create(Model model) throws ModelAlreadyExistsException;
}
