package Data.Repository;

import java.util.ArrayList;
import Data.Models.Model;

import Data.Repository.Interfaces.*;
import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;

abstract public class DataRepository extends Storable implements Fetchable, Writeable, RepositoryGetable, RepositoryCreatable, RepositoryDeletable,RepositoryUpdatable {

    //Mapping Model to String and vice versa
    protected abstract ArrayList<Model> toModelList(ArrayList<ArrayList<String>> csv);
    protected abstract ArrayList<ArrayList<String>> toCSV(ArrayList<Model> alm);

    protected ArrayList<Model> listOfModels = new ArrayList<>();

    protected void fetch(){
        updateAll(toModelList(load()));
    }

    protected void store(){
        save(toCSV(listOfModels));
    }

    public int size(){
        return listOfModels.size();
    }

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
