package Data.Repository.Interfaces;

import Data.Models.Model;

import java.util.ArrayList;

public interface RepositoryUpdatable {
    void update();
    void updateAll(ArrayList<Model> models);
}
