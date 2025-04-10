package Data.Repository;

import java.util.ArrayList;
import Data.Models.Model;

import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;

import javax.xml.crypto.Data;

abstract public class DataRepository extends Storable{

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
            if (model.getID().equalsIgnoreCase(ID)) {
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

    public void update(String oldID,Model model) throws ModelNotFoundException {
        int index = listOfModels.indexOf(get(oldID));

        if(index == -1){
            throw new ModelNotFoundException();
        }else{
            listOfModels.set(index, model);
        }

        store();
    };

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
