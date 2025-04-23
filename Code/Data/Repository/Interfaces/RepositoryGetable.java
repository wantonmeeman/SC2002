package Data.Repository.Interfaces;

import Data.Models.Model;

import java.util.ArrayList;

public interface RepositoryGetable {
    Model get(String ID);
    ArrayList<Model> getAll();
}
