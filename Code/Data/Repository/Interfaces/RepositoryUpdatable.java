package Data.Repository.Interfaces;

import Data.Models.Model;

import java.util.ArrayList;

/**
 * The interface Repository updatable.
 */
public interface RepositoryUpdatable {
    /**
     * Update.
     */
    void update();

    /**
     * Update all.
     *
     * @param models the models
     */
    void updateAll(ArrayList<Model> models);
}
