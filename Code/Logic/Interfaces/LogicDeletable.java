package Logic.Interfaces;

import Exceptions.ModelNotFoundException;

public interface LogicDeletable {
    void delete(String ID) throws ModelNotFoundException;
}
