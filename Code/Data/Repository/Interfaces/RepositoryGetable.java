package Data.Repository.Interfaces;

import Data.Models.Model;
import Exceptions.ModelNotFoundException;

import java.util.ArrayList;

public interface RepositoryGetable {
    Model get(String ID) throws ModelNotFoundException;
    ArrayList<Model> getAll();
}
