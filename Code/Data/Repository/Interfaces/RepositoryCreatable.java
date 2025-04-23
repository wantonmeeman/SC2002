package Data.Repository.Interfaces;

import Data.Models.Model;
import Exceptions.ModelAlreadyExistsException;

public interface RepositoryCreatable {
    void create(Model model) throws ModelAlreadyExistsException;
}
