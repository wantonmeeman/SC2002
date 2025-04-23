package Data.Repository.Interfaces;

import Exceptions.ModelNotFoundException;

public interface RepositoryDeletable {
    void delete(String ID) throws ModelNotFoundException;
    void deleteAll();
}
