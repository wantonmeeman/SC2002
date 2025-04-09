package Repository;

import java.util.ArrayList;

import Exceptions.ModelAlreadyExistsException;
import Exceptions.ModelNotFoundException;

import Util.CSVUtils;

abstract public class DataRepository<Model> extends CSVUtils{
    private String filepath;
    protected ArrayList<Model> listOfModels = new ArrayList<>();

    public abstract void save();

    public abstract void load();

    public abstract ArrayList<Model> toModelList(ArrayList<ArrayList<String>> csv);

    public abstract ArrayList<ArrayList<String>> toCSV(ArrayList<Model> alm);

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public boolean contains(String ID) {
        try {
            get(ID);
            return true;
        } catch (ModelNotFoundException e) {
            return false;
        }
    }

    public int size(){
        return listOfModels.size();
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

            save();
        }
    }

    public void update(Model model){
        Model oldModel = get(model.getID());
        listOfModels.set(listOfModels.indexOf(get(oldModel.getID())), model);

        save();
    };

    public void updateAll(ArrayList<Model> models){
        this.listOfModels = models;

        save();
    };

    public void delete(String ID){

        try {
            listOfModels.remove(get(ID));

            save();
        } catch (ModelNotFoundException e) {
            //
        }
    };

    public void deleteAll(){
        listOfModels.clear();

        save();
    };
}
