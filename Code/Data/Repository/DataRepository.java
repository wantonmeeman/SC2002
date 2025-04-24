package Data.Repository;

import java.util.ArrayList;
import Data.Models.Model;

import Data.Repository.Interfaces.*;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;
import Util.Storable;

/**
 * The type Data repository.
 */
abstract public class DataRepository extends Storable implements
        RepositoryGetable, RepositoryCreatable, RepositoryDeletable,RepositoryUpdatable {

    /**
     * To model list array list.
     *
     * @param csv the csv
     * @return the array list
     */
//Mapping Model to String and vice versa
    protected abstract ArrayList<Model> toModelList(ArrayList<ArrayList<String>> csv);

    /**
     * To csv array list.
     *
     * @param alm the alm
     * @return the array list
     */
    protected abstract ArrayList<ArrayList<String>> toCSV(ArrayList<Model> alm);

    /**
     * The List of models.
     */
    protected ArrayList<Model> listOfModels = new ArrayList<>();

    /**
     * Fetch.
     */
    protected void fetch(){
        updateAll(toModelList(load()));
    }

    /**
     * Store.
     */
    protected void store(){
        save(toCSV(listOfModels));
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size(){
        return listOfModels.size();
    }

    /**
     * Contains boolean.
     *
     * @param ID the id
     * @return the boolean
     */
    public boolean contains(String ID) {
        try {
            get(ID);
            return true;
        } catch (ModelNotFoundException e) {
            return false;
        }
    }

    public Model get(String ID) throws ModelNotFoundException{
        for (Model model : listOfModels) {
            if (model.getID().equals(ID)) {
                return model;
            }
        }

        throw new ModelNotFoundException();
    }

    public ArrayList<Model> getAll(){
        return listOfModels;
    }; 
    
    public void create(Model model) throws ModelAlreadyExistsException {
        if (contains(model.getID())) {
            throw new ModelAlreadyExistsException();
        } else {
            listOfModels.add(model);
        }

        store();
    }

    public void update(){
        store();
    }

    public void updateAll(ArrayList<Model> models){
        listOfModels = models;

        store();
    };

    public void delete(String ID) throws ModelNotFoundException{
            listOfModels.remove(get(ID));
            store();
    };

    public void deleteAll(){
        listOfModels.clear();

        store();
    };
}
